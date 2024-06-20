package util;

import java.util.Scanner;

import model.Empleado;

public class MenuOpciones {

    public static void mostrarMenuPrincipal() {
        System.out.println("\nIngrese opción a operar:");
        System.out.println("1. GESTIONAR CLIENTES");
        System.out.println("2. GESTIONAR PROVEEDORES");
        System.out.println("3. GESTIONAR COMPRAS");
        System.out.println("4. GESTIONAR VENTAS");
        System.out.println("5. GESTIONAR PRODUCTOS");
        System.out.println("6. GESTIONAR EMPLEADOS");
        System.out.println("7. CAMBIAR CONTRASEÑA");
        System.out.println("8. IMPRESIONES");
        System.out.println("99. Salir");
    }

    public static void gestionarClientes(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nGESTIONAR CLIENTES");
            System.out.println("1. Alta cliente");
            System.out.println("2. Baja cliente");
            System.out.println("3. Buscar cliente");
            System.out.println("0. Volver al menú principal\n");
            System.out.print("Ingrese opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    Operaciones.altaCliente(scanner);
                    break;
                case 2:
                    Operaciones.bajaCliente(scanner);
                    break;
                case 3:
                    Operaciones.buscarCliente(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void gestionarProveedores(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nGESTIONAR PROVEEDORES");
            System.out.println("1. Alta proveedor");
            System.out.println("2. Baja proveedor");
            System.out.println("3. Buscar proveedor");
            System.out.println("0. Volver al menú principal\n");
            System.out.print("Ingrese opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    Operaciones.altaProveedor(scanner);
                    break;
                case 2:
                    Operaciones.bajaProveedor(scanner);
                    break;
                case 3:
                    Operaciones.buscarProveedor(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void gestionarCompras(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nGESTIONAR COMPRAS");
            System.out.println("1. Alta compra");
            System.out.println("2. Baja compra");
            System.out.println("3. Buscar compra");
            System.out.println("0. Volver al menú principal\n");
            System.out.print("Ingrese opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    Operaciones.altaCompra(scanner);
                    break;
                case 2:
                    Operaciones.bajaCompra(scanner);
                    break;
                case 3:
                    Operaciones.buscarCompra(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void gestionarVentas(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nGESTIONAR VENTAS");
            System.out.println("1. Alta venta");
            System.out.println("2. Baja venta");
            System.out.println("3. Buscar venta");
            System.out.println("0. Volver al menú principal\n");
            System.out.print("Ingrese opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    Operaciones.altaVenta(scanner);
                    break;
                case 2:
                    Operaciones.bajaVenta(scanner);
                    break;
                case 3:
                    Operaciones.buscarVenta(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void gestionarProductos(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nGESTIONAR PRODUCTOS");
            System.out.println("1. Alta producto");
            System.out.println("2. Baja producto");
            System.out.println("3. Buscar producto");
            System.out.println("0. Volver al menú principal\n");
            System.out.print("Ingrese opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    Operaciones.altaProducto(scanner);
                    break;
                case 2:
                    Operaciones.bajaProducto(scanner);
                    break;
                case 3:
                    Operaciones.buscarProducto(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void gestionarEmpleados(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nGESTIONAR EMPLEADOS");
            System.out.println("1. Alta empleado");
            System.out.println("2. Baja empleado");
            System.out.println("3. Buscar empleado");
            System.out.println("0. Volver al menú principal\n");
            System.out.print("Ingrese opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    Operaciones.altaEmpleado(scanner);
                    break;
                case 2:
                    Operaciones.bajaEmpleado(scanner);
                    break;
                case 3:
                    Operaciones.buscarEmpleado(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }
    
    	
    	public static void impresiones(Scanner scanner) {
    		int opcion;
            do {
                System.out.println("\nIMPRESIONES");
                System.out.println("1. LISTA PROVEEDORES");
                System.out.println("2. LISTA CLIENTES");
                System.out.println("3. LISTA PRODUCTOS");
                System.out.println("4. LISTA EMPLEADOS");
                System.out.println("5. LISTA VENTAS");
                System.out.println("6. LISTA COMPRAS");
                System.out.println("7. ESTADO CUENTA");
                System.out.println("0. Volver al menú principal\n");
                System.out.print("Ingrese opción: ");
                
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        Operaciones.listaProveedores(scanner);
                        break;
                    case 2:
                        Operaciones.listaClientes(scanner);
                        break;
                    case 3:
                        Operaciones.listaProductos(scanner);
                        break;
                    case 4:
                        Operaciones.listaEmpleados(scanner);
                        break;
                    case 5:
                        Operaciones.listaVentas(scanner);
                        break;
                    case 6:
                        Operaciones.listaCompras(scanner);
                        break;
                    case 7:
                    	Operaciones.estadoCuenta(scanner);
                    case 0:
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (opcion != 0);
        }	
    	

    	
    public static void cambiarContraseña(Scanner scanner) {
         System.out.println("Realizando cambio de contraseña...");      
         System.out.println("Ingrese su usuario:");
         scanner.nextLine();
         String usuario = scanner.nextLine();
         System.out.println("Ingrese su contraseña actual:");
         String contraseñaActual = scanner.nextLine();   
         boolean cambioExitoso = Empleado.cambiarContraseña(usuario, contraseñaActual, scanner); 
            if (cambioExitoso) {
                System.out.println("Contraseña cambiada exitosamente.");
            } else {
                System.out.println("No se pudo cambiar la contraseña. Verifique sus credenciales.");
            }
        }
    
    
}
    
