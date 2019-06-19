package gestion;

import entidades.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * @param query Query a ejecutar
     * @return Cantidad de lineas cambiadas en la BD
     * @see java.sql.Statement
     */
    public static int ejecutarQuery(String query){
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
        } catch (SQLException e) {
            System.out.println("Hubo un error con la consulta: " + e.getMessage());
        }
    }

    /**
     * Lee los datos de la BD y los guarda en las listas
     */
    public static void obtenerDatos() {
        try {
            String query = "SELECT id_cliente, nombre, apellido, telefono FROM clientes ORDER BY id_cliente";
            ResultSet result = ManejoDB.ejecutarQuery(query, true);

            while (result.next()) {
                TamyNails.getListaClientes().add(new Cliente(result.getInt(1), result.getString(2), result.getString(3), result.getString(4)));
            }

            query = "SELECT ht.id_turno, c.id_cliente, ht.descripcion, ht.fecha_hora, ht.monto, ht.realizado FROM clientes c "
                    + "INNER JOIN historial_turnos ht "
                    + "WHERE c.id_cliente=ht.id_cliente "
                    + "ORDER BY ht.id_turno;";
            result = ManejoDB.ejecutarQuery(query, true);
            while(result.next()){
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
                TamyNails.getListaTurnos().add(new Turno(result.getInt(1), TamyNails.getListaClientes().get(result.getInt(2)-1), result.getString(3), fecha, hora, result.getInt(5), result.getBoolean(6)));
                
                for(Turno turno : TamyNails.getListaTurnos()){
                    for(Cliente cliente : TamyNails.getListaClientes()){
                        if(turno.getCliente().equals(cliente)){
                            cliente.asignarTurno(turno);
                        }
                    }
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManejoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
