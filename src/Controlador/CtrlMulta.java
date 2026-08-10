/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Multa;
import Modelo.SentenciasMulta;
import Vista.frmMulta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class CtrlMulta implements ActionListener {

    private final Multa modelo;
    private final SentenciasMulta consultas;
    private final frmMulta vista;

    public CtrlMulta(Multa modelo, SentenciasMulta consultas, frmMulta vista) {
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
        vista.setTitle("Control de Multa");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public void Limpiar() {
        vista.txtIdMulta.setText("");
        vista.txtMonto.setText("");
        vista.txtIdDevolucion.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        //guardar
        if (e.getSource() == vista.btnGuardar) {
            try {
                modelo.setIdMulta(Integer.parseInt(vista.txtIdMulta.getText()));
                modelo.setMonto(Integer.parseInt(vista.txtMonto.getText()));
                modelo.setIdDevolucion(Integer.parseInt(vista.txtIdDevolucion.getText()));
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
                modelo.setIdMulta(Integer.parseInt(vista.txtIdMulta.getText()));
                modelo.setMonto(Integer.parseInt(vista.txtMonto.getText()));
                modelo.setIdDevolucion(Integer.parseInt(vista.txtIdDevolucion.getText()));
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
                modelo.setIdMulta(Integer.parseInt(vista.txtIdMulta.getText()));
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
                modelo.setIdMulta(Integer.parseInt(vista.txtIdMulta.getText()));
                if (consultas.buscar(modelo)) {
                    vista.txtIdMulta.setText(String.valueOf(modelo.getIdMulta()));
                    vista.txtMonto.setText(String.valueOf(modelo.getMonto()));
                    vista.txtIdDevolucion.setText(String.valueOf(modelo.getIdDevolucion()));
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
