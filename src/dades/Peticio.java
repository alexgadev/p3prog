package dades;

import java.util.Scanner;

public class Peticio {
    private String codi;            //Codi de la peticio  
    private Usuari usuariOfereix;           //El usuari que ofereix l'intercanvi
    private Usuari usuariRep;           //El usuari que rep l'intercanvi
    private Producte desitjat;          //El producte que intercanvia l'usuari que rep l'intercanvi
    private Producte oferit;            //El producte que intercanvia l'usuari que ofereix l'intercanvi
    private int resposta;           //Guarda la resposta de la peticio
    private int valoracioOfereix;           //La valoracio de l'intercanvi que s'ofereix
    private int valoracioRep;           //La valoracio de l'intercanvi que es rep

    public Peticio(String codi, Usuari usuariOfereix, Usuari usuariRep, Producte desitjat, Producte oferit) {
        this.codi = codi;
        this.usuariOfereix = usuariOfereix;
        this.usuariRep = usuariRep;
        this.desitjat = desitjat;
        this.oferit = oferit;
        resposta = 0;           //Inicialitzem a 0 la resposta ja que sera una peticio no resposta           
        valoracioOfereix = 0;   //Inicialitzem la valoracio de l'intercanvi que s'ofereix a 0
        valoracioRep = 0;           //Inicialitzem la valoracio de l'intercanvi que es rep a 0
    }

    public String getCodi() {
        return codi;
    }


    public void setCodi(String codi) {
        this.codi = codi;
    }


    public Usuari getUsuariOfereix() {
        return usuariOfereix;
    }


    public void setUsuariOfereix(Usuari usuariOfereix) {
        this.usuariOfereix = usuariOfereix;
    }


    public Usuari getUsuariRep() {
        return usuariRep;
    }


    public void setUsuariRep(Usuari usuariRep) {
        this.usuariRep = usuariRep;
    }


    public Producte getDesitjat() {
        return desitjat;
    }


    public void setDesitjat(Producte desitjat) {
        this.desitjat = desitjat;
    }


    public Producte getOferit() {
        return oferit;
    }


    public void setOferit(Producte oferit) {
        this.oferit = oferit;
    }


    public int getResposta() {
        return resposta;
    }


    public void setResposta(int resposta) {
        this.resposta = resposta;
    }


    public int getValoracioOfereix() {
        return valoracioOfereix;
    }


    public void setValoracioOfereix(int valoracioOfereix) {
        this.valoracioOfereix = valoracioOfereix;
    }


    public int getValoracioRep() {
        return valoracioRep;
    }


    public void setValoracioRep(int valoracioRep) {
        this.valoracioRep = valoracioRep;
    }


    public void valoracions() {
        if(resposta == 1) {
            Scanner ans = new Scanner(System.in);
            System.out.println("Com valoraries aquest intercanvi com a oferidor de la petició?");
            valoracioOfereix = ans.nextInt();
            oferit.setIntercanviat(true);

            System.out.println("Com valoraries aquest intercanvi com a rebedor de la petició?");
            valoracioRep = ans.nextInt();
            desitjat.setIntercanviat(true);
           
            ans.close();
        }
    }

    public Peticio copia(){
        Peticio aux = new Peticio(codi, usuariOfereix, usuariRep, desitjat, oferit);
        aux.resposta = 0;
        return aux;
    } 

    @Override
    public String toString() {
        return "Peticio [codi=" + codi + ", usuariOfereix=" + usuariOfereix + ", usuariRep=" + usuariRep + ", desitjat="
                + desitjat + ", oferit=" + oferit + ", resposta=" + resposta + "]";
    }

}
