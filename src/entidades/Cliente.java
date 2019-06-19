package entidades;

import gestion.Turno;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author demaa
 */
public class Cliente implements Comparable<Cliente>{
    private final int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private final List<Turno> listaTurnos = new ArrayList<>();

    public List<Turno> getListaTurnos() {
        return listaTurnos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Cliente(int numCliente, String nombre, String apellido, String telefono) {
        this.id = numCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }
    
    public void asignarTurno(Turno turno){
        listaTurnos.add(turno);
    }

    @Override
    public int compareTo(Cliente o) {
        Integer numCliente = this.id;
        return numCliente.compareTo(o.getId());
    }
    
    
}
