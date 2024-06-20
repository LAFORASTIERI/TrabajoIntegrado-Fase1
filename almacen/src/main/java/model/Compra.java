package model;
import java.sql.Date;

public class Compra {
    private int idCompra;
    private Date fecha;
    private int idProveedor;
    private int idProducto;
    private int cantidadCompra;
    private double precioCompra;
    private String nombreProveedor;
    private String nombreProducto;
    
    public Compra () {}

    public Compra(int idCompra, Date fecha, int idProveedor, int idProducto, int cantidadCompra, double precioCompra) {
        this.idCompra = idCompra;
        this.fecha = fecha;
        this.idProveedor = idProveedor;
        this.idProducto = idProducto;
        this.cantidadCompra = cantidadCompra;
        this.precioCompra = precioCompra;
    }
    
 // Constructor para el join en CompraDAO
    public Compra(int idCompra, Date fecha, String nombreProveedor, String nombreProducto, int cantidadCompra, double precioCompra) {
        this.idCompra = idCompra;
        this.fecha = fecha;
        this.nombreProveedor = nombreProveedor;
        this.nombreProducto = nombreProducto;
        this.cantidadCompra = cantidadCompra;
        this.precioCompra = precioCompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadCompra() {
        return cantidadCompra;
    }

    public void setCantidadCompra(int cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }
    

    public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	@Override
    public String toString() {
        return "Compra [idCompra=" + idCompra + ", fecha=" + fecha + ", idProveedor=" + idProveedor + ", idProducto=" + idProducto
                + ", cantidadCompra=" + cantidadCompra + ", precioCompra=" + precioCompra + "]";
    }
}