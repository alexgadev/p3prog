package aplicacio;

import dades.Peticio;
import dades.Producte;
import dades.Usuari;
import dades.llistaPeticions;
import dades.llistaProductes;
import dades.llistaUsuaris;

public class mainUsuaris {
    public static void main(String[] args) {
        llistaUsuaris llistaUsu = new llistaUsuaris(5);
        llistaPeticions llistaPet = new llistaPeticions(5);
        llistaProductes llistaProd = new llistaProductes(5);
        
        Usuari u1 = new Usuari("CryptoBro", "cryptobro@penemail.com", "35372");
        Usuari u2 = new Usuari("tomasTurbao", "foimbap@gmai.com", "34562");
        Usuari u3 = new Usuari("CryptoBro", "cryptobro@penemail.com", "35372");
        
        Producte prod1 = new Producte("portatil", "maquina per treure 10 a programacio", "be", null);
        Producte prod2 = new Producte("portatil2", "maquina per treure 10 a programacio", "be", null);
        Peticio p1 = new Peticio("portatil", u1, u2, prod1, prod2);

        llistaUsu.afegirUsuari(u1);
        llistaUsu.afegirUsuari(u2);
        llistaUsu.afegirUsuari(u3);
        llistaPet.afegeixPeticio(p1);
        llistaProd.afegeixProducte(prod1);
        llistaProd.afegeixProducte(prod2);

        p1.setValoracioOfereix(1);
        p1.setValoracioRep(5);
        System.out.println("---------------------------");
        System.out.println(llistaUsu.valoUsuaris(2, p1));    
        
        //System.out.println(llistaUsu.toString()); 
       
    }
}
