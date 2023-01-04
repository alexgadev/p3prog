package auxiliar;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import dades.llistaPeticions;

public class escriuPeticions {
    public void escriurePeticions(llistaPeticions llistaPet) throws IOException {
        try{
            BufferedWriter fitxerPeticions = new BufferedWriter(new FileWriter("Peticions.txt"));

            for (int i = 0; i < llistaPet.getnPeticions(); i++){
                fitxerPeticions.write(llistaPet.getIessim(i).getCodi()+";"+llistaPet.getIessim(i).getUsuariOfereix()+";"
                    +llistaPet.getIessim(i).getUsuariRep()+";"+llistaPet.getIessim(i).getDesitjat()+";"+llistaPet.getIessim(i).getOferit());
            }
            fitxerPeticions.close();
        } catch (FileNotFoundException e){
            System.err.println("Fitxer no trobat!");
        }
    }
}
