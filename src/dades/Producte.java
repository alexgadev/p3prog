package dades;

import java.util.Date;

public class Producte {
    
    private String nomProd;
    private String descripcio;
    private String tipusProd;
    private Date dataOferta;

    public Producte(String nom, String desc, String tipus, Date dataOferta){
        nomProd = nom;
        descripcio = desc;
        tipusProd = tipus;
        this.dataOferta = dataOferta;
    }

    public void modificaDescripcio(String desc){
        if(descripcio.equals(desc)){
            System.out.println("Descripció no vàlida");
        } else {
            descripcio = desc;
        }
    }

    public Producte copia(){
        Producte aux = new Producte(nomProd, descripcio, tipusProd, dataOferta);
        return aux;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getTipusProd() {
        return tipusProd;
    }

    public void setTipusProd(String tipusProd) {
        this.tipusProd = tipusProd;
    }

    public Date getDataOferta() {
        return dataOferta;
    }

    public void setDataOferta(Date dataOferta) {
        this.dataOferta = dataOferta;
    }

}
