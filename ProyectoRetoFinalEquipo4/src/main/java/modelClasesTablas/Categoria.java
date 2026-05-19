package modelClasesTablas;

/**
 * Entidad que representa una categoría de material en el taller de informática.
 * Corresponde con la tabla 'categorias' de la base de datos.
 *
 * @author sergio camacho
 */
public class Categoria {

    private int id_categoria;
    private String nombre;
    private String descripcion;

    /**
     * Constructor completo para instanciar una categoría con todos sus datos.
     *
     * @param id_categoria el identificador único numérico de la categoría.
     * @param nombre el nombre representativo del tipo de material.
     * @param descripcion detalles adicionales sobre los materiales incluidos.
     */
    public Categoria(int id_categoria, String nombre, String descripcion) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
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

    /**
     * Devuelve el nombre de la categoría para pintarlo directamente en los
     * desplegables.
     *
     * @return el nombre de la categoría.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
