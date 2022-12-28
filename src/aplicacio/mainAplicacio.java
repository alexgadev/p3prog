package aplicacio;

import java.io.*;
import java.util.Scanner;

import auxiliar.CrearArxiuBinari;
import auxiliar.carregarUsuaris;
import dades.*;

public class mainAplicacio {
    static final int MAX = 0;
    static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, EOFException, IOException {
        boolean finalFitxer = false;
        // BufferedReader fitUsuaris = new BufferedReader(newFileReader("Usuaris.txt"));
        
        llistaUsuaris llistaUsu = new llistaUsuaris(MAX);
        llistaProductes llistaProd = new llistaProductes(MAX);
        llistaPeticions llistaPet = new llistaPeticions(MAX);
        iniciarSesio();

        //mostraOpcions();
        //int opcio = teclat.nextInt();
        
        /* 
        switch(opcio) {
            case 1:
            llegirFitxers();
            break;

            case 2: 
            llistaUsu.toString();
            System.out.println();
            llistaProd.toString();
            System.out.println();
            llistaPet.toString();
            break;

            case 3: 
            System.out.println(llistaProd.serveisActius()); 
            break;

            case 4: 
            System.out.println(llistaProd.bensActius());
            break;

            case 5:
            Scanner servei = new Scanner(System.in);
            System.out.println("Introdueix el nom del servei: ");
            servei.nextLine();
            System.out.println("");
            llistaProd.afegeixProducteServei(null);
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
        teclat.close();
    */
    } 

    private static void llegirFitxers() {
        boolean finalFitxer = false;
        try {
            llegirPeticions();
            llegirProductes();
            llegirUsuaris();

        } catch (EOFException e) {
            System.out.println("[+] No hi ha més dades a llegir... " + e);
            finalFitxer = true;
        } catch (FileNotFoundException e) {
            System.out.println("Fitxer incorrecte" + e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private static void iniciarSesio () throws IOException {
        Scanner resposta = new Scanner(System.in);
        int valor;
        Usuari user = new Usuari(null, null, null);
        llistaUsuaris llistaUsers = new llistaUsuaris(100);
        carregarUsuaris carregarUsu = new carregarUsuaris();
        CrearArxiuBinari arxiuBin = new CrearArxiuBinari();
       

        System.out.println("Tria una opció: ");
        System.out.println("[1]: REGISTRAR UN NOU USUARI");
        System.out.println("[2]: INICIAR SESSIÓ");
        valor = resposta.nextInt();
        
        if (valor == 1){
            System.out.println("Introdueix l'alies: ");
            String alies = teclat.nextLine();
            System.out.println("Introdueix el correu: ");
            String correu = teclat.nextLine();
            System.out.println("Introdueix el codi postal: ");
            String codiPos = teclat.nextLine();
            
            user = new Usuari(alies, correu, codiPos);
            llistaUsers = carregarUsu.carregarUsu();    //Carreguem els usuaris en una llista

            if(llistaUsers.getnUsuaris() == 0){
                llistaUsers.afegirUsuari(user);
                arxiuBin.CreaArxiuBinari(llistaUsers);

            } else {
                if(!llistaUsers.comprovaIgualtats(user)){
                    llistaUsers.afegirUsuari(user);
                    arxiuBin.CreaArxiuBinari(llistaUsers);
                }
            }
            System.out.println(llistaUsers.toString()); 
              
        }
        else if (valor == 2) {
            System.out.println("Introdueix l'alies de l'usuari: ");
            String alies = resposta.nextLine();
            
        }
        resposta.close();
    }

	private static void mostraOpcions() {

        System.out.println("Benvinguts a l'aplicació d'intercanvis");
        System.out.print("Elegeix la opció que vols: ");
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
    }

    public static void llegirProductes() throws IOException {
        BufferedReader fitProductes = new BufferedReader(new FileReader("Productes.txt"));
        boolean finalFitxer = false;
        String frase = "";
        String[] fraseSplit;
        llistaProductes llistaProd = new llistaProductes(MAX);
        try{
            while (!finalFitxer) {
                frase = fitProductes.readLine();
                fraseSplit = frase.split(";");
                String[] splitData = fraseSplit[3].split("/");
                Data data = new Data(Integer.parseInt(splitData[0]), Integer.parseInt(splitData[1]), Integer.parseInt(splitData[2]));
                Producte prod = new Producte(fraseSplit[0], fraseSplit[1], fraseSplit[2], data);
                llistaProd.afegeixProducte(prod);
            }
        } catch (EOFException e) {
            finalFitxer = true;
        }
        fitProductes.close();
    }

    public static void llegirPeticions() throws IOException {
        BufferedReader fitPeticions = new BufferedReader(new FileReader("Peticions.txt"));
        String frase = "";
        String[] fraseSplit;
        String[] fraseSplitData;
        boolean finalFitxer = false;
        llistaPeticions llistaPet = new llistaPeticions(MAX);
        
        try{
            while (!finalFitxer) {
                frase = fitPeticions.readLine();
                fraseSplit = frase.split(";");

                Usuari userOf = new Usuari(fraseSplit[1], fraseSplit[2], fraseSplit[3]);
                Usuari userRep = new Usuari(fraseSplit[5], fraseSplit[6], fraseSplit[7]);

                fraseSplitData = fraseSplit[11].split("/");
                Data dataProd = new Data(Integer.parseInt(fraseSplitData[0]), Integer.parseInt(fraseSplitData[1]), Integer.parseInt(fraseSplitData[2]));
                Producte prodDes = new Producte(fraseSplit[8], fraseSplit[9], fraseSplit[10], dataProd);
                
                fraseSplitData = fraseSplit[14].split("/");
                dataProd = new Data(Integer.parseInt(fraseSplitData[0]), Integer.parseInt(fraseSplitData[1]), Integer.parseInt(fraseSplitData[2]));
                Producte prodOf = new Producte(fraseSplit[11], fraseSplit[12], fraseSplit[13], dataProd);
    
                Peticio peticio = new Peticio(fraseSplit[0], userOf, userRep, prodDes, prodOf);
                llistaPet.afegeixPeticio(peticio);
                //peticio.setNumeroIntercanvis(Integer.parseInt(fraseSplit[4]));
            }
        } catch (EOFException e){
            finalFitxer = true;
        }
        fitPeticions.close();
    }

    public static void llegirUsuaris() throws IOException {
        ObjectInputStream fitxerBinari = new ObjectInputStream(new FileInputStream("Usuaris.bin"));
        boolean finalFitxer = false;
        String frase = "";
        String[] fraseSplit;
        llistaUsuaris llistaUsu = new llistaUsuaris(MAX);
        /*
        try {
            while(!finalFitxer) {
                //frase = fitxerBinari.readObject();
                fraseSplit = frase.split(";");
                Usuari usu = new Usuari(fraseSplit[0], fraseSplit[1], fraseSplit[2]);
                usu.setNumeroIntercanvis(0);
                llistaUsu.afegeixUsuari(usu);
            }
        } catch (EOFException e){
            finalFitxer = true;
        }
        fitxerBinari.close(); */
    } 

}