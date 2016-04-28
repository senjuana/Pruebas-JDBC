/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;
import pruebas.Propietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author senjuana
 */
public class MPropietarioDAO implements propDAO{
    
    final String insert ="INSERT INTO Propietario(nom,apellido1,apellido2,edad,norecau) VALUES(?,?,?,?,?)";
    final String update ="UPDATE Propietario set nom=?,apellido1=?,apellido2=?,edad=? where idProp=?";
    final String delete ="DELETE FROM Propietario WHERE idProp=?";
    final String getall ="select idProp,nom,apellido1,apellido2,edad from Propietario";
    final String getone ="select nom,apellido1,apellido2,edad,norecau from Propietario where idProp =?";
     
    private  Connection conn;
    
    public MPropietarioDAO(Connection conn){
        this.conn = conn;
    }
    
   
    @Override
    public void insertar(Propietario a) throws DAOException {
    PreparedStatement stat = null;
        ResultSet rs=null;
    try{
        stat = conn.prepareStatement(insert);
        stat.setString(1, a.getNombre());
        stat.setString(2, a.getApellido());
        stat.setString(3,a.getApellido2());
        stat.setLong(4,a.getEdad());
        stat.setLong(5,a.getNorecau());
        if(stat.executeUpdate()==0){
            throw new DAOException("Puede que no se haya guadardo.");
        }
        rs=stat.getGeneratedKeys();
        if(rs.next()){
            a.setIdprop(rs.getLong(1));
        }else{
            throw new DAOException("No puedo asignar id a ese Propietario");
        
        }
        }catch (SQLException ex) {
           throw new DAOException("Error en sql ",ex);
        }finally{
        if(rs!=null){
                try{
                rs.close();
                }catch(SQLException ex){
                    new DAOException("Error en el sql",ex);
                }
            }
        if(stat!= null){
            try{
                stat.close();
            }catch(SQLException ex){
                throw new DAOException("Error en sql",ex);
            }
        }
        }
    }

    @Override
    public void eliminar(Propietario a) throws DAOException {
         PreparedStatement stat = null;
    try{
        stat = conn.prepareStatement(delete);
        stat.setLong(1, a.getIdprop());
        if(stat.executeUpdate()==0){
            throw new DAOException("Puede que no se haya eliminado.");
        }
        }catch (SQLException ex) {
           throw new DAOException("Error en sql ",ex);
        }finally{
        if(stat!= null){
            try{
                stat.close();
            }catch(SQLException ex){
                throw new DAOException("Error en sql",ex);
            }
        }
        }
    }


    @Override
    public void modificar(Propietario a) throws DAOException {
             PreparedStatement stat = null;
    try{
        stat = conn.prepareStatement(update);
        stat.setString(1, a.getNombre());
        stat.setString(2,a.getApellido());
        stat.setString(3,a.getApellido2());
        stat.setLong(4,a.getEdad());
        stat.setLong(5,a.getIdprop());
        if(stat.executeUpdate()==0){
            throw new DAOException("Puede que no se haya modificado.");
        }
        }catch (SQLException ex) {
           throw new DAOException("Error en sql ",ex);
        }finally{
        if(stat!= null){
            try{
                stat.close();
            }catch(SQLException ex){
                throw new DAOException("Error en sql",ex);
            }
        }
        }
    }

    
    private Propietario getconvert(ResultSet rs) throws SQLException {
        String nombre=rs.getString("nom");
        String apellido=rs.getString("apellido1");
        String apellido2=rs.getString("apellido2");
        long edad=rs.getLong("edad");
        long norecau=rs.getLong("norecau");
        Propietario row= new Propietario(nombre,apellido,apellido2,edad,norecau);
        row.setIdprop(rs.getLong("idProp"));
        return row;
    }
    
    
    @Override
    public List<Propietario> obtenerTodos() throws DAOException {
        PreparedStatement stat =null;
        ResultSet rs=null;
        List<Propietario> propietarios=new ArrayList<>();
        try{
        stat =conn.prepareStatement(getall);
        rs=stat.executeQuery();
        while(rs.next()){
            propietarios.add(getconvert(rs));    
        }
        }catch(SQLException ex){
            throw new DAOException("Error de sql",ex);
        }finally{
            if(rs!=null){
                try{
                rs.close();
                }catch(SQLException ex){
                    new DAOException("Error en el sql",ex);
                }
            }
            if(stat!=null){
            try{
                stat.close();
            }catch(SQLException ex){
                new DAOException("Error en el sql",ex);
            }
            }
        }
        
      return propietarios;
    }

    @Override
    public Propietario obtener(long id) throws DAOException {
     PreparedStatement stat =null;
        ResultSet rs=null;
        Propietario p=null;
        try{
        stat =conn.prepareStatement(getone);
        stat.setLong(1,id);
        rs=stat.executeQuery();
        if(rs.next()){
            p=getconvert(rs);    
        }else{
            throw new DAOException("No se ha encontrado ese registro.");
        }
        }catch(SQLException ex){
            throw new DAOException("Error de sql",ex);
        }finally{
            if(rs!=null){
                try{
                rs.close();
                }catch(SQLException ex){
                    new DAOException("Error en el sql",ex);
                }
            }
            if(stat!=null){
            try{
                stat.close();
            }catch(SQLException ex){
                new DAOException("Error en el sql",ex);
            }
            }
        }
        
      return p;  
    }

    private void a() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}