package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Conexion;

public class Producto {
    private int idProducto;
    private String nombreProducto;
    private double precioProducto;
    private int stockProducto;
    
    public Producto () {}

    public Producto(int idProducto, String nombreProducto, double precioProducto, int stockProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    @Override
    public String toString() {
        return "Producto [idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", precioProducto=" + precioProducto
                + ", stockProducto=" + stockProducto + "]";
    }
    
    
    public int obtenerStockActual(int idProducto) {
        Connection con = Conexion.conectar();
        int stockActual = 0;
        try {
            String query = "SELECT stockProducto FROM Producto WHERE idProducto = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, idProducto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                stockActual = rs.getInt("stockProducto");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("ERROR AL OBTENER STOCK DEL PRODUCTO");
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stockActual;
    }

    public static boolean existeProducto(Connection con, int idProducto) throws SQLException {
        String query = "SELECT COUNT(*) FROM Producto WHERE idProducto = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, idProducto);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        boolean existe = rs.getInt(1) > 0;
        rs.close();
        stmt.close();
        return existe;
    }
  
    public static void actualizarStockProducto(Connection con, int idProducto, int cantidadCompra) throws SQLException {
        String query = "UPDATE Producto SET stockProducto = stockProducto + ? WHERE idProducto = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, cantidadCompra);
        stmt.setInt(2, idProducto);
        stmt.executeUpdate();
        System.out.println("Stock del producto actualizado después de la compra.");
        stmt.close();
    }
    
    public static int obtenerStock(Connection con, int idProducto) {
        int stock = 0;
        try {
            String query = "SELECT stockProducto FROM Producto WHERE idProducto = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, idProducto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                stock = rs.getInt(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER STOCK DE PRODUCTO");
            e.printStackTrace();
        }
        return stock;
    }
    
    public static void aumentarStockProducto(int idProducto, int cantidad) {
        Connection con = Conexion.conectar();
        try {
            String query = "UPDATE Producto SET stockProducto = stockProducto + ? WHERE idProducto = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, cantidad);
            stmt.setInt(2, idProducto);
            stmt.executeUpdate();
            System.out.println("Stock del producto actualizado después de revertir la venta.");
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al aumentar el stock del producto: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void disminuirStockProducto(Connection con, int idProducto, int cantidad) throws SQLException {
        String query = "UPDATE Producto SET stockProducto = stockProducto - ? WHERE idProducto = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, cantidad);
        stmt.setInt(2, idProducto);
        stmt.executeUpdate();
        System.out.println("Stock del producto disminuido después de revertir la compra.");
        stmt.close();
    }
}