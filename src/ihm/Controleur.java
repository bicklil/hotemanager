/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import facadeI.HotelFacade;
import facadeI.HotelFacadeInterface;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JFrame;

import chambre.Chambre;
import chambre.Hotel;
import chambre.SalleDEau;
import chambre.CategorieC;

/**
 * Classe qui sert de Controleur entre les vues et la façade du système (modèle)
 * (HotelFacade) Elle contient ici une JFrame qui va venir accueillir les
 * différentes vues héritant de JPanel (VuePrincipale, GestionDesChambres)
 * suivant le contexte
 *
 * @author riviere
 */
public class Controleur {

    private final JFrame fenetre;
    private final HotelFacadeInterface modele;
    private final VuePrincipale vueP;
    private GestionDesChambres gestion;

    private final static int FRAME_WIDTH = 900;
    private final static int FRAME_HEIGHT = 480;

    public Controleur(HotelFacadeInterface modele) {
        fenetre = new JFrame();
        vueP = new VuePrincipale(this);
        this.modele = modele;

        fenetre.add(vueP);
        fenetre.setName("HotelManager");
        fenetre.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        fenetre.setTitle("HotelManager - UBO - M1 Informatique - Free Software under GPL License");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        fenetre.setVisible(true);

        modele.addListener(vueP);
    }

    /**
     * Appelée depuis la vue VuePrinciale, demande au modèle la création d'une
     * nouvelle réservation après vérification des paramètres
     *
     * @param numChambre Liste des identifiants des Chambres à réserver
     * @param date Date de début de la Réservation
     * @param nbNuits de la Réservation sous la forme d'une chaine de caractères
     * @param idClient Identifiant du Client concerné par la Réservation sous la
     * forme d'une chaine de caractères
     */
    public void demandeReservation(List<Integer> numChambre, String date, String nbNuits, String idClient) {
        Date d = convertStringToDate(date);
        verifEtReserve(numChambre, d, nbNuits, idClient);
    }

    /**
     * Appelée depuis la vue GestionDesChambres, demande au modèle la création
     * d'une nouvelle Chambre après vérification des paramètres
     *
     * @param num le numéro de la nouvelle Chambre sous la forme d'une chaine de
     * caractères
     * @param t la taille de la nouvelle Chambre sous la forme d'une chaine de
     * caractères
     */
    public void creerChambre(String num, String t) {
        try {
            int numero = Integer.parseInt(num);
            int taille = Integer.parseInt(t);
            if (modele.getToutesChambres().contains(numero)) {
                gestion.afficherErreur("Une chambre avec ce numéro existe déjà !");
            } else {
                modele.creerChambre(numero, taille);
            }
        } catch (Exception e) {
            gestion.afficherErreur("Le format du numero de chambre ou sa taille est erroné.");
        }
    }

    /**
     * Appelée depuis la vue GestionDesChambres, demande au modèle la
     * suppression d'une Chambre existante
     *
     * @param num le numéro de la Chambre existante sous la forme d'une chaine
     * de caractères
     */
    public void supprimerChambre(String num) {
        try {
            int numero = Integer.parseInt(num.trim());
            if (!modele.getToutesChambres().contains(numero)) {
                gestion.afficherErreur("Aucune chambre avec ce numéro n'existe.");
            } else {
                modele.supprimerChambre(numero);
            }
        } catch (Exception e) {
            gestion.afficherErreur("Le format du numero de chambre est erroné.");
        }
    }

    /**
     * Appelée depuis la vue GestionDesChambres, demande au modèle la
     * modification d'une Chambre existante après vérification des paramètres
     *
     * @param num le numéro de la Chambre existante sous la forme d'une chaine
     * de caractères
     * @param t la nouvelle taille pour la Chambre existante sous la forme d'une
     * chaine de caractères
     */
    public void modifierChambre(String num, String t) {
        try {
            int numero = Integer.parseInt(num);
            int taille = Integer.parseInt(t);
            if (!modele.getToutesChambres().contains(numero)) {
                gestion.afficherErreur("Aucune chambre avec ce numéro n'existe.");
            } else {
                modele.modifierChambre(numero, taille);
            }
        } catch (Exception e) {
            gestion.afficherErreur("Le format du numero de chambre ou sa taille est erroné.");
        }
    }

    /**
     * Permet de switcher de la vue : afficher GestionDesChambres et l'ajouter à
     * la liste des listeners du modèle
     */
    public void afficherGestion() {
        gestion = new GestionDesChambres(this);

        fenetre.remove(vueP);
        fenetre.add(gestion, BorderLayout.CENTER);
        vueP.setVisible(false);
        gestion.setVisible(true);
        fenetre.pack();

        modele.addListener(gestion);
    }

    /**
     * Permet de switcher de vue : afficher VuePrincipale
     */
    public void retourVueP() {
        fenetre.remove(gestion);
        fenetre.add(vueP);
        gestion.setVisible(false);
        vueP.setVisible(true);
        fenetre.pack();

        modele.removeListener(gestion);
    }

