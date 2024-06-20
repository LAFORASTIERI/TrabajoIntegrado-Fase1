package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Conexion;

public class CajaAhorro {
	private int idCajaAhorro;
    private double saldo=100000;

    public CajaAhorro(double saldo, int idCajaAhorro) {
        this.saldo = saldo;
        this.idCajaAhorro = idCajaAhorro;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void aumentarSaldo(double monto) {
        this.saldo += monto;
    }

    public void disminuirSaldo(double monto) {
        this.saldo -= monto;
    }

	public int getIdCajaAhorro() {
		return idCajaAhorro;
	}

	public void setIdCajaAhorro(int idCajaAhorro) {
		this.idCajaAhorro = idCajaAhorro;
	}

	@Override
	public String toString() {
		return "CajaAhorro [idCajaAhorro=" + idCajaAhorro + ", saldo=" + saldo + "]";
	}

	public static void actualizarSaldoCompra(Connection con, double monto) throws SQLException {
        String query = "UPDATE CajaAhorro SET saldo = saldo - ? WHERE idCajaAhorro = 1";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setDouble(1, monto);
        stmt.executeUpdate();
        System.out.println("Saldo de la caja de ahorro actualizado por compra.");
        stmt.close();
    }
	
	 public static boolean saldoSuficiente(Connection con, double monto) throws SQLException {
	        String query = "SELECT saldo FROM CajaAhorro WHERE idCajaAhorro = 1";
	        PreparedStatement stmt = con.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();
	        rs.next();
	        boolean suficiente = rs.getDouble(1) >= monto;
	        rs.close();
	        stmt.close();
	        return suficiente;
	    }
	 
	 public static void actualizarSaldoVenta(Connection con, double monto) throws SQLException {
	        String query = "UPDATE CajaAhorro SET saldo = saldo + ? WHERE idCajaAhorro = 1";
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setDouble(1, monto);
	        stmt.executeUpdate();
	        System.out.println("Saldo de la caja de ahorro actualizado por venta.");
	        stmt.close();
	    }
	 
	    public static void reversionVentaSaldo(double monto) {
	        Connection con = Conexion.conectar();
	        try {
	            String query = "UPDATE CajaAhorro SET saldo = saldo - ? WHERE idCajaAhorro = 1";
	            PreparedStatement stmt = con.prepareStatement(query);
	            stmt.setDouble(1, monto);
	            stmt.executeUpdate();
	            System.out.println("Saldo de la caja de ahorro actualizado después de revertir la venta.");
	            stmt.close();
	            con.close();
	        } catch (SQLException e) {
	            System.out.println("Error al aumentar el saldo de la caja de ahorro: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	    
	    public static void reversionCompraSaldo(Connection con, double monto) throws SQLException {
	        String query = "UPDATE CajaAhorro SET saldo = saldo + ? WHERE idCajaAhorro = ?";
	        PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setDouble(1, monto);
	        stmt.setInt(2, 1); 
	        stmt.executeUpdate();
	        System.out.println("Saldo de la caja de ahorro actualizado después de revertir una compra.");
	        stmt.close();
	    }
	    
	    public static double obtenerSaldoActual(Connection con) throws SQLException {
	        String query = "SELECT saldo FROM CajaAhorro WHERE idCajaAhorro = 1";
	        PreparedStatement stmt = con.prepareStatement(query);
	        ResultSet rs = stmt.executeQuery();
	        double saldo = 0;
	        if (rs.next()) {
	            saldo = rs.getDouble("saldo");
	        }
	        rs.close();
	        stmt.close();
	        return saldo;
	    }
	}

