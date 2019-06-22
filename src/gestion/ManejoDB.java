package gestion;

import entidades.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
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
            String query = "CREATE TABLE IF NOT EXISTS `tamynails`.`clientes` (\n"
                    + "  `id_cliente` INT(11) NOT NULL,\n"
                    + "  `nombre` VARCHAR(45) NULL DEFAULT NULL,\n"
                    + "  `apellido` VARCHAR(45) NULL DEFAULT NULL,\n"
                    + "  `telefono` VARCHAR(45) NULL DEFAULT NULL,\n"
                    + "  PRIMARY KEY (`id_cliente`))\n"
                    + "ENGINE = InnoDB\n"
                    + "DEFAULT CHARACTER SET = utf8mb4\n"
                    + "COLLATE = utf8mb4_0900_ai_ci;";
            instruccion.execute(query);
            query = "CREATE TABLE IF NOT EXISTS `tamynails`.`turnos` (\n"
                    + "  `id_turno` INT(11) NOT NULL,\n"
                    + "  `id_cliente` INT(11) NOT NULL,\n"
                    + "  `fecha_hora` TIMESTAMP NOT NULL,\n"
                    + "  `monto` INT(11) NULL DEFAULT NULL,\n"
                    + "  `descripcion` VARCHAR(90) NULL DEFAULT NULL,\n"
                    + "  `realizado` TINYINT(1) NULL DEFAULT '0',\n"
                    + "  PRIMARY KEY (`id_turno`),\n"
                    + "  INDEX `id_cliente` (`id_cliente` ASC) VISIBLE,\n"
                    + "  CONSTRAINT `historial_turnos_ibfk_1`\n"
                    + "    FOREIGN KEY (`id_cliente`)\n"
                    + "    REFERENCES `tamynails`.`clientes` (`id_cliente`)\n"
                    + "    ON DELETE CASCADE)\n"
                    + "ENGINE = InnoDB\n"
                    + "DEFAULT CHARACTER SET = utf8mb4\n"
                    + "COLLATE = utf8mb4_0900_ai_ci;";
            instruccion.execute(query);
            query = "CREATE TABLE IF NOT EXISTS `tamynails`.`productos` (\n"
                    + "  `id_producto` INT(11) NOT NULL,\n"
                    + "  `tipo` VARCHAR(90) NOT NULL,\n"
                    + "  `precio` DOUBLE NOT NULL,\n"
                    + "  `cantidad` INT(11) NULL,\n"
                    + "  PRIMARY KEY (`id_producto`))\n"
                    + "ENGINE = InnoDB\n"
                    + "DEFAULT CHARACTER SET = utf8mb4\n"
                    + "COLLATE = utf8mb4_0900_ai_ci;";
            instruccion.execute(query);
            query = "CREATE TABLE IF NOT EXISTS `tamynails`.`esmaltes` (\n"
                    + "  `color` VARCHAR(20) NOT NULL,\n"
                    + "  `efecto` VARCHAR(20) NULL,\n"
                    + "  `productos_id_producto` INT(11) NOT NULL,\n"
                    + "  PRIMARY KEY (`productos_id_producto`),\n"
                    + "  CONSTRAINT `fk_esmaltes_productos`\n"
                    + "    FOREIGN KEY (`productos_id_producto`)\n"
                    + "    REFERENCES `tamynails`.`productos` (`id_producto`)\n"
                    + "    ON DELETE NO ACTION\n"
                    + "    ON UPDATE NO ACTION)\n"
                    + "ENGINE = InnoDB;";
            instruccion.execute(query);
            query = "CREATE TABLE IF NOT EXISTS `tamynails`.`productos_turnos` (\n"
                    + "  `productos_id_producto` INT(11) NOT NULL,\n"
                    + "  `turnos_id_turno` INT(11) NOT NULL,\n"
                    + "  PRIMARY KEY (`productos_id_producto`, `turnos_id_turno`),\n"
                    + "  INDEX `fk_productos_turnos_turnos1_idx` (`turnos_id_turno` ASC) VISIBLE,\n"
                    + "  CONSTRAINT `fk_productos_turnos_productos1`\n"
                    + "    FOREIGN KEY (`productos_id_producto`)\n"
                    + "    REFERENCES `tamynails`.`productos` (`id_producto`)\n"
                    + "    ON DELETE NO ACTION\n"
                    + "    ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_productos_turnos_turnos1`\n"
                    + "    FOREIGN KEY (`turnos_id_turno`)\n"
                    + "    REFERENCES `tamynails`.`turnos` (`id_turno`)\n"
                    + "    ON DELETE NO ACTION\n"
                    + "    ON UPDATE NO ACTION)\n"
                    + "ENGINE = InnoDB;";
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
            query = "SELECT t.id_turno, c.id_cliente, t.descripcion, t.fecha_hora, t.monto, t.realizado FROM clientes c "
                    + "INNER JOIN turnos t "
                    + "WHERE c.id_cliente=t.id_cliente "
                    + "ORDER BY t.id_turno;";
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

            query = "SELECT p.id_producto, p.tipo, e.color, e.efecto, p.precio, p.cantidad FROM esmaltes e JOIN productos p WHERE p.id_producto = e.productos_id_producto ORDER BY p.id_producto;";
            result = ManejoDB.ejecutarQuery(query, true);
            while (result.next()) {
                TamyNails.getListaProductos().add(new Esmalte(result.getInt(1), result.getString(2), result.getDouble(5), result.getInt(6), result.getString(3), result.getString(4)));
            }
            query = "SELECT * FROM productos ORDER BY productos.id_producto;";
            result = ManejoDB.ejecutarQuery(query, true);
            while (result.next()) {
                String nombre = result.getString(2);
                if (nombre.split(" ")[0].equalsIgnoreCase("Herramienta")) {
                    String tipo = result.getString(2).substring(12);
                    TamyNails.getListaProductos().add(new Herramienta(result.getInt(1), tipo, result.getDouble(3), result.getInt(4)));
                } else if (nombre.split(" ")[0].equalsIgnoreCase("Removedor")) {
                    String tipo = result.getString(2).substring(10);
                    TamyNails.getListaProductos().add(new Removedor(result.getInt(1), tipo, result.getDouble(3), result.getInt(4)));
                }
            }
            Collections.sort(TamyNails.getListaProductos());
            
            query = "SELECT * FROM productos_turnos";
            result = ManejoDB.ejecutarQuery(query, true);
            System.out.println(TamyNails.getListaTurnos().size()+"\t"+TamyNails.getListaProductos().size());
            while(result.next()){
                TamyNails.getListaTurnos().get(result.getInt(2)-1).getListaProductos().add(TamyNails.getListaProductos().get(result.getInt(1)-1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManejoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
