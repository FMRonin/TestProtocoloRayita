package edu.ud.informatica.taller2.test.presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener{

    private final Vista ventana;
    private Modelo modelo;

    public Controlador(Vista aThis) {
        ventana = aThis;
        modelo = ventana.getModelo();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        JButton boton;
        boton = (JButton) e.getSource();
        if(boton == ventana.getEnviar()){
            modelo.enviarMensaje();
        }else{
            if (ventana.getEscuchar().getText().equals("Escuchar")) {
                modelo.esperarConexion();
            }else{
                modelo.terminarConexion();
            }
        }
    }
}
