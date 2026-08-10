/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Modelo.SentenciasUsuario;
import Vista.frmUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class CtrlUsuario implements ActionListener {

    private final Usuario modelo;
    private final SentenciasUsuario consultas;
    private final frmUsuario vista;

    public CtrlUsuario(Usuario modelo, SentenciasUsuario consultas, frmUsuario vista) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
    }

    public void inicio() {
        vista.setTitle("Control de Usuario");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public void Limpiar() {
        vista.txtIdUsuario.setText("");
        vista.txtNombre.setText("");
        vista.txtCorreo.setText("");
        vista.txtContrasena.setText("");
        vista.txtRol.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        //guardar
        if (e.getSource() == vista.btnGuardar) {
            try {
                modelo.setIdUsuario(Integer.parseInt(vista.txtIdUsuario.getText()));
                modelo.setNombre(vista.txtNombre.getText());
                modelo.setCorreo(vista.txtCorreo.getText());
                modelo.setContrasena(vista.txtContrasena.getText());
                modelo.setRol(vista.txtRol.getText());
                if (consultas.registrar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro guardado correctamente");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El ID debe ser numero");
            }
        }
        //modificar
        if (e.getSource() == vista.btnModificar) {
            try {
                modelo.setIdUsuario(Integer.parseInt(vista.txtIdUsuario.getText()));
                modelo.setNombre(vista.txtNombre.getText());
                modelo.setCorreo(vista.txtCorreo.getText());
                modelo.setContrasena(vista.txtContrasena.getText());
                modelo.setRol(vista.txtRol.getText());
                if (consultas.modificar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro modificado correctamente");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El ID debe ser numero");
            }
        }
        //eliminar
        if (e.getSource() == vista.btnEliminar) {
            try {
                modelo.setIdUsuario(Integer.parseInt(vista.txtIdUsuario.getText()));
                if (consultas.eliminar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El ID debe ser numero");
            }
        }
        //buscar
        if (e.getSource() == vista.btnBuscar) {
            try {
                modelo.setIdUsuario(Integer.parseInt(vista.txtIdUsuario.getText()));
                if (consultas.buscar(modelo)) {
                    vista.txtIdUsuario.setText(String.valueOf(modelo.getIdUsuario()));
                    vista.txtNombre.setText(modelo.getNombre());
                    vista.txtCorreo.setText(modelo.getCorreo());
                    vista.txtContrasena.setText(modelo.getContrasena());
                    vista.txtRol.setText(modelo.getRol());
                } else {
                    JOptionPane.showMessageDialog(null, "Registro no encontrado");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El ID debe ser numero");
            }
        }
        //limpiar
        if (e.getSource() == vista.btnLimpiar) {
            Limpiar();
        }
    }

}
