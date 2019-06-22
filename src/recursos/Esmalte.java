package recursos;

/**
 *
 * @author demaa
 */
public class Esmalte extends Producto {

    private String color;
    private String efecto;

    public Esmalte(int id, String tipo, double precio, String color, String efecto) {
        super(id, tipo, precio);
        this.color = color;
        this.efecto = efecto;
    }
    
    public Esmalte(int id, String tipo, double precio, int cantidad, String color, String efecto){
        super(id, tipo, precio, cantidad);
        this.color=color;
        this.efecto = efecto;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEfecto() {
        return efecto;
    }

    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }

    @Override
    public String toString() {
        return "Esmalte{"+ "id= " + this.getId() + ", tipo="+ this.getTipo() + ", color=" + color + ", efecto=" + efecto + ", precio=" + this.getPrecio() + '}';
    }
    
    
}
