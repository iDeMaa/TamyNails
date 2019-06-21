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
}
