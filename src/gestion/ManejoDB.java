package gestion;

import entidades.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import recursos.Esmalte;
import recursos.Herramienta;
import recursos.Producto;
import recursos.Removedor;

/**
 *
 * @author demaa
 */
public abstract class ManejoDB {

    private static Connection conexion;
    private static Statement instruccion;

    /**
     * Ejecuta las querys en la BD
     *
     * @param query Query a ejecutar
     * @param select Si la query es un SELECT o no
     * @return Resultados del SELECT (En caso de que select sea true)
     */
    public static ResultSet ejecutarQuery(String query, boolean select) {
        try {
            if (select) {
                return instruccion.executeQuery(query);
            } else {
                instruccion.execute(query);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManejoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Ejecuta las querys en la BD
     *
     * @param query Query a ejecutar
     * @return Cantidad de lineas cambiadas en la BD
     */
    public static int ejecutarQuery(String query) {
        try {
            return instruccion.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ManejoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Conecta con la BD
     */
    public static void conectar() {
        String url = "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(url, "root", "demaadota");
            instruccion = conexion.createStatement();
            String query = "CREATE SCHEMA IF NOT EXISTS tamynails;";
            instruccion.execute(query);
            url = "jdbc:mysql://localhost:3306/tamynails?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            conexion = (Connection) DriverManager.getConnection(url, "root", "demaadota");
            instruccion = conexion.createStatement();
            ManejoDB.crearBD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea la BD en caso de que no exista
     */
    private static void crearBD() {
        try {

            //TODO: Arreglar que no puedo crear las dos tablas en una sola consulta. SQLSyntaxError
            String query = "CREATE TABLE IF NOT EXISTS clientes (\n"
                    + "	id_cliente int(11) NOT NULL,\n"
                    + "	nombre varchar(45) DEFAULT NULL,\n"
                    + " apellido varchar(45) DEFAULT NULL,\n"
                    + "	telefono varchar(45) DEFAULT NULL,\n"
                    + " PRIMARY KEY (id_cliente)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
            instruccion.execute(query);
            query = "CREATE TABLE IF NOT EXISTS historial_turnos(\n"
                    + "	id_turno int(11) NOT NULL,\n"
                    + "    id_cliente int(11) NOT NULL,\n"
                    + "    fecha_hora timestamp NOT NULL,\n"
                    + "    monto int(11) DEFAULT NULL,\n"
                    + "    descripcion varchar(90) DEFAULT NULL,\n"
                    + "    realizado boolean DEFAULT FALSE,\n"
                    + "    PRIMARY KEY (id_turno),\n"
                    + "    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente) ON DELETE CASCADE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
            instruccion.execute(query);
            query = "CREATE TABLE IF NOT EXISTS esmaltes (\n"
                    + "	id_producto int(11) NOT NULL,\n"
                    + "    tipo varchar(45) NOT NULL,\n"
                    + "    color varchar(45) NOT NULL,\n"
                    + "    efecto varchar(45) NOT NULL,\n"
                    + "    precio double NOT NULL\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;";
            instruccion.execute(query);
            query = "CREATE TABLE IF NOT EXISTS herramientas (\n"
                    + "	id_producto int(11) NOT NULL,\n"
                    + "    tipo varchar(45) NOT NULL,\n"
                    + "    precio double NOT NULL\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;";
            instruccion.execute(query);
            query = "CREATE TABLE IF NOT EXISTS removedores (\n"
                    + "	id_producto int(11) NOT NULL,\n"
                    + "    tipo varchar(45) NOT NULL,\n"
                    + "    precio double NOT NULL\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;";
            instruccion.execute(query);
        } catch (SQLException e) {
            System.out.println("Hubo un error con la consulta: " + e.getMessage());
        }
    }

    /**
     * Lee los datos de la BD y los guarda en las listas
     */
    public static void obtenerDatos() {
        try {
            //OBTENCIÓN DE CLIENTES
            String query = "SELECT id_cliente, nombre, apellido, telefono FROM clientes ORDER BY id_cliente";
            ResultSet result = ManejoDB.ejecutarQuery(query, true);

            while (result.next()) {
                TamyNails.getListaClientes().add(new Cliente(result.getInt(1), result.getString(2), result.getString(3), result.getString(4)));
            }

            //OBTENCIÓN DE TURNOS Y ASIGNACIÓN DE TURNOS A CLIENTES
            query = "SELECT ht.id_turno, c.id_cliente, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado FROM clientes c "
                    + "INNER JOIN historial_turnos ht "
                    + "WHERE c.id_cliente=ht.id_cliente "
                    + "ORDER BY ht.id_turno;";
            result = ManejoDB.ejecutarQuery(query, true);
            while (result.next()) {
                String[] fechaArray = result.getString(4).split(" ")[0].split("-");
                String fecha = "";
                String hora = result.getString(4).split(" ")[1].substring(0, 5);
                for (int i = fechaArray.length - 1; i >= 0; i--) {
                    if (i > 0) {
                        fecha += (fechaArray[i] + "/");
                    } else {
                        fecha += fechaArray[i];
                    }
                }
                TamyNails.getListaTurnos().add(new Turno(result.getInt(1), TamyNails.getListaClientes().get(result.getInt(2) - 1), result.getString(3), fecha, hora, result.getInt(5), result.getBoolean(6)));

                for (Turno turno : TamyNails.getListaTurnos()) {
                    for (Cliente cliente : TamyNails.getListaClientes()) {
                        if (turno.getCliente().equals(cliente)) {
                            cliente.asignarTurno(turno);
                        }
                    }
                }
            }

            query = "SELECT * FROM esmaltes;";
            result = ManejoDB.ejecutarQuery(query, true);
            while(result.next()){
                TamyNails.getListaProductos().add(new Esmalte(result.getInt(1), result.getString(2), result.getDouble(5), result.getString(3), result.getString(4)));
            }
            query = "SELECT * FROM removedores;";
            result = ManejoDB.ejecutarQuery(query, true);
            while(result.next()){
                TamyNails.getListaProductos().add(new Removedor(result.getInt(1), result.getString(2), result.getDouble(3)));
            }
            query = "SELECT * FROM herramientas;";
            result = ManejoDB.ejecutarQuery(query, true);
            while(result.next()){
                TamyNails.getListaProductos().add(new Herramienta(result.getInt(1), result.getString(2), result.getDouble(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManejoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
