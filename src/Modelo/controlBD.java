
package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class controlBD extends ConexionBD {
    
    
    public boolean registrarPersona(modeloPersona modeloP){
        
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "INSERT INTO persona (cedula, nombre, apellido) VALUES(?,?,?)";
        
        try {
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, modeloP.getCedula());
            ps.setString(2, modeloP.getNombre());
            ps.setString(3, modeloP.getApellido());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
            return false;
        }
        
        finally{
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e);
            }
        }
        
        
    }
    
    public boolean buscarPersona(modeloPersona modeloP){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();        
        
        String sql = "SELECT * FROM persona WHERE cedula = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, modeloP.getCedula());
            rs = ps.executeQuery();
            
            if (rs.next()){
                modeloP.setCedula(Integer.parseInt(rs.getString("cedula")));
                modeloP.setNombre(rs.getString("nombre"));
                modeloP.setApellido(rs.getString("apellido"));
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
    
    public boolean mostrarPersonas (modeloPersona modeloP){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();        
        
        String sql = "SELECT * FROM persona";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, modeloP.getCedula());
            rs = ps.executeQuery();
            
            if (rs.next()){
                modeloP.setCedula(Integer.parseInt(rs.getString("cedula")));
                modeloP.setNombre(rs.getString("nombre"));
                modeloP.setApellido(rs.getString("apellido"));
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e);
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
    
    public boolean eliminarPersona (modeloPersona modeloP){
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM persona WHERE cedula = ? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, modeloP.getCedula());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }        
    }
    
    
    
}
