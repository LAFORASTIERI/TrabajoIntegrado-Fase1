package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Proveedor;
import util.Conexion;

public class ProveedorDAO implements IAlmacenDAO<Proveedor> {

    @Override
    public void insertar(Proveedor proveedor) {
        Connection con = Conexion.conectar();
        try {
            con.setAutoCommit(false);
            String query = "INSERT INTO Proveedor (idProveedor, cuit, nombreProveedor, email, telefono) VALUES (?,?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,proveedor.getIdProveedor());
            stmt.setString(2, proveedor.getCuit());
            stmt.setString(3, proveedor.getNombreProveedor());
            stmt.setString(4, proveedor.getEmail());
            stmt.setString(5, proveedor.getTelefono());
            stmt.execute();
            System.out.println("Proveedor agregado correctamente.");
            con.commit();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL INSERTAR PROVEEDOR");
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
            String query = "DELETE FROM Proveedor WHERE idProveedor = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Proveedor eliminado correctamente.");
            con.commit();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL BORRAR PROVEEDOR");
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
    public List<Proveedor> listarTodos() {
        List<Proveedor> proveedores = new ArrayList<>();
        Connection con = Conexion.conectar();
        try {
            String query = "SELECT * FROM Proveedor";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                    rs.getString("cuit"),
                    rs.getString("nombreProveedor"),
                    rs.getString ("email"),
                    rs.getString("telefono")
                );
                proveedores.add(proveedor);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR PROVEEDORES");
            e.printStackTrace();
        }
        return proveedores;
    }



    @Override
    public Proveedor mostrarPorId(int id) {
        Proveedor proveedor = null;
        Connection con = Conexion.conectar();
        try {
            String query = "SELECT * FROM Proveedor WHERE idProveedor = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                proveedor = new Proveedor(
                		rs.getInt("idProveedor"),
                		rs.getString("cuit"),
                        rs.getString("nombreProveedor"),
                        rs.getString ("email"),
                        rs.getString("telefono")
                );
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL MOSTRAR PROVEEDOR POR ID");
            e.printStackTrace();
        }
        return proveedor;
    }


    
	
}
