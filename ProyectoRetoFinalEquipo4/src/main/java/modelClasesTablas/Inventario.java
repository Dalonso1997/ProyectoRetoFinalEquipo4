/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelClasesTablas;

/**
 *
 * @author DAM126
 */
public class Inventario {
    private int id_inventario;
    private int cantidad;

    public Inventario(int id_inventario, int cantidad) {
        this.id_inventario = id_inventario;
        this.cantidad = cantidad;
    }

    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Inventario{" + "id_inventario=" + id_inventario + ", cantidad=" + cantidad + '}';
    }
    
    
}
