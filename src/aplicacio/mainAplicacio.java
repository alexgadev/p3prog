package aplicacio;

import java.io.*;
import java.util.Scanner;

import dades.*;

public class mainAplicacio {
    static final int MAX = 0;

    public static void main(String[] args) throws FileNotFoundException, EOFException, IOException {
        boolean finalFitxer = false;
        // BufferedReader fitUsuaris = new BufferedReader(newFileReader("Usuaris.txt"));
        
        llistaUsuaris llistaUsu = new llistaUsuaris(MAX);

        try {
            llegirPeticions();
            llegirProductes();
            // llegirUsuaris();

        } catch (EOFException e) {
            System.out.println("[+] No hi ha més dades a llegir... " + e);
            finalFitxer = true;
        } catch (FileNotFoundException e) {
            System.out.println("Fitxer incorrecte" + e);
        } catch (IOException e) {
            System.out.println(e);
        }
        Scanner teclat = new Scanner(System.in);

        System.out.println("Benvinguts a l'aplicació d'intercanvis");
        System.out.println("[1] Carregar les dades dels fitxers");
        System.out.println("[2] Llistar les dades de qualsevol llista que tingueu definida");
        System.out.println("[3] Llistar les ofertes de serveis que estan actives");
        System.out.println("[4] Llistar els béns o productes físics que estan disponibles");
        System.out.println("[5] Afegir una nova oferta de serveis");
        System.out.println("[6] Afegir un nou bé o producte físic a intercanviar");
        System.out.println("[7] Afegir una nova petició d’intercanvi");
        System.out.println("[8] Acceptar o refusar una petició d’intercanvi. Si s’accepta, s’ha d’afegir la valoració de les dues parts");
        System.out.println("[9] Donar d’alta un nou usuari");
        System.out.println("[10] Donar de baixa un bé o producte físic a intercanviar i eliminar-lo de la llista\n     Només es podrà de donar de baixa si encara no s’ha fet cap intercanvi amb ell");
        System.out.println("[11] Desactivar un servei. Aquest servei ja no estarà operatiu però no l’esborrem de les llistes");
        System.out.println("[12] Mostrar les peticions d’intercanvi pendents de respondre");
        System.out.println("[13] Mostrar les peticions d’intercanvi acceptades");
        System.out.println("[14] Mostrar les peticions d’intercanvi refusades");
        System.out.println("[15] Mostrar els usuaris que tenen valoracions en els seus intercanvis superiors a un llindar que indiqui l’usuari");
        System.out.println("[16] Mostrar el servei del qual s’han fet més intercanvis i indicar el número d’aquests");
        System.out.println("[17] Sortir de l’aplicació");
        System.out.print("Elegeix la opció que vols: ");
        int opcio = teclat.nextInt();
        teclat.close();

        switch(opcio) {
            case 1:

            break;
            case 2: 
            
            break;
            case 3: 
            
            break;
            case 4: 
            
            break;
            case 5: 
            
            break;
            case 6: 
            
            break;
            case 7: 
            
            break;
            case 8: 
            
            break;
            case 9: 
            
            break;
            case 10: 
            
            break;
            case 11: 
            
            break;
            case 12: 
            
            break;
            case 13: 
            
            break;
            case 14: 
            
            break;
            case 15: 
            
            break;
            case 16: 
            
            break;
            case 17: 
            
            break;
        }
    }

    public static void llegirProductes() throws IOException {
        BufferedReader fitProductes = new BufferedReader(new FileReader("Usuaris.txt"));
        boolean finalFitxer = false;
        String frase = "";
        String[] fraseSplit;
        llistaProductes llistaProd = new llistaProductes(MAX);

        while (!finalFitxer) {
            frase = fitProductes.readLine();
            fraseSplit = frase.split(";");
            String[] splitData = fraseSplit[3].split("/");
            Data data = new Data(Integer.parseInt(splitData[0]), Integer.parseInt(splitData[1]), Integer.parseInt(splitData[2]));
            Producte prod = new Producte(fraseSplit[0], fraseSplit[1], fraseSplit[2], data);
            llistaProd.afegeixProducte(prod);
        }
        fitProductes.close();
    }

    public static void llegirPeticions() throws IOException {
        BufferedReader fitPeticions = new BufferedReader(new FileReader("Peticions.txt"));
        String frase = "";
        String[] fraseSplit;
        boolean finalFitxer = false;
        llistaPeticions llistaPet = new llistaPeticions(MAX);
        
        while (!finalFitxer) {
            frase = fitPeticions.readLine();
            fraseSplit = frase.split(";");
            Usuari userOf = new Usuari(fraseSplit[1], fraseSplit[2], fraseSplit[3]);
            Usuari userRep = new Usuari(fraseSplit[5], fraseSplit[6], fraseSplit[7]);

    

            //Peticio peticio = new Peticio(fraseSplit[0], userOf, userRep, );
            //peticio.setNumeroIntercanvis(Integer.parseInt(fraseSplit[3]));
        }
        fitPeticions.close();
    }

    
    public static void llegirUsuaris() throws IOException {
        BufferedReader fitxerBinari = new BufferedReader(new FileReader("Usuaris.bin"));
        boolean llegit = false;
        String frase = "";
        String[] fraseSplit;
        
        while(!llegit) {
            frase = fitxerBinari.readLine();
            fraseSplit = frase.split(";");
        }
    } 


}