package recursos;

/**
 *
 * @author demaa
 */
public abstract class Producto {

    private final int id;
    private String tipo;
    private double precio;

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Producto(int id, String tipo, double precio) {
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", tipo=" + tipo + ", precio=" + precio + '}';
    }
    
    
    
}
