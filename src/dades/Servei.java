package dades;

import java.util.Date;


public class Servei extends Producte {
    
    protected Date dataFiOferiment;

    public Servei(){
        super(null, null, null, null);
        dataFiOferiment = null;
    }

    public Date getDataFiOferiment() {
        return dataFiOferiment;
    }

    public void setDataFiOferiment(Date dataFiOferiment) {
        this.dataFiOferiment = dataFiOferiment;
    }

    
}
