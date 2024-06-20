package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Producto;
import util.Conexion;

public class ProductoDAO implements IAlmacenDAO<Producto> {

    @Override
    public void insertar(Producto producto) {
        Connection con = Conexion.conectar();
        try {
            con.setAutoCommit(false);
            String query = "INSERT INTO Producto (idProducto, nombreProducto, precioProducto, stockProducto) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, producto.getIdProducto());
            stmt.setString(2, producto.getNombreProducto());
            stmt.setDouble(3, producto.getPrecioProducto());
            stmt.setInt(4, producto.getStockProducto());
            stmt.execute();
            System.out.println("Producto agregado correctamente.");
            con.commit();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL INSERTAR PRODUCTO");
            e.printStackTrace();
            try {
                System.out.println("REVIERTO TRANSACCION");
                con.rollback();
            } catch (Exception e2) {
                System.out.println("ERROR AL REVERTIR: " + e2.getMessage());
            }
        }
    }

    @Override
    public void borrar(int id) {
        Connection con = Conexion.conectar();
        try {
            con.setAutoCommit(false);
            String query = "DELETE FROM Producto WHERE idProducto = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Producto eliminado correctamente.");
            con.commit();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL BORRAR PRODUCTO");
            e.printStackTrace();
            try {
                System.out.println("REVIERTO TRANSACCION");
                con.rollback();
            } catch (Exception e2) {
                System.out.println("ERROR AL REVERTIR: " + e2.getMessage());
            }
        }
    }

    @Override
    public List<Producto> listarTodos() {
        List<Producto> productos = new ArrayList<>();
        Connection con = Conexion.conectar();
        try {
            String query = "SELECT * FROM Producto";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getInt("idProducto"),
                    rs.getString("nombreProducto"),
                    rs.getDouble("precioProducto"),
                    rs.getInt("stockProducto")
                );
                productos.add(producto);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR PRODUCTOS");
            e.printStackTrace();
        }
        return productos;
    }

 
    @Override
    public Producto mostrarPorId(int id) {
        Producto producto = null;
        Connection con = Conexion.conectar();
        try {
            String query = "SELECT * FROM Producto WHERE idProducto = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                producto = new Producto(
                    rs.getInt("idProducto"),
                    rs.getString("nombreProducto"),
                    rs.getDouble("precioProducto"),
                    rs.getInt("stockProducto")
                );
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL MOSTRAR PRODUCTO POR ID");
            e.printStackTrace();
        }
        return producto;
    }
    
    
    
    
}
    

