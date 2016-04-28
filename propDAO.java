/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.List;

/**
 *
 * @author senjuana
 */
public interface propDAO {
   
    void insertar(Propietario a)throws DAOException;
    void eliminar (Propietario a)throws DAOException;
    void modificar (Propietario a)throws DAOException;
    
    List<Propietario> obtenerTodos()throws DAOException;
    
    Propietario obtener (long id)throws DAOException;

}
