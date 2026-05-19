package modelClasesTablas;

import java.time.LocalDateTime;

/**
 * Representa un elemento del inventario del taller. Refleja la estructura de la
 * tabla 'materiales' de la base de datos.
 *
 * * @author DAM126
 */
public class Material {

    private int id_material;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private int id_estado; // CAMBIADO: Antes era String, ahora es int (FK)
    private LocalDateTime fecha_Alta;
    private int id_categoria;
    private int id_ubicacion;

    /**
     * Constructor completo para cargar materiales desde la base de datos.
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
     * Constructor para la creación de nuevos materiales (sin ID ni fecha).
     */
    public Material(String nombre, String descripcion, int cantidad, int id_estado, int id_categoria, int id_ubicacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.id_estado = id_estado;
        this.id_categoria = id_categoria;
        this.id_ubicacion = id_ubicacion;
    }

    // --- Getters y Setters ---
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

    /**
     * @return El ID del estado asociado (FK a la tabla estado).
     */
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

    @Override
    public String toString() {
        return "[" + id_material + "] " + nombre + " (Stock: " + cantidad + ")";
    }
}
