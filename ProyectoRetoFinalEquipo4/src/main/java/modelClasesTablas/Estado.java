package modelClasesTablas;

/**
 * Entidad que representa los diferentes estados posibles de las herramientas.
 * Evita el uso de enums rígidos mapeando directamente la tabla 'estado'.
 *
 * @author sergio camacho
 */
public class Estado {

    private int id_estado;
    private String nombre;

    /**
     * Constructor completo para instanciar un estado de material.
     *
     * @param id_estado el identificador numérico del estado.
     * @param nombre la cadena de texto con el nombre del estado (disponible,
     * prestado, etc).
     */
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

    /**
     * Devuelve el nombre del estado, crucial para su renderizado en los
     * JComboBox.
     *
     * @return el texto descriptivo del estado.
     */
    @Override
    public String toString() {
        return this.nombre;
    }
}
