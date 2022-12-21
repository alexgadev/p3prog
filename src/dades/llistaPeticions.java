package dades;

public class llistaPeticions {
    
    private int nPeticions;
    private Peticio[] llistaPeticio;


    public llistaPeticions(int mida) {
        llistaPeticio = new Peticio[mida];
        nPeticions = 0;
    }

    public Peticio getIessim(int i) {
        Peticio aux = llistaPeticio[i].copia();
        return aux;
    }

    public void afegeixPeticio(Peticio pe){
        if(nPeticions < llistaPeticio.length){
            llistaPeticio[nPeticions] = pe.copia();
            nPeticions++;
        }
    }

    public String toString(){
        String text = "";
        for (int i = 0; i < nPeticions; i++){
            text = text + llistaPeticio[i]+"\n";
        }
        return text;
    }
    
}
