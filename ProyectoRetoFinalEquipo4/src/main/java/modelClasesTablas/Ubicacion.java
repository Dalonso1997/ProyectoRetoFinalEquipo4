/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelClasesTablas;

/**
 *
 * @author DAM126
 */
public class Ubicacion {

    private int id_ubicacion;
    private String ubicacion;
    private String cajon;
    private String descripcion;

    public Ubicacion(int id_ubicacion, String ubicacion, String cajon, String descripcion) {
        this.id_ubicacion = id_ubicacion;
        this.ubicacion = ubicacion;
        this.cajon = cajon;
        this.descripcion = descripcion;
    }

    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCajon() {
        return cajon;
    }

    public void setCajon(String cajon) {
        this.cajon = cajon;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        String texto = "";
        if (cajon != null) {
            texto = "Armario: " + ubicacion+ " Cajon: " + cajon;
        } else if (cajon == null) {
            texto = "Armario: " + ubicacion;
        }

        return texto;

    }

}
