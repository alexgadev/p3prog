package auxiliar;

import java.io.*;

import dades.Be;
import dades.Producte;
import dades.Servei;
import dades.llistaProductes;

public class escriuProductes {
    public void escriureProductes(llistaProductes llistaProd) throws IOException {
        try{
            BufferedWriter fitxerProd = new BufferedWriter(new FileWriter("Productes.txt"));
            Producte prodAux;

            for (int i = 0; i < llistaProd.getnProductes(); i++){
                prodAux = llistaProd.getIessim(i);
                if(prodAux instanceof Servei){
                    fitxerProd.write("ser;"+llistaProd.getIessimServei(i).getNomProd()+";"+llistaProd.getIessimServei(i).getDescripcio()+";"
                        +llistaProd.getIessimServei(i).getDataOferta()+";"+((Servei)llistaProd.getIessimServei(i)).getDataFiOferiment()+"\n");
                } else {
                    fitxerProd.write("be;"+llistaProd.getIessimBe(i).getNomProd()+";"+llistaProd.getIessimBe(i).getDescripcio()+";"
                        +llistaProd.getIessimBe(i).getDataOferta()+";"+((Be)llistaProd.getIessimBe(i)).getAmplada()+";"
                            +((Be)llistaProd.getIessimBe(i)).getAlçada()+";"+((Be)llistaProd.getIessimBe(i)).getFons()+";"+((Be)llistaProd.getIessimBe(i)).getPes()+"\n");
                }
            }
            fitxerProd.close();
        } catch (FileNotFoundException e){
            System.err.println("Fitxer no trobat");
        } 
    }
}
