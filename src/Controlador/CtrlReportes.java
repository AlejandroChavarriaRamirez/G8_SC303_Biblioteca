/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Libro;
import Modelo.SentenciasLibro;
import Modelo.SentenciasPrestamo;
import Modelo.Usuario;
import Modelo.SentenciasUsuario;
import Modelo.Multa;
import Modelo.SentenciasMulta;
import Modelo.Devolucion;
import Modelo.SentenciasDevolucion;
import Modelo.Prestamo;
import Vista.frmReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aleja
 */
public class CtrlReportes implements ActionListener {

    private final frmReportes vista;
    private final SentenciasLibro consultasLibro = new SentenciasLibro();
    private final SentenciasPrestamo consultasPrestamo = new SentenciasPrestamo();
    private final SentenciasUsuario consultasUsuario = new SentenciasUsuario();
    private final SentenciasMulta consultasMulta = new SentenciasMulta();
    private final SentenciasDevolucion consultasDevolucion = new SentenciasDevolucion();

    public CtrlReportes(frmReportes vista) {
        this.vista = vista;
        this.vista.btnLibrosMasPrestados.addActionListener(this);
        this.vista.btnUsuariosAtrasos.addActionListener(this);
    }

    public void inicio() {
        vista.setTitle("Reportes");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //reporte de libros mas prestados
        if (e.getSource() == vista.btnLibrosMasPrestados) {
            String[] columnas = {"Titulo", "Veces Prestado"};
            DefaultTableModel model = new DefaultTableModel(columnas, 0);
            ArrayList<Libro> libros = consultasLibro.todos();
            int n = libros.size();
            int[] veces = new int[n];
            for (int i = 0; i < n; i++) {
                veces[i] = consultasPrestamo.contarPrestamos(libros.get(i).getIdLibro());
            }
            //ordenar de mayor a menor con burbuja
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    if (veces[j] < veces[j + 1]) {
                        int tempV = veces[j];
                        veces[j] = veces[j + 1];
                        veces[j + 1] = tempV;
                        Libro tempL = libros.get(j);
                        libros.set(j, libros.get(j + 1));
                        libros.set(j + 1, tempL);
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                Object[] fila = {libros.get(i).getTitulo(), veces[i]};
                model.addRow(fila);
            }
            vista.jtReporte.setModel(model);
        }
        //reporte de usuarios con atrasos
        if (e.getSource() == vista.btnUsuariosAtrasos) {
            String[] columnas = {"Nombre", "Correo"};
            DefaultTableModel model = new DefaultTableModel(columnas, 0);
            ArrayList<Multa> multas = consultasMulta.todos();
            ArrayList<Usuario> encontrados = new ArrayList<>();
            for (Multa mul : multas) {
                Devolucion dev = new Devolucion();
                dev.setIdDevolucion(mul.getIdDevolucion());
                if (consultasDevolucion.buscar(dev)) {
                    Prestamo pre = new Prestamo();
                    pre.setIdPrestamo(dev.getIdPrestamo());
                    if (consultasPrestamo.buscar(pre)) {
                        Usuario usu = new Usuario();
                        usu.setIdUsuario(pre.getIdUsuario());
                        if (consultasUsuario.buscar(usu)) {
                            boolean repetido = false;
                            for (Usuario u : encontrados) {
                                if (u.getIdUsuario() == usu.getIdUsuario()) {
                                    repetido = true;
                                }
                            }
                            if (!repetido) {
                                encontrados.add(usu);
                            }
                        }
                    }
                }
            }
            for (Usuario usu : encontrados) {
                Object[] fila = {usu.getNombre(), usu.getCorreo()};
                model.addRow(fila);
            }
            vista.jtReporte.setModel(model);
        }
    }

}
