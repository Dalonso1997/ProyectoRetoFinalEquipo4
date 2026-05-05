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
public class Registro {
    private int id_registro;
    private LocalDateTime fecha_entrada;
    private LocalDateTime fecha_salida;
    private int cantidad;
    private boolean estado;

    public Registro(int id_registro, LocalDateTime fecha_entrada, LocalDateTime fecha_salida, int cantidad, boolean estado) {
        this.id_registro = id_registro;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public int getId_registro() {
        return id_registro;
    }

    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    public LocalDateTime getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(LocalDateTime fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public LocalDateTime getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(LocalDateTime fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Registro{" + "id_registro=" + id_registro + ", fecha_entrada=" + fecha_entrada + ", fecha_salida=" + fecha_salida + ", cantidad=" + cantidad + ", estado=" + estado + '}';
    }
    
    
    
    
    
}
