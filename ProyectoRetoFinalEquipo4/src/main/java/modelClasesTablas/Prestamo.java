/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelClasesTablas;

import java.time.LocalDateTime;

/**
 *
 * @author DAM126
 */
public class Prestamo {
    private int id_prestamo;
    private String nombre;
    private String descripcion;
    private Estado estado;
    private int cantidad;
    private LocalDateTime fecha;
    private String observaciones;

    public Prestamo(int id_prestamo, String nombre, String descripcion, Estado estado, int cantidad, LocalDateTime fecha, String observaciones) {
        this.id_prestamo = id_prestamo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Registro{" + "id_prestamo=" + id_prestamo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado + ", cantidad=" + cantidad + ", fecha=" + fecha + ", observaciones=" + observaciones + '}';
    }
    
}
