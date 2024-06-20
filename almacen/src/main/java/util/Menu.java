package util;
import java.util.Scanner;

import model.Empleado;

public class Menu {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        

        boolean sesionIniciada = iniciarSesion(scanner);
        
        if (sesionIniciada) {

            int opcion;
            do {
                MenuOpciones.mostrarMenuPrincipal();
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        MenuOpciones.gestionarClientes(scanner);
                        break;
                    case 2:
                        MenuOpciones.gestionarProveedores(scanner);
                        break;
                    case 3:
                        MenuOpciones.gestionarCompras(scanner);
                        break;
                    case 4:
                        MenuOpciones.gestionarVentas(scanner);
                        break;
                    case 5:
                        MenuOpciones.gestionarProductos(scanner);
                        break;
                    case 6:
                        MenuOpciones.gestionarEmpleados(scanner);
                        break;
                    case 7:
                        MenuOpciones.cambiarContraseña(scanner);
                        break;
                    case 8: MenuOpciones.impresiones(scanner);

                    case 99:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (opcion != 99);
        }
        
        scanner.close();
    }
    
	
	
	////////////////////////////////////////////////////////////////////////////////
	
    public static boolean iniciarSesion(Scanner scanner) {
        System.out.println("=== Iniciar Sesión === \nUser: admin \nPassword: admin\n");
        System.out.println("Ingrese su usuario:");
        String usuario = scanner.next(); 
        
        System.out.println("Ingrese su contraseña:");
        String contraseña = scanner.next(); 
        
        boolean credencialesValidas = Empleado.validarCredenciales(usuario, contraseña);
        
        if (credencialesValidas) {
            System.out.println("Inicio de sesión exitoso.");
            return true;
        } else {
            System.out.println("Usuario o contraseña incorrectos. No se puede iniciar sesión.");
            return false;
        }
    }
    
    
}
