/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package modelClasesTablas;

public class Estado {
    private int id_estado;
    private String nombre;

    public Estado(int id_estado, String nombre) {
        this.id_estado = id_estado;
        this.nombre = nombre;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // ESTO ES CLAVE PARA EL JCOMBOBOX
    @Override
    public String toString() {
        return this.nombre;
    }
}