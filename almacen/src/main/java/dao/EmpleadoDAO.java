package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Empleado;
import util.Conexion;

public class EmpleadoDAO implements IAlmacenDAO<Empleado> {

    @Override
    public void insertar(Empleado empleado) {
        Connection con = Conexion.conectar();
        try {
            con.setAutoCommit(false);
            String query = "INSERT INTO Empleado (usuario, contraseña, dni, nombre, apellido, telefono, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, empleado.getUsuario());
            stmt.setString(2, empleado.getContraseña());
            stmt.setString(3, empleado.getDni());
            stmt.setString(4, empleado.getNombre());
            stmt.setString(5, empleado.getApellido());
            stmt.setString(6, empleado.getTelefono());
            stmt.setString(7, empleado.getEmail());
            stmt.execute();
            System.out.println("Empleado agregado correctamente.");
            con.commit();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL INSERTAR EMPLEADO");
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
            String query = "DELETE FROM Empleado WHERE idEmpleado = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Empleado eliminado correctamente.");
            con.commit();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL BORRAR EMPLEADO");
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
    public List<Empleado> listarTodos() {
        List<Empleado> empleados = new ArrayList<>();
        Connection con = Conexion.conectar();
        try {
            String query = "SELECT * FROM Empleado";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Empleado empleado = new Empleado(
                	rs.getInt("idEmpleado"),
                	rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),       
                    rs.getString("email"),
                    rs.getString("usuario"),
                    rs.getString("contraseña"));
                empleados.add(empleado);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR EMPLEADOS");
            e.printStackTrace();
        }
        return empleados;
    }


    @Override
    public Empleado mostrarPorId(int id) {
        Empleado empleado = null;
        Connection con = Conexion.conectar();
        try {
            String query = "SELECT * FROM Empleado WHERE idEmpleado = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	empleado = new Empleado(
            			rs.getInt("idEmpleado"),
            			rs.getString("dni"),
            			rs.getString("nombre"),
            			rs.getString("apellido"),
            			rs.getString ("telefono"),
            			rs.getString("email"),
            			rs.getString("usuario"),
            			rs.getString("contraseña")
            			
            			
            			
            			
                    );
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERROR AL MOSTRAR EMPLEADO POR ID");
            e.printStackTrace();
        }
        return empleado;
    }


}
