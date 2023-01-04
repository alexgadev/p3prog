package auxiliar;

import java.io.*;

import dades.Be;
import dades.Producte;
import dades.Servei;
import dades.llistaProductes;

public class escriuProductes {
    public void escriureProductes(llistaProductes llistaProd, Producte prod) throws IOException {
        try{
            BufferedWriter fitxerProd = new BufferedWriter(new FileWriter("Productes.txt"));
        
            for (int i = 0; i < llistaProd.getnProductes(); i++){
                if(prod instanceof Servei){
                    fitxerProd.write("ser;"+llistaProd.getIessimServei(i).getNomProd()+";"+llistaProd.getIessimServei(i).getDescripcio()+";"
                        +llistaProd.getIessimServei(i).getDataOferta()+";"+((Servei)llistaProd.getIessimServei(i)).getDataFiOferiment());
                } else {
                    fitxerProd.write("be;"+llistaProd.getIessimBe(i).getNomProd()+";"+llistaProd.getIessimBe(i).getDescripcio()+";"
                        +llistaProd.getIessimBe(i).getDataOferta()+";"+((Be)llistaProd.getIessimBe(i)).getAmplada()+";"
                            +((Be)llistaProd.getIessimBe(i)).getAlçada()+";"+((Be)llistaProd.getIessimBe(i)).getFons()+";"+((Be)llistaProd.getIessimBe(i)).getPes());
                }
            }
            fitxerProd.close();
        } catch (FileNotFoundException e){
            System.err.println("Fitxer no trobat");
        } 
    }
}
