/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelClasesTablas;

import java.time.LocalDateTime;

public class Prestamo {
    private int id_prestamo;
    private int id_material; // NUEVO: Crucial para saber qué material se resta
    private String nombre;
    private String descripcion;
    private Estado estado;
    private int cantidad;
    private LocalDateTime fecha;
    private String observaciones;

    // Dentro de la clase Prestamo.java
    public Prestamo() {
        // Constructor vacío para que Java no proteste
    }
    // Constructor COMPLETO (para cuando lees de la Base de Datos)
    public Prestamo(int id_prestamo, int id_material, String nombre, String descripcion, Estado estado, int cantidad, LocalDateTime fecha, String observaciones) {
        this.id_prestamo = id_prestamo;
        this.id_material = id_material;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    // Constructor SIN ID_PRESTAMO (para cuando creas uno nuevo desde la pantalla)
    public Prestamo(int id_material, String nombre, String descripcion, Estado estado, int cantidad, String observaciones) {
        this.id_material = id_material;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now(); // La fecha se pone sola al momento de crearlo
        this.observaciones = observaciones;
    }

    // --- GETTERS Y SETTERS ---
    public int getId_prestamo() { return id_prestamo; }
    public void setId_prestamo(int id_prestamo) { this.id_prestamo = id_prestamo; }

    public int getId_material() { return id_material; } // NUEVO
    public void setId_material(int id_material) { this.id_material = id_material; } // NUEVO

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    @Override
    public String toString() {
        return "Registro{" + "id_prestamo=" + id_prestamo + ", id_material=" + id_material + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado + ", cantidad=" + cantidad + ", fecha=" + fecha + ", observaciones=" + observaciones + '}';
    }
}
