/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        Integer ultimoID = null;

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
    
}
