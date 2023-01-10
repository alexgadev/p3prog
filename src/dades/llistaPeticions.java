package dades;

import java.util.Scanner;

public class llistaPeticions {
    
    private int nPeticions;         //Numero de peticions fetes
    private Peticio[] llistaPeticio;            //Llista de les peticions que s'han dut a terme


    public llistaPeticions(int mida) {
        llistaPeticio = new Peticio[mida];
        nPeticions = 0;             //Inicialment suposem que no hi ha cap petició a la llista
    }

    public Peticio getIessim(int i) {
        Peticio aux = llistaPeticio[i].copia();         //Retornem una copia de la petició a la posició "i" de la llista
        return aux;
    }

    public Usuari getIessimUsuari(int i){
        Usuari aux = llistaPeticio[i].getUsuariOfereix();           //Retornem l'usuari que ha iniciat la petició de la posició "i" de la llista
        return aux;
    }

    public void afegeixPeticio(Peticio pe){
        if(nPeticions < llistaPeticio.length){          //Si hi ha espai a la llista de peticions (definida al construir la llista)...
            llistaPeticio[nPeticions] = pe.copia();         //afegim una copia d'aquesta petició a la llista
            nPeticions++;           //Actualitzem el numero de peticions que tenima  al llista
        }
    }

    public llistaPeticions mostraPeticionsPendents(){
        llistaPeticions llistaAux = new llistaPeticions(nPeticions);
        for (int i = 0; i < nPeticions; i++) {
            if (llistaPeticio[i].getResposta() == 0) {
                llistaAux.afegeixPeticio(llistaPeticio[i]);
            }
        }
        return llistaAux;
    }

    public llistaPeticions mostraPeticionsAcceptades() {
        llistaPeticions llistaAux = new llistaPeticions(nPeticions);
        for(int i = 0; i < nPeticions; i++){
            if(llistaPeticio[i].getResposta() == 1){
                llistaAux.afegeixPeticio(llistaPeticio[i]);
            }
        }
        return llistaAux;
    }

    public llistaPeticions mostraPeticionsRefusades() {
        llistaPeticions llistaAux = new llistaPeticions(nPeticions);
        for(int i = 0; i < nPeticions; i++){
            if(llistaPeticio[i].getResposta() == 2){
                llistaAux.afegeixPeticio(llistaPeticio[i]);
            }
        }
        return llistaAux;
    }

    public void respostaPeticio(llistaPeticions llistaPet, int pos){
        Scanner teclat = new Scanner(System.in);
        int val;

        if(llistaPet.getIessim(pos).getResposta() == 1){
            //serv.comptadorInter++;
            //llistaPet.g
            System.out.println("Introdueix la valoració del venedor: ");
            val = teclat.nextInt();
            //pet.setValoracioOfereix(val);
            
            System.out.println("Introdueix la valoració del comprador: ");
            val = teclat.nextInt();
            //pet.setValoracioRep(val);
            teclat.close();
        } 
    }
    
    public String toString(){
        String text = "";
        for (int i = 0; i < nPeticions; i++){
            text = text + llistaPeticio[i]+"\n";
        }
        return text;
    }

    public int getnPeticions() {
        return nPeticions;
    }

    public void setnPeticions(int nPeticions) {
        this.nPeticions = nPeticions;
    }

    public Peticio[] getLlistaPeticio() {
        return llistaPeticio;
    }

    public void setLlistaPeticio(Peticio[] llistaPeticio) {
        this.llistaPeticio = llistaPeticio;
    }
    
}
