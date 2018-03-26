package edu.ud.informatica.taller2.test;

import edu.ud.informatica.taller2.test.presentacion.Modelo;

public class Main {

    private Modelo modelo;

    public Main() {
        modelo = new Modelo();
        modelo.iniciar();
    }

    public static void main(String[] args) {
	    new Main();
    }
}
