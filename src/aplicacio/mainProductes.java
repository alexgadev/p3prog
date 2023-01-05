package aplicacio;

import dades.*;

import java.io.*;
import java.util.Scanner;

import auxiliar.escriuProductes;

public class mainProductes {
    static Scanner teclat = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        
        Data fecha;
        String[] fechaSplit;
        String data;
        llistaProductes llistaProd = new llistaProductes(100);
        escriuProductes escriureFitxerProd = new escriuProductes();

        System.out.println("Introdueix el dia, mes i any amb format [dd/mm/aaaa]: ");
        
        data = teclat.nextLine();
        fechaSplit = data.split("/");
        fecha = new Data(Integer.parseInt(fechaSplit[0]), Integer.parseInt(fechaSplit[1]), Integer.parseInt(fechaSplit[2]));
        
        Be b1 = new Be("taula", "vagi be", fecha, 30, 40, 15, 80);
        Be b2 = new Be("poma", "aliment", fecha, 15, 25, 10, 35);
        Data data1 = new Data(6, 1, 23);
        Servei serv1 = new Servei("Classes repas", "classes de mates", data1);
        llistaProd.afegeixServei(serv1);
        llistaProd.afegeixBe(b1);
        llistaProd.afegeixBe(b2);

        escriureFitxerProd.escriureProductes(llistaProd);
        
        llegirProductes();
        System.out.println(llistaProd.toString());
        
        //llistaProd.eliminaProducteFisic(p1);
        //System.out.println(llistaProd.toString());

        teclat.close();
    }
    private static void llegirProductes() throws IOException {
        BufferedReader fitProductes = new BufferedReader(new FileReader("Productes.txt"));
        boolean finalFitxer = false;
        String frase = "";
        //String data;
        String[] fraseSplit, dataSplit;
        Servei serv;
        Be be;
        llistaProductes llistaProd = new llistaProductes(100);
        try{
            while (!finalFitxer) {
                frase = fitProductes.readLine();
                fraseSplit = frase.split(";");

                //El nom, desc y dataOf son compartits ja sigui bé o servei
                String nom = fraseSplit[1]; 
                String desc = fraseSplit[2];
                dataSplit = fraseSplit[3].split("/");
                Data dataOf = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));

                if(fraseSplit[0].equals("ser")){ //Si tenim un servei, crearem un nou servei
                    dataSplit = fraseSplit[4].split("/"); //Separem per / per poder crear un objecte de la classe Data
                    if(dataSplit[0].equals("null")){
                        serv = new Servei(nom, desc, dataOf);
                    } else {
                        Data dataFiOf = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
                    serv = new Servei(nom, desc, dataOf, dataFiOf); //Creem el servei
                    }
                    llistaProd.afegeixServei(serv); //L'afegim a la llista

                } else if (fraseSplit[0].equals("be")){
                    int amp = Integer.parseInt(fraseSplit[4]);  //Assignem l'amplada
                    int alç = Integer.parseInt(fraseSplit[5]);  //Assignem l'alçada
                    int fons = Integer.parseInt(fraseSplit[6]); //Assignem el fons
                    int pes = Integer.parseInt(fraseSplit[7]);  //Assignem el pes
                    be = new Be(nom, desc, dataOf, amp, alç, fons, pes); //Creem el bé
                    llistaProd.afegeixBe(be); //L'afegim a la llista
                }
            }
        } catch (EOFException e) {
            finalFitxer = true;
        }
        fitProductes.close();
    }
    
}
