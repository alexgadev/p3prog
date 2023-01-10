package dades;


public class Servei extends Producte {
    
    protected Data dataFiOferiment;         //Data en la que el servei deixa d'estar actiu
    protected boolean donatDeBaixa;         //Variable boleana per saber si un servei segueix actiu o no

    //Constructor on dataFiOferiment es rep per paràmetre
    public Servei(String nom, String desc, Data data, Data dataFiOf){
        super(nom, desc, data);
        dataFiOferiment = dataFiOf;
        donatDeBaixa = false;           //Inicialment suposem que el servei no està donat de baixa
    }

    //Construcor sobrecàrrega on no es rep dataFiOferiment per paràmetre
    public Servei(String nom, String desc, Data data){
        super(nom, desc, data);
        dataFiOferiment = null;         //Inicialment suposem que el servei no caducarà mai
        donatDeBaixa = false;           //Inicialment suposem que el servei no està donat de baixa
    }

    public Data getDataFiOferiment() {
        return dataFiOferiment;
    }

    public void setDataFiOferiment(Data dataFiOferiment) {
        this.dataFiOferiment = dataFiOferiment;
    }

    public boolean getDonatDeBaixa() {
        return donatDeBaixa;
    }

    public void setDonatDeBaixa(boolean donatDeBaixa) {
        this.donatDeBaixa = donatDeBaixa;
    }
   

    public Servei copia(){
        Servei aux = new Servei(getNomProd(), getDescripcio(), getDataOferta(), getDataFiOferiment());
        aux.setDonatDeBaixa(this.donatDeBaixa);             //Si el producte copiat està donat de baixa o d'alta, també ho copiem
        return aux;
    }

    @Override
    public String toString() {
        return super.toString() + "Servei [dataFiOferiment=" + dataFiOferiment + ", donatDeBaixa=" + donatDeBaixa + " és un";
    }
    
}
