package aplicacio;

import javax.swing.*;
import java.awt.*;

import dades.Usuari;
import interficieGrafica.*;

public class mainAplicacioGrafica extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panell;
    private JTextArea teclat;
    private JButton boto;
    private JLabel etiqueta;
    
    

    public mainAplicacioGrafica(String titol){
        super(titol);
        this.setLocation(100, 100);
        this.setSize(400, 350);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        demanaUsuari();
        this.setVisible(true);
        
        
    }
    
    public void demanaUsuari(){
        accioDelTextField accioText = new accioDelTextField(this);

        this.setLayout(new FlowLayout(FlowLayout.LEFT,5 ,5));
        JLabel etiquetaUsu = new JLabel("Introdueix el nom de l'usuari: ");
        this.add(etiquetaUsu);
        etiquetaUsu.setBackground(Color.WHITE);

        JTextField ompleUsuari = new JTextField("el teu nom...      ", 15);
        ompleUsuari.setForeground(Color.LIGHT_GRAY);
        ompleUsuari.addActionListener(accioText);
        this.add(ompleUsuari);
    }  
        
    public static void main(String[] args) {
        new mainAplicacioGrafica("BENVINGUT/DA");
    }
}

