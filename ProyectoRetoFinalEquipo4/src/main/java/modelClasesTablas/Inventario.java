package modelClasesTablas;

/**
 * Entidad secundaria para gestionar los recuentos globales de existencias.
 *
 * @author sergio camacho
 */
public class Inventario {

    private int id_inventario;
    private int cantidad;

    /**
     * Constructor completo de la entidad inventario.
     *
     * @param id_inventario el identificador numérico de la fila.
     * @param cantidad el número de unidades en stock.
     */
    public Inventario(int id_inventario, int cantidad) {
        this.id_inventario = id_inventario;
        this.cantidad = cantidad;
    }

    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Inventario{" + "id_inventario=" + id_inventario + ", cantidad=" + cantidad + '}';
    }
}
