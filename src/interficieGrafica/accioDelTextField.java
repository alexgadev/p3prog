package interficiegrafica;

import aplicacio.mainAplicacioGrafica;
import javax.swing.*;
import java.awt.event.*;

public class accioDelTextField implements ActionListener {
    
    private mainAplicacioGrafica mGrafica;

    public accioDelTextField(mainAplicacioGrafica mGrafica) {
        this.mGrafica = mGrafica;
    }
    
    public void actionPerformed(ActionEvent evt) {
       JTextField tf = (JTextField) evt.getSource(); //Obtenim referencia del control que ha fet l'element
       
       //Obtenim el text de l'àrea
       String text = tf.getText();

       //Enviem el text al TextArea
       tf.setText("");
    }
}