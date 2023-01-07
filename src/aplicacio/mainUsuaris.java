package aplicacio;

import dades.*;
public class mainUsuaris {
    public static void main(String[] args) {
        llistaUsuaris llistaUsu = new llistaUsuaris(5);
        llistaPeticions llistaPet = new llistaPeticions(5);
        llistaProductes llistaProd = new llistaProductes(5);
        
        Usuari u1 = new Usuari("CryptoBro", "cryptobro@penemail.com", "35372");
        Usuari u2 = new Usuari("tomasTurbao", "foimbap@gmai.com", "34562");
        Usuari u3 = new Usuari("CryptoBro", "cryptobro@penemail.com", "35372");
        
        Be b1 = new Be("portatil", "maquina per treure 10 a programacio", null, 13, 14, 10, 3);
        Be b2 = new Be("portatil2", "maquina per treure 10 a programacio", null, 5, 10, 2, 2);
        Peticio p1 = new Peticio("portatil", u1, u2, b1, b2);

        llistaUsu.afegirUsuari(u1);
        llistaUsu.afegirUsuari(u2);
        llistaUsu.afegirUsuari(u3);
        llistaPet.afegeixPeticio(p1);
        llistaProd.afegeixBe(b1);
        llistaProd.afegeixBe(b2);

        p1.setValoracioOfereix(1);
        p1.setValoracioRep(5);
        System.out.println("---------------------------");
        //System.out.println(llistaUsu.valoUsuaris(2, p1));    
        
        //System.out.println(llistaUsu.toString()); 
       
    }
}
