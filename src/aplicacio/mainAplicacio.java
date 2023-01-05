package aplicacio;

import java.io.*;
import java.util.Scanner;

import auxiliar.*;
import dades.*;

public class mainAplicacio {
    static final int MAX = 0;
    static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException, EOFException, IOException {
        boolean finalFitxer = false;
        // BufferedReader fitUsuaris = new BufferedReader(newFileReader("Usuaris.txt"));
        Usuari user;
        
        llistaUsuaris llistaUsu = new llistaUsuaris(MAX);
        llistaPeticions llistaPet = new llistaPeticions(MAX);
        llegirFitxers();
        
        user = iniciarSesio();
        

        //mostraOpcions();
        //int opcio = teclat.nextInt();
        
        /* 
        switch(opcio) {
            case 1:
            llegirFitxers();
            break;

            case 2: 
            System.out.println(llistaUsu.toString()+"\n"); 
            
            System.out.println(llistaProd.toString()+"\n");    
            
            System.out.println(llistaPet.toString()+"\n"); 
            break;

            case 3: 
            System.out.println(llistaProd.serveisActius()); 
            break;

            case 4: 
            System.out.println(llistaProd.bensActius());
            break;

            case 5:
            registraServei();
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
        } catch (ClassNotFoundException e){
            System.err.println(e);
        }

    }

    private static Usuari iniciarSesio () throws IOException {
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
            llistaUsers = carregarUsu.carregarUsu();  //Carreguem els usuaris en una llista

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
        return user;
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
        String data;
        String[] fraseSplit, dataSplit;
        Servei serv;
        Be be;
        llistaProductes llistaProd = new llistaProductes(MAX);
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
                    Data dataFiOf = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
                    serv = new Servei(nom, desc, dataOf, dataFiOf); //Creem el servei
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

    public static void llegirPeticions() throws IOException {
        BufferedReader fitPeticions = new BufferedReader(new FileReader("Peticions.txt"));
        String frase = "";
        String[] fraseSplit;
        boolean finalFitxer = false;
        llistaPeticions llistaPet = new llistaPeticions(MAX);
        
        try{
            while (!finalFitxer) {
                frase = fitPeticions.readLine();
                fraseSplit = frase.split(";"); 
                Usuari usuariOf, usuariRep;
                String nom, desc;
                Data data, dataFiOferiment;
                String[] dataSplit;
                Peticio pet;  
                Servei servOf, servRep;
                Be beOf, beRep;           
                int amplada, alçada, fons, pes;
                
                String codi = fraseSplit[0]; //Codi de la petició
                String alies = fraseSplit[1];
                String correu = fraseSplit[2];
                String codiPos = fraseSplit[3];
                usuariOf = new Usuari(alies, correu, codiPos); //Creem l'usuari que ofereix

                alies = fraseSplit[4];
                correu = fraseSplit[5];
                codiPos = fraseSplit[6];
                usuariRep = new Usuari(alies, correu, codiPos);

                //Si prodOf es un servei i prodRep es servei també
                if(fraseSplit[7].equals("ser") && fraseSplit[12].equals("ser")){

                    nom = fraseSplit[8];
                    desc = fraseSplit[9];
                    dataSplit = fraseSplit[10].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
                    if(dataSplit[11] != null){
                        dataSplit = fraseSplit[11].split("/");
                        dataFiOferiment = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
                        servOf = new Servei(nom, desc, data, dataFiOferiment);
                    } else {
                        servOf = new Servei(nom, desc, data);
                    }
                    nom = fraseSplit[13];
                    desc = fraseSplit[14];
                    dataSplit = fraseSplit[15].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
                    if(dataSplit[16] != null) {
                        dataSplit = fraseSplit[16].split("/");
                        dataFiOferiment = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
                        servRep = new Servei(nom, desc, data, dataFiOferiment);
                    } else {
                        servRep = new Servei(nom, desc, data);
                    }
                    pet = new Peticio(codi, usuariOf, usuariRep, servOf, servRep);
                    llistaPet.afegeixPeticio(pet);

                } else if(fraseSplit[7].equals("ser") && fraseSplit[12].equals("be")){ //Si prodOf es un servei i prodRep es un be

                    nom = fraseSplit[8];
                    desc = fraseSplit[9];
                    dataSplit = fraseSplit[10].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
                    if(dataSplit[11] != null){
                        dataSplit = fraseSplit[11].split("/");
                        dataFiOferiment = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
                        servOf = new Servei(nom, desc, data, dataFiOferiment);
                    } else {
                        servOf = new Servei(nom, desc, data);
                    }

                    nom = fraseSplit[13];
                    desc = fraseSplit[14];
                    dataSplit = fraseSplit[15].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
                    amplada = Integer.parseInt(fraseSplit[16]);
                    alçada = Integer.parseInt(fraseSplit[17]);
                    fons = Integer.parseInt(fraseSplit[18]);
                    pes = Integer.parseInt(fraseSplit[19]);
                    beRep = new Be(nom, desc, data, amplada, alçada, fons, pes);
                    
                    pet = new Peticio(codi, usuariOf, usuariRep, servOf, beRep);
                    llistaPet.afegeixPeticio(pet);

                } else if (fraseSplit[7].equals("be") && fraseSplit[12].equals("be")){ //Si prodOf es un be i prodRep es un be també
                    
                    nom = fraseSplit[8];
                    desc = fraseSplit[9];
                    dataSplit = fraseSplit[10].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
                    amplada = Integer.parseInt(fraseSplit[11]);
                    alçada = Integer.parseInt(fraseSplit[12]);
                    fons = Integer.parseInt(fraseSplit[13]);
                    pes = Integer.parseInt(fraseSplit[14]);
                    beOf = new Be(nom, desc, data, amplada, alçada, fons, pes);

                    nom = fraseSplit[15];
                    desc = fraseSplit[16];
                    dataSplit = fraseSplit[17].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
                    amplada = Integer.parseInt(fraseSplit[18]);
                    alçada = Integer.parseInt(fraseSplit[19]);
                    fons = Integer.parseInt(fraseSplit[20]);
                    pes = Integer.parseInt(fraseSplit[21]);
                    beRep = new Be(nom, desc, data, amplada, alçada, fons, pes);

                    pet = new Peticio(codi, usuariOf, usuariRep, beOf, beRep);
                    llistaPet.afegeixPeticio(pet);

                } else if (fraseSplit[7].equals("be") && fraseSplit[12].equals("ser")){ //Si prodOf es un be i prodRep es un servei               
                    
                    nom = fraseSplit[8];
                    desc = fraseSplit[9];
                    dataSplit = fraseSplit[10].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
                    amplada = Integer.parseInt(fraseSplit[11]);
                    alçada = Integer.parseInt(fraseSplit[12]);
                    fons = Integer.parseInt(fraseSplit[13]);
                    pes = Integer.parseInt(fraseSplit[14]);
                    beOf = new Be(nom, desc, data, amplada, alçada, fons, pes);
                    
                    nom = fraseSplit[15];
                    desc = fraseSplit[16];
                    dataSplit = fraseSplit[17].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
                    if(dataSplit[18] != null){
                        dataSplit = fraseSplit[18].split("/");
                        dataFiOferiment = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
                        servRep = new Servei(nom, desc, data, dataFiOferiment);
                    } else {
                        servRep = new Servei(nom, desc, data);
                    }

                    pet = new Peticio(codi, usuariOf, usuariRep, beOf, servRep);
                    llistaPet.afegeixPeticio(pet);
                } 
            }
            
        } catch (EOFException e){
            finalFitxer = true;
        }
        fitPeticions.close();
    }

    public static void llegirUsuaris() throws IOException, ClassNotFoundException {
        ObjectInputStream fitxerBinari = new ObjectInputStream(new FileInputStream("Usuaris.bin"));
        boolean finalFitxer = false;
        llistaUsuaris llistaUsu = new llistaUsuaris(MAX);
        Usuari usu;
        
        try {
            while(!finalFitxer) {
                usu = (Usuari) fitxerBinari.readObject();
                llistaUsu.afegirUsuari(usu);
            }
        } catch (EOFException e){
            finalFitxer = true;
        } catch (ClassNotFoundException e){
            System.err.println(e);
        }
        fitxerBinari.close(); 
    } 

    public void registraServei(){
        Servei serv;
        String nom, desc, dat;
        String[] dataSplit;
        Data data;
        llistaProductes llistaProd = new llistaProductes(MAX);

        System.out.println("Introdueix el nom del servei:");
        nom = teclat.nextLine();
        System.out.println("Indica la descripcio:");
        desc = teclat.nextLine();
        System.out.println("Indica la data: [dd/mm/aaaa]:");
        dat = teclat.nextLine();
        dataSplit = dat.split("/");
        data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
        serv = new Servei(nom, desc, data);
        llistaProd.afegeixBe(serv);
        
    }

    public void registraBe(){
        Be be;
        String nom, desc, dat;
        String[] dataSplit;
        Data data;
        int ampl, alça, fons, pes;
    
        //TODO falta passar llistaprod

        System.out.println("Introudeix el nom del be:");
        nom = teclat.nextLine();
        System.out.println("Indica la descripcio del producte:");
        desc = teclat.nextLine();
        System.out.println("Indica la data [dd/mm/aaaa]:");
        dat = teclat.nextLine();
        dataSplit = dat.split("/");
        data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
        System.out.println("Indica la amplada del be en centimetres:");
        ampl = teclat.nextInt();
        System.out.println("Indica la alçada del be en centimetres:");
        alça = teclat.nextInt();
        System.out.println("Indica la fons del be en centimetres:");
        fons = teclat.nextInt();
        System.out.println("Indica el pes del be en kilograms i aproxima'l a un nombre enter:");
        pes = teclat.nextInt();
        be = new Be(nom, desc, data, ampl, alça, fons, pes);
        //llistaProd.afegeixBe(be);
    }
}