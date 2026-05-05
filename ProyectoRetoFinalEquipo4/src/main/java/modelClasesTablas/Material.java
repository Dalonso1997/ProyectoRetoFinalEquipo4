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
public class Material {
    private int id_material;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private LocalDateTime fecha_Alta;

    public Material(int id_material, String nombre, String descripcion, int cantidad, LocalDateTime fechaAlta) {
        this.id_material = id_material;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fecha_Alta = fechaAlta;
    }

    public int getId_material() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material = id_material;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaAlta() {
        return fecha_Alta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fecha_Alta = fechaAlta;
    }

    @Override
    public String toString() {
        return "Material{" + "id_material=" + id_material + ", nombre=" + nombre + ", descripcion=" + descripcion + ", cantidad=" + cantidad + ", fechaAlta=" + fecha_Alta + '}';
    }
    
    
    
    
}
