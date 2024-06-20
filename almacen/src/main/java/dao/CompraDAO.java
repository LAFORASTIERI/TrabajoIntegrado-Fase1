package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CajaAhorro;
import model.Compra;
import model.Producto;
import model.Proveedor;
import util.Conexion;

public class CompraDAO implements IAlmacenDAO<Compra> {

	   @Override
	    public void insertar(Compra compra) {
	        Connection con = Conexion.conectar();
	        try {
	            con.setAutoCommit(false);

	   
	            if (!Proveedor.existeProveedor(con, compra.getIdProveedor())) {
	                System.out.println("ERROR: El proveedor no existe. Dar de alta.");
	                return;
	            }

	   
	            if (!Producto.existeProducto(con, compra.getIdProducto())) {
	                System.out.println("ERROR: El producto no existe. Dar de alta.");
	                return;
	            }

	      
	            if (!CajaAhorro.saldoSuficiente(con, compra.getPrecioCompra())) {
	                System.out.println("ERROR: Saldo insuficiente.");
	                return;
	            }

	            // Registrar la compra
	            String queryCompra = "INSERT INTO Compra (fecha, idProveedor, idProducto, cantidadCompra, precioCompra) VALUES (?, ?, ?, ?, ?)";
	            PreparedStatement stmtCompra = con.prepareStatement(queryCompra);
	            stmtCompra.setDate(1, compra.getFecha());
	            stmtCompra.setInt(2, compra.getIdProveedor());
	            stmtCompra.setInt(3, compra.getIdProducto());
	            stmtCompra.setInt(4, compra.getCantidadCompra());
	            stmtCompra.setDouble(5, compra.getPrecioCompra());
	            stmtCompra.execute();
	            System.out.println("Compra registrada correctamente.");

	            // Actualizar saldo y stock
	            CajaAhorro.actualizarSaldoCompra(con, compra.getPrecioCompra()*compra.getCantidadCompra());
	            Producto.actualizarStockProducto(con, compra.getIdProducto(), compra.getCantidadCompra());

	            con.commit();
	            stmtCompra.close();
	            con.close();
	        } catch (SQLException e) {
	            System.out.println("ERROR AL REGISTRAR COMPRA");
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

	            // Obtener la compra para revertir sus efectos
	            Compra compra = mostrarPorId(id);
	            if (compra == null) {
	                System.out.println("La compra no existe.");
	                return;
	            }

	            // Eliminar la compra
	            String query = "DELETE FROM Compra WHERE idCompra = ?";
	            PreparedStatement stmt = con.prepareStatement(query);
	            stmt.setInt(1, compra.getIdCompra());
	            stmt.executeUpdate();
	            System.out.println("Compra eliminada correctamente.");

	            // Revertir el saldo en la caja de ahorro
	            CajaAhorro.reversionCompraSaldo(con, compra.getPrecioCompra() * compra.getCantidadCompra());

	            // Disminuir el stock del producto vendido
	            Producto.disminuirStockProducto(con, compra.getIdProducto(), compra.getCantidadCompra());

	            con.commit();
	            stmt.close();
	            con.close();
	        } catch (SQLException e) {
	            System.out.println("Error al eliminar la compra: " + e.getMessage());
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


	    @Override
	    public List<Compra> listarTodos() {
	        List<Compra> compras = new ArrayList<>();
	        Connection con = Conexion.conectar();
	        try {
	            String query = "SELECT c.idCompra, c.fecha, c.cantidadCompra, c.precioCompra, " +
	                           "p.nombreProveedor, pr.nombreProducto " +
	                           "FROM Compra c " +
	                           "JOIN Proveedor p ON c.idProveedor = p.idProveedor " +
	                           "JOIN Producto pr ON c.idProducto = pr.idProducto";
	            PreparedStatement stmt = con.prepareStatement(query);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                Compra compra = new Compra(
	                        rs.getInt("idCompra"),
	                        rs.getDate("fecha"),
	                        rs.getString("nombreProveedor"),
	                        rs.getString("nombreProducto"),
	                        rs.getInt("cantidadCompra"),
	                        rs.getDouble("precioCompra")
	                );
	                compras.add(compra);
	            }
	            rs.close();
	            stmt.close();
	            con.close();
	        } catch (SQLException e) {
	            System.out.println("ERROR AL LISTAR COMPRAS");
	            e.printStackTrace();
	        }
	        return compras;
	    }


	    @Override
	    public Compra mostrarPorId(int id) {
	        Compra compra = null;
	        Connection con = Conexion.conectar();
	        try {
	            String query = "SELECT * FROM Compra WHERE idCompra = ?";
	            PreparedStatement stmt = con.prepareStatement(query);
	            stmt.setInt(1, id);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                compra = new Compra(
	                        rs.getInt("idCompra"),
	                        rs.getDate("fecha"),
	                        rs.getInt("idProveedor"),
	                        rs.getInt("idProducto"),
	                        rs.getInt("cantidadCompra"),
	                        rs.getDouble("precioCompra")
	                );
	            }
	            rs.close();
	            stmt.close();
	            con.close();
	        } catch (SQLException e) {
	            System.out.println("ERROR AL OBTENER COMPRA POR ID");
	            e.printStackTrace();
	        }
	        return compra;
	    }

	    

	}



