package aplicacio;

import dades.*;
import java.util.Scanner;

public class mainProductes {
    static Scanner teclat = new Scanner(System.in);
    public static void main(String[] args) {
        
        Data fecha;
        String[] fechaSplit;
        String data;
        llistaProductes llistaProd = new llistaProductes(5);
        
        System.out.println("Introdueix el dia, mes i any amb format [dd/mm/aaaa]: ");
        
        data = teclat.nextLine();
        fechaSplit = data.split("/");
        fecha = new Data(Integer.parseInt(fechaSplit[0]), Integer.parseInt(fechaSplit[1]), Integer.parseInt(fechaSplit[2]));
        
        Be b1 = new Be("taula", "vagi be", fecha, 30, 40, 15, 80);
        Be b2 = new Be("poma", "aliment", fecha, 15, 25, 10, 35);

        llistaProd.afegeixBe(b1);
        llistaProd.afegeixBe(b2);
        System.out.println(llistaProd.toString());
        
        //llistaProd.eliminaProducteFisic(p1);
        System.out.println(llistaProd.toString());

        teclat.close();
    }
}
