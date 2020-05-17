/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;
import dao.ClientesDAO;
import entidades.Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author seryu
 */
public class Menu {
    
    static ClientesDAO clientes = new ClientesDAO();
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    
    public void menu() throws IOException{
        Scanner sc = new Scanner(System.in, "ISO-8859-1");
        Integer opcion = null;
        Boolean salida = true;
        do{
            try {
                System.out.println("\nBase de datos de clientes");
                System.out.println("---------------------------------");
                System.out.println("1.- Añadir nuevo cliente");
                System.out.println("2.- Borrar un cliente");
                System.out.println("3.- Modificar un cliente");
                System.out.println("4.- Visualizar tabla clientes");
                System.out.println("0.- Salir");
                System.out.println("---------------------------------");
                opcion = Integer.parseInt(sc.nextLine());
                System.out.println();
                switch (opcion) {
                    case 0:
                        salida = false;
                        break;
                    case 1:
                        anadirCliente();
                        break;
                    case 2:
                        borrarCliente(sc);
                        break;
                    case 3:
                        modificarCliente(sc);
                        break;
                    case 4:
                        visualizarTabla(sc);
                        break;
                    default:
                        System.out.println("\n"+ANSI_RED + "Introduce una opcion valida" + ANSI_RESET+"\n");
                }
                System.out.println();
            } catch (NumberFormatException nfe) {
                System.out.println("\n"+ANSI_RED + "Error: debes introducir un numero" + ANSI_RESET+"\n");
            }
        }while(salida);
    }

    private void anadirCliente() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Cliente cliente = new Cliente();
        System.out.println("Introduce codigo del cliente(0 para cancelar)");
        cliente.setCodigo(br.readLine());
        if(!cliente.getCodigo().equals("0")){
            System.out.println("Introduce empresa del cliente");
            cliente.setEmpresa(br.readLine());
            System.out.println("Introduce contacto del cliente");
            cliente.setContacto(br.readLine());
            System.out.println("Introduce cargo_contacto del cliente");
            cliente.setCargoContacto(br.readLine());
            System.out.println("Introduce direcion del cliente");
            cliente.setDireccion(br.readLine());
            System.out.println("Introduce ciudad del cliente");
            cliente.setCiudad(br.readLine());
            System.out.println("Introduce region del cliente");
            cliente.setRegion(br.readLine());
            System.out.println("Introduce cp del cliente");
            cliente.setCp(br.readLine());
            System.out.println("Introduce pais del cliente");
            cliente.setPais(br.readLine());
            System.out.println("Introduce telefono del cliente");
            cliente.setTelefono(br.readLine());
            System.out.println("Introduce fax del cliente");
            cliente.setFax(br.readLine());

            if(clientes.insert(cliente)){
                System.out.println("\n"+ANSI_GREEN + "Cliente creado correctamente" + ANSI_RESET+"\n");
            }else{
                System.out.println("\n"+ANSI_RED + "Error al añadir cliente" + ANSI_RESET+"\n");
            }
        }else{
            System.out.println("\n"+ANSI_YELLOW + "Operacion cancelada" + ANSI_RESET+"\n");
        }
        
    }
    
    private void borrarCliente(Scanner sc){
        Integer id=0;
        Boolean salida = true;
        
        System.out.println("Introduce el id del cliente que deseas eliminar(0 para cancelar)");
        do{
            try{
                id = Integer.parseInt(sc.nextLine());            
                salida = false;
            }catch(NumberFormatException exc){
                System.out.println(ANSI_RED + "Debes introducir un numero" + ANSI_RESET);
            }
        }while(salida);
        
        if(id!=0){
            if(id<=clientes.maximoId()){
                if(clientes.delete(id)){
                    System.out.println("\n"+ANSI_GREEN + "Cliente borrado correctamente" + ANSI_RESET+"\n");
                }else{
                    System.out.println("\n"+ANSI_RED + "Error al borrar el cliente" + ANSI_RESET+"\n");
                }
            }else{
                System.out.println("\n"+ANSI_RED + "No existe cliente co nese id" + ANSI_RESET+"\n");
            }
            
        }else{
            System.out.println("\n"+ANSI_YELLOW + "Operacion cancelada" + ANSI_RESET+"\n");
        }
        
    }
    
    
    private void modificarCliente(Scanner sc) throws IOException{
        Integer id=0;
        Boolean salida = true;
        
        System.out.println("Introduce el id del cliente que deseas modificar(0 para cancelar)");
        do{
            try{
                id = Integer.parseInt(sc.nextLine());
                salida = false;
            }catch(NumberFormatException exc){
                System.out.println("\n"+ANSI_RED + "Debes introducir un numero" + ANSI_RESET+"\n");
            }
        }while(salida);
        
        if(id!=0){
            if(clientes.update(id)){
                System.out.println("\n"+ANSI_GREEN + "Cliente modificado correctamente" + ANSI_RESET+"\n");
            }else{
                System.out.println("\n"+ANSI_RED + "Error al modificar el cliente" + ANSI_RESET+"\n");
            }
        }else{
            System.out.println("\n"+ANSI_YELLOW + "Operacion cancelada" + ANSI_RESET+"\n");
        }
        
        
    }
    
    private void visualizarTabla(Scanner sc){
        Boolean salida = true;
        Integer opcion = null;
        Integer comienzo = 1;
        Integer fin = 10;
        do{ 
                try{   
                System.out.println("\n|   ID  |  CODIG  |        EMPRESA       |       CONTACTO       |     CARGO CONTACTO     |   DIRECCION  |   CIUDAD   |  REGION  |      CP      |      PAIS    |    TELEFONO  |      FAX     |");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for(Cliente cliente: clientes.listar(comienzo, fin)){
                    System.out.println(cliente);
                }       
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("1.- Visualizar siguientes");
                System.out.println("2.- Visualizar anteriores");
                System.out.println("0.- Salir");
                opcion = Integer.parseInt(sc.nextLine());

                switch(opcion){
                    case 0:
                        salida = false;
                        break;
                    case 1:
                        if((comienzo+10)>clientes.maximoId()){
                            System.out.println("\n"+ANSI_RED + "No hay mas clientes para mostrar" + ANSI_RESET+"\n");
                        }else{
                            comienzo += 10;
                            fin += 10;
                        }                
                        break;
                    case 2:
                        if(comienzo!=1){
                            comienzo -= 10;
                            fin -= 10;
                        }else{
                            System.out.println("\n"+ANSI_RED + "Estas visualizando el primer registro, no puedes ir hacia atras" + ANSI_RESET+"\n");
                        }    
                        break;
                    default:
                        System.out.println("\n"+ANSI_RED + "Introduce una opcion valida" + ANSI_RESET+"\n");
                        break;    
                }
        
                }catch(NumberFormatException nfe){
                    System.out.println("\n"+ANSI_RED + "Error: debes introducir un numero" + ANSI_RESET+"\n");
                }
        }while(salida);    
    }
    

}
