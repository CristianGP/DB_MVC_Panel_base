/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.ModelAgenda;
import views.ViewAgenda;

/**
 *
 * @author Salvador Hernandez Mendoza
 */
public class ControllerAgenda {

    public ModelAgenda modelAgenda;
    public ViewAgenda viewAgenda;

    /**
     * Objeto de tipo ActionListener para atrapar los eventos actionPerformed y
     * llamar a los metodos para ver los registros almacenados en la bd.
     */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewAgenda.jbtn_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_ultimo) {
                jbtn_ultimo_actionPerformed();
            }else if(e.getSource() == viewAgenda.jbt_eliminar){
                try {
                    jbtn_eliminar();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getSource() == viewAgenda.jbt_insertar){
                try {
                    jbtn_insertar();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getSource() == viewAgenda.jbt_modificar){
                try {
                    jbtn_modificar();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getSource() == viewAgenda.jbt_nuevo){
                jbtn_nuevo();
            }

        }

    };

    /**
     * Constructor de Controlador para unir el ModelAgenda y ViewAgenda
     *
     * @param modelAgenda objeto de tipo ModelAgenda
     * @param viewAgenda objeto de tipo ViewAgenda
     */
    public ControllerAgenda(ModelAgenda modelAgenda, ViewAgenda viewAgenda) {
        this.modelAgenda = modelAgenda;
        this.viewAgenda = viewAgenda;
        initComponents();
        setActionListener();
        initDB();;
    }

    /**
     * Método que llama al método conectarBD del modelo y muestra el nombre y
     * email del primer registro en las cajas de texto de ViewAgenda.
     */
    private void initDB() {
        modelAgenda.conectarDB();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_telefono.setText(modelAgenda.getTelefono());
    }

    /**
     * Metodo para inicializar la ViewAgenda
     */
    public void initComponents() {
        //viewAgenda.setLocationRelativeTo(null);
        //viewAgenda.setTitle("Agenda MVC");
        viewAgenda.setVisible(true);
    }


    /**
     * Método para agregar el actionListener a cada boton de la vista
     */
    private void setActionListener() {
        viewAgenda.jbtn_primero.addActionListener(actionListener);
        viewAgenda.jbtn_anterior.addActionListener(actionListener);
        viewAgenda.jbtn_siguiente.addActionListener(actionListener);
        viewAgenda.jbtn_ultimo.addActionListener(actionListener);
        viewAgenda.jbt_eliminar.addActionListener(actionListener);
        viewAgenda.jbt_insertar.addActionListener(actionListener);
        viewAgenda.jbt_modificar.addActionListener(actionListener);
        viewAgenda.jbt_nuevo.addActionListener(actionListener);
    }

    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        System.out.println("Action del boton jbtn_primero");
         modelAgenda.moverPrimerRegistro();
        setValues();
    }

    /**
     * Método para ver el registro anterior de la tabla contactos.
     */
    private void jbtn_anterior_actionPerformed() {
        System.out.println("Action del boton jbtn_anterior");
        modelAgenda.moverAnteriorRegistro();
        setValues();
    }

    /**
     * Método para ver el último registro de la tabla contactos.
     */
    private void jbtn_ultimo_actionPerformed() {
        System.out.println("Action del boton jbtn_ultimo");
        modelAgenda.moverUltimoRegistro();
        setValues();
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos.
     */
    private void jbtn_siguiente_actionPerformed() {
        System.out.println("Action del boton jbtn_siguiente");
        modelAgenda.moverSiguienteRegistro();
        setValues();
    }

    /**
     * Muestra el nombre y email almacenados en el modelAgenda en el viewAgenda.
     */
    private void setValues() {
        System.out.println("setValues - Controller Agenda");
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_telefono.setText(modelAgenda.getTelefono());
    }
    
    private void jbtn_eliminar() throws SQLException {
            System.out.println("Action del boton jbtn_eliminar");
            System.out.println(modelAgenda.getEmail());
            modelAgenda.eliminarRegistro(modelAgenda.getEmail());
            JOptionPane.showMessageDialog(viewAgenda, "Registro eliminado correctamente");
            jbtn_primero_actionPerformed();
        }

        private void jbtn_insertar() throws SQLException {
            System.out.println("Action del boton jbtn_insertar");
            modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
            modelAgenda.setEmail(viewAgenda.jtf_email.getText());
            modelAgenda.insertarRegistro(modelAgenda.getNombre(),modelAgenda.getEmail(),modelAgenda.getTelefono());
            JOptionPane.showMessageDialog(viewAgenda, "Registro guardado correctamente");
            jbtn_primero_actionPerformed();
        }

        private void jbtn_modificar() throws SQLException {
            System.out.println("Action del boton jbtn_modificar");
            modelAgenda.modificarRegistro(viewAgenda.jtf_nombre.getText(), viewAgenda.jtf_email.getText(),viewAgenda.jtf_telefono.getText());
            JOptionPane.showMessageDialog(viewAgenda, "Registro actualizado correctamente");
            jbtn_primero_actionPerformed();
        }

        private void jbtn_nuevo() {
            System.out.println("Action del boton jbtn_nuevo");
            modelAgenda.setEmail(null);
            modelAgenda.setNombre(null);
             modelAgenda.setTelefono(null);
            viewAgenda.jtf_email.setText(modelAgenda.getEmail());
            viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
            viewAgenda.jtf_telefono.setText(modelAgenda.getTelefono());
            
        }
}
