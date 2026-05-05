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
    private String armario;
    private String balda;
    private String cajon;
    private String descripcion;

    public Ubicacion(int id_ubicacion, String armario, String balda, String cajon, String descripcion) {
        this.id_ubicacion = id_ubicacion;
        this.armario = armario;
        this.balda = balda;
        this.cajon = cajon;
        this.descripcion = descripcion;
    }

    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public String getArmario() {
        return armario;
    }

    public void setArmario(String armario) {
        this.armario = armario;
    }

    public String getBalda() {
        return balda;
    }

    public void setBalda(String balda) {
        this.balda = balda;
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
        return "Ubicacion{" + "id_ubicacion=" + id_ubicacion + ", armario=" + armario + ", balda=" + balda + ", cajon=" + cajon + ", descripcion=" + descripcion + '}';
    }
    
    
    
}
