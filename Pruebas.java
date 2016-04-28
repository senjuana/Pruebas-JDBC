/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.sql.Connection;

/**
 *
 * @author senjuana
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DAOException {
        
        Propietario p1=null;
        conexion c = new conexion();
        c.establecerConexion();
        MPropietarioDAO p =new MPropietarioDAO(c.getConnection());
        p1=p.obtener(1);
       System.out.println(p1.getNombre()+" "+p1.getApellido()+" "+p1.getApellido2());
    }
    
}
