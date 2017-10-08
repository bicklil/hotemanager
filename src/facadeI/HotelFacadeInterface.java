/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facadeI;

import ihm.HotelListener;
import java.util.Date;
import java.util.List;

/**
 * Interface que la classe HotelFacade doit implémenter pour faire le lien avec
 * l'IHM. Méthodes (paramètres, types de retour) à compléter/modifier/ajouter au
 * besoin
 *
 * @author riviere
 */
public interface HotelFacadeInterface {

    /**
     * Créer une réservation pour une liste de Chambres, à une date d pour un
     * nombre de nuits nbNuits, pour le client identifié par son ID.
     *
     * @param numChambre identifiants des Chambres concernées par la Réservation
     * @param d Date de début de la Réservation
     * @param nbNuits nombre de nuits de la réservation
     * @param idClient identifiant du Client lié à la Réservation
     */
    public void reserver(List<Integer> numChambre, Date d, int nbNuits, int idClient);

    /**
     * @return la liste des identifiants de toutes les Chambres de l'Hotel
     */
    public List<Integer> getToutesChambres();

    /**
     * Notifier les vues des chambres disponibles à la Réservation
     */
    public void chambresDispo();

    /**
     * Filtrer les Chambres disponibles à la Réservation à la Date d, pour un
     * nombre de nuits nbNuits, et notifier les vues.
     *
     * @param d la Date de début d'une potentielle Réservation
     * @param nbNuits le nombre de nuits d'une potentielle Réservation
     */
    public void filtrerChambresDispo(Date d, int nbNuits);

    /**
     * Filtrer les Chambres disponibles à la Réservation à la Date d, pour un
     * nombre de nuits nbNuits, avec une taille minimale de taille m², et
     * notifier les vues.
     *
     * @param d la Date de début d'une potentielle Réservation
     * @param nbNuits le nombre de nuits d'une potentielle Réservation
     * @param tailleMinimum des Chambres d'une potentielle Réservation
     */
    public void filtrerChambresDispo(Date d, int nbNuits, int tailleMinimum);

    /**
     * Créer une nouvelle Chambre
     *
     * @param numero de la nouvelle Chambre
     * @param taille de la nouvelle Chambre
     */
    public void creerChambre(int numero, int taille);

    /**
     * Supprimer une Chambre existante
     *
     * @param numero de la Chambre existante
     */
    public void supprimerChambre(int numero);

    /**
     * Modifier une Chambre existante
     *
     * @param numero de la Chambre existante
     * @param taille de la Chambre existante
     */
    public void modifierChambre(int numero, int taille);

    /**
     * @return la liste de tous les Clients enregistrés par le système
     */
    public List<Integer> getTousClients();

    /**
     * Trouver un Client à partir de son nom, et notifier les vues
     *
     * @param nom
     * @return true si le Client a été trouvé, false sinon
     */
    public boolean filtrerClient(String nom);

    /**
     * Trouver un Client à partir de son identifiant, et notifier les vues
     *
     * @param id
     * @return true si le Client a été trouvé, false sinon
     */
    public boolean filtrerClient(int id);

    /**
     * Enregistrer un nouveau Client dans le système et lui attriuer un
     * identifiant unique
     *
     * @param nom du nouveau Client
     * @param prenom du nouveau Client
     * @param naissance date de naissance du nouveau Client
     */
    public void creerClient(String nom, String prenom, String naissance);

    /**
     * Filtrer les Réservations liés au Client demandé, et notifier les vues
     *
     * @param idClient l'identifiant du Client lié aux Réservations
     */
    public void filtrerReservationsClient(int idClient);

    /**
     * Filtrer les Réservations liés à la Chambre demandée, et notifier les vues
     *
     * @param numChambre l'identifiant de la Chambre liée aux Réservations
     */
    public void filtrerReservationsChambre(int numChambre);

    /**
     * Permet d'ajouter à la liste de listeners une vue implémentant l'interface
     * HotelListener pour qu'elle s'abonne aux notifications
     *
     * @param l la vue implémentant l'interface HotelListener
     */
    public void addListener(HotelListener l);

    /**
     * Permet de supprimer de la liste de listeners une vue implémentant
     * l'interface HotelListener
     *
     * @param l la vue implémentant l'interface HotelListener
     */
    public void removeListener(HotelListener l);
}
