package dades;


public abstract class Producte {
    
    private String nomProd;             //Nom del producte
    private String descripcio;          //Descripció del producte 
    private Data dataOferta;            //Data en la que s'ha ofertat el producte
    private boolean intercanviat;       //Variable per saber si el producte ha estat intercanviat
    protected int comptadorInter;       //comptador per a tenir un seguiment dels cops que s'ha intercanviat un producte

    public Producte(String nom, String desc, Data data){
        nomProd = nom;
        descripcio = desc;
        dataOferta = data;
        intercanviat = false;           //Inicialment suposem que el producte no ha estat intercanviat
        comptadorInter = 0;             //Inicialment suposem que el producte no ha estat intercanviat CAP cop
    }

    public void modificaDescripcio(String desc){
        if(descripcio.equals(desc)){            //Si la descripció nova és la mateixa que la vella, aquesta no es pot actualitzar
            System.out.println("Descripció no vàlida");
        } else {        //En cas contrari, actualitzem la descripcio
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
