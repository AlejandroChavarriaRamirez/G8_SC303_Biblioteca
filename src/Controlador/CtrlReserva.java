/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Reserva;
import Modelo.SentenciasReserva;
import Vista.frmReserva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class CtrlReserva implements ActionListener {

    private final Reserva modelo;
    private final SentenciasReserva consultas;
    private final frmReserva vista;

    public CtrlReserva(Reserva modelo, SentenciasReserva consultas, frmReserva vista) {
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
        vista.setTitle("Control de Reserva");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public void Limpiar() {
        vista.txtIdReserva.setText("");
        vista.txtFechaReserva.setText("");
        vista.txtEstado.setText("");
        vista.txtIdLibro.setText("");
        vista.txtIdUsuario.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        //guardar
        if (e.getSource() == vista.btnGuardar) {
            try {
                modelo.setIdReserva(Integer.parseInt(vista.txtIdReserva.getText()));
                modelo.setFechaReserva(vista.txtFechaReserva.getText());
                modelo.setEstado(vista.txtEstado.getText());
                modelo.setIdLibro(Integer.parseInt(vista.txtIdLibro.getText()));
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
                modelo.setIdReserva(Integer.parseInt(vista.txtIdReserva.getText()));
                modelo.setFechaReserva(vista.txtFechaReserva.getText());
                modelo.setEstado(vista.txtEstado.getText());
                modelo.setIdLibro(Integer.parseInt(vista.txtIdLibro.getText()));
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
                modelo.setIdReserva(Integer.parseInt(vista.txtIdReserva.getText()));
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
                modelo.setIdReserva(Integer.parseInt(vista.txtIdReserva.getText()));
                if (consultas.buscar(modelo)) {
                    vista.txtIdReserva.setText(String.valueOf(modelo.getIdReserva()));
                    vista.txtFechaReserva.setText(modelo.getFechaReserva());
                    vista.txtEstado.setText(modelo.getEstado());
                    vista.txtIdLibro.setText(String.valueOf(modelo.getIdLibro()));
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
