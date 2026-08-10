/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Devolucion;
import Modelo.SentenciasDevolucion;
import Modelo.Prestamo;
import Modelo.SentenciasPrestamo;
import Modelo.Multa;
import Modelo.SentenciasMulta;
import Modelo.SentenciasLibro;
import Vista.frmDevolucion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class CtrlDevolucion implements ActionListener {

    private final Devolucion modelo;
    private final SentenciasDevolucion consultas;
    private final frmDevolucion vista;
    private final SentenciasPrestamo consultasPrestamo = new SentenciasPrestamo();
    private final SentenciasMulta consultasMulta = new SentenciasMulta();
    private final SentenciasLibro consultasLibro = new SentenciasLibro();

    public CtrlDevolucion(Devolucion modelo, SentenciasDevolucion consultas, frmDevolucion vista) {
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
        vista.setTitle("Control de Devolucion");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public void Limpiar() {
        vista.txtIdDevolucion.setText("");
        vista.txtFechaDevolucion.setText("");
        vista.txtIdPrestamo.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        //guardar
        if (e.getSource() == vista.btnGuardar) {
            try {
                modelo.setIdDevolucion(Integer.parseInt(vista.txtIdDevolucion.getText()));
                modelo.setFechaDevolucion(vista.txtFechaDevolucion.getText());
                modelo.setIdPrestamo(Integer.parseInt(vista.txtIdPrestamo.getText()));
                if (consultas.registrar(modelo)) {
                    //buscar el prestamo para comparar la fecha limite
                    Prestamo pre = new Prestamo();
                    pre.setIdPrestamo(modelo.getIdPrestamo());
                    if (consultasPrestamo.buscar(pre)) {
                        //el libro vuelve a estar disponible
                        consultasLibro.actualizarEstado(pre.getIdLibro(), "disponible");
                        //las fechas se escriben en formato aaaa-mm-dd
                        if (modelo.getFechaDevolucion().compareTo(pre.getFechaLimite()) > 0) {
                            Multa mul = new Multa();
                            mul.setIdMulta(modelo.getIdDevolucion());
                            mul.setMonto(1000);
                            mul.setIdDevolucion(modelo.getIdDevolucion());
                            consultasMulta.registrar(mul);
                            JOptionPane.showMessageDialog(null, "Registro guardado. Se genero una multa de 1000 por atraso");
                        } else {
                            JOptionPane.showMessageDialog(null, "Registro guardado correctamente");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Registro guardado correctamente");
                    }
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
                modelo.setIdDevolucion(Integer.parseInt(vista.txtIdDevolucion.getText()));
                modelo.setFechaDevolucion(vista.txtFechaDevolucion.getText());
                modelo.setIdPrestamo(Integer.parseInt(vista.txtIdPrestamo.getText()));
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
                modelo.setIdDevolucion(Integer.parseInt(vista.txtIdDevolucion.getText()));
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
                modelo.setIdDevolucion(Integer.parseInt(vista.txtIdDevolucion.getText()));
                if (consultas.buscar(modelo)) {
                    vista.txtIdDevolucion.setText(String.valueOf(modelo.getIdDevolucion()));
                    vista.txtFechaDevolucion.setText(modelo.getFechaDevolucion());
                    vista.txtIdPrestamo.setText(String.valueOf(modelo.getIdPrestamo()));
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
