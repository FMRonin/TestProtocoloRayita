package edu.ud.informatica.taller2.test.logica;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public
class Sistema implements Runnable{

    static private final int PUERTO_SALIDA = 9090;
    static private final int TIMEOUT = 3000; // 3 segundos

    private int puerto;
    private String ipSalida;
    private String mensaje;
    private ServerSocket serverSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket envio;
    private Socket cliente;
    private Thread hiloConexion;
    private boolean conectado;
    private boolean comandoValido;

    public void ActivarConexion() throws IOException {
        serverSocket = new ServerSocket(PUERTO_SALIDA);
        hiloConexion = new Thread(this);
        hiloConexion.start();
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }


    @Override
    public void run() {
        try {
            conectado = true;
            synchronized (hiloConexion){
                while (conectado) {
                    cliente = serverSocket.accept();
                    cliente.setSoTimeout(TIMEOUT);
                    inputStream = new DataInputStream(cliente.getInputStream());
                    leerMensaje();
                    hiloConexion.wait(500);
                    inputStream.close();
                    cliente.close();
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
    }

    private void leerMensaje() throws IOException
    {
        comandoValido = false;
        byte buffer[] = new byte[256];
        inputStream.read(buffer);
        mensaje = new String(buffer);
        validarComando(mensaje);
    }

    private void validarComando(String mensaje) {
        String fecha = mensaje.substring(3,11);
        String hora = mensaje.substring(11,14);
        String comando = mensaje.substring(17,20);
        String param = null;
        if (mensaje.substring(0,3).equals("QDT")) {
            try {
                int fechaInt = Integer.parseInt(fecha);
                int horaInt = Integer.parseInt(hora);
                if (comando.equals("INI")
                        || comando.equals("SNM")
                        || comando.equals("TUR")
                        || comando.equals("JUG")) {
                    param = mensaje.substring(20);
                    comandoValido = true;
                }
            } catch (NumberFormatException e) {
                comandoValido = false;
            }
        }else if (mensaje.substring(0,2).equals("OK")
                || mensaje.substring(0,2).equals("NK")){
            comandoValido = true;
            param = mensaje.substring(3);
        }else {
            comandoValido = false;
        }
        System.out.println(param);

    }

    public void detenerConexion() throws IOException {
        conectado = false;
        hiloConexion = null;
        serverSocket.close();
    }


    public boolean isConectado() {
        return conectado;
    }

    public void enviarMensaje(String ip, String msg) throws IOException {

        envio = new Socket(ip, 9090);

        outputStream = new DataOutputStream(envio.getOutputStream());
        outputStream.write(msg.getBytes());

        outputStream.close();
        envio.close();
        outputStream.write(msg.getBytes());
    }

    public Socket getCliente() {
        return cliente;
    }

    public
    ServerSocket getServerSocket() {
        return serverSocket;
    }

    public
    String getMensaje() {
        return mensaje;
    }

    public
    boolean getComandoValido() {
        return comandoValido;
    }
}
