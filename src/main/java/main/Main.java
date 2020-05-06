/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.ClientesDAO;
import entidades.Cliente;

/**
 *
 * @author seryu
 */
public class Main {
    
    static ClientesDAO clientes = new ClientesDAO();
    
    public static void main(String[] args) {
        
        if (clientes.getConexion() == null) {
            System.err.println("Programa terminado. Error en la conexión.");
            System.exit(0);
        }
      /*    
        Cliente cliente = new Cliente (92, "WOLZE", "Wolski  Zajazz", "Zbyszek Piestrzeniewicc", "Propietario", "ul. Filtrowa 69", "Warszaww", "patria", "01-012", "Polonia", "(26) 642-7013", "(26) 642-7013");

        if (clientes.insert(cliente)) {
            System.out.println("El empleado ha sido añadido satisfactoriamente.");
        } else {
            System.err.println("El empleado que intenta introducir no es válido.\n");
        }
        */
      
        if (clientes.delete(91)){
            System.out.println("Eliminado correctamente");
        }else{
            System.out.println("Error");
        }
    }
    
}
