package aplicacio;

import java.io.*;
import java.util.Scanner;

import auxiliar.*;
import dades.*;

public class mainAplicacio {
    static final int MAX = 0;
    static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args)
            throws FileNotFoundException, EOFException, IOException, ClassNotFoundException {
        boolean correcte = false;
        int valor;
        carregarUsuaris carregarUsu = new carregarUsuaris();
        llistaPeticions llistaPet = new llistaPeticions(MAX);
        llistaProductes llistaProd = new llistaProductes(MAX);
        llistaUsuaris llistaUsers = new llistaUsuaris(MAX);
        CrearArxiuBinari arxiuBin = new CrearArxiuBinari();

        llistaUsers = llegirUsuaris();
        llistaProd = llegirProductes();
        // llistaPet = llegirPeticions();
        System.out.println("Tria una opció: ");
        System.out.println("[1]: REGISTRAR UN NOU USUARI");
        System.out.println("[2]: INICIAR SESSIÓ");
        System.out.println("[3]: SORTIR");
        valor = Integer.parseInt(teclat.nextLine());

        while (!correcte) {
            if (valor == 1) {
                System.out.println("Introdueix l'alies: ");
                String alies = teclat.nextLine();
                System.out.println("Introdueix el correu: ");
                String correu = teclat.nextLine();
                System.out.println("Introdueix el codi postal: ");
                String codiPos = teclat.nextLine();

                Usuari user = new Usuari(alies, correu, codiPos);
                llistaUsers = carregarUsu.carregarUsu(); // Carreguem els usuaris en una llista

                if (llistaUsers.getnUsuaris() == 0) {
                    llistaUsers.afegirUsuari(user);
                    arxiuBin.CreaArxiuBinari(llistaUsers);

                } else {
                    if (!llistaUsers.comprovaIgualtats(user)) {
                        llistaUsers.afegirUsuari(user);
                        arxiuBin.CreaArxiuBinari(llistaUsers);
                    }
                }
                correcte = true;
            } else if (valor == 2) {
                Scanner teclat2 = new Scanner(System.in);
                System.out.println("Introdueix l'alies de l'usuari: ");
                String alies = teclat2.nextLine();
                int posUsuari;
                if (!llistaUsers.comprovaUsuari(alies)) {
                    System.out.println("Usuari no existent! Introdueix un nom vàlid!");
                } else {
                    System.out.println("Benvingut/da!" + alies);
                    posUsuari = llistaUsers.getPosicioUsuari(alies, llistaUsers);
                    String correu = llistaUsers.getIessim(posUsuari).getCorreu();
                    String codiPos = llistaUsers.getIessim(posUsuari).getCodiPostal();
                    Usuari usu = new Usuari(alies, correu, codiPos);
                    System.out.println("A continuació, tria una opció");

                    while (!correcte) {
                        mostraOpcions();
                        int opcio = Integer.parseInt(teclat2.nextLine());

                        if (triaOpcio(opcio, llistaPet, llistaProd, llistaUsers, usu)) {
                            correcte = true;
                            System.out.println("Que tinguis un bon dia!");
                        } else {
                            correcte = false;
                        }
                    }
                    teclat2.close();
                }
            } else if (valor == 3) {
                correcte = true;
            }
        }
    }

    private static boolean triaOpcio(int opcio, llistaPeticions llistaPet, llistaProductes llistaProd,
            llistaUsuaris llistaUsu, Usuari user)
            throws IOException, ClassNotFoundException {
        boolean correcte = false;
        boolean acabarProg = false;
        int pos = 0;
        while (!correcte) {
            switch (opcio) {
                // 1. Carregar les dades dels fitxers
                case 1:
                    llegirPeticions();
                    llegirProductes();
                    llegirUsuaris();
                    correcte = true;
                    break;

                // 2. Llistar les dades de qualsevol llista que tingueu definida
                case 2:
                    System.out.println("Llista d'Usuaris");
                    System.out.println(llistaUsu.toString() + "\n");

                    System.out.println("Llista de Productes");
                    System.out.println(llistaProd.toString() + "\n");

                    System.out.println("Llista de Peticions");
                    System.out.println(llistaPet.toString() + "\n");
                    correcte = true;
                    break;

                // 3. Llistar les ofertes de serveis que estan actives
                case 3:
                    System.out.println(llistaProd.serveisActius().toString());
                    correcte = true;
                    break;

                // 4. Llistar els béns o productes físics que estan disponibles
                case 4:
                    System.out.println(llistaProd.bensActius().toString());
                    correcte = true;
                    break;

                // 5. Afegir una nova oferta de serveis
                case 5:
                    llistaProd = registraServei(user, llistaProd);
                    escriuProductes escriuServeiFitxer = new escriuProductes();
                    escriuServeiFitxer.escriureProductes(llistaProd);
                    // llistaProd = llegirProductes();
                    correcte = true;
                    break;

                // 6. Afegir un nou bé o producte físic a intercanviar.
                case 6:
                    llistaProd = registraBe(user, llistaProd, llistaUsu);
                    llistaProd = llegirProductes();
                    escriuProductes escriuBeFitxer = new escriuProductes();
                    escriuBeFitxer.escriureProductes(llistaProd);
                    llistaProd = llegirProductes();

                    correcte = true;
                    break;

                // 7. Afegir una nova petició d’intercanvi.
                case 7:
                    llistaPet = registraPeticio(user, llistaUsu, llistaProd, llistaPet);
                    correcte = true;
                    break;

                // 8. Acceptar o refusar una petició d’intercanvi. Si s’accepta, s’ha d’afegir
                // la valoració de les dues parts.
                // TODO HACERLOOOO HAY QUE DEVOLVER POSICION Y ACTUALIZAR NUM INTER DE PRODUCTO
                case 8:
                    pos = respostaPet(llistaPet);
                    llistaPet.respostaPeticio(llistaPet, pos);
                    correcte = true;
                    break;

                /*
                 * 9. Donar de baixa un bé o producte físic a intercanviar i eliminar-lo de la
                 * llista. Només es podrà
                 * de donar de baixa si encara no s’ha fet cap intercanvi amb ell.
                 */
                case 9:
                    llistaProd.eliminaProducteFisic(eliminaProducte());
                    correcte = true;
                    break;

                // 10. Desactivar un servei. Aquest servei ja no estarà operatiu però no
                // l’esborrem de les llistes.
                case 10:
                    llistaProd.donarDeBaixaServei(desactivaServei());
                    correcte = true;
                    break;

                // 11. Mostrar les peticions d’intercanvi pendents de respondre.
                case 11:
                    System.out.println(llistaPet.mostraPeticionsPendents().toString());
                    correcte = true;
                    break;

                // 12. Mostrar les peticions d’intercanvi acceptades.
                case 12:
                    System.out.println(llistaPet.mostraPeticionsAcceptades().toString());
                    correcte = true;
                    break;

                // 13. Mostrar les peticions d’intercanvi refusades.
                case 13:
                    System.out.println(llistaPet.mostraPeticionsRefusades().toString());
                    correcte = true;
                    break;

                // 14. Mostrar els usuaris que tenen valoracions en els seus intercanvis
                // superiors a un llindar que indiqui l’usuari.
                case 14:
                    System.out.println("Escriu el llindar si us plau");
                    int llindar = Integer.parseInt(teclat.nextLine());
                    System.out.println(llistaUsu.valoUsuaris(llindar, llistaPet).toString());
                    correcte = true;
                    break;

                // 15. Mostrar el servei del qual s’han fet més intercanvis i indicar el número
                // d’aquests.
                case 15:
                    System.out.println(llistaProd.serveiMesIntercanvis().toString());
                    correcte = true;
                    break;

                // 16. Sortir de l’aplicació.
                case 16:
                    correcte = true;
                    acabarProg = true;
                    break;
            }
        }
        return acabarProg;
    }

    private static int respostaPet(llistaPeticions llistaPet) {
        System.out.println("Introdueix el codi de la Peticio");
        String codi = teclat.nextLine();
        boolean trobat = false;
        int i = 0;
        int pos = 0;
        while (!trobat) {
            if (llistaPet.getIessim(i).getCodi().equalsIgnoreCase(codi)) {
                trobat = true;
                pos = i;
            }
            i++;
        }
        return pos;
    }

    private static llistaPeticions registraPeticio(Usuari user, llistaUsuaris llistaUsu, llistaProductes llistaProd,
            llistaPeticions llistaPet) {
        String codi, resp1, resp2;
        Usuari usuariRep;
        Be beOf = null, beRep = null;
        Servei servOf = null, servRep = null;
        Peticio pet;

        System.out.println("Indica el codi de la petició a afegir: ");
        codi = teclat.nextLine();
        usuariRep = llistaUsu.buscaUsuariDest(llistaUsu);
        System.out.println("El producte a intercanviar és un be o un servei?");
        resp1 = teclat.nextLine();
        if (resp1.equalsIgnoreCase("servei")) {
            servOf = llistaProd.buscaServei(user, llistaProd);
        } else {
            beOf = llistaProd.buscaBe(user, llistaProd);
        }
        System.out.println("El producte que es rep és un be o un servei?");
        resp2 = teclat.nextLine();
        if (resp2.equalsIgnoreCase("servei")) {
            servRep = llistaProd.buscaServei(user, llistaProd);

        } else {
            beRep = llistaProd.buscaBe(user, llistaProd);
        }
        if (resp1.equalsIgnoreCase("servei") && resp2.equalsIgnoreCase("be")) {
            pet = new Peticio(codi, user, usuariRep, servOf, beRep);
        } else if (resp1.equalsIgnoreCase("servei") && resp2.equalsIgnoreCase("servei")) {
            pet = new Peticio(codi, user, usuariRep, servOf, servRep);
        } else if (resp1.equalsIgnoreCase("be") && resp2.equalsIgnoreCase("servei")) {
            pet = new Peticio(codi, user, usuariRep, beOf, servRep);
        } else {
            pet = new Peticio(codi, user, usuariRep, beOf, beRep);
        }
        llistaPet.afegeixPeticio(pet);
        return llistaPet;
    }

    private static Servei desactivaServei() {
        Servei serv;
        String[] dataSplit;
        Data data, dataFiOf;

        System.out.println("Quin servei vols desactivar?");
        String nomSer = teclat.nextLine();
        System.out.println("Indica la descripcio del servei");
        String desc = teclat.nextLine();
        System.out.println("Introdueix la data si us plau [dd/mm/aaaa]");
        String dat = teclat.nextLine();
        dataSplit = dat.split("/");
        data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
        System.out.println("El servei te data fi d'oferiment? [Si/No]");
        String resp = teclat.nextLine();
        if (resp.equalsIgnoreCase("Si")) {
            System.out.println("Introdueix-la si us plau [dd/mm/aaaa]");
            dat = teclat.nextLine();
            dataSplit = dat.split("/");
            dataFiOf = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                    Integer.parseInt(dataSplit[2]));
            serv = new Servei(nomSer, desc, data, dataFiOf);
        } else {
            serv = new Servei(nomSer, desc, data);
        }
        return serv;
    }

    private static Be eliminaProducte() {
        String[] dataSplit;
        Data data;
        Be be;

        System.out.println("Quin be vols eliminar?");
        String prod = teclat.nextLine();
        System.out.println("D'acord, ara indica la resta de dades si us plau");
        System.out.println("Indica la descripcio");
        String desc = teclat.nextLine();
        System.out.println("Indica la data [dd/mm/aaaa]");
        String dat = teclat.nextLine();
        dataSplit = dat.split("/");
        data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
        System.out.println("Indica l'amplada en cm");
        int amplada = teclat.nextInt();
        System.out.println("Indica l'alçada en cm");
        int alçada = teclat.nextInt();
        System.out.println("Indica el fons en cm");
        int fons = teclat.nextInt();
        System.out.println("Indica el pes en kg, arrodonint enters a l'alça");
        int pes = teclat.nextInt();

        be = new Be(prod, desc, data, amplada, alçada, fons, pes);
        return be;
    }

    private static void mostraOpcions() {

        System.out.println("Benvinguts a l'aplicació d'intercanvis");
        System.out.println("Tria la opció que vols: ");
        System.out.println("[1] Carregar les dades dels fitxers");
        System.out.println("[2] Llistar les dades de qualsevol llista que tingueu definida");
        System.out.println("[3] Llistar les ofertes de serveis que estan actives");
        System.out.println("[4] Llistar els béns o productes físics que estan disponibles");
        System.out.println("[5] Afegir una nova oferta de serveis");
        System.out.println("[6] Afegir un nou bé o producte físic a intercanviar");
        System.out.println("[7] Afegir una nova petició d’intercanvi");
        System.out.println(
                "[8] Acceptar o refusar una petició d’intercanvi. Si s’accepta, s’ha d’afegir la valoració de les dues parts");
        System.out.println("[9] Donar d’alta un nou usuari");
        System.out.println(
                "[10] Donar de baixa un bé o producte físic a intercanviar i eliminar-lo de la llista. Només es podrà de donar de baixa si encara no s’ha fet cap intercanvi amb ell");
        System.out.println(
                "[11] Desactivar un servei. Aquest servei ja no estarà operatiu però no l’esborrem de les llistes");
        System.out.println("[12] Mostrar les peticions d’intercanvi pendents de respondre");
        System.out.println("[13] Mostrar les peticions d’intercanvi acceptades");
        System.out.println("[14] Mostrar les peticions d’intercanvi refusades");
        System.out.println("[15] Mostrar els usuaris que tenen valoracions en els seus intercanvis superiors a un llindar que indiqui l’usuari");
        System.out.println("[16] Mostrar el servei del qual s’han fet més intercanvis i indicar el número d’aquests");
        System.out.println("[17] Sortir de l’aplicació");
    }

    public static llistaProductes llegirProductes() throws IOException {
        BufferedReader fitProductes = new BufferedReader(new FileReader("Productes.txt"));
        boolean finalFitxer = false;
        String frase = "";
        String[] fraseSplit, dataSplit;
        Servei serv;
        Be be;
        llistaProductes llistaProd = new llistaProductes(MAX);

        try {
            while (!finalFitxer) {
                try {
                    frase = fitProductes.readLine();
                    fraseSplit = frase.split(";");

                    // El nom, desc y dataOf son compartits ja sigui bé o servei
                    String nom = fraseSplit[1];
                    String desc = fraseSplit[2];
                    dataSplit = fraseSplit[3].split("/");
                    Data dataOf = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                            Integer.parseInt(dataSplit[2]));

                    if (fraseSplit[0].equals("ser")) { // Si tenim un servei, crearem un nou servei
                        dataSplit = fraseSplit[4].split("/"); // Separem per / per poder crear un objecte de la classe
                                                              // Data
                        if (dataSplit[0].equals("null")) {
                            serv = new Servei(nom, desc, dataOf);
                        } else {
                            Data dataFiOf = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                                    Integer.parseInt(dataSplit[2]));
                            serv = new Servei(nom, desc, dataOf, dataFiOf); // Creem el servei
                        }
                        llistaProd.afegeixServei(serv); // L'afegim a la llista

                    } else if (fraseSplit[0].equals("be")) {
                        int amp = Integer.parseInt(fraseSplit[4]); // Assignem l'amplada
                        int alç = Integer.parseInt(fraseSplit[5]); // Assignem l'alçada
                        int fons = Integer.parseInt(fraseSplit[6]); // Assignem el fons
                        int pes = Integer.parseInt(fraseSplit[7]); // Assignem el pes
                        be = new Be(nom, desc, dataOf, amp, alç, fons, pes); // Creem el bé
                        llistaProd.afegeixBe(be); // L'afegim a la llista
                    }
                    fitProductes.close();
                } catch (EOFException e) {
                    finalFitxer = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fitxer no trobat!");
        } catch (IOException e) {
            System.err.println(e);
        }
        return llistaProd;
    }

    public static llistaPeticions llegirPeticions() throws IOException {
        BufferedReader fitPeticions = new BufferedReader(new FileReader("Peticions.txt"));
        String frase = "";
        String[] fraseSplit;
        boolean finalFitxer = false;
        llistaPeticions llistaPet = new llistaPeticions(MAX);
        Usuari usuariOf, usuariRep;
        String nom, desc;
        Data data, dataFiOferiment;
        String[] dataSplit;
        Peticio pet;
        Servei servOf, servRep;
        Be beOf, beRep;
        int amplada, alçada, fons, pes;

        try {
            while (!finalFitxer) {
                frase = fitPeticions.readLine();
                fraseSplit = frase.split(";");

                String codi = fraseSplit[0]; // Codi de la petició
                String alies = fraseSplit[1];
                String correu = fraseSplit[2];
                String codiPos = fraseSplit[3];
                usuariOf = new Usuari(alies, correu, codiPos); // Creem l'usuari que ofereix

                alies = fraseSplit[4];
                correu = fraseSplit[5];
                codiPos = fraseSplit[6];
                usuariRep = new Usuari(alies, correu, codiPos); // Creem l'usuari que rep

                // Si prodOf es un servei i prodRep es servei també
                if (fraseSplit[7].equals("ser") && fraseSplit[12].equals("ser")) {

                    nom = fraseSplit[8];
                    desc = fraseSplit[9];
                    dataSplit = fraseSplit[10].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                            Integer.parseInt(dataSplit[2]));
                    if (dataSplit[11] != null) {
                        dataSplit = fraseSplit[11].split("/");
                        dataFiOferiment = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                                Integer.parseInt(dataSplit[2]));
                        servOf = new Servei(nom, desc, data, dataFiOferiment);
                    } else {
                        servOf = new Servei(nom, desc, data);
                    }
                    nom = fraseSplit[13];
                    desc = fraseSplit[14];
                    dataSplit = fraseSplit[15].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                            Integer.parseInt(dataSplit[2]));
                    if (dataSplit[16] != null) {
                        dataSplit = fraseSplit[16].split("/");
                        dataFiOferiment = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                                Integer.parseInt(dataSplit[2]));
                        servRep = new Servei(nom, desc, data, dataFiOferiment);
                    } else {
                        servRep = new Servei(nom, desc, data);
                    }
                    pet = new Peticio(codi, usuariOf, usuariRep, servOf, servRep);
                    llistaPet.afegeixPeticio(pet);

                } else if (fraseSplit[7].equals("ser") && fraseSplit[12].equals("be")) { // Si prodOf es un servei i
                                                                                         // prodRep es un be

                    nom = fraseSplit[8];
                    desc = fraseSplit[9];
                    dataSplit = fraseSplit[10].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                            Integer.parseInt(dataSplit[2]));
                    if (dataSplit[11] != null) {
                        dataSplit = fraseSplit[11].split("/");
                        dataFiOferiment = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                                Integer.parseInt(dataSplit[2]));
                        servOf = new Servei(nom, desc, data, dataFiOferiment);
                    } else {
                        servOf = new Servei(nom, desc, data);
                    }

                    nom = fraseSplit[13];
                    desc = fraseSplit[14];
                    dataSplit = fraseSplit[15].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                            Integer.parseInt(dataSplit[2]));
                    amplada = Integer.parseInt(fraseSplit[16]);
                    alçada = Integer.parseInt(fraseSplit[17]);
                    fons = Integer.parseInt(fraseSplit[18]);
                    pes = Integer.parseInt(fraseSplit[19]);
                    beRep = new Be(nom, desc, data, amplada, alçada, fons, pes);

                    pet = new Peticio(codi, usuariOf, usuariRep, servOf, beRep);
                    llistaPet.afegeixPeticio(pet);

                } else if (fraseSplit[7].equals("be") && fraseSplit[12].equals("be")) { // Si prodOf es un be i prodRep
                                                                                        // es un be també

                    nom = fraseSplit[8];
                    desc = fraseSplit[9];
                    dataSplit = fraseSplit[10].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                            Integer.parseInt(dataSplit[2]));
                    amplada = Integer.parseInt(fraseSplit[11]);
                    alçada = Integer.parseInt(fraseSplit[12]);
                    fons = Integer.parseInt(fraseSplit[13]);
                    pes = Integer.parseInt(fraseSplit[14]);
                    beOf = new Be(nom, desc, data, amplada, alçada, fons, pes);

                    nom = fraseSplit[15];
                    desc = fraseSplit[16];
                    dataSplit = fraseSplit[17].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                            Integer.parseInt(dataSplit[2]));
                    amplada = Integer.parseInt(fraseSplit[18]);
                    alçada = Integer.parseInt(fraseSplit[19]);
                    fons = Integer.parseInt(fraseSplit[20]);
                    pes = Integer.parseInt(fraseSplit[21]);
                    beRep = new Be(nom, desc, data, amplada, alçada, fons, pes);

                    pet = new Peticio(codi, usuariOf, usuariRep, beOf, beRep);
                    llistaPet.afegeixPeticio(pet);

                } else if (fraseSplit[7].equals("be") && fraseSplit[12].equals("ser")) { // Si prodOf es un be i prodRep
                                                                                         // es un servei

                    nom = fraseSplit[8];
                    desc = fraseSplit[9];
                    dataSplit = fraseSplit[10].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                            Integer.parseInt(dataSplit[2]));
                    amplada = Integer.parseInt(fraseSplit[11]);
                    alçada = Integer.parseInt(fraseSplit[12]);
                    fons = Integer.parseInt(fraseSplit[13]);
                    pes = Integer.parseInt(fraseSplit[14]);
                    beOf = new Be(nom, desc, data, amplada, alçada, fons, pes);

                    nom = fraseSplit[15];
                    desc = fraseSplit[16];
                    dataSplit = fraseSplit[17].split("/");
                    data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                            Integer.parseInt(dataSplit[2]));
                    if (dataSplit[18] != null) {
                        dataSplit = fraseSplit[18].split("/");
                        dataFiOferiment = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                                Integer.parseInt(dataSplit[2]));
                        servRep = new Servei(nom, desc, data, dataFiOferiment);
                    } else {
                        servRep = new Servei(nom, desc, data);
                    }

                    pet = new Peticio(codi, usuariOf, usuariRep, beOf, servRep);
                    llistaPet.afegeixPeticio(pet);
                }
            }

        } catch (EOFException e) {
            finalFitxer = true;
        }
        fitPeticions.close();
        return llistaPet;
    }

    public static llistaUsuaris llegirUsuaris() throws IOException, ClassNotFoundException {
        carregarUsuaris carregarUsu = new carregarUsuaris();
        llistaUsuaris llistaUsu = new llistaUsuaris(MAX);
        llistaUsu = carregarUsu.carregarUsu();
        return llistaUsu;
    }

    public static llistaProductes registraServei(Usuari user, llistaProductes llistaProd) {
        Servei serv;
        String nom, desc, dat, opcio;
        String[] dataSplit;
        Data data, dataFiOferiment;
        // llistaProductes llistaProd = new llistaProductes(MAX);

        System.out.println("Introdueix el nom del servei:");
        nom = teclat.nextLine();
        System.out.println("Indica la descripcio:");
        desc = teclat.nextLine();
        System.out.println("Indica la data: [dd/mm/aaaa]:");
        dat = teclat.nextLine();
        dataSplit = dat.split("/");
        data = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
        System.out.println("Vols introduir data de final d'oferiment del servei? [Si/No]");
        opcio = teclat.nextLine();
        if (opcio.equalsIgnoreCase("Si")) {
            System.out.println("Indica la data: [dd/mm/aaaa]");
            dat = teclat.nextLine();
            dataSplit = dat.split("/");
            dataFiOferiment = new Data(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]),
                    Integer.parseInt(dataSplit[2]));
            serv = new Servei(nom, desc, data, dataFiOferiment);
        } else {
            serv = new Servei(nom, desc, data);
        }
        llistaProd.afegeixServei(serv);
        user.afegeixProducteUsu(serv);
        return llistaProd;
    }

    public static llistaProductes registraBe(Usuari user, llistaProductes llistaProd, llistaUsuaris llistaUsu) {
        Be be;
        String nom, desc, dat;
        String[] dataSplit;
        Data data;
        int ampl, alça, fons, pes;
        // llistaProductes llistaProd = new llistaProductes(MAX);

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
        llistaProd.afegeixBe(be);
        user.afegeixProducteUsu(be);

        return llistaProd;
    }
}