package aplicacio;

import javax.swing.*;
import java.awt.*;

import interficiegrafica.*;
        
public class mainAplicacioGrafica extends JFrame {
    private static final long serialVersionUID = 1L;
    accioDelTextField accioBoto = new accioDelTextField(this);
    //private JPanel panell;
    //private JTextArea teclat;
    //private JButton boto;
    //private JLabel etiqueta;

    public mainAplicacioGrafica(String titol) {
        super(titol);
        this.setLocation(100, 200);
        this.setSize(500,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void demanaUsuari(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JLabel etiquetaUsu = new JLabel("Introdueix el nom de l'usuari: ");
        this.add(etiquetaUsu);
        etiquetaUsu.setBackground(Color.WHITE);
        
        JTextField ompleUsuari = new JTextField("el teu nom...      ", 15);
        ompleUsuari.setForeground(Color.LIGHT_GRAY);
        ompleUsuari.addActionListener(accioBoto);
        this.add(ompleUsuari);
    }
    
    public static void main(String[] args) {
        new mainAplicacioGrafica("BENVINGUT/DA");
        
    }
}
