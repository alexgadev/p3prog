package dades;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class llistaUsuaris {
    private int nUsuaris;
    private Usuari[] llistaUsuaris;

    public llistaUsuaris(int mesura){
        nUsuaris = 0;
        llistaUsuaris = new Usuari[mesura];
    }

    public void afegirUsuari(Usuari usuari){
        if(nUsuaris == 0){
            llistaUsuaris[nUsuaris] = usuari.copia();
            nUsuaris++;
        } else {
            if(!comprovaIgualtats(usuari)){
                llistaUsuaris[nUsuaris] = usuari.copia();
                nUsuaris++;
            } 
        }
    }

    public boolean comprovaIgualtats(Usuari usuariAux){
        boolean igual = false;
        int i = 0;
        while(!igual && i < nUsuaris){
            if (llistaUsuaris[i].getAlies().equals(usuariAux.getAlies()) && llistaUsuaris[i].getCorreu().equals(usuariAux.getCorreu())) {
                igual = true;
                System.out.println("Nom d'usuari: "+usuariAux.getAlies()+" i correu: "+usuariAux.getCorreu()+" repetits!");
            } else if (llistaUsuaris[i].getCorreu().equals(usuariAux.getCorreu())){
                igual = true;
                System.out.println("Correu de l'usuari: "+usuariAux.getCorreu()+" repetit! ");
            } else if(llistaUsuaris[i].getAlies().equals(usuariAux.getAlies())) {
                igual = true;
                System.out.println("Nom d'usuari: "+usuariAux.getAlies()+" repetit! ");
            }
            i++;
        }
        return igual;
    }

    /**
     * Procediment que retorna una llista auxiliar amb els usuaris que superin el llindar. 
     * @param llindar llindar que l'usuari indica per paràmetre 
     * @param pet petició 
     * @return
     */
    /* 
    public llistaUsuaris valoUsuaris (int llindar, Peticio pet){
        Usuari aux =new Usuari(null, null, null); 
        llistaUsuaris llistaAux= new llistaUsuaris(nUsuaris);
        for(int i = 0; i < nUsuaris; i++){
           if (pet.getValoracioOfereix() > llindar){
                aux = pet.getUsuariOfereix();
                llistaAux.afegirUsuari(aux);
            }
            if (pet.getValoracioRep() > llindar){
                aux = pet.getUsuariRep();
                llistaAux.afegirUsuari(aux);
            }
        }
        return llistaAux;
    }
*/
    public String toString(){
        String text = "";
        for (int i = 0; i < nUsuaris; i++){
            text = text + llistaUsuaris[i]+"\n";
        }
        return text;
    }

    public static void storeData(Usuari[] llistaUsuaris){
        ObjectOutputStream outputFile;
        try{
            outputFile = new ObjectOutputStream(new FileOutputStream("usuaris.bin"));
            for (int i = 0; i < llistaUsuaris.length; i++){
                outputFile.writeObject(llistaUsuaris[i]);
            }
            outputFile.close();
        } catch (IOException e){
            System.out.println("Error en l'arxiu de sortida!");
        }
    }

    public int getnUsuaris() {
        return nUsuaris;
    }

    public void setnUsuaris(int nUsuaris) {
        this.nUsuaris = nUsuaris;
    }

    public Usuari[] getLlistaUsuaris() {
        return llistaUsuaris;
    }

    public void setLlistaUsuaris(Usuari[] llistaUsuaris) {
        this.llistaUsuaris = llistaUsuaris;
    }

    public Usuari getIessim(int i){
        Usuari aux = llistaUsuaris[i].copia();
        return aux;
    }

    public boolean comprovaUsuari(String alies){
        boolean trobat = false;
        int i = 0;
        while(i<nUsuaris && !trobat){
            if(llistaUsuaris[i].getAlies().equalsIgnoreCase(alies)){
                trobat = true;
            }
            i++;
        }
        return trobat;
    }

    public int getPosicioUsuari(String alies, llistaUsuaris llistaUsu){
        int pos = 0;
        for (int i = 0; i<nUsuaris; i++){
            if(alies.equals(llistaUsu.getIessim(i).getAlies())){
                pos = i;
            }
        }
        return pos;
    }
    
}
