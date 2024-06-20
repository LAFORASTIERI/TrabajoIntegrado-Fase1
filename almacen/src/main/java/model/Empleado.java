package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import util.Conexion;

public class Empleado extends Persona {
    private int idEmpleado;
    private String usuario;
    private String contraseña;
    private String email;
    
    public Empleado () {}

    	public Empleado(int idEmpleado,String dni, String nombre, String apellido, String telefono, String email, String usuario, String contraseña) {
    	super (dni, nombre, apellido, telefono);
    	this.idEmpleado= idEmpleado;
    	this.usuario=usuario;
    	this.email = email;
      	this.contraseña = contraseña;
    }
    	

    	public Empleado (String usuario) {
    		this.usuario= usuario;
    	}
    	
 
    public int getIdEmpleado() {
			return idEmpleado;
		}

		public void setIdEmpleado(int idEmpleado) {
			this.idEmpleado = idEmpleado;
		}

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public String getContraseña() {
			return contraseña;
		}

		public void setContraseña(String contraseña) {
			this.contraseña = contraseña;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	@Override
    public String toString() {
        return "Empleado [idEmpleado=" + idEmpleado + ", usuario=" + usuario + ", contraseña=" + contraseña + ", " + super.toString() + "]";
    }
	
	
	public static boolean cambiarContraseña(String usuario, String contraseñaActual, Scanner scanner) {
        boolean cambioExitoso = false;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            con = Conexion.conectar();
            
            // Validar las credenciales
            if (!validarCredenciales(con, usuario, contraseñaActual)) {
                return false; // Credenciales no válidas
            }
            
            // Pedir nueva contraseña
            System.out.println("Ingrese su nueva contraseña:");
            String nuevaContraseña = scanner.nextLine();
            
            // Actualizar la contraseña en la base de datos
            String query = "UPDATE Empleado SET contraseña = ? WHERE usuario = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, nuevaContraseña);
            stmt.setString(2, usuario);
            int filasActualizadas = stmt.executeUpdate();
            
            if (filasActualizadas > 0) {
                cambioExitoso = true;
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cambiar la contraseña: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        return cambioExitoso;
    }
	
	
	 private static boolean validarCredenciales(Connection con, String usuario, String contraseña) throws SQLException { //validar credenciales al momento de cambiar la contraseña.
	        boolean credencialesValidas = false;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        
	        try {
	            String query = "SELECT contraseña FROM Empleado WHERE usuario = ?";
	            stmt = con.prepareStatement(query);
	            stmt.setString(1, usuario);
	            rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                String contraseñaDB = rs.getString("contraseña");
	                if (contraseña.equals(contraseñaDB)) {
	                    credencialesValidas = true;
	                }
	            }
	            
	        } finally {
	            // Cerrar recursos
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        }
	        
	        return credencialesValidas;
	    
	}
	 
	 public static boolean validarCredenciales(String usuario, String contraseña) { // validar para el inicio de sesión proveniente de menu
		    boolean credencialesValidas = false;
		    Connection con = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;
		    
		    try {
		        con = Conexion.conectar();
		        
		        String query = "SELECT contraseña FROM Empleado WHERE usuario = ?";
		        stmt = con.prepareStatement(query);
		        stmt.setString(1, usuario);
		        rs = stmt.executeQuery();
		        
		        if (rs.next()) {
		            String contraseñaDB = rs.getString("contraseña");
		            // Comparar la contraseña ingresada con la contraseña almacenada en la base de datos
		            if (contraseña.equals(contraseñaDB)) {
		                credencialesValidas = true;
		            }
		        }
		        
		    } catch (SQLException e) {
		        System.out.println("Error al validar las credenciales: " + e.getMessage());
		        e.printStackTrace();
		    } finally {
		        // Cerrar recursos
		        try {
		            if (rs != null) rs.close();
		            if (stmt != null) stmt.close();
		            if (con != null) con.close();
		        } catch (SQLException e) {
		            System.out.println("Error al cerrar recursos: " + e.getMessage());
		            e.printStackTrace();
		        }
		    }
		    
		    return credencialesValidas;
		}
}
