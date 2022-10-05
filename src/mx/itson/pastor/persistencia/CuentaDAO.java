/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.pastor.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.itson.pastor.entidades.Cliente;
import mx.itson.pastor.entidades.Cuenta;

/**
 *
 * @author jesus
 */
public class CuentaDAO {
    
    public static List<Cuenta> obtenerTodos(){
        List<Cuenta> cuentas = new ArrayList<>();
        try {
            Connection connection = Conexion.obtener();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT cu.id, cu.numero, cl.id, cl.nombre, cl.direccion, cl.telefono, cl.email FROM cuenta cu INNER JOIN cliente cl ON cu.idCliente = cl.id");
            while(resultSet.next()){
                Cuenta cuenta = new Cuenta();
                cuenta.setId(resultSet.getInt(1));
                cuenta.setNumero(resultSet.getString(2));
                
                Cliente c = new Cliente();
                c.setId(resultSet.getInt(3));
                c.setNombre(resultSet.getString(4));
                c.setDireccion(resultSet.getString(5));
                c.setTelefono(resultSet.getString(6));
                c.setEmail(resultSet.getString(7));
                
                cuenta.setCliente(c);
                
                cuentas.add(cuenta);
            }
        } catch(Exception ex){
            System.err.print("Ocurrió un error: " + ex.getMessage());
        }
        return cuentas;
    }
    
}
