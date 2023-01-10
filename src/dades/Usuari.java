package dades;

import java.io.Serializable;

public class Usuari implements Serializable {
    
    private String alies;           //Nom d'usuari
    private String correu;          //Correu electronic de l'usuari
    private String codiPostal;          //Codi Postal
    private int numeroIntercanvis, numProdUsuari;       //Numero d'intercanvis fets per l'usuari i numero de productes que té l'usuari a l'aplicació
    private Producte[] arrayProd;           //Llista amb tots els productes que tingui un usuari a l'aplicació
    
    public Usuari (String alies, String correu, String codiPos){
        this.alies = alies;                     
        this.correu = correu;
        codiPostal = codiPos;
        numeroIntercanvis = 0;          //Inicialment suposem que l'usuari no ha fet cap intercanvi
        numProdUsuari = 0;              //Inicialment suposem que l'usuari no té cap producte a l'aplicació
        arrayProd = new Producte[50];
    }

    //Mètode que afegeix un producte a un usuari en específic
    public Producte afegeixProducteUsu(Producte prod){
        if(numProdUsuari < arrayProd.length){           //Si hi ha espai a la llista de productes que té l'usuari (l'usuari té menys de 50 productes)...
            arrayProd[numProdUsuari] = prod.copia();    //...afegim el producte a la llista
            numProdUsuari++;            //Indiquem que l'usuari té un producte més a la seva disposició
        }
        return prod.copia();
    }

    public boolean pertanyProducte(Usuari usu, String producte){
        boolean pertany = false;
        int i = 0;
        while(!pertany){            //Recorrem la llista de productes de l'usuari buscant si és el propietari del poducte indicat
            if(usu.arrayProd[i].getNomProd().equals(producte)){         
                pertany = true;         //retornem cert si el producte indicat és propietat de l'usuari indicat
            }
            i++;
        }
        return pertany;
    }

    public String getAlies() {
        return alies;
    }


    public void setAlies(String alies) {
        this.alies = alies;
    }


    public String getCorreu() {
        return correu;
    }


    public void setCorreu(String correu) {
        this.correu = correu;
    }


    public String getCodiPostal() {
        return codiPostal;
    }


    public void setCodiPostal(String codiPostal) {
        this.codiPostal = codiPostal;
    }


    public int getNumeroIntercanvis() {
        return numeroIntercanvis;
    }


    public void setNumeroIntercanvis(int numeroIntercanvis) {
        this.numeroIntercanvis = numeroIntercanvis;
    }


    public Usuari copia(){
        Usuari aux;
        aux = new Usuari(alies, correu, codiPostal);
        aux.numeroIntercanvis = this.getNumeroIntercanvis();        //Copiem el numero d'intercanvis
        aux.arrayProd = this.getArrayProd();                //Copiem la llista de productes de l'usuari
        return (aux);
    }

    public Producte[] getArrayProd() {
        return arrayProd;
    }


    public void setArrayProd(Producte[] arrayProd) {
        this.arrayProd = arrayProd;
    }


    public int getNumProdUsuari() {
        return numProdUsuari;
    }

    public void setNumProdUsuari(int numProdUsuari) {
        this.numProdUsuari = numProdUsuari;
    }


    @Override
    public String toString() {
        String text = "";
        
        if(numProdUsuari > 0){
            for(int i = 0; i < numProdUsuari; i++){
                text = text + arrayProd[i];
            }
        } else if (numProdUsuari == 0){
            text = "0";
        }
        return "Usuari [alies=" + alies + ", correu=" + correu + ", codiPostal=" + codiPostal + ", numeroIntercanvis="
                + numeroIntercanvis + ", numProdUsuari=" + numProdUsuari + ", arrayProd=" + text + "]";
    }

}


