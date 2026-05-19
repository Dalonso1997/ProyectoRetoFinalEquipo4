package modelClasesTablas;

public class Ubicacion {

    private int id_ubicacion;
    private String tipo;      // 'mesa' o 'armario'
    private String ubicacion; // Lo que en SQL se llama 'nombre'
    private Integer cajon;    // Integer para permitir NULL en las mesas
    private String descripcion;

    // Constructor con los 5 campos del nuevo script
    public Ubicacion(int id_ubicacion, String tipo, String ubicacion, Integer cajon, String descripcion) {
        this.id_ubicacion = id_ubicacion;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.cajon = cajon;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    } // Mantenemos el nombre que usas

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCajon() {
        return cajon;
    }

    public void setCajon(Integer cajon) {
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
        if ("armario".equalsIgnoreCase(tipo) && cajon != null) {
            return ubicacion + " - Cajón " + cajon;
        } else {
            return ubicacion;
        }
    }
}
