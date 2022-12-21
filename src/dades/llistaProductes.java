package dades;

public class llistaProductes {

    private int nProductes;
    private Producte[] llistaProd;

    public llistaProductes(int mida) {
        llistaProd = new Producte[mida];
        nProductes = 0;
    }

    public void afegeixProducte(Producte prod) {
        if (nProductes < llistaProd.length) {
            llistaProd[nProductes] = prod.copia();
            nProductes++;
        }
    }

    public void eliminaProducteFisic(Producte prod) {
        for (int i = 0; i < nProductes; i++) {
            if (llistaProd[i].getTipusProd().equalsIgnoreCase("be") && llistaProd[i].getIntercanviat() == false) {
                llistaProd[i] = llistaProd[i+1];
                for (int j = i; j < nProductes-1; j++) {
                    llistaProd[j] = llistaProd[j + 1];
                }
                nProductes--;
            }
        }
    }

    public String toString() {
        String text = "";
        for (int i = 0; i < nProductes; i++) {
            text = text + llistaProd[i] + "\n";
        }
        return text;
    }

}
