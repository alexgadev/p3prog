package aplicacio;

import dades.Usuari;
import dades.llistaUsuaris;

public class mainUsuaris {
    public static void main(String[] args) {
        llistaUsuaris llistaUsu = new llistaUsuaris(5);
        
        Usuari u1 = new Usuari("CryptoBro", "cryptobro@penemail.com", "35372");
        Usuari u2 = new Usuari("tomasTurbao", "foimbap@gmai.com", "34562");
        Usuari u3 = new Usuari("CryptoBro", "cryptobro@penemail.com", "35372");

        llistaUsu.registraUsuari(u1);
        llistaUsu.registraUsuari(u2);
        llistaUsu.registraUsuari(u3);
        
        System.out.println(llistaUsu.toString()); 
       
    }
}
