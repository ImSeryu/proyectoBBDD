/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import dao.ClientesDAO;
import entidades.Cliente;
import java.io.IOException;

/**
 *
 * @author seryu
 */
public class Main {
    
    static ClientesDAO clientes = new ClientesDAO();
    static Menu menu = new Menu();
    
    public static void main(String[] args) throws IOException {
        
        if (clientes.getConexion() == null) {
            System.err.println("Programa terminado. Error en la conexión.");
            System.exit(0);
        }
          
        menu.menu();
    }   
}