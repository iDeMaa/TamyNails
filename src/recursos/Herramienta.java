package recursos;

/**
 *
 * @author demaa
 */
public class Herramienta extends Producto{

    public Herramienta(int id, String tipo, double precio) {
        super(id, tipo, precio);
    }
    
    public Herramienta(int id, String tipo, double precio, int cantidad) {
        super(id, tipo, precio, cantidad);
    }
    
}
