package dades;

import java.util.Date;

public class Be extends Producte {

    protected int amplada, alçada, fons, pes;       //Volum del bé
    protected Date dataIntercanvi;              //Data en que s'ha intercanviat el bé

    public Be(String nom, String desc, Data data, int amplada, int alçada, int fons, int pes){
        super(nom, desc, data);
        this.amplada = amplada;
        this.alçada = alçada;
        this.fons = fons;
        this.pes = pes;
        dataIntercanvi = null;          //Inicialment suposem que el bé no ha estat intercanviat encara
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
        aux.dataIntercanvi = this.dataIntercanvi;           //Copiem la data d'intercanvi del bé a copiar
        return aux;
    }

    @Override
    public String toString() {
        return "Be [amplada=" + amplada + ", alçada=" + alçada + ", fons=" + fons + ", pes=" + pes + ", dataIntercanvi="
                + dataIntercanvi + "]";
    }

}
