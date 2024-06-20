package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Proveedor {
    private int idProveedor;
    private String cuit;
    private String nombreProveedor;
    private String email;
    private String telefono;
    
    public Proveedor () {}
    
    public Proveedor (int idProveedor, String cuit, String nombreProveedor, String email, String telefono ) {
    	this.idProveedor=idProveedor;
    	this.cuit=cuit;
    	this.nombreProveedor=nombreProveedor;
    	this.email=email;
    	this.telefono=telefono;
    }
    
public Proveedor (String cuit, String nombreProveedor, String email, String telefono ) {
    	this.cuit=cuit;
    	this.nombreProveedor=nombreProveedor;
    	this.email=email;
    	this.telefono=telefono;
    }
    
    
    

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Proveedor [idProveedor=" + idProveedor + ", cuit=" + cuit + ", nombreProveedor=" + nombreProveedor
				+ ", email=" + email + ", telefono=" + telefono + "]";
	}

    
    public static boolean existeProveedor(Connection con, int idProveedor) throws SQLException {
        String query = "SELECT COUNT(*) FROM Proveedor WHERE idProveedor = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, idProveedor);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        boolean existe = rs.getInt(1) > 0;
        rs.close();
        stmt.close();
        return existe;
    }
    
	
	}
    
    


