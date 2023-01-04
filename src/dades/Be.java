package dades;

import java.util.Date;

public class Be extends Producte {

    protected int amplada, alçada, fons, pes;
    protected Date dataIntercanvi;

    public Be(String nom, String desc, Data data, int amplada, int alçada, int fons, int pes){
        super(nom, desc, data);
        this.amplada = amplada;
        this.alçada = alçada;
        this.fons = fons;
        this.pes = pes;
        dataIntercanvi = null;
    }

    public int getAmplada() {
        return amplada;
    }

    public void setAmplada(int amplada) {
        this.amplada = amplada;
    }

    public int getAlçada() {
        return alçada;
    }

    public void setAlçada(int alçada) {
        this.alçada = alçada;
    }

    public int getFons() {
        return fons;
    }

    public void setFons(int fons) {
        this.fons = fons;
    }

    public int getPes() {
        return pes;
    }

    public void setPes(int pes) {
        this.pes = pes;
    }

    public Date getDataIntercanvi() {
        return dataIntercanvi;
    }

    public void setDataIntercanvi(Date dataIntercanvi) {
        this.dataIntercanvi = dataIntercanvi;
    }

    public Be copia (){
        Be aux = new Be(getNomProd(), getDescripcio(), getDataOferta(), amplada, alçada, fons, pes);
        return aux;
    }

    @Override
    public String toString() {
        return "Be [amplada=" + amplada + ", alçada=" + alçada + ", fons=" + fons + ", pes=" + pes + ", dataIntercanvi="
                + dataIntercanvi + "]";
    }

}
