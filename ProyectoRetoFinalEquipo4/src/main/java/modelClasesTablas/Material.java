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
    private String estado;
    private LocalDateTime fecha_Alta;
    private int id_categoria;
    private int id_ubicacion;

    public Material(int id_material, String nombre, String descripcion, int cantidad, String estado, LocalDateTime fecha_Alta, int id_categoria, int id_ubicacion) {
        this.id_material = id_material;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.estado = estado;
        this.fecha_Alta = fecha_Alta;
        this.id_categoria = id_categoria;
        this.id_ubicacion = id_ubicacion;
    }

    public Material(String nombre, String descripcion, int cantidad, String estado, int id_categoria, int id_ubicacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.estado = estado;
        this.id_categoria = id_categoria;
        this.id_ubicacion = id_ubicacion;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha_Alta() {
        return fecha_Alta;
    }

    public void setFecha_Alta(LocalDateTime fecha_Alta) {
        this.fecha_Alta = fecha_Alta;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    @Override
    public String toString() {
        return "Material{" + "id_material=" + id_material + ", nombre=" + nombre + ", descripcion=" + descripcion + ", cantidad=" + cantidad + ", estado=" + estado + ", fecha_Alta=" + fecha_Alta + ", id_categoria=" + id_categoria + ", id_ubicacion=" + id_ubicacion + '}';
    }

   
    
    
    
    
}
