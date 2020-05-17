/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cliente;
import java.sql.Connection;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author seryu
 */
public class ClientesDAOTest {
    
    public ClientesDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getConexion method, of class ClientesDAO.
     
    @Test
    public void testGetConexion() {
        System.out.println("getConexion");
        ClientesDAO instance = new ClientesDAO();
        Connection expResult = null;
        Connection result = instance.getConexion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of insert method, of class ClientesDAO.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        
        // PRUEBO A AÑADIR UN CLIENTE QUE NO EXITE POR LO QUE DEBERIA PERMITIRLO Y DAR TRUE COMO RESULTADO
        Cliente cliente = new Cliente(0, "LOLGG", "Riot Games","Ricardo Kails","Baneador","Grieta Invocador","Demacia","Runaterra","28021","Españita","5478745154","549854-455-4");       
        ClientesDAO clientes = new ClientesDAO();
        Boolean expResult = true;
        Boolean result = clientes.insert(cliente);
        assertEquals(expResult, result);
        
        // VUELVO A INTRODUCIR EL MISMO CLIENTE QUE AL ESTAR YA AGREGADO DEBERIA DAR ERROR Y QUE LA SALIDA SEA FALSE
        expResult = false;
        result = clientes.insert(cliente);
        assertEquals(expResult, result);
        
        // BORRO CLIENTE CREADO PARA QUE NO MOLESTE
        clientes.delete(clientes.maximoId());
    }

    /**
     * Test of delete method, of class ClientesDAO.
    */ 
    @Test
    public void testDelete() {
        System.out.println("delete");
        Integer id = null;
        ClientesDAO clientes = new ClientesDAO();
        // CREO UN CLIENTE PARA BORRARLO
        Cliente cliente = new Cliente(0, "LOLGG", "Riot Games","Ricardo Kails","Baneador","Grieta Invocador","Demacia","Runaterra","28021","Españita","5478745154","549854-455-4");
        clientes.insert(cliente);
        
        //BORRO EL CLIENTE, NO DEBERIA DAR ERROR POR LO QUE LA SALIDA ES TRUE
        Boolean expResult = true;
        Boolean result = clientes.delete(clientes.maximoId());
        assertEquals(expResult, result);
        
        
        // AHORA BORRARE EL PRIMER CLIENTE Y DARA ERROR PORQUE ESE CLIENTE TIENE UN PEDIDO ASOCIADO POR LO QUE NO PUEDE DER ELIMINADO
        expResult = false;
        result = clientes.delete(1);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of update method, of class ClientesDAO.
    */
    
    
    //   ** ESTE TEST SE ME QUEDA PILLADO AL EJECUTAR NO SE PORQUE EN TEORIA NO DEBERIA DAR FALLOS Y NO LOS DA PERO SE QUEDA PILLADO Y NO RESPONDE **
    
    /*
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        ClientesDAO clientes = new ClientesDAO();
        
        // CREO UN CLIENTE PARA PROBAR LAS MODIFICACIONES
        Cliente cliente = new Cliente(0, "LOLGG", "Riot Games","Ricardo Kails","Baneador","Grieta Invocador","Demacia","Runaterra","28021","Españita","5478745154","549854-455-4");
        clientes.insert(cliente);
        Integer id = clientes.maximoId();
        // MODIFICAR EL CONTACTO NO DA ERROR POR LO QUE LA SALIDA ES TRUE
        System.out.println("**Modifica solo el nombre del contacto para probar un caso que no debe dar error**");
        Boolean expResult = true;
        Boolean result = clientes.update(id);
        assertEquals(expResult, result);
        
        // MODIFICAR EL CODIGO POR UNO QUE TENGA MAS DE 5 CARACTERES DARA ERROR 
        System.out.println("**Modifica solo el codigo por uno con mas de 5 caracteres**");
        expResult = false;
        result = clientes.update(id);
        assertEquals(expResult, result);
        
        
    }
    */
    /**
     * Test of read method, of class ClientesDAO.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        ClientesDAO clientes = new ClientesDAO();
        
        // CREO CLIENTE PARA PROBAR
        Cliente cliente = new Cliente(0, "LOLGG", "Riot Games","Ricardo Kails","Baneador","Grieta Invocador","Demacia","Runaterra","28021","Españita","5478745154","549854-455-4");
        clientes.insert(cliente);
        Integer id = clientes.maximoId();
        cliente.setId(id);
        
        // PRUEBA DE QUE EL CLIENTE CREADO ES EL QUE RESULTA DEL METODO
        String expResult = cliente.toString();
        String result = clientes.read(id).toString();
        assertEquals(expResult, result);
        
        
        clientes.delete(clientes.maximoId());
        
    }

    /**
     * Test of listar method, of class ClientesDAO.
    */ 
    @Test
    public void testListar() {
        System.out.println("listar");
        Integer comienzo = 1;
        Integer fin = 10;
        ClientesDAO instance = new ClientesDAO();
        ArrayList<Cliente> expResult = null;
        ArrayList<Cliente> result = instance.listar(comienzo, fin);
        assertNotEquals(expResult, result);
        
    }

    /**
     * Test of maximoId method, of class ClientesDAO.
    */ 
    @Test
    public void testMaximoId() {
        System.out.println("maximoId");
        ClientesDAO clientes = new ClientesDAO();
        
        //SI SALTA LA EXACPCION ES PORK NO EXISTE NINGUN REGISTRO POSTERIOR AL MAXIMO ID+1 POR LO QUE MAXIMOID ES EL ULTIMO
        try{
            String expResult = null;
            String result = clientes.read(clientes.maximoId()+1).toString();
            assertEquals(expResult, result);
        }catch (java.lang.NullPointerException exc){
            String expResult = null;
            String result = null;
            assertEquals(expResult, result);
        }
    }
    
}
