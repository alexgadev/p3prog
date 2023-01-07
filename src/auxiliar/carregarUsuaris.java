package auxiliar;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import dades.Usuari;
import dades.llistaUsuaris;

public class carregarUsuaris {
    public llistaUsuaris carregarUsu() throws IOException {
        llistaUsuaris llistaAux = new llistaUsuaris(100);
        boolean finalFitxer = false;
        ObjectInputStream fitxerBinari;
        Usuari usu;
        
        try{
            fitxerBinari = new ObjectInputStream(new FileInputStream("Usuaris.bin"));
            try{

                while(!finalFitxer){
                    usu = (Usuari)fitxerBinari.readObject();
                    llistaAux.afegirUsuari(usu);
                }

            } catch(EOFException e){
                finalFitxer = true;
            } catch (ClassNotFoundException e){
                System.err.println(e);
            }
        } catch (FileNotFoundException e){
            System.out.println("Fitxer no trobat!");
        }
        return llistaAux;
    }
}
