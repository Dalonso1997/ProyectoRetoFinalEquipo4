/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.menuPrincipal;

import daoClasesSQL.MaterialDAO;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Panel gráfico para consultar el listado completo de los materiales del
 * taller. Dibuja una tabla automática que muestra las cantidades, categorías,
 * estados y ubicaciones en tiempo real.
 *
 * * @author sergio camacho
 */
public class PanelConsultaMateriales extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    /**
     * Constructor que crea el panel de consulta y activa la carga inicial de
     * los datos.
     */
    public PanelConsultaMateriales() {

        setLayout(new BorderLayout());

        inicializarTabla();

        cargarMateriales();

    }

    /**
     * Configura la estructura visual de la tabla, define los nombres de las
     * columnas y bloquea la edición directa de celdas.
     */
    private void inicializarTabla() {

        String[] columnas = {
            "ID",
            "Nombre",
            "Descripción",
            "Categoría",
            "Estado",
            "Ubicación",
            "Cantidad"
        };

        modelo = new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modelo);

        tabla.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        //Creo un evento para escuchar dobles clicks en la tabla
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                //Cuando se hace doble click sobre un material
                if (evt.getClickCount() == 2) {

                    //Uso el metodo para recoger el ID en el que se ha pulsado dos veces
                    int idMaterial = getIdMaterialSeleccionado();

                    if (idMaterial != -1) {

                        System.out.println("Doble click detectado id: " + idMaterial);
                        MaterialDAO dao = new MaterialDAO();
                        byte[] imagen = dao.obtenerImagen(idMaterial);

                        if (imagen != null && imagen.length > 0) {

                            javax.swing.ImageIcon icono = new javax.swing.ImageIcon(imagen);

                            JOptionPane.showMessageDialog(
                                    PanelConsultaMateriales.this,
                                    "",
                                    "Vista previa del material",
                                    JOptionPane.PLAIN_MESSAGE,
                                    icono
                            );

                        } else {

                            JOptionPane.showMessageDialog(
                                    PanelConsultaMateriales.this,
                                    "Este material todavía no tiene una imagen asignada en la base de datos.",
                                    "Sin imagen",
                                    JOptionPane.INFORMATION_MESSAGE
                            );

                        }

                    }
                }
                // Comprobamos si el usuario ha hecho exactamente 2 clics
                
            }
        });

    }

    /**
     * Llama al buscador de vuestro MaterialDAO para recuperar todos los
     * registros de AWS y rellenar la tabla.
     */
    private void cargarMateriales() {

        MaterialDAO dao = new MaterialDAO();

        List<Object[]> materiales = dao.buscar("", "", "", "");

        System.out.println(materiales.size());

        modelo.setRowCount(0);

        if (materiales.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No hay materiales registrados.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        for (Object[] fila : materiales) {
            modelo.addRow(fila);
        }
    }

    /**
     * Recupera el identificador numérico único del material que el usuario
     * tiene marcado en la tabla.
     *
     * * @return el id numérico del material seleccionado, o -1 si no ha
     * pinchado en ninguna fila.
     * @author adrian gonzalez gil
     */
    public int getIdMaterialSeleccionado() {
        //si es -1 significa que no has seleccionado ninguna
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            return -1;
        } else {
            //devolvemos el id del seleccionado, que esta en la columna 0
            return (int) modelo.getValueAt(filaSeleccionada, 0);
        }
    }
    
    /**
     * Recupera el nombre del material seleccionado por el usuario.
     * @return el nombre del material o " " si no ha pinchado en nada.
     * @author sergio camacho
     */
    public String getNombreMaterialSeleccionado() {
        //si es -1 significa que no has seleccionado ninguna
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            return " ";
        } else {
            //devolvemos el id del seleccionado, que esta en la columna 0
            return modelo.getValueAt(filaSeleccionada, 1).toString();
        }
    }
    /**
     * Recupera la descripción del material seleccionado por el usuario.
     * @return la descripción del material o " " si no ha pinchado en nada.
     * @author sergio camacho
     */
    public String getDescripcionMaterialSeleccionado() {
        //si es -1 significa que no has seleccionado ninguna
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            return " ";
        } else {
            //devolvemos el id del seleccionado, que esta en la columna 0
            return modelo.getValueAt(filaSeleccionada, 2).toString();
        }
    }

    /**
     * Recupera el texto completo de la columna de ubicaciones de la fila que
     * esté marcada.
     *
     * * @return cadena de caracteres con la mesa o el armario y cajón, o un
     * texto vacío si no hay selección.
     * @author adrian gonzalez gil
     */
    public String getIdUbicacionSeleccionada() {
        //si es -1 significa que no has seleccionado ninguna
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            return "";
        } else {
            //devolvemos el id del seleccionado, que esta en la columna 0
            return modelo.getValueAt(filaSeleccionada, 5).toString();
        }
    }

    /**
     * Método público para forzar la recarga visual de las filas llamando al
     * cargador privado de materiales.
     *
     * * @author adrian gonzalez gil
     */
    public void refrescarListado() {
        cargarMateriales();
    }

    public JTable getTabla() {
        return this.tabla;
    }
}
