package recursos;

/**
 *
 * @author demaa
 */
public abstract class Producto implements Comparable{

    private final int id;
    private String tipo;
    private double precio;
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
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
        cantidad = 1;
    }
    
    public Producto(int id, String tipo, double precio, int cantidad){
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", tipo=" + tipo + ", precio=" + precio + '}';
    }
    
    @Override
    public int compareTo(Object o){
        Producto p = (Producto) o;
        if(this.getId() > p.getId()){
            return 1;
        } else if (this.getId() < p.getId()){
            return -1;
        }
        return 0;
    }
}
