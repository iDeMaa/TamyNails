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
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import recursos.Esmalte;
import recursos.Herramienta;
import recursos.Producto;
import recursos.Removedor;

/**
 *
 * @author DeMaa
 */
public abstract class TamyNails {

    private static final List<Cliente> listaClientes = new ArrayList<>();
    private static final List<Turno> listaTurnos = new ArrayList<>();
    private static final List<Producto> listaProductos = new ArrayList<>();

    public static List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public static List<Turno> getListaTurnos() {
        return listaTurnos;
    }

    public static List<Producto> getListaProductos() {
        return listaProductos;
    }

    public static void main(String[] args) {
        final JOptionPane optionPane = new JOptionPane("Cargando datos, por favor espere...", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        final JDialog dialog = new JDialog();
        dialog.setTitle("Cargando...");
        dialog.setContentPane(optionPane);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);

        ManejoDB.conectar();
        ManejoDB.obtenerDatos();

        dialog.dispose();
        System.out.println(listaProductos.size());
        MainMenu mainMenu = new MainMenu();
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
        int respuesta = JOptionPane.showConfirmDialog(null, texto, "¿Estás segura?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            for (Cliente cliente : listaClientes) {
                if (cliente.getNombre().equalsIgnoreCase(nom) && cliente.getApellido().equalsIgnoreCase(ape) && cliente.getTelefono().equalsIgnoreCase(tel)) {
                    JOptionPane.showMessageDialog(null, "¡El cliente ya existe en la base de datos!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
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
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás segura que queres borrar al cliente? Los datos no se pueden recuperar", "¿Estás segura?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
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
    public static void asignarTurno(int idCliente, String desc, String fecha, String hora, int monto) {
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
            String texto = "¿Estás segura que queres añadir el turno al cliente?\nCliente: " + cliente.getNombre() + " " + cliente.getApellido()
                    + "\nDescripción: " + desc + "\nFecha: " + fecha + "\nHora: " + hora + "\nMonto: " + monto;
            int respuesta = JOptionPane.showConfirmDialog(null, texto, "¿Estás segura?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                Turno turno = new Turno(idTurno, cliente, desc, fecha, hora, monto, false);
                String[] fechaArray = fecha.split("/");
                fecha = "";
                for (int i = fechaArray.length - 1; i >= 0; i--) {
                    if (i > 0) {
                        fecha += (fechaArray[i] + "-");
                    } else {
                        fecha += fechaArray[i];
                    }
                }
                fecha += " " + hora + ":00";

                String query = "INSERT INTO turnos(id_turno, id_cliente, fecha_hora, monto, descripcion)"
                        + "VALUES (" + turno.getId() + ", " + idCliente + ", \"" + fecha + "\", " + monto + ", \"" + desc + "\");";

                if (ManejoDB.ejecutarQuery(query) > 0) {
                    cliente.asignarTurno(turno);
                    listaTurnos.add(turno);
                }
                listaTurnos.forEach((turnos) -> {
                    System.out.println("ID: " + turnos.getId() + "\n Cliente: " + turnos.getCliente().getNombre() + " " + turnos.getCliente().getApellido());
                });
            }
        }
    }

    /**
     * Eliminar un turno
     *
     * @param id
     */
    public static void eliminarTurno(int id) {
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás segura que queres borrar el turno? Los datos no se pueden recuperar", "¿Estás segura?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            String query = "DELETE FROM turnos WHERE id_turno = " + id + ";";
            if (ManejoDB.ejecutarQuery(query) > 0) {
                listaTurnos.remove(id - 1);
            }
        }
    }

    /**
     * Cambia el estado de un turno
     *
     * @param id ID del turno
     * @param q query a ejecutar para la relación productos-turnos
     * @param lProd Lista de productos usados en el turno
     */
    public static void cambiarEstadoTurno(int id, String q, List<Producto> lProd) {
        Turno turno = listaTurnos.get(id - 1);
        String query = "UPDATE turnos SET realizado = " + !turno.isRealizado() + " WHERE id_turno=" + turno.getId() + ";";;
        if (ManejoDB.ejecutarQuery(query) > 0) {
            turno.setRealizado(!turno.isRealizado());
            if (ManejoDB.ejecutarQuery(q) > 0) {
                turno.setListaProductos(lProd);
            }
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
     *
     * @param categoria Por qué buscar (ID de turno, Fecha, Monto, etc)
     * @param busqueda Búsqueda a realizar
     * @return Lista de turnos que coinciden con la búsqueda
     */
    public static List<Turno> buscarEnDB(int categoria, String busqueda) {
        String sql = "";
        switch (categoria) {
            case 0: //ID_TURNO
                sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado "
                        + "FROM clientes c INNER JOIN turnos t "
                        + "WHERE c.id_cliente=t.id_cliente AND t.id_turno=" + busqueda + ";";
                break;
            case 1: //NOMBRE CLIENTE
                if (busqueda.length() == 0) {
                    sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado \n"
                            + "FROM turnos t INNER JOIN  clientes c\n"
                            + "WHERE c.id_cliente=t.id_cliente \n"
                            + "ORDER BY c.nombre;";
                    break;
                } else if (busqueda.split(" ").length > 1) {
                    String nombre[] = busqueda.split(" ");
                    sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado "
                            + "FROM clientes c INNER JOIN turnos t "
                            + "WHERE c.id_cliente=t.id_cliente AND c.nombre=\"" + nombre[0] + "\" AND c.apellido=\"" + nombre[1] + "\";";
                } else {
                    sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado "
                            + "FROM clientes c INNER JOIN turnos t "
                            + "WHERE c.id_cliente=t.id_cliente AND c.nombre=\"" + busqueda + "\";";
                }
                break;
            case 2: //DESCRIPCION
                sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado "
                        + "FROM clientes c INNER JOIN turnos t "
                        + "WHERE c.id_cliente=t.id_cliente AND t.descripcion LIKE \"%" + busqueda + "%\";";
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
                    sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado \n"
                            + "FROM turnos t INNER JOIN  clientes c\n"
                            + "WHERE c.id_cliente=t.id_cliente \n"
                            + "ORDER BY t.fecha_hora;";
                    break;
                } else if (fecha.charAt(fecha.length() - 3) == '>') {
                    StringBuilder fechaSB = new StringBuilder(fecha);
                    fechaSB.deleteCharAt(8);
                    sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado \n"
                            + "FROM turnos t INNER JOIN  clientes c\n"
                            + "WHERE c.id_cliente=t.id_cliente AND t.fecha_hora >= \"" + fechaSB + "%\"\n"
                            + "ORDER BY t.fecha_hora;";
                    break;
                } else if (fecha.charAt(fecha.length() - 3) == '<') {
                    StringBuilder fechaSB = new StringBuilder(fecha);
                    fechaSB.deleteCharAt(8);
                    sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado \n"
                            + "FROM turnos t INNER JOIN  clientes c\n"
                            + "WHERE c.id_cliente=t.id_cliente AND t.fecha_hora <= \"" + fechaSB + "%\"\n"
                            + "ORDER BY t.fecha_hora;";
                    break;
                } else if (fecha.contains(">") && fecha.contains("hoy")) {
                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    fecha = ts.toString().split(" ")[0];
                    sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado \n"
                            + "FROM turnos t INNER JOIN  clientes c\n"
                            + "WHERE c.id_cliente=t.id_cliente AND t.fecha_hora > \"" + fecha + "%\"\n"
                            + "ORDER BY t.fecha_hora;";
                    break;
                } else if (fecha.contains("<") && fecha.contains("hoy")) {
                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    fecha = ts.toString().split(" ")[0];
                    sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado \n"
                            + "FROM turnos t INNER JOIN  clientes c\n"
                            + "WHERE c.id_cliente=t.id_cliente AND t.fecha_hora < \"" + fecha + "%\"\n"
                            + "ORDER BY t.fecha_hora;";
                    break;
                } else if (fecha.equals("hoy")) {
                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    fecha = ts.toString().split(" ")[0];
                    sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado \n"
                            + "FROM turnos t INNER JOIN  clientes c\n"
                            + "WHERE c.id_cliente=t.id_cliente AND t.fecha_hora LIKE \"" + fecha + "%\"\n"
                            + "ORDER BY t.fecha_hora;";
                    break;
                } else {
                    sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado "
                            + "FROM clientes c INNER JOIN turnos t "
                            + "WHERE c.id_cliente=t.id_cliente AND t.fecha_hora LIKE \"" + fecha + "%\"\n"
                            + "ORDER BY t.fecha_hora;";
                    break;
                }
            case 4: //HORA
                String hora = busqueda + ":00";
                sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado "
                        + "FROM clientes c INNER JOIN turnos t "
                        + "WHERE c.id_cliente=t.id_cliente AND TIME(t.fecha_hora) IN (\"" + hora + "\");";
                break;
            case 5: //MONTO
                String monto = busqueda.replace("$", "");
                sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado "
                        + "FROM clientes c INNER JOIN turnos t "
                        + "WHERE c.id_cliente=t.id_cliente AND t.monto=" + monto + ";";
                break;

            case 6: //REALIZADO
                if (busqueda.equalsIgnoreCase("Si")) {
                    sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado "
                            + "FROM clientes c INNER JOIN turnos t "
                            + "WHERE c.id_cliente=t.id_cliente AND t.realizado=true;";
                } else if (busqueda.equalsIgnoreCase("No")) {
                    sql = "SELECT t.id_turno, c.nombre, c.apellido, t.descripcion, t.fecha_hora, t.monto, t.realizado "
                            + "FROM clientes c INNER JOIN turnos t "
                            + "WHERE c.id_cliente=t.id_cliente AND t.realizado=false;";
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

    /**
     * Obtiene una lista con los productos usados en un turno
     *
     * @param idTurno ID del turno
     * @return Lista con los productos
     */
    public static List<Producto> obtenerProductosUsados(int idTurno) {
        List<Producto> lista = new ArrayList<>();

        String query = "SELECT p.id_producto FROM productos_turnos pt JOIN productos p WHERE p.id_producto = pt.productos_id_producto AND pt.turnos_id_turno = " + idTurno + ";";
        ResultSet result = ManejoDB.ejecutarQuery(query, true);
        try {
            while (result.next()) {
                lista.add(listaProductos.get(result.getInt(1) - 1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TamyNails.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    /**
     * Agrega un producto (Esmalte) a la BD
     *
     * @param producto Producto
     * @param tipo Tipo de producto
     * @param color Color de producto
     * @param efecto Efecto del producto
     * @param precio Precio del producto
     */
    public static void agregarProducto(String producto, String tipo, String color, String efecto, String precio) {
        for (Producto prod : listaProductos) {
            Esmalte p = null;
            if (prod.getClass().getSimpleName().equalsIgnoreCase("Esmalte")) {
                p = (Esmalte) prod;
            }
            if (p != null && p.getTipo().equalsIgnoreCase(tipo) && p.getEfecto().equalsIgnoreCase(efecto) && p.getColor().equalsIgnoreCase(color) && p.getPrecio() == Double.parseDouble(precio)) {
                String query = "UPDATE productos SET cantidad = " + (p.getCantidad() + 1) + " WHERE id_producto = " + p.getId() + ";";
                if (ManejoDB.ejecutarQuery(query) > 0) {
                    p.setCantidad(p.getCantidad() + 1);
                }
                return;
            }
        }

        String query = "INSERT INTO productos (id_producto, tipo, precio, cantidad) VALUES (" + (listaProductos.size() + 1) + ", '" + tipo + "', " + Double.parseDouble(precio) + ", 1)";
        if (ManejoDB.ejecutarQuery(query) > 0) {
            query = "INSERT INTO esmaltes (color, efecto, productos_id_producto) VALUES ('" + color + "', '" + efecto + "', " + (listaProductos.size() + 1) + ");";
            if (ManejoDB.ejecutarQuery(query) > 0) {
                listaProductos.add(new Esmalte(listaProductos.size() + 1, tipo, Double.parseDouble(precio), color, efecto));
            } else {
                query = "DELETE FROM productos WHERE id_producto = " + (listaProductos.size() + 1) + ";";
                ManejoDB.ejecutarQuery(query);
            }
        }
    }

    /**
     * Agrega un producto (Herramienta o Removedor)
     *
     * @param producto Producto (Herramienta o Removedor)
     * @param tipo Tipo de producto
     * @param precio Precio de producto
     */
    public static void agregarProducto(String producto, String tipo, String precio) {

        for (Producto p : listaProductos) {
            if (p.getTipo().equalsIgnoreCase(tipo) && p.getPrecio() == Double.parseDouble(precio)) {
                String query = "UPDATE productos SET cantidad = " + (p.getCantidad() + 1) + " WHERE id_producto = " + p.getId() + ";";
                if (ManejoDB.ejecutarQuery(query) > 0) {
                    p.setCantidad(p.getCantidad() + 1);
                }
                return;
            }
        }

        String query = "INSERT INTO productos (id_producto, tipo, precio, cantidad) VALUES (" + (listaProductos.size() + 1) + ", '" + producto + " " + tipo + "', " + Double.parseDouble(precio) + ", 1)";
        if (ManejoDB.ejecutarQuery(query) > 0) {
            switch (producto) {
                case "Herramienta":
                    listaProductos.add(new Herramienta(listaProductos.size() + 1, tipo, Double.parseDouble(precio)));
                    break;
                case "Removedor":
                    listaProductos.add(new Removedor(listaProductos.size() + 1, tipo, Double.parseDouble(precio)));
                    break;
            }
        }
    }
}
