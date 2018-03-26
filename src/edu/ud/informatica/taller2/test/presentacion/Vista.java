package edu.ud.informatica.taller2.test.presentacion;

import javax.swing.*;

public class Vista extends JFrame{

    private final Modelo modelo;
    private Controlador controlador;

    public Vista(Modelo modelo) {
        this.modelo = modelo;
        initComponents();
        asignarEventos();
    }

    private void asignarEventos() {
        btn_enviar.addActionListener(getControlador());
        btn_escuchar.addActionListener(getControlador());
    }

    private void initComponents() {

        JSeparator separator = new JSeparator();
        JLabel lb_mensaje = new JLabel();
        JLabel lb_remoto = new JLabel();

        btn_escuchar = new JButton();
        btn_enviar = new JButton();
        txf_mensaje = new JTextField();
        txf_ipRemota = new JTextField();
        txa_respuesta = new JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        lb_ipRemota.setText("IP Remota: 000.000.000.000");
        add(lb_ipRemota);
        lb_ipRemota.setBounds(200,10,180,20);

        btn_escuchar.setText("Escuchar");
        add(btn_escuchar);
        btn_escuchar.setBounds(20,40,150,50);

        txa_respuesta.setText("Respuesta:\nCodigo Correcto\nQDT20180322185635INI");
        txa_respuesta.setEditable(false);
        add(txa_respuesta);
        txa_respuesta.setBounds(200,40,180, 50);

        add(separator);
        separator.setBounds(20,120,360,10);

        lb_mensaje.setText("Mensaje:");
        add(lb_mensaje);
        lb_mensaje.setBounds(20,170,60,20);

        lb_remoto.setText("IP:");
        add(lb_remoto);
        lb_remoto.setBounds(20,140,20,20);

        add(txf_mensaje);
        txf_mensaje.setBounds(80,170,170,20);
        txf_mensaje.setEditable(false);
        txf_mensaje.setEnabled(false);

        add(txf_ipRemota);
        txf_ipRemota.setBounds(50,140,200,20);
        txf_ipRemota.setEnabled(false);
        txf_mensaje.setEditable(false);

        btn_enviar.setText("Enviar");
        add(btn_enviar);
        btn_enviar.setBounds(270,150,100,40);
        btn_enviar.setEnabled(false);

    }

    public Controlador getControlador() {
        if (controlador == null) {
            controlador = new Controlador(this);
        }
        return controlador;
    }

    public Modelo getModelo() {
        return modelo;
    }

    private JButton btn_escuchar;
    private JButton btn_enviar;
    private JTextField txf_mensaje;
    private JTextField txf_ipRemota;
    private JTextArea txa_respuesta;
    private JLabel lb_ipRemota = new JLabel();


    public JButton getEscuchar() {
        return btn_escuchar;
    }

    public JButton getEnviar() {
        return btn_enviar;
    }

    public JLabel getLb_ipRemota() {
        return lb_ipRemota;
    }

    public JTextField getMensaje() {
        return txf_mensaje;
    }

    public JTextArea getRespuesta() {
        return txa_respuesta;
    }

    public
    JTextField getTxf_ipRemota() {
        return txf_ipRemota;
    }
}
