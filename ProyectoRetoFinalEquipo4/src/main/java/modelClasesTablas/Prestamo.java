package modelClasesTablas;

import java.time.LocalDateTime;

/**
 * Entidad que representa la solicitud o control de préstamo de una herramienta en el taller.
 * @author sergio camacho
 */
public class Prestamo {
    private int id_prestamo;
    private int id_material; 
    private String nombre;
    private String descripcion;
    private Estado estado;
    private int cantidad;
    private LocalDateTime fecha;
    private String observaciones;

    /**
     * Constructor predeterminado vacío para permitir inicializaciones parciales o mapeos genéricos.
     */
    public Prestamo() {
    }

    /**
     * Constructor completo utilizado para leer y cargar los historiales desde las tablas.
     * @param id_prestamo identificador del préstamo.
     * @param id_material identificador del material afectado.
     * @param nombre nombre del préstamo o persona asociada.
     * @param descripcion detalles complementarios.
     * @param estado objeto de tipo Estado con el flujo activo.
     * @param cantidad número de unidades prestadas.
     * @param fecha fecha y hora exacta del movimiento.
     * @param observaciones anotaciones realizadas por el profesor.
     */
    public Prestamo(int id_prestamo, int id_material, String nombre, String descripcion, Estado estado, int cantidad, LocalDateTime fecha, String observaciones) {
        this.id_prestamo = id_prestamo;
        this.id_material = id_material;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    /**
     * Constructor parcial parametrizado para generar nuevos vales de préstamo desde el formulario de la interfaz.
     * Captura automáticamente el momento de creación mediante LocalDateTime.now().
     * @param id_material identificador del componente físico prestado.
     * @param nombre nombre asignado al vale.
     * @param descripcion detalles del movimiento.
     * @param estado objeto de estado relacional.
     * @param cantidad unidades retiradas del taller.
     * @param observaciones incidencias anotadas.
     */
    public Prestamo(int id_material, String nombre, String descripcion, Estado estado, int cantidad, String observaciones) {
        this.id_material = id_material;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now(); 
        this.observaciones = observaciones;
    }

    public int getId_prestamo() { return id_prestamo; }
    public void setId_prestamo(int id_prestamo) { this.id_prestamo = id_prestamo; }

    public int getId_material() { return id_material; } 
    public void setId_material(int id_material) { this.id_material = id_material; } 

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    @Override
    public String toString() {
        return "Registro{" + "id_prestamo=" + id_prestamo + ", id_material=" + id_material + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado + ", cantidad=" + cantidad + ", fecha=" + fecha + ", observaciones=" + observaciones + '}';
    }
}