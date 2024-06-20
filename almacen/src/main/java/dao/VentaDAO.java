package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CajaAhorro;
import model.Cliente;
import model.Producto;
import model.Venta;
import util.Conexion;

public class VentaDAO implements IAlmacenDAO<Venta> {

    @Override
    public void insertar(Venta venta) {
        Connection con = Conexion.conectar();
        try {
            con.setAutoCommit(false);

            // Validar existencia del cliente
            if (!Cliente.existeCliente(con, venta.getIdCliente())) {
                System.out.println("ERROR: El cliente no existe. Dar de alta.");
                return;
            }

            // Validar existencia del producto
            if (!Producto.existeProducto(con, venta.getIdProducto())) {
                System.out.println("ERROR: El producto no existe. Dar de alta.");
                return;
            }

            // Validar stock suficiente
            int stockDisponible = Producto.obtenerStock(con, venta.getIdProducto());
            if (venta.getCantidadVenta() > stockDisponible) {
                System.out.println("ERROR: Stock insuficiente.");
                return;
            }

            // Registrar la venta
            String queryVenta = "INSERT INTO Venta (fecha, idProducto, idCliente, cantidadVenta, precioVenta) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmtVenta = con.prepareStatement(queryVenta);
            stmtVenta.setDate(1, new Date(venta.getFecha().getTime()));
            stmtVenta.setInt(2, venta.getIdProducto());
            stmtVenta.setInt(3, venta.getIdCliente());
            stmtVenta.setInt(4, venta.getCantidadVenta());
            stmtVenta.setDouble(5, venta.getPrecioVenta());
            stmtVenta.execute();
            System.out.println("Venta registrada correctamente.");

            // Actualizar saldo y stock
            CajaAhorro.actualizarSaldoVenta(con, venta.getPrecioVenta() * venta.getCantidadVenta());
            Producto.actualizarStockProducto(con, venta.getIdProducto(), - venta.getCantidadVenta());

            con.commit();
            stmtVenta.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("ERROR AL REGISTRAR VENTA");
            e.printStackTrace();
            try {
                System.out.println("REVIERTO TRANSACCIÓN");
                con.rollback();
            } catch (SQLException e2) {
                System.out.println("ERROR AL REVERTIR: " + e2.getMessage());
            }
        }
    }


    @Override
    public void borrar(int id) {
        Connection con = Conexion.conectar();
        try {
            con.setAutoCommit(false);

            // Obtener la venta para revertir sus efectos
            Venta venta = mostrarPorId(id);
            if (venta == null) {
                System.out.println("La venta no existe.");
                return;
            }

            // Eliminar la venta
            String query = "DELETE FROM Venta WHERE idVenta = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, venta.getIdVenta());
            stmt.executeUpdate();
            System.out.println("Venta eliminada correctamente.");

            // Aumentar el stock del producto vendido
            Producto.aumentarStockProducto(venta.getIdProducto(), venta.getCantidadVenta());

            // Revertir el saldo de la caja de ahorro
            CajaAhorro.reversionVentaSaldo(venta.getPrecioVenta() * venta.getCantidadVenta());

            con.commit();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar la venta: " + e.getMessage());
            e.printStackTrace();
            try {
                System.out.println("Revierto transacción.");
                con.rollback();
            } catch (SQLException e2) {
                System.out.println("Error al revertir: " + e2.getMessage());
                e2.printStackTrace();
            }
        }
    }
  

    public List<Venta> listarTodos() {
        List<Venta> ventas = new ArrayList<>();
        Connection con = Conexion.conectar();
        try {
            String query = "SELECT v.idVenta, v.fecha, v.cantidadVenta, v.precioVenta, " +
                           "c.nombre AS nombreCliente, c.apellido AS apellidoCliente, p.nombreProducto " +
                           "FROM Venta v " +
                           "JOIN Cliente c ON v.idCliente = c.idCliente " +
                           "JOIN Producto p ON v.idProducto = p.idProducto";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta(
                        rs.getInt("idVenta"),
                        rs.getDate("fecha"),
                        rs.getString("nombreCliente") + " " + rs.getString("apellidoCliente"),
                        rs.getString("nombreProducto"),
                        rs.getInt("cantidadVenta"),
                        rs.getDouble("precioVenta")
                );
                ventas.add(venta);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("ERROR AL LISTAR VENTAS");
            e.printStackTrace();
        }
        return ventas;
    }


    @Override
    public Venta mostrarPorId(int id) {
        Venta venta = null;
        Connection con = Conexion.conectar();
        try {
            String query = "SELECT * FROM Venta WHERE idVenta = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                venta = new Venta(
                        rs.getInt("idVenta"),
                        rs.getDate("fecha"),
                        rs.getInt("idProducto"),
                        rs.getInt("idCliente"),
                        rs.getInt("cantidadVenta"),
                        rs.getDouble("precioVenta")
                );
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER VENTA POR ID");
            e.printStackTrace();
        }
        return venta;
    }

	
}