    /**
     * Demander au modèle de filtrer les Chambres selon leur disponibilité à la
     * Réservation, après vérification des paramètres.
     *
     * @param date la Date de début d'une potentielle Réservation
     * @param nbNuits le nombre de nuits d'une potentielle Réservation
     * @param taille la taille minimum des Chambres recherchées
     */
    public void filtrerDispoChambres(String date, String nbNuits, String taille) {
        if ((date.isEmpty()) && (nbNuits.isEmpty()) && (taille.isEmpty())) {
            modele.chambresDispo();
        } else {
            try {
                Date d = convertStringToDate(date);
                if ((d == null) || (dateFuture(d))) {
                    vueP.afficherErreur("Le format de la date n'est pas correct !");
                } else {
                    if (taille.isEmpty()) {
                        modele.filtrerChambresDispo(d, Integer.parseInt(nbNuits));
                    } else {
                        modele.filtrerChambresDispo(d, Integer.parseInt(nbNuits), Integer.parseInt(taille));
                    }
                }
            } catch (Exception e) {
                vueP.afficherErreur("Le format du nombre de jours ou de la taille demandée est incorrect.");
            }
        }
    }

    /**
     * Demander au modèle de notifier les vues du Client avec l'identifiant
     * demandé trouvé. Affiche une erreur dans la vue si le Client n'existe pas.
     *
     * @param id identifiant du Client recherché
     */
    public void rechercheClientID(String id) {
        try {
            if (!modele.filtrerClient(Integer.parseInt(id))) {
                vueP.afficherErreur("Client " + id + " inexistant.");
            }
        } catch (Exception e) {
            vueP.afficherErreur("Le format de l'identifiant client est incorrect.");
        }
    }

    /**
     * Demander au modèle de notifier les vues du Client avec le nom demandé
     * trouvé. Affiche une erreur dans la vue si le Client n'existe pas.
     *
     * @param nom du Client recherché
     */
    public void rechercheClientNom(String nom) {
        if (!modele.filtrerClient(nom)) {
            vueP.afficherErreur("Client " + nom + " inexistant.");
        }
    }
    
     /**
     * Vérifier que les paramètres de la demande de réservation sont corrects,
     * puis demander la création d'une nouvelle Réservation au modèle. Sont
     * vérifiés : - le format des paramètres nbNuitsS et idClients entrés par
     * l'utilisateur - le format de la Date d - que la Date d se situe dans le
     * futur - que le client d'identifiant idClient existe
     *
     * @param numChambre Liste des identifiants des Chambres à réserver
     * @param date Date de début de la Réservation
     * @param nbNuitsS de la Réservation sous la forme d'une chaine de
     * caractères
     * @param idClientS Identifiant du Client concerné par la Réservation sous
     * la forme d'une chaine de caractères
     */
    private void verifEtReserve(List<Integer> numChambre, Date d, String nbNuitsS, String idClientS) {
        try {
            int nbJours = Integer.parseInt(nbNuitsS);
            int idClient = Integer.parseInt(idClientS);

            if (d != null) {
                if (dateFuture(d)) {
                    if (clientExiste(idClient)) {
                        modele.reserver(numChambre, d, nbJours, idClient);
                    } else {
                        vueP.afficherErreur("Le client n'existe pas !");
                    }
                } else {
                    vueP.afficherErreur("La date n'est pas correcte !");
                }
            } else {
                vueP.afficherErreur("Le format de la date n'est pas correct !");
            }

        } catch (Exception e) {
            vueP.afficherErreur("Le format du nombre de jours ou de l'identifiant Client est incorrect.");
        }
    }

    /**
     * Convertit une chaine de la forme jj/mm/aaaa sous la forme d'une Date
     *
     * @param date
     * @return null si le format n'est pas respecté
     */
    private Date convertStringToDate(String date) {
        try {
            int jours = Integer.parseInt(date.substring(0, 2));
            int mois = Integer.parseInt(date.substring(3, 5)) - 1;
            int annee = Integer.parseInt(date.substring(6, 10));
            Calendar c = new GregorianCalendar(annee, mois, jours);
            return c.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Teste si la Date d est bien située dans le futur
     *
     * @param d la Date d à tester
     * @return true si d est située dans le futur, false sinon
     */
    private boolean dateFuture(Date d) {
        System.out.println(d + " " + new Date() + "  " + d.after(new Date()));
        return d.after(new Date());
    }

    /**
     * Teste si le client d'identifiant numeroClient existe dans le modèle
     *
     * @param numeroClient identifiant du client recherché
     * @return true si le Client existe dans le modèle, false sinon
     */
    private boolean clientExiste(int numeroClient) {
        return modele.getTousClients().contains(numeroClient);
    }

    /**
     * Méthode d'entrée du programme
     *
     * @param args
     */
    public static void main(String[] args) {
        // Créer ici une instance du modèle et de l'interface (HotelFacade)
    	// puis créer une instance du Controleur en lui passant l'instance de la facade
    	Chambre chb1 = new Chambre(1,10,CategorieC.chbstandard,new SalleDEau(2));
    	Chambre chb2 = new Chambre(2,10,CategorieC.chbstandard,new SalleDEau(2));
    	List<Chambre> chbs = new ArrayList<Chambre>();
    	chbs.add(chb1);
    	chbs.add(chb2);
    	HotelFacade hf = new HotelFacade(Hotel.creationHotel(chbs));
    	Controleur ctrl = new Controleur(hf);
    }

}
