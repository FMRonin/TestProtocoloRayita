package edu.ud.informatica.taller2.test.presentacion;

import edu.ud.informatica.taller2.test.logica.Sistema;

import java.io.IOException;

public class Modelo implements Runnable{

    private Sistema sistema;
    private Vista ventana;
    private Thread hiloDibujo;


    public Sistema getSistema() {
        if(sistema == null){
            sistema = new Sistema();
        }
        return sistema;
    }

    public Vista getVentana() {
        if(ventana == null){
            ventana = new Vista(this);
        }
        return ventana;
    }

    public void iniciar(){
        getVentana().setSize( 400, 230);
        getVentana().setVisible(true);
    }

    @Override
    public void run() {
        while(getSistema().isConectado()){
            recibirMensajes();
        }
    }

    private void recibirMensajes() {
        if (sistema.getCliente() != null)
        {
            String host = sistema.getCliente().
                    getInetAddress().getHostAddress();
            String mensaje = sistema.getMensaje();

            boolean comandoValido = sistema.getComandoValido();

            ventana.getLb_ipRemota().setText("IP cliente: " + host);
            ventana.getRespuesta().setText("Respuesta:\n" +
                    comandoValido + "\n" + mensaje);

        }
    }

    public void enviarMensaje() {
        try {
            sistema.enviarMensaje(
                    getVentana().getTxf_ipRemota().getText(),
                    getVentana().getMensaje().getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void esperarConexion() {
        try {
            getSistema().ActivarConexion();
            getVentana().getEscuchar().setText("Detener");
            getVentana().getLb_ipRemota().setText("Esperando conexion.");
            getVentana().getTxf_ipRemota().setEnabled(true);
            getVentana().getTxf_ipRemota().setEditable(true);
            getVentana().getMensaje().setEnabled(true);
            getVentana().getMensaje().setEditable(true);
            getVentana().getEnviar().setEnabled(true);
            hiloDibujo = new Thread(this);
            hiloDibujo.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void terminarConexion() {
        try {
            hiloDibujo = null;
            getSistema().setConectado(false);
            getSistema().detenerConexion();
            getVentana().getEscuchar().setText("Escuchar");
            getVentana().getLb_ipRemota().setText("IP Remota: 000.000.000.000");
            getVentana().getRespuesta().setText("");
            getVentana().getTxf_ipRemota().setEnabled(false);
            getVentana().getTxf_ipRemota().setEditable(false);
            getVentana().getMensaje().setEnabled(false);
            getVentana().getMensaje().setEditable(false);
            getVentana().getEnviar().setEnabled(false);
        } catch (IOException ex) {
        }
    }
}
