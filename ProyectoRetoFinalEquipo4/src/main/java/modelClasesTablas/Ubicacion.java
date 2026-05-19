package modelClasesTablas;

/**
 * Entidad que representa la localización física de almacenamiento en el taller
 * de informática. Mapea de forma directa la tabla 'ubicacion' de la base de
 * datos de MySQL.
 *
 * @author sergio camacho
 */
public class Ubicacion {

    private int id_ubicacion;
    private String tipo;
    private String ubicacion;
    private Integer cajon;
    private String descripcion;

    /**
     * Constructor completo alineado al diseño físico de almacenamiento de la
     * base de datos.
     *
     * @param id_ubicacion clave primaria numérico identificadora del sitio.
     * @param tipo clasificador físico ('mesa' o 'armario').
     * @param ubicacion nombre real descriptivo del mueble (en la base de datos
     * mapea a la columna 'nombre').
     * @param cajon número entero identificador de cajón (admite NULL en caso de
     * configuraciones externas).
     * @param descripcion notas informativas sobre la localización.
     */
    public Ubicacion(int id_ubicacion, String tipo, String ubicacion, Integer cajon, String descripcion) {
        this.id_ubicacion = id_ubicacion;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

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

    /**
     * Formatea el texto de salida. Como se han quitado las mesas, concatena
     * directamente el nombre del armario y su cajón.
     *
     * @return la cadena formateada de localización legible para el JComboBox.
     */
    @Override
    public String toString() {
        if ("armario".equalsIgnoreCase(tipo) && cajon != null) {
            return ubicacion + " - Cajón " + cajon;
        } else {
            return ubicacion;
        }
    }
}
