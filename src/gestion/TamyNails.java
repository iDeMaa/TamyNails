package gestion;

import entidades.Cliente;
import gui.MainMenu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DeMaa
 */
public abstract class TamyNails {

    private static final List<Cliente> listaClientes = new ArrayList<>();
    private static final List<Turno> listaTurnos = new ArrayList<>();

    public static List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public static List<Turno> getListaTurnos() {
        return listaTurnos;
    }

    public static void main(String[] args) {
        ManejoDB.conectar();
        ManejoDB.obtenerDatos();
        MainMenu mainMenu = new MainMenu(null, false);
        mainMenu.setVisible(true);
        mainMenu.setLocationRelativeTo(null);
    }

    /**
     * Agrega un nuevo cliente a la lista
     *
     * @param nom Nombre del cliente
     * @param ape Apellido del cliente
     * @param tel Telefono del cliente
     */
    public static void agregarCliente(String nom, String ape, String tel) {
        String texto = "¿Estás segura que queres añadir al cliente?\nNombre: " + nom + "\nApellido: " + ape + "\nTeléfono: " + tel;
        int respuesta = JOptionPane.showConfirmDialog(null, texto, "¿Estás segura?", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            int num = listaClientes.size() + 1;
            if (!listaClientes.isEmpty()) {
                for (int i = 0; i < listaClientes.size() - 1; i++) {
                    if (listaClientes.get(i).getId() + 1 < listaClientes.get(i + 1).getId()) {
                        num = listaClientes.get(i).getId() + 1;
                        break;
                    }
                }
            }
            String query = "INSERT INTO clientes(id_cliente, nombre, apellido, telefono) VALUES (" + num + ",'" + nom + "','" + ape + "','" + tel + "');";
            if (ManejoDB.ejecutarQuery(query) > 0) {
                listaClientes.add(new Cliente(num, nom, ape, tel));
                Collections.sort(listaClientes);
            }
        }
    }

