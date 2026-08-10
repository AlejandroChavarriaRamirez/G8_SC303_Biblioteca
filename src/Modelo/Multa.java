/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author aleja
 */
public class Multa {

    private int idMulta;
    private int monto;
    private int idDevolucion;

    public Multa() {
        this.idMulta = 0;
        this.monto = 0;
        this.idDevolucion = 0;
    }

    public Multa(int idMulta, int monto, int idDevolucion) {
        this.idMulta = idMulta;
        this.monto = monto;
        this.idDevolucion = idDevolucion;
    }

    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

}
