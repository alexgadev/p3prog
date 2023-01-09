/*package interficiegrafica;

import javax.swing.*;

import interficiegrafica.*;

import java.awt.*;

public class finestraIniciar extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panell;
    private JTextArea teclat;
    private JButton boto;
    private JLabel etiqueta;
    
    public finestraIniciar(String titol){
        super(titol);
        this.setLocation(100, 100);
        this.setSize(400, 350);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        demanaUsuari();
        this.setVisible(true); 
    }
    
    public void demanaUsuari(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT,5 ,5));
        JLabel etiquetaUsu = new JLabel("Introdueix el nom de l'usuari: ");
        this.add(etiquetaUsu);
        etiquetaUsu.setBackground(Color.WHITE);

        JTextField ompleUsuari = new JTextField("el teu nom...      ", 15);
        ompleUsuari.setForeground(Color.LIGHT_GRAY);
        //accioDelTextField accioText = new accioDelTextField();
        //ompleUsuari.addActionListener(accioText);
        this.add(ompleUsuari);
    }  

}*/