package auxiliar;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import dades.Usuari;
import dades.llistaUsuaris;

public class carregarUsuaris {
    public llistaUsuaris carregarUsu() {
        ObjectInputStream fitxerBinari;
        Usuari usu;
        llistaUsuaris llistaAux = new llistaUsuaris(100);
        boolean finalFitxer = false;
        
        try{
            fitxerBinari = new ObjectInputStream(new FileInputStream("Usuaris.bin"));
            try{

                while(!finalFitxer){
                    usu = (Usuari)fitxerBinari.readObject();
                    llistaAux.afegirUsuari(usu);
                }
                fitxerBinari.close();

            } catch(EOFException e){
                finalFitxer = true;
            } catch (ClassNotFoundException e){
                System.err.println(e);
            }
        } catch (FileNotFoundException e){
            System.out.println("Fitxer no trobat!");
        } catch (IOException e){
            System.err.println(e);
            e.printStackTrace();
        }
        return llistaAux;
    }
}
