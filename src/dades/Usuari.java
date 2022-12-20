package dades;

public class Usuari {
    
    private String alies;
    private String correu;
    private String codiPostal;
    private int numeroIntercanvis;
    private String productesOferits;

    
    public Usuari (String alies, String correu, String codiPos){
        this.alies = alies;
        this.correu = correu;
        codiPostal = codiPos;
        numeroIntercanvis = 0;
        productesOferits = null;
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


    public String getProductesOferits() {
        return productesOferits;
    }


    public void setProductesOferits(String productesOferits) {
        this.productesOferits = productesOferits;
    }

    
    public Usuari copia(){
        Usuari aux;
        aux = new Usuari(alies, correu, codiPostal);
        aux.numeroIntercanvis = 0;
        aux.productesOferits = null;
        return (aux);
    }

    @Override
    public String toString() {
        return "Usuaris [alies=" + alies + ", correu=" + correu + ", codiPostal=" + codiPostal + ", numeroIntercanvis="
                + numeroIntercanvis + ", productesOferits=" + productesOferits + "]";
    }

}


