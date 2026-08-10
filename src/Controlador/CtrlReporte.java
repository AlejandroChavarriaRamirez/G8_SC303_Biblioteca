/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Reporte;
import Modelo.SentenciasReporte;
import Vista.frmReporte;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class CtrlReporte implements ActionListener {

    private final Reporte modelo;
    private final SentenciasReporte consultas;
    private final frmReporte vista;

    public CtrlReporte(Reporte modelo, SentenciasReporte consultas, frmReporte vista) {
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
        vista.setTitle("Control de Reporte");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public void Limpiar() {
        vista.txtIdReporte.setText("");
        vista.txtTipo.setText("");
        vista.txtFechaGeneracion.setText("");
        vista.txtIdUsuario.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        //guardar
        if (e.getSource() == vista.btnGuardar) {
            try {
                modelo.setIdReporte(Integer.parseInt(vista.txtIdReporte.getText()));
                modelo.setTipo(vista.txtTipo.getText());
                modelo.setFechaGeneracion(vista.txtFechaGeneracion.getText());
                modelo.setIdUsuario(Integer.parseInt(vista.txtIdUsuario.getText()));
                if (consultas.registrar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro guardado correctamente");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Los codigos deben ser numeros");
            }
        }
        //modificar
        if (e.getSource() == vista.btnModificar) {
            try {
                modelo.setIdReporte(Integer.parseInt(vista.txtIdReporte.getText()));
                modelo.setTipo(vista.txtTipo.getText());
                modelo.setFechaGeneracion(vista.txtFechaGeneracion.getText());
                modelo.setIdUsuario(Integer.parseInt(vista.txtIdUsuario.getText()));
                if (consultas.modificar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro modificado correctamente");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Los codigos deben ser numeros");
            }
        }
        //eliminar
        if (e.getSource() == vista.btnEliminar) {
            try {
                modelo.setIdReporte(Integer.parseInt(vista.txtIdReporte.getText()));
                if (consultas.eliminar(modelo)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Los codigos deben ser numeros");
            }
        }
        //buscar
        if (e.getSource() == vista.btnBuscar) {
            try {
                modelo.setIdReporte(Integer.parseInt(vista.txtIdReporte.getText()));
                if (consultas.buscar(modelo)) {
                    vista.txtIdReporte.setText(String.valueOf(modelo.getIdReporte()));
                    vista.txtTipo.setText(modelo.getTipo());
                    vista.txtFechaGeneracion.setText(modelo.getFechaGeneracion());
                    vista.txtIdUsuario.setText(String.valueOf(modelo.getIdUsuario()));
                } else {
                    JOptionPane.showMessageDialog(null, "Registro no encontrado");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Los codigos deben ser numeros");
            }
        }
        //limpiar
        if (e.getSource() == vista.btnLimpiar) {
            Limpiar();
        }
    }

}
