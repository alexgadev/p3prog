package aplicacio;

import javax.swing.*;
import java.awt.*;

import dades.Usuari;

public class mainAplicacioGrafica extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panell;
    private JTextArea teclat;
    private JButton boto;
    private JLabel etiqueta;

    public mainAplicacioGrafica(String titol){
        super(titol);
        this.setLocation(100, 200);
        this.setSize(500, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel etiqueta=new JLabel("Indica el teu codi d'usuari");
        this.add(etiqueta);
        JTextField nom=new JTextField(25);
        this.add(nom);
    }

    public void registraUsuari() {
        
        
    }
    
    public static void main(String[] args) {
        new mainAplicacioGrafica("BENVINGUT/DA");
    }
}

