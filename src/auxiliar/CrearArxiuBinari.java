package auxiliar;
import java.io.*;

import dades.llistaUsuaris;

public class CrearArxiuBinari {
    
    public void CreaArxiuBinari(llistaUsuaris llistaUsers) throws IOException {
        ObjectOutputStream fitxerBinari = new ObjectOutputStream(new FileOutputStream("Usuaris.bin"));

        for(int i = 0; i < llistaUsers.getnUsuaris(); i++){
           fitxerBinari.writeObject(llistaUsers.getIessim(i));
        }
        fitxerBinari.close();
    }
        
}
