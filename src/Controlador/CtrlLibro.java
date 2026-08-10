/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Libro;
import Modelo.SentenciasLibro;
import Vista.frmLibro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class CtrlLibro implements ActionListener {

    private final Libro modelo;
    private final SentenciasLibro consultas;
    private final frmLibro vista;

    public CtrlLibro(Libro modelo, SentenciasLibro consultas, frmLibro vista) {
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
        vista.setTitle("Control de Libro");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public void Limpiar() {
        vista.txtIdLibro.setText("");
        vista.txtTitulo.setText("");
        vista.txtAutor.setText("");
        vista.txtCategoria.setText("");
        vista.txtEditorial.setText("");
        vista.txtCantidad.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        //guardar
        if (e.getSource() == vista.btnGuardar) {
            try {
                modelo.setIdLibro(Integer.parseInt(vista.txtIdLibro.getText()));
                modelo.setTitulo(vista.txtTitulo.getText());
                modelo.setAutor(vista.txtAutor.getText());
                modelo.setCategoria(vista.txtCategoria.getText());
                modelo.setEditorial(vista.txtEditorial.getText());
                modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));
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
                modelo.setIdLibro(Integer.parseInt(vista.txtIdLibro.getText()));
                modelo.setTitulo(vista.txtTitulo.getText());
                modelo.setAutor(vista.txtAutor.getText());
                modelo.setCategoria(vista.txtCategoria.getText());
                modelo.setEditorial(vista.txtEditorial.getText());
                modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));
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
                modelo.setIdLibro(Integer.parseInt(vista.txtIdLibro.getText()));
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
                modelo.setIdLibro(Integer.parseInt(vista.txtIdLibro.getText()));
                if (consultas.buscar(modelo)) {
                    vista.txtIdLibro.setText(String.valueOf(modelo.getIdLibro()));
                    vista.txtTitulo.setText(modelo.getTitulo());
                    vista.txtAutor.setText(modelo.getAutor());
                    vista.txtCategoria.setText(modelo.getCategoria());
                    vista.txtEditorial.setText(modelo.getEditorial());
                    vista.txtCantidad.setText(String.valueOf(modelo.getCantidad()));
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
