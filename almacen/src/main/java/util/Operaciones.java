package util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import dao.ClienteDAO;
import dao.CompraDAO;
import dao.EmpleadoDAO;
import dao.ProductoDAO;
import dao.ProveedorDAO;
import dao.VentaDAO;
import model.CajaAhorro;
import model.Cliente;
import model.Compra;
import model.Empleado;
import model.Producto;
import model.Proveedor;
import model.Venta;

public class Operaciones {
	
	 /*--------------------OPERACIONES CLIENTES---------------------*/

    public static void altaCliente(Scanner scanner) {
        System.out.println("\nRealizando alta de cliente...");
        Cliente cliente = new Cliente();
        System.out.println("Ingrese id del cliente:");
        cliente.setIdCliente(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Ingrese nombre del cliente:");
        cliente.setNombre(scanner.nextLine());
        System.out.println("Ingrese apellido del cliente:");
        cliente.setApellido(scanner.nextLine());
        System.out.println("Ingrese telefono del cliente:");
        cliente.setTelefono(scanner.nextLine());
        System.out.println("Ingrese email del cliente:");
        cliente.setEmail(scanner.nextLine());
        ClienteDAO IAlmacenDAO = new ClienteDAO ();
        IAlmacenDAO.insertar(cliente);
    }

    public static void bajaCliente(Scanner scanner) {
        System.out.println("\nRealizando baja de cliente...");
        System.out.println("Ingrese el ID del cliente a borrar:");
        int id = scanner.nextInt();
        ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.borrar(id);
    }

    public static void buscarCliente(Scanner scanner) {
        System.out.println("\nRealizando búsqueda de cliente...");
        System.out.println("Ingrese el ID del cliente a buscar:");
        int id = scanner.nextInt();
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.mostrarPorId(id);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Apellido: " + cliente.getApellido());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Teléfono: " + cliente.getTelefono());
        } else {
            System.out.println("No se encontró ningún cliente con el ID ingresado.");
        }
    }

    
    /*--------------------OPERACIONES PROVEEDOR---------------------*/
    
    public static void altaProveedor(Scanner scanner) {
        System.out.println("\nRealizando alta de proveedor...");
        Proveedor proveedor = new Proveedor();
        System.out.println("Ingrese Id del proveedor:");
        proveedor.setIdProveedor(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Ingrese CUIT del proveedor:");
        proveedor.setCuit(scanner.nextLine());
        System.out.println("Ingrese nombre del proveedor:");
        proveedor.setNombreProveedor(scanner.nextLine());
        System.out.println("Ingrese email del proveedor:");
        proveedor.setEmail(scanner.nextLine());
        System.out.println("Ingrese telefono del proveedor:");
        proveedor.setTelefono(scanner.nextLine());
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedorDAO.insertar(proveedor);
        
    }

    public static void bajaProveedor(Scanner scanner) {
        System.out.println("\nRealizando baja de proveedor...");
        System.out.println("Ingrese el ID del proveedor a borrar:");
        int id = scanner.nextInt();
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedorDAO.borrar(id);
    }

    public static void buscarProveedor(Scanner scanner) {
        System.out.println("\nRealizando búsqueda de proveedor...");
        System.out.println("Ingrese el ID del proveedor a buscar:");
        int id = scanner.nextInt();
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        Proveedor proveedor = proveedorDAO.mostrarPorId(id);
        if (proveedor != null) {
            System.out.println("Proveedor encontrado:");
            System.out.println("ID: " + proveedor.getIdProveedor());
            System.out.println("CUIT: " + proveedor.getCuit());
            System.out.println("Nombre: " + proveedor.getNombreProveedor());
            System.out.println("Email: " + proveedor.getEmail());
            System.out.println("Teléfono: " + proveedor.getTelefono());
        } else {
            System.out.println("No se encontró ningún proveedor con el ID ingresado.");
        }
    }
    
    /*--------------------OPERACIONES COMPRAS---------------------*/

    		public static void altaCompra(Scanner scanner) {
    	        System.out.println("\nRealizando alta de compra...");
    	        Compra compra = new Compra();
    	       
    	        System.out.println("Ingrese la fecha (YYYY-MM-DD):");
    	        scanner.nextLine(); 
    	        String fechaStr = scanner.nextLine();
    	        try {
    	            Date fecha = Date.valueOf(fechaStr); //convierto la cadena en tipo dato.
    	            compra.setFecha(fecha);
    	        } catch (IllegalArgumentException e) {
    	            System.out.println("Formato de fecha incorrecto. Por favor ingrese la fecha en el formato YYYY-MM-DD.");
    	            return;
    	        }

    	        System.out.println("Ingrese el ID del proveedor:");
    	        compra.setIdProveedor(scanner.nextInt());

    	        System.out.println("Ingrese el ID del producto:");
    	        compra.setIdProducto(scanner.nextInt());

    	        System.out.println("Ingrese la cantidad de la compra:");
    	        compra.setCantidadCompra(scanner.nextInt());

    	        System.out.println("Ingrese el precio de la compra:");
    	        compra.setPrecioCompra(scanner.nextDouble());
    	        scanner.nextLine();

    	        CompraDAO compraDAO = new CompraDAO();
    	        compraDAO.insertar(compra);
    	    }
        

    public static void bajaCompra(Scanner scanner) {
        System.out.println("\nRealizando baja de compra...");
        System.out.println("Ingrese el ID de la compra a eliminar:");
        int idCompra = scanner.nextInt();
        scanner.nextLine();
        
        CompraDAO compraDAO = new CompraDAO();
        compraDAO.borrar(idCompra);
    }

    public static void buscarCompra(Scanner scanner) {
    	 System.out.println("\nRealizando búsqueda de compra...");
         System.out.println("Ingrese el ID de la compra a buscar:");
         int idCompra = scanner.nextInt();
         scanner.nextLine(); 

         CompraDAO compraDAO = new CompraDAO();
         Compra compra = compraDAO.mostrarPorId(idCompra);

         if (compra != null) {
             System.out.println(compra.toString());
         } else {
             System.out.println("Compra no encontrada.");
         }
    }
    
    
    /*--------------------OPERACIONES VENTAS---------------------*/

    public static void altaVenta(Scanner scanner) {
        System.out.println("\nRealizando alta de venta...");
        Venta venta = new Venta();

        System.out.println("Ingrese la fecha (YYYY-MM-DD):");
        scanner.nextLine(); 
        String fechaStr = scanner.nextLine();
        try {
            Date fecha = Date.valueOf(fechaStr);
            venta.setFecha(fecha);
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de fecha incorrecto. Por favor ingrese la fecha en el formato YYYY-MM-DD.");
            return;
        }

        System.out.println("Ingrese el ID del producto:");
        venta.setIdProducto(scanner.nextInt());

        System.out.println("Ingrese el ID del cliente:");
        venta.setIdCliente(scanner.nextInt());

        System.out.println("Ingrese la cantidad de la venta:");
        venta.setCantidadVenta(scanner.nextInt());

        System.out.println("Ingrese el precio de la venta:");
        venta.setPrecioVenta(scanner.nextDouble());
        scanner.nextLine();
        
        VentaDAO ventaDAO = new VentaDAO();
        ventaDAO.insertar(venta);
    }

    

    public static void bajaVenta(Scanner scanner) {
    	System.out.println("\nRealizando baja de Ventas...");
        System.out.println("Ingrese el ID de la venta a eliminar:");
        int idVenta = scanner.nextInt();
        scanner.nextLine();
        
        VentaDAO ventaDAO = new VentaDAO();
        ventaDAO.borrar(idVenta);
    }
    
    public static void buscarVenta(Scanner scanner) {
   	 System.out.println("Realizando búsqueda de Ventas...");
        System.out.println("Ingrese el ID de la venta a buscar:");
        int idVenta = scanner.nextInt();
        scanner.nextLine(); 

        VentaDAO ventaDAO = new VentaDAO();
        Venta venta = ventaDAO.mostrarPorId(idVenta);

        if (venta != null) {
            System.out.println(venta.toString());
        } else {
            System.out.println("Compra no encontrada.");
        }
   }

    
    /*--------------------OPERACIONES PRODUCTOS---------------------*/
    
    public static void altaProducto(Scanner scanner) {
        System.out.println("\nRealizando alta de producto...");
        Producto producto = new Producto();
        System.out.println("Ingrese ID del producto:");
        producto.setIdProducto(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Ingrese nombre del producto:");
        producto.setNombreProducto(scanner.nextLine());
        System.out.println("Ingrese precio del producto:");
        producto.setPrecioProducto(scanner.nextDouble());
        System.out.println("Ingrese stock del producto:");
        producto.setStockProducto(scanner.nextInt());

        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.insertar(producto);
    }

    public static void bajaProducto(Scanner scanner) {
        System.out.println("\nRealizando baja de producto...");
        System.out.println("Ingrese el ID del producto a eliminar:");
        int idProducto = scanner.nextInt();
        scanner.nextLine(); 

        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.borrar(idProducto);    }

    public static void buscarProducto(Scanner scanner) {
        System.out.println("\nRealizando búsqueda del producto...");
        System.out.println("Ingrese el ID del producto a buscar:");
        int id = scanner.nextInt();
        ProductoDAO productoDAO = new ProductoDAO();
        Producto producto = productoDAO.mostrarPorId(id);
        if (producto != null) {
           System.out.println("Producto encontrado:");
           System.out.println("ID: " + producto.getIdProducto());
           System.out.println("Nombre: " + producto.getNombreProducto());
           System.out.println("Precio: " + producto.getPrecioProducto());
           System.out.println("Stock: " + producto.getStockProducto());
   
          
        } else {
            System.out.println("No se encontró ningún producto con el ID ingresado.");
        }
    }

    
    /*--------------------OPERACIONES EMPLEADOS---------------------*/
    
    public static void altaEmpleado(Scanner scanner) {
        System.out.println("\nRealizando alta de empleado...");
        Empleado empleado = new Empleado();
        scanner.nextLine();
        System.out.println("Ingrese usuario del empleado:");
        empleado.setUsuario(scanner.nextLine());
        System.out.println("Ingrese contraseña del empleado:");
        empleado.setContraseña(scanner.nextLine());
        System.out.println("Ingrese DNI del empleado:");
        empleado.setDni(scanner.nextLine());
        System.out.println("Ingrese nombre del empleado:");
        empleado.setNombre(scanner.nextLine());    
        System.out.println("Ingrese apellido del empleado:");
        empleado.setApellido(scanner.nextLine());    
        System.out.println("Ingrese telefono del empleado:");
        empleado.setTelefono(scanner.nextLine());   
        System.out.println("Ingrese email del empleado:");
        empleado.setEmail(scanner.nextLine());
        
        
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        empleadoDAO.insertar(empleado);
        
    }

    public static void bajaEmpleado(Scanner scanner) {
        System.out.println("\nRealizando baja de empleado...");
        System.out.println("Ingrese el ID del empleado a borrar:");
        int id = scanner.nextInt();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        empleadoDAO.borrar(id);
    }

    public static void buscarEmpleado(Scanner scanner) {
        System.out.println("\nRealizando búsqueda de empleado...");
        System.out.println("Ingrese el ID del empleado a buscar:");
        int id = scanner.nextInt();
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        Empleado empleado = empleadoDAO.mostrarPorId(id);
        if (empleado != null) {
           System.out.println("Empleado encontrado:");
           System.out.println("ID: " + empleado.getIdEmpleado());
           System.out.println("DNI: " + empleado.getDni());
           System.out.println("Nombre: " + empleado.getNombre());
           System.out.println("Apellido: " + empleado.getApellido());
           System.out.println("Teléfono: " + empleado.getTelefono());
           System.out.println("Email: " + empleado.getEmail());
           System.out.println("Usuario: " + empleado.getUsuario());
           System.out.println("Contraseña:"+ empleado.getContraseña());

           
        } else {
            System.out.println("No se encontró ningún empleado con el ID ingresado.");
        }
    }
    
    /*--------------------OPERACIONES DE IMPRESION---------------------*/
    
    public static void listaProveedores(Scanner scanner) {
    	System.out.println("\nListado de Proveedores:");
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        List<Proveedor> proveedores = proveedorDAO.listarTodos();

        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            System.out.printf("%-15s %-30s %-30s %-15s\n", "CUIT", "Nombre", "Email", "Teléfono");
            System.out.println("---------------------------------------------------------------------------");
            for (Proveedor proveedor : proveedores) {
                System.out.printf("%-15s %-30s %-30s %-15s\n",
                        proveedor.getCuit(), proveedor.getNombreProveedor(),
                        proveedor.getEmail(), proveedor.getTelefono());
            }
        }
    }
    
    public static void listaClientes(Scanner scanner) {
        System.out.println("\nListado de Clientes:");
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.listarTodos();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.printf("%-15s %-15s %-15s %-30s %-30s\n", "ID", "Nombre", "Apellido", "Email", "Teléfono");
            System.out.println("---------------------------------------------------------------------------");
            for (Cliente cliente : clientes) {
                System.out.printf("%-15s %-15s %-15s %-30s %-30s\n",
                        cliente.getIdCliente(), cliente.getNombre(),cliente.getApellido(),
                        cliente.getEmail(), cliente.getTelefono());
            }
        }
    }
    
    
    public static void listaProductos(Scanner scanner) {
        System.out.println("\nListado de Productos:");
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.listarTodos();

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.printf("%-15s %-30s %-15s %-15s\n", "ID", "Nombre", "Precio", "Stock");
            System.out.println("------------------------------------------------------------");
            for (Producto producto : productos) {
                System.out.printf("%-15s %-30s %-15.2f %-15s\n",
                        producto.getIdProducto(), producto.getNombreProducto(),
                        producto.getPrecioProducto(), producto.getStockProducto());
            }
        }
    }
    
    public static void listaEmpleados(Scanner scanner) {
        System.out.println("\nListado de Empleados:");
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        List<Empleado> empleados = empleadoDAO.listarTodos();

        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            System.out.printf("%-15s %-20s %-20s %-20s %-20s\n", "ID", "Usuario", "Email", "DNI", "Teléfono");
            System.out.println("------------------------------------------------------------");
            for (Empleado empleado : empleados) {
                System.out.printf("%-15s %-20s %-20s %-20s %-20s\n",
                        empleado.getIdEmpleado(), empleado.getUsuario(),
                        empleado.getEmail(), empleado.getDni(), empleado.getTelefono());
            }
        }
    }
    
    public static void listaVentas(Scanner scanner) {
        System.out.println("\nListado de Ventas:");
        VentaDAO ventaDAO = new VentaDAO();
        List<Venta> ventas = ventaDAO.listarTodos();

        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            System.out.printf("%-10s %-20s %-20s %-20s %-15s %-15s %-15s\n", "ID Venta", "Fecha", "Cliente", "Producto", "Cantidad", "Precio", "total");
            System.out.println("---------------------------------------------------------------------------------------------");
            for (Venta venta : ventas) {
            	double total = venta.getCantidadVenta()*venta.getPrecioVenta();
                System.out.printf("%-10d %-20s %-20s %-20s %-15d %-15.2f %-15.2f\n",
                        venta.getIdVenta(), venta.getFecha(), venta.getNombreCliente(),
                        venta.getNombreProducto(), venta.getCantidadVenta(), venta.getPrecioVenta(),total);
            }
        }
    }
    
    public static void listaCompras(Scanner scanner) {
        System.out.println("\nListado de Compras:");
        CompraDAO compraDAO = new CompraDAO();
        List<Compra> compras = compraDAO.listarTodos();
         
        if (compras.isEmpty()) {
            System.out.println("No hay compras registradas.");
        } else {
            System.out.printf("%-10s %-20s %-20s %-20s %-15s %-15s %-15s\n", "ID", "Fecha", "Proveedor", "Producto", "Cantidad", "Precio", "Total");
            System.out.println("-------------------------------------------------------------------------------------------");
            for (Compra compra : compras) {
                double total=compra.getCantidadCompra()*compra.getPrecioCompra();
				System.out.printf("%-10d %-20s %-20s %-20s %-15d %-15.2f %-15.2f\n",
                        compra.getIdCompra(), compra.getFecha(), compra.getNombreProveedor(),
                        compra.getNombreProducto(), compra.getCantidadCompra(), compra.getPrecioCompra(),total);
            }
        }
 
     } 
    
    public static void estadoCuenta (Scanner scanner) {
    	Connection con = Conexion.conectar();
        try {
            double saldoActual = CajaAhorro.obtenerSaldoActual(con);
            System.out.printf("\nEl saldo actual de la caja de ahorro es: $%.2f\n", saldoActual);
        } catch (SQLException e) {
            System.out.println("\nError al obtener el saldo de la cuenta: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("\nError al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    	
    }
    
}
    
	

