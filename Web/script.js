        const dbTaller = {
            "A1": {
                "1": ["Tornillos de caja", "Tornillos M.2"],
                "2": ["Bridas de nylon", "Pasta térmica"],
                "9": ["Placas Base Gigabyte B550", "Placas Base ASUS Prime"],
                "10": ["Procesadores Intel i5-12400", "AMD Ryzen 5 5600"],
                "Lateral Izq": ["Cables de alimentación IEC", "Regletas de 6 tomas"],
                "Lateral Der": ["Bobina UTP Cat6", "Herramientas de red (Crimpadora)"]
            },
            "A2": {
                "1": ["Módulos RAM DDR4 8GB", "RAM DDR4 16GB"],
                "5 (Cajón grande)": ["Discos HDD 1TB", "Discos SSD 500GB Kingston"]
            },
            "A3": {
                "1": ["Multímetro Digital", "Osciloscopio"],
                "4": ["Pulseras antiestáticas", "Limpiador de contactos"]
            }
        };

        const tituloBalda = document.getElementById('id-balda-titulo');
        const panelObjetos = document.getElementById('panel-objetos');
        const resumenTotal = document.getElementById('resumen-total');
        const todasLasCeldas = document.querySelectorAll('.tabla-forma td');

        todasLasCeldas.forEach(celda => {
            celda.addEventListener('click', function(e) {
                e.stopPropagation();
                
                todasLasCeldas.forEach(c => c.classList.remove('balda-activa'));
                this.classList.add('balda-activa');

                const idArmario = this.closest('.armario').getAttribute('data-id');
                const idBalda = this.innerText.trim();

                tituloBalda.innerText = `Armario ${idArmario} - Espacio [${idBalda}]`;
                
                const objetos = dbTaller[idArmario] ? dbTaller[idArmario][idBalda] : null;

                if (objetos && objetos.length > 0) {
                    let html = '<ul class="lista-objetos">';
                    objetos.forEach(obj => html += `<li>${obj}</li>`);
                    html += '</ul>';
                    panelObjetos.innerHTML = html;
                    resumenTotal.innerHTML = `Total tipos de componentes: ${objetos.length}`;
                    resumenTotal.style.display = "flex";
                } else {
                    panelObjetos.innerHTML = '<div class="panel-vacio">Esta balda está vacía actualmente.</div>';
                    resumenTotal.style.display = "none";
                }
            });
        });