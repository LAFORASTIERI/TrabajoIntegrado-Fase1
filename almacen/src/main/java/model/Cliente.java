package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente extends Persona {
    private int idCliente;
    private String email;
    
     public Cliente (int idCliente, String nombre, String apellido, String email, String telefono) {
	  super (nombre, apellido, telefono);
	  this.idCliente = idCliente;
	  this.email = email;
  }
  

	public Cliente() {
	}

	public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente [idCliente=" + idCliente + ", email=" + email + ", " + super.toString() + "]";
    }
    
    
    public static boolean existeCliente(Connection con, int idCliente) {
        boolean existe = false;
        try {
            String query = "SELECT COUNT(*) FROM Cliente WHERE idCliente = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("ERROR AL VERIFICAR EXISTENCIA DE CLIENTE");
            e.printStackTrace();
        }
        return existe;
    }
}