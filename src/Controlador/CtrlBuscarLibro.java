/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Libro;
import Modelo.SentenciasLibro;
import Vista.frmBuscarLibro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aleja
 */
public class CtrlBuscarLibro implements ActionListener {

    private final SentenciasLibro consultas;
    private final frmBuscarLibro vista;

    public CtrlBuscarLibro(SentenciasLibro consultas, frmBuscarLibro vista) {
        this.consultas = consultas;
        this.vista = vista;
        this.vista.btnBuscar.addActionListener(this);
    }

    public void inicio() {
        vista.setTitle("Buscar Libro");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBuscar) {
            String texto = vista.txtBuscar.getText();
            DefaultTableModel model = (DefaultTableModel) vista.jtLibros.getModel();
            model.setNumRows(0);
            ArrayList<Libro> lista = consultas.buscarTexto(texto);
            for (Libro lib : lista) {
                Object[] fila = {lib.getIdLibro(), lib.getTitulo(), lib.getAutor(), lib.getCategoria(), lib.getEditorial(), lib.getCantidad()};
                model.addRow(fila);
            }
        }
    }

}
