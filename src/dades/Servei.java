package dades;


public class Servei extends Producte {
    
    protected Data dataFiOferiment;
    

    public Servei(String nom, String desc, Data data){
        super(nom, desc, data);
        dataFiOferiment = null;
        
    }

    public Data getDataFiOferiment() {
        return dataFiOferiment;
    }

    public void setDataFiOferiment(Data dataFiOferiment) {
        this.dataFiOferiment = dataFiOferiment;
    }

    public Servei copia(){
        Servei aux = new Servei(getNomProd(), getDescripcio(), getDataOferta());
        return aux;
    }

    
}
