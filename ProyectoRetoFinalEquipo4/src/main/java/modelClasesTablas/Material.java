package modelClasesTablas;

import java.time.LocalDateTime;

/**
 * Representa un elemento del inventario del taller de informática. Refleja
 * fielmente la estructura relacional de la tabla 'materiales' en la base de
 * datos.
 *
 * @author sergio camacho
 */
public class Material {

    private int id_material;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private int id_estado;
    private LocalDateTime fecha_Alta;
    private int id_categoria;
    private int id_ubicacion;

    /**
     * Constructor completo utilizado para volcar las consultas de materiales
     * leídas desde la base de datos.
     *
     * @param id_material id identificador numérico de la herramienta.
     * @param nombre nombre representativo del material.
     * @param descripcion detalles o características del material.
     * @param cantidad número de unidades disponibles en stock.
     * @param id_estado clave foránea asociada a la tabla de estados.
     * @param fecha_Alta fecha de registro capturada en el sistema.
     * @param id_categoria clave foránea asociada a la tabla de categorías.
     * @param id_ubicacion clave foránea asociada a la tabla de ubicaciones.
     */
    public Material(int id_material, String nombre, String descripcion, int cantidad, int id_estado, LocalDateTime fecha_Alta, int id_categoria, int id_ubicacion) {
        this.id_material = id_material;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.id_estado = id_estado;
        this.fecha_Alta = fecha_Alta;
        this.id_categoria = id_categoria;
        this.id_ubicacion = id_ubicacion;
    }

    /**
     * Constructor parcial optimizado para la creación de nuevos materiales que
     * aún no están en la BD. No requiere ID ni fecha al autogenerarse de forma
     * nativa en MySQL.
     *
     * @param nombre nombre asignado al nuevo material.
     * @param descripcion detalles informativos sobre la herramienta.
     * @param cantidad stock inicial de entrada.
     * @param id_estado clave foránea del estado inicial asignado.
     * @param id_categoria clave foránea del tipo de categoría.
     * @param id_ubicacion clave foránea del armario de destino.
     */
    public Material(String nombre, String descripcion, int cantidad, int id_estado, int id_categoria, int id_ubicacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.id_estado = id_estado;
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

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
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

    /**
     * Devuelve una cadena formateada con el ID, nombre y stock útil para
     * depuraciones e interfaces.
     *
     * @return texto representativo de la entidad material.
     */
    @Override
    public String toString() {
        return "[" + id_material + "] " + nombre + " (Stock: " + cantidad + ")";
    }
}
