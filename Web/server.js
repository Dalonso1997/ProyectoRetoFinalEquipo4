const express = require('express');
const mysql = require('mysql2');
const cors = require('cors');

const app = express();
app.use(cors()); // Permite que el HTML hable con este servidor sin bloqueos

// 1. Configuramos la llave a la cocina (AWS)
const db = mysql.createConnection({
    host: '172.31.45.180',
    port: 3306,
    user: 'admin',
    password: 'Grupo4',
    database: 'inventario_taller',
    ssl: { rejectUnauthorized: false } // Necesario para AWS
});

// 2. Le decimos al camarero qué hacer cuando alguien pida '/prueba'
app.get('/prueba', (req, res) => {
    // Hacemos una consulta tonta para ver si responde
    db.query('SELECT 1 + 1 AS resultado', (err, results) => {
        if (err) return res.send("Error en la base de datos: " + err.message);
        res.json({ mensaje: "¡Conexión exitosa desde la base de datos!" });
    });
});

// 3. Encendemos al camarero
app.listen(3000, '0.0.0.0', () => {
    console.log("Camarero escuchando en toda la red local en el puerto 3000");
}); 


app.get('/inventario', (req, res) => {
    // Consulta con JOIN para traer datos de ambas tablas
    // Unimos donde el id_material del inventario coincida con el id de materiales
    const sql = `
        SELECT 
            i.id_inventario, 
            i.cantidad, 
            i.fecha_registro, 
            m.nombre, 
            m.descripcion 
        FROM inventario i
        INNER JOIN materiales m ON i.id_material = m.id_material
    `; 

    db.query(sql, (err, results) => {
        if (err) {
            console.error("Error en JOIN:", err.message); 
            return res.status(500).json({ error: err.message });
        }
        res.json(results); 
    });
});