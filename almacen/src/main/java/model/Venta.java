package model;
import java.util.Date;

public class Venta {
    private int idVenta;
    private Date fecha;
    private int idProducto;
    private int idCliente;
    private int cantidadVenta;
    private double precioVenta;
    private String nombreCliente;
    private String nombreProducto;
    
    public Venta () {}

    public Venta(int idVenta, Date fecha, int idProducto, int idCliente, int cantidadVenta, double precioVenta) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.idProducto = idProducto;
        this.idCliente = idCliente;
        this.cantidadVenta = cantidadVenta;
        this.precioVenta = precioVenta;
    }
    
    // Constructor para el Join en VentaDAO
    public Venta(int idVenta, Date fecha, String nombreCliente, String nombreProducto, int cantidadVenta, double precioVenta) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.nombreProducto = nombreProducto;
        this.cantidadVenta = cantidadVenta;
        this.precioVenta = precioVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(int cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
    

    public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	@Override
    public String toString() {
        return "Venta [idVenta=" + idVenta + ", fecha=" + fecha + ", idProducto=" + idProducto + ", idCliente=" + idCliente
                + ", cantidadVenta=" + cantidadVenta + ", precioVenta=" + precioVenta + "]";
    }
}