    /**
     * Eliminar un cliente de la BD y la lista
     *
     * @param id Numero del cliente a eliminar
     */
    public static void eliminarCliente(int id) {
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás segura que queres borrar al cliente? Los datos no se pueden recuperar", "¿Estás segura?", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            String query = "DELETE FROM clientes WHERE id_cliente = " + id + ";";
            if (ManejoDB.ejecutarQuery(query) > 0) {
                for (Cliente cliente : listaClientes) {
                    if (cliente.getId() == id) {
                        listaClientes.remove(cliente);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Asigna un turno a un cliente
     *
     * @param idCliente ID del cliente que solicitó el turno
     * @param desc Descripción del turno
     * @param fecha Fecha y hora del turno
     * @param monto Monto del turno
     */
    public static void asignarTurno(int idCliente, String desc, String fecha, int monto) {
        Cliente cliente = null;
        for (Cliente c : listaClientes) {
            if (c.getId() == idCliente) {
                cliente = c;
                break;
            }
        }
        if (cliente != null) {
            int idTurno = -1;
            if (!listaTurnos.isEmpty()) {
                idTurno = listaTurnos.get(listaTurnos.size() - 1).getId() + 1;
            } else {
                idTurno = 1;
            }
            String[] fh = fecha.split(" ");
            String texto = "¿Estás segura que queres añadir el turno al cliente?\nCliente: " + cliente.getApellido() + " " + cliente.getApellido()
                    + "\nDescripción: " + desc + "\nFecha: " + fh[0] + "\nHora: " + fh[1] + "\nMonto: " + monto;
            int respuesta = JOptionPane.showConfirmDialog(null, texto, "¿Estás segura?", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                Turno turno = new Turno(idTurno, cliente, desc, fh[0], fh[1], monto, false);

                String query = "INSERT INTO historial_turnos(id_turno, id_cliente, fecha_hora, monto, descripcion)"
                        + "VALUES (" + turno.getId() + ", " + idCliente + ", \"" + fecha + "\", " + monto + ", \"" + desc + "\");";

                if (ManejoDB.ejecutarQuery(query) > 0) {
                    cliente.asignarTurno(turno);
                    listaTurnos.add(turno);
                }
                for (Turno turnos : listaTurnos) {
                    System.out.println("ID: " + turnos.getId() + "\n Cliente: " + turnos.getCliente().getNombre() + " " + turnos.getCliente().getApellido());
                }
            }
        }
    }

    /**
     * Eliminar un turno
     *
     * @param id
     */
    public static void eliminarTurno(int id) {
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás segura que queres borrar el turno? Los datos no se pueden recuperar", "¿Estás segura?", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            String query = "DELETE FROM historial_turnos WHERE id_turno = " + id + ";";
            if (ManejoDB.ejecutarQuery(query) > 0) {
                listaTurnos.remove(id - 1);
            }
        }
    }

    /**
     * Cambia el estado de un turno
     *
     * @param id ID del turno
     */
    public static void cambiarEstadoTurno(int id) {
        Turno turno = listaTurnos.get(id - 1);
        String query = "UPDATE historial_turnos SET realizado = " + !turno.isRealizado() + " WHERE id_turno=" + turno.getId() + ";";;
        if (ManejoDB.ejecutarQuery(query) > 0) {
            turno.setRealizado(!turno.isRealizado());
        }
    }

    /**
     * Obtiene datos en relación a los turnos (Cantidad de turnos totales,
     * cantidad de turnos realizados, cantidad de turnos por realizar y monto
     * total obtenido)
     *
     * @return vector con cada dato obtenido, respectivamente
     */
    public static int[] obtenerDatosTurnos() {
        int[] result = new int[4];
        int cantTurnosRealizados = 0;
        int montoTotal = 0;

        for (Turno turno : listaTurnos) {
            if (turno.isRealizado()) {
                cantTurnosRealizados++;
                montoTotal += turno.getMonto();
            }
        }
        result[0] = listaTurnos.size();
        result[1] = cantTurnosRealizados;
        result[2] = result[0] - result[1];
        result[3] = montoTotal;
        return result;
    }

    /**
     * Realiza una búsqueda en la DB para encontrar turnos en específico
     * @param categoria Por qué buscar (ID de turno, Fecha, Monto, etc)
     * @param busqueda Búsqueda a realizar
     * @return Lista de turnos que coinciden con la búsqueda
     */
    public static List<Turno> buscarEnDB(int categoria, String busqueda) {
        String sql = "";
        switch (categoria) {
            case 0: //ID_TURNO
                sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado "
                        + "FROM clientes c INNER JOIN historial_turnos ht "
                        + "WHERE c.id_cliente=ht.id_cliente AND ht.id_turno=" + busqueda + ";";
                break;
            case 1: //NOMBRE CLIENTE
                if (busqueda.length() == 0) {
                    sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado \n"
                            + "FROM historial_turnos ht INNER JOIN  clientes c\n"
                            + "WHERE c.id_cliente=ht.id_cliente \n"
                            + "ORDER BY c.nombre;";
                    break;
                } else if (busqueda.split(" ").length > 1) {
                    String nombre[] = busqueda.split(" ");
                    sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado "
                            + "FROM clientes c INNER JOIN historial_turnos ht "
                            + "WHERE c.id_cliente=ht.id_cliente AND c.nombre=\"" + nombre[0] + "\" AND c.apellido=\"" + nombre[1] + "\";";
                } else {
                    sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado "
                            + "FROM clientes c INNER JOIN historial_turnos ht "
                            + "WHERE c.id_cliente=ht.id_cliente AND c.nombre=\"" + busqueda + "\";";
                }
                break;
            case 2: //DESCRIPCION
                sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado "
                        + "FROM clientes c INNER JOIN historial_turnos ht "
                        + "WHERE c.id_cliente=ht.id_cliente AND ht.descripcion LIKE \"%" + busqueda + "%\";";
                break;
            case 3: //FECHA
                String[] fechaArray = busqueda.split(" ")[0].split("/");
                String fecha = "";
                for (int i = fechaArray.length - 1; i >= 0; i--) {
                    System.out.println(fechaArray[i]);
                    if (i > 0) {
                        fecha += (fechaArray[i] + "-");
                    } else {
                        fecha += fechaArray[i];
                    }
                }
                fecha = fecha.toLowerCase();
                System.out.println(fecha.length());
                if (fecha.length() == 0) {
                    sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado \n"
                            + "FROM historial_turnos ht INNER JOIN  clientes c\n"
                            + "WHERE c.id_cliente=ht.id_cliente \n"
                            + "ORDER BY ht.fecha_hora;";
                    break;
                } else if (fecha.charAt(fecha.length() - 3) == '>') {
                    StringBuilder fechaSB = new StringBuilder(fecha);
                    fechaSB.deleteCharAt(8);
                    sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado \n"
                            + "FROM historial_turnos ht INNER JOIN  clientes c\n"
                            + "WHERE c.id_cliente=ht.id_cliente AND ht.fecha_hora >= \"" + fechaSB + "%\"\n"
                            + "ORDER BY ht.fecha_hora;";
                    break;
                } else if (fecha.charAt(fecha.length() - 3) == '<') {
                    StringBuilder fechaSB = new StringBuilder(fecha);
                    fechaSB.deleteCharAt(8);
                    sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado \n"
                            + "FROM historial_turnos ht INNER JOIN  clientes c\n"
                            + "WHERE c.id_cliente=ht.id_cliente AND ht.fecha_hora <= \"" + fechaSB + "%\"\n"
                            + "ORDER BY ht.fecha_hora;";
                    break;
                } else if (fecha.contains(">") && fecha.contains("hoy")) {
                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    fecha = ts.toString().split(" ")[0];
                    sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado \n"
                            + "FROM historial_turnos ht INNER JOIN  clientes c\n"
                            + "WHERE c.id_cliente=ht.id_cliente AND ht.fecha_hora > \"" + fecha + "%\"\n"
                            + "ORDER BY ht.fecha_hora;";
                    break;
                } else if (fecha.contains("<") && fecha.contains("hoy")) {
                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    fecha = ts.toString().split(" ")[0];
                    sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado \n"
                            + "FROM historial_turnos ht INNER JOIN  clientes c\n"
                            + "WHERE c.id_cliente=ht.id_cliente AND ht.fecha_hora < \"" + fecha + "%\"\n"
                            + "ORDER BY ht.fecha_hora;";
                    break;
                } else if (fecha.equals("hoy")) {
                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    fecha = ts.toString().split(" ")[0];
                    sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado \n"
                            + "FROM historial_turnos ht INNER JOIN  clientes c\n"
                            + "WHERE c.id_cliente=ht.id_cliente AND ht.fecha_hora LIKE \"" + fecha + "%\"\n"
                            + "ORDER BY ht.fecha_hora;";
                    break;
                } else {
                    sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado "
                            + "FROM clientes c INNER JOIN historial_turnos ht "
                            + "WHERE c.id_cliente=ht.id_cliente AND ht.fecha_hora LIKE \"" + fecha + "%\"\n"
                            + "ORDER BY ht.fecha_hora;";
                    break;
                }
            case 4: //HORA
                String hora = busqueda + ":00";
                sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado "
                        + "FROM clientes c INNER JOIN historial_turnos ht "
                        + "WHERE c.id_cliente=ht.id_cliente AND TIME(ht.fecha_hora) IN (\"" + hora + "\");";
                break;
            case 5: //MONTO
                String monto = busqueda.replace("$", "");
                sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado "
                        + "FROM clientes c INNER JOIN historial_turnos ht "
                        + "WHERE c.id_cliente=ht.id_cliente AND ht.monto=" + monto + ";";
                break;

            case 6: //REALIZADO
                if (busqueda.equalsIgnoreCase("Si")) {
                    sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado "
                            + "FROM clientes c INNER JOIN historial_turnos ht "
                            + "WHERE c.id_cliente=ht.id_cliente AND ht.realizado=true;";
                } else if (busqueda.equalsIgnoreCase("No")) {
                    sql = "SELECT ht.id_turno, c.nombre, c.apellido, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado "
                            + "FROM clientes c INNER JOIN historial_turnos ht "
                            + "WHERE c.id_cliente=ht.id_cliente AND ht.realizado=false;";
                }
                break;
        }
        ResultSet result = ManejoDB.ejecutarQuery(sql, true);
        List<Turno> aux = new ArrayList<>();
        try {
            while (result.next()) {
                for (Turno turno : listaTurnos) {
                    if (result.getInt(1) == turno.getId()) {
                        aux.add(turno);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TamyNails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }
    
    
}