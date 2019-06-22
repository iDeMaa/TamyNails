package recursos;

/**
 *
 * @author demaa
 */
public class Removedor extends Producto{
    public Removedor(int id, String tipo, double precio){
        super(id, tipo, precio);
    }
    
    public Removedor(int id, String tipo, double precio, int cantidad){
        super(id, tipo, precio, cantidad);
    }
}
