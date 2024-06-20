package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import util.Conexion;

public class ClienteDAO implements IAlmacenDAO<Cliente> {

    @Override
    public void insertar(Cliente cliente) {
        Connection con = Conexion.conectar();
        try {
            con.setAutoCommit(false);
            String query = "INSERT INTO Cliente (idCliente, nombre, apellido, email, telefono) VALUES (?,?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, cliente.getIdCliente());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellido());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getTelefono());
            stmt.execute();
            System.out.println("Cliente agregado correctamente.");
            con.commit();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL INSERTAR CLIENTE");
            e.printStackTrace();
            try {
                System.out.println("REVIERTO TRANSACCION");
                con.rollback();
            } catch (Exception e2) {
                System.out.println("ERROR AL REVERTIR: " + e2.getMessage());
            }
        }
    }

    public void borrar(int id) {
        Connection con = Conexion.conectar();
        try {
            con.setAutoCommit(false);
            String query = "DELETE FROM Cliente WHERE idCliente = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Cliente eliminado correctamente.");
            con.commit();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL BORRAR CLIENTE");
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
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        Connection con = Conexion.conectar();
        try {
            String query = "SELECT * FROM Cliente";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("idCliente"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getString("telefono")
                );
                clientes.add(cliente);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR CLIENTES");
            e.printStackTrace();
        }
        return clientes;
    }


    @Override
    public Cliente mostrarPorId(int id) {
        Cliente cliente = null;
        Connection con = Conexion.conectar();
        try {
            String query = "SELECT * FROM Cliente WHERE idCliente = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(
                    rs.getInt("idCliente"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getString("telefono"));
             
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL MOSTRAR CLIENTE POR ID");
            e.printStackTrace();
        }
        return cliente;
    }
    

}

