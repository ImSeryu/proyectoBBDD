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
                        System.out.println("\nIntroduzca alguna de las opciones válidas.");
                }
                System.out.println();
            } catch (NumberFormatException nfe) {
                System.err.println("\nError: debes introducir un numero\n");
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
                System.out.println("Cliente añadido correctamente");
            }else{
                System.out.println("Error al añadir cliente");
            }
        }else{
            System.out.println("Operacion cancelada");
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
                System.out.println("Debes de introducir un numero");
            }
        }while(salida);
        
        if(id!=0){
            if(id<clientes.maximoId()){
                if(clientes.delete(id)){
                    System.out.println("Cliente borrado correctamente");
                }else{
                    System.out.println("Error al borrar cliente");
                }
            }else{
                System.out.println("No existe cliente con ese id");
            }
            
        }else{
            System.out.println("Operacion cancelada");
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
                System.out.println("Debes de introducir un numero");
            }
        }while(salida);
        
        if(id!=0){
            if(clientes.update(id)){
                System.out.println("Cliente modificado correctamente");
            }else{
                System.out.println("Error al modificar cliente");
            }
        }else{
            System.out.println("Operacion cancelada");
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
                            System.out.println("\nNo hay mas clientes para mostrar\n");
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
                            System.out.println("\nEstas en el visualizando el primer registro, no se puede ir hacia atras\n");
                        }    
                        break;
                    default:
                        System.out.println("Introduce una opcion valida");
                        break;    
                }
        
                }catch(NumberFormatException nfe){
                    System.out.println("\nDebes introducir un numero\n");
                }
        }while(salida);    
    }
    

}
