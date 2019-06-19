package gestion;

import entidades.Cliente;

/**
 *
 * @author demaa
 */
public class Turno {

    private final int id;
    private Cliente cliente;
    private String descripcion;
    private String fecha;
    private String hora;
    private int monto;
    private boolean realizado = false;

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
    
    public Turno(int id, Cliente cliente, String descripcion, String fecha, String hora, int monto, boolean realizado) {
        this.id = id;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.monto = monto;
        this.realizado = realizado;
    }
}
