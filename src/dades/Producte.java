package dades;


public abstract class Producte {
    
    private String nomProd;
    private String descripcio;;
    private Data dataOferta;
    private boolean intercanviat;
    protected int comptadorInter;

    public Producte(String nom, String desc, Data data){
        nomProd = nom;
        descripcio = desc;
        dataOferta = data;
        intercanviat = false;
        comptadorInter = 0;
    }

    public void modificaDescripcio(String desc){
        if(descripcio.equals(desc)){
            System.out.println("Descripció no vàlida");
        } else {
            descripcio = desc;
        }
    }
    
    public abstract Producte copia();

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


    public Data getDataOferta() {
        return dataOferta;
    }

    public void setDataOferta(Data dataOferta) {
        this.dataOferta = dataOferta;
    }

    public boolean getIntercanviat() {
        return intercanviat;
    }

    public void setIntercanviat(boolean boolea) {
        intercanviat = boolea;
    }
    public int getComptadorInter() {
        return comptadorInter;
    }

    public void setComptadorInter(int comptadorInter) {
        this.comptadorInter = comptadorInter;
    }

    @Override
    public String toString() {
        return "Producte [nomProd=" + nomProd + ", descripcio=" + descripcio + ", dataOferta=" + dataOferta
                + ", intercanviat=" + intercanviat + ", comptadorInter=" + comptadorInter + "]";
    }
    
}
