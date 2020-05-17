/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author seryu
 */
public class ClientesDAO {
    
    private Connection conexion = null;
    
    public ClientesDAO() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/neptuno", "root", "");
        } catch (SQLException ex) {
            System.err.println("Error al conectar: " + ex.getMessage());
        }
    }
    
    public Connection getConexion() {
        return conexion;
    }
    
    
    public Boolean insert(Cliente cliente) {
        Boolean resultado = false;
        PreparedStatement stmt = null;

        if (this.conexion == null || cliente == null) {
            return false;
        }

        try {

            String sql = "INSERT INTO clientes "
                    + "(id, codigo, empresa, contacto, cargo_contacto, direccion, ciudad, region, cp, pais, telefono, fax) "
                    + "VALUES ((SELECT Max(id)+1 FROM `clientes` E), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, cliente.getCodigo());
            stmt.setString(2, cliente.getEmpresa());
            stmt.setString(3, cliente.getContacto());
            stmt.setString(4, cliente.getCargoContacto());
            stmt.setString(5, cliente.getDireccion());
            stmt.setString(6, cliente.getCiudad());
            stmt.setString(7, cliente.getRegion());
            stmt.setString(8, cliente.getCp());
            stmt.setString(9, cliente.getPais());
            stmt.setString(10, cliente.getTelefono());
            stmt.setString(11, cliente.getFax());
            

            if (stmt.executeUpdate() > 0) {
                resultado = true;

            }
        } catch (SQLException e) {
            System.err.println("Error en el Insert: " + e.getMessage()+ " SQL:" + stmt.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }

        return resultado;

    }
    
    
    public Boolean delete(Integer id) {
        Boolean resultado = false;
        Boolean salida = true;
        PreparedStatement stmt = null;

        try {
            String sql = "DELETE FROM clientes WHERE id = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id);

            resultado = stmt.execute();
            salida= true;
            
            stmt.close();

            System.out.println();

        } catch (SQLException e) {
            salida=false;
            System.err.println("Error en el Delete: " + e.getMessage() + " " + stmt.toString());
        }

        return salida; 
        
    }
    
    
    
    public Boolean update(Integer id) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Boolean resultado = null;
        PreparedStatement stmt = null;
        String aux;
        Cliente cliente = read(id);

        if (this.conexion == null || cliente == null) {
            return false;
        }
        
        System.out.println("Modificando cliente con id : "+id+" (intro sin escribir para mantener el existente)");
        System.out.print("Codigo ("+cliente.getCodigo()+") :");
        aux = br.readLine();
        if (!aux.equals("")){
            cliente.setCodigo(aux);
        }
        System.out.print("Empresa ("+cliente.getEmpresa()+") :");
        aux = br.readLine();
        if (!aux.equals("")){
            cliente.setEmpresa(aux);
        }
        System.out.print("Contacto ("+cliente.getContacto()+") :");
        aux = br.readLine();
        if (!aux.equals("")){
            cliente.setContacto(aux);
        }
        System.out.print("Cargo contacto ("+cliente.getCargoContacto()+") :");
        aux = br.readLine();
        if (!aux.equals("")){
            cliente.setCargoContacto(aux);
        }
        System.out.print("Direccion ("+cliente.getDireccion()+") :");
        aux = br.readLine();
        if (!aux.equals("")){
            cliente.setDireccion(aux);
        }
        System.out.print("Ciudad ("+cliente.getCiudad()+") :");
        aux = br.readLine();
        if (!aux.equals("")){
            cliente.setCiudad(aux);
        }
        System.out.print("Region ("+cliente.getRegion()+") :");
        aux = br.readLine();
        if (!aux.equals("")){
            cliente.setRegion(aux);
        }
        System.out.print("Codigo postal ("+cliente.getCp()+") :");
        aux = br.readLine();
        if (!aux.equals("")){
            cliente.setCp(aux);
        }
        System.out.print("Pais ("+cliente.getPais()+") :");
        aux = br.readLine();
        if (!aux.equals("")){
            cliente.setPais(aux);
        }
        System.out.print("Telefono ("+cliente.getTelefono()+") :");
        aux = br.readLine();
        if (!aux.equals("")){
            cliente.setTelefono(aux);
        }
        System.out.print("Fax ("+cliente.getFax()+") :");
        aux = br.readLine();
        if (!aux.equals("")){
            cliente.setFax(aux);
        }
        
        
        
        
        try {

            String sql = "UPDATE clientes SET codigo = ?, empresa = ?, contacto = ?, cargo_contacto = ?"
                    + ", direccion = ?, ciudad = ?, region = ?, cp = ?, pais = ?, telefono = ?, "
                    + "fax = ? WHERE id = ?";

            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, cliente.getCodigo());
            stmt.setString(2, cliente.getEmpresa());
            stmt.setString(3, cliente.getContacto());
            stmt.setString(4, cliente.getCargoContacto());
            stmt.setString(5, cliente.getDireccion());
            stmt.setString(6, cliente.getCiudad());
            stmt.setString(7, cliente.getRegion());
            stmt.setString(8, cliente.getCp());
            stmt.setString(9, cliente.getPais());
            stmt.setString(10, cliente.getTelefono());
            stmt.setString(11, cliente.getFax());

            stmt.setInt(12, cliente.getId());
            if (stmt.executeUpdate() > 0) {
                resultado = true;

            }
        } catch (SQLException e) {
            System.err.println("Error en el Update: " + e.getMessage()+ " SQL:" + stmt.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }

        return resultado;
    }
    
    
    public Cliente read(Integer id) {
        Cliente cliente = null;
        PreparedStatement stmt = null;

        if (this.conexion == null) {
            return null;
        }

        try {
            String query = "SELECT * FROM clientes WHERE id = ?";
            stmt = conexion.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("empresa"),
                    rs.getString("contacto"),
                    rs.getString("cargo_contacto"),
                    rs.getString("direccion"),
                    rs.getString("ciudad"),
                    rs.getString("region"),
                    rs.getString("cp"),
                    rs.getString("pais"),
                    rs.getString("telefono"),
                    rs.getString("fax")
                );
            }

            stmt.close();

        } catch (SQLException e) {

            System.err.println("Error en el Select: " + e.getMessage() + "\nQuery: " + stmt.toString());
        }

        return cliente;
    }
    
    public ArrayList<Cliente> listar(Integer comienzo, Integer fin) {
        Cliente cliente = null;
        PreparedStatement stmt = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        try{
            String query = "SELECT * FROM clientes where id BETWEEN ? AND ?";
            stmt = conexion.prepareStatement(query);
            stmt.setInt(1, comienzo);
            stmt.setInt(2, fin);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("empresa"),
                    rs.getString("contacto"),
                    rs.getString("cargo_contacto"),
                    rs.getString("direccion"),
                    rs.getString("ciudad"),
                    rs.getString("region"),
                    rs.getString("cp"),
                    rs.getString("pais"),
                    rs.getString("telefono"),
                    rs.getString("fax")
                );
                clientes.add(cliente);
            }      
        }catch(SQLException e){
            System.out.println("Error: "+e.getMessage()+" "+stmt.toString());
        }
        return clientes;
    }
    
    public int maximoId(){
        Cliente cliente = null;
        PreparedStatement stmt = null;

        try {
            String query = "SELECT * FROM clientes WHERE id = (SELECT Max(id) FROM `clientes`)";
            stmt = conexion.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id")); 
            }

            stmt.close();

        } catch (SQLException e) {

            System.err.println("Error en el Select: " + e.getMessage() + "\nQuery: " + stmt.toString());
        }

        return cliente.getId();
    }
}
