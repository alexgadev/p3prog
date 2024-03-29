package dades;

import java.util.Scanner;

public class llistaProductes {
    Scanner teclat = new Scanner(System.in);
    private int nProductes;
    private Producte[] llistaProd;

    public llistaProductes(int mida) {
        llistaProd = new Producte[100];
        nProductes = 0;
    }
    
    public void afegeixServei(Producte prod) { 
        if(nProductes < llistaProd.length) {
            llistaProd[nProductes] = ((Servei)prod).copia();
            nProductes++;
        }
    }
    
    public void afegeixBe(Producte prod){
        if(nProductes == 0){
            llistaProd[nProductes] = ((Be)prod).copia();
            nProductes++;
        } else {
            if(nProductes < llistaProd.length){
                llistaProd[nProductes] = ((Be)prod).copia(); //TODO volver aqui sino
                nProductes++;
            }
        }
    }

    public void eliminaProducteFisic(Be b) {
        Scanner teclat = new Scanner(System.in);
        System.out.println("Introdueix el nom del producte: ");
        String nom = teclat.nextLine();
        System.out.println("Introdueix la descripció: ");
        String desc = teclat.nextLine();
        System.out.println("Introdueix la data d'oferta: [dd/mm/aaaa]");
        String dataOferta = teclat.nextLine();
        teclat.close();

        String[] dataSplit = dataOferta.split("/");
        Data dataProducte = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
        
        for (int i = 0; i < nProductes; i++) {
            if ((llistaProd[i] instanceof Be) && (llistaProd[i].getIntercanviat() == false)
                && llistaProd[i].getNomProd().equalsIgnoreCase(nom) && llistaProd[i].getDescripcio().equalsIgnoreCase(desc)
                    && llistaProd[i].getDataOferta().equals(dataProducte)) {
                llistaProd[i] = llistaProd[i+1];
                for (int j = i; j < nProductes-1; j++) {
                    llistaProd[j] = llistaProd[j + 1];
                }
                nProductes--;
            }
        }
    }

    public void donarDeBaixaServei(Servei serv) {
        Scanner teclat = new Scanner(System.in);
        System.out.println("Introdueix el nom del producte: ");
        String nom = teclat.nextLine();
        System.out.println("Introdueix la descripció: ");
        String desc = teclat.nextLine();
        System.out.println("Introdueix data de finalització de servei: [dd/mm/aaaa]");
        String data = teclat.nextLine();
        teclat.close();
        String[] dataSplit = data.split("/");
        Data dataFiServei = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
        
        for(int i = 0; i < nProductes; i++){
            if ((llistaProd[i] instanceof Servei) && llistaProd[i].getDataOferta().equals(dataFiServei) 
                && llistaProd[i].getNomProd().equals(nom) && llistaProd[i].getDescripcio().equals(desc)){
                ((Servei)llistaProd[i]).dataFiOferiment = dataFiServei;
                ((Servei)llistaProd[i]).donatDeBaixa = true;
            }
        }
    }

    public void afegeixProducteFisic(Producte prod) {
        if ((nProductes < llistaProd.length) && (prod instanceof Be)) {
            llistaProd[nProductes] = ((Be)prod).copia();
            nProductes++;
        }
    }

    public void afegeixProducteServei(Producte prod) {
        if ((nProductes < llistaProd.length) && (prod instanceof Servei)) {
            llistaProd[nProductes] = ((Servei)prod).copia();
            nProductes++;
        }
    }
    
    public Producte serveiMesIntercanvis() {
        Servei serveiMes = null;

        int j = 0;
        for(int i = 0; i < nProductes; i++) {
            if(llistaProd[i] instanceof Servei) { 
                while( !(llistaProd[j+1] instanceof Servei)) {
                    j++;
                }
                if(llistaProd[j].getComptadorInter() < llistaProd[i].getComptadorInter()) {
                    serveiMes = ((Servei)llistaProd[j]).copia();
                    i = j;
                    j++;
                }
            }
        }
        return serveiMes;
    }

    public String toString() {
        String text = "";
        for (int i = 0; i < nProductes; i++) {
            text = text + llistaProd[i] + "\n";
        }
        return text;
    }


    public llistaProductes serveisActius(){
        llistaProductes aux = new llistaProductes(nProductes);
        for(int i = 0; i<nProductes; i++){
            if((llistaProd[i] instanceof Servei) && ((Servei)llistaProd[i]).donatDeBaixa == false){
                aux.afegeixServei(llistaProd[i]);
            }
        }
        return aux;
    }

    public llistaProductes bensActius() {
        llistaProductes aux = new llistaProductes(nProductes);
        for(int i = 0; i < nProductes; i++) {
            if(llistaProd[i] instanceof Be && llistaProd[i].getIntercanviat() == false) {
                aux.afegeixBe(llistaProd[i]);
            }
        }
        return aux;
    }

    public Servei buscaServei(Usuari usu, llistaProductes llistaProd){
        boolean trobat = false;
        String resp;
        int i = 0;
        Servei serv = null;

        System.out.println("Indica el nom del servei: ");
        resp = teclat.nextLine();
        if(usu.pertanyProducte(usu, resp)){
            while(!trobat){
                if(llistaProd.getIessim(i).getNomProd().equalsIgnoreCase(resp)){
                    trobat = true;
                    serv = ((Servei)llistaProd.getIessimServei(i));
                }
                i++;
            }
        }
        return serv;
    }

    public Be buscaBe(Usuari usu, llistaProductes llistaProd){
        Be be = null;
        int i = 0;
        String resp;
        boolean trobat = false;

        System.out.println("Indica el nom del be: ");
        resp = teclat.nextLine();
        if(usu.pertanyProducte(usu, resp)){
            while(!trobat){
                if(llistaProd.getIessim(i).getNomProd().equalsIgnoreCase(resp)){
                    trobat = true;
                    be = ((Be)llistaProd.getIessimBe(i));
                }
                i++;
            }
        }
        return be;
    }

    public int getnProductes() {
        return nProductes;
    }

    public void setnProductes(int nProductes) {
        this.nProductes = nProductes;
    }

    public Producte[] getLlistaProd() {
        return llistaProd;
    }

    public void setLlistaProd(Producte[] llistaProd) {
        this.llistaProd = llistaProd;
    }
    
    public Producte getIessimServei(int i){
        Servei aux = ((Servei)llistaProd[i]).copia();
        return aux;
    }
    
    public Producte getIessimBe(int i){
        Be aux = ((Be)llistaProd[i]).copia();
        return aux;
    }

    public Producte getIessim(int i){
        Producte aux = llistaProd[i].copia();
        return aux;
    }
}
