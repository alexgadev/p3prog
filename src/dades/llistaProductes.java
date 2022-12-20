package dades;

public class llistaProductes {
    
    private int nProductes;
    private Producte[] llistaProd;

    public llistaProductes(int mida){
        llistaProd = new Producte[mida];
        nProductes = 0;
    }

    public void afegeixProducte(Producte prod){
        if(nProductes < llistaProd.length){
            llistaProd[nProductes] = prod.copia();
            nProductes++;
        }
    }

    public String toString(){
        String text = "";
        for (int i = 0; i < nProductes; i++){
            text = text + llistaProd[i]+"\n";
        }
        return text;
    }
    

}
