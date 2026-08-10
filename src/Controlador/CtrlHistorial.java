/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
 
import Modelo.Prestamo;
import Modelo.SentenciasPrestamo;
import Vista.frmHistorial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
 
/**
 *
 * @author aleja
 */
public class CtrlHistorial implements ActionListener {
 
    private final SentenciasPrestamo consultas;
    private final frmHistorial vista;
 
    public CtrlHistorial(SentenciasPrestamo consultas, frmHistorial vista) {
        this.consultas = consultas;
        this.vista = vista;
        this.vista.btnBuscar.addActionListener(this);
    }
 
    public void inicio() {
        vista.setTitle("Historial de Prestamos");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBuscar) {
            try {
                int idUsuario = Integer.parseInt(vista.txtIdUsuario.getText());
                DefaultTableModel model = (DefaultTableModel) vista.jtHistorial.getModel();
                model.setNumRows(0);
                ArrayList<Prestamo> lista = consultas.buscarPorUsuario(idUsuario);
                for (Prestamo pre : lista) {
                    Object[] fila = {pre.getIdPrestamo(), pre.getFechaEntrega(), pre.getFechaLimite(), pre.getIdLibro()};
                    model.addRow(fila);
                }
                if (lista.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El estudiante no tiene prestamos registrados");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "El ID debe ser numero");
            }
        }
    }
 
}
