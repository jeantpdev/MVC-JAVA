
package Controlador;

import Modelo.controlBD;
import Modelo.modeloPersona;
import Vista.vistaPersonas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ctrlPersona implements ActionListener{
    
    modeloPersona modeloP;
    vistaPersonas vistaP;
    controlBD ctrlBD;
    
    
    public ctrlPersona (modeloPersona modeloP, vistaPersonas vistaP, controlBD ctrlBD){
        
        this.modeloP = modeloP;
        this.vistaP = vistaP;
        this.ctrlBD = ctrlBD;
        this.vistaP.btnAgregar.addActionListener(this);
        this.vistaP.btnEliminar.addActionListener(this);
        this.vistaP.btnBuscar.addActionListener(this);
        this.vistaP.btnLimpiar.addActionListener(this);
        this.vistaP.btnActualizar.addActionListener(this);
   
    }
    
    public void iniciarPersona(){
      vistaP.setTitle("Registrar Personas");
      vistaP.setLocationRelativeTo(null);
    }
    
    @Override
    public void actionPerformed (ActionEvent e){
        
        if(e.getSource() == vistaP.btnAgregar){
            boolean acceso = false;
            
            if (acceso == false){
                
                modeloP.setCedula(Integer.parseInt(vistaP.txtCedula.getText()));
                modeloP.setNombre(vistaP.txtNombre.getText());
                modeloP.setApellido(vistaP.txtApellido.getText());
                
                if(ctrlBD.registrarPersona(modeloP)){
                    JOptionPane.showMessageDialog(null,"Persona registrada");
                }else{
                    JOptionPane.showMessageDialog(null,"Hubo un error");
                }
            }
        }
        
        if (e.getSource() == vistaP.btnBuscar){
            modeloP.setCedula(Integer.parseInt(vistaP.txtCedula.getText()));
            if(ctrlBD.buscarPersona(modeloP)){
                vistaP.mostrarTabla(modeloP.getCedula());
                vistaP.txtCedula.setText(String.valueOf(modeloP.getCedula()));
                vistaP.txtNombre.setText(modeloP.getNombre());
                vistaP.txtApellido.setText(modeloP.getApellido());
            }else{
                JOptionPane.showMessageDialog(null,"no se encontró registro");
            }
        }
        
        if(e.getSource() == vistaP.btnEliminar){
            if(ctrlBD.eliminarPersona(modeloP)){
                JOptionPane.showMessageDialog(null,"registro modificado");                
            }
        }
        
        if(e.getSource() == vistaP.btnActualizar){
            vistaP.mostrarTodo();
        }
        
        if(e.getSource() == vistaP.btnLimpiar){
            vistaP.txtCedula.setText("");
            vistaP.txtNombre.setText("");
            vistaP.txtApellido.setText("");
        }
        
    }
    
}
