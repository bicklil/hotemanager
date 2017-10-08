/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.util.Date;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;

/**
 * Cette interface doit être implémentée par les différentes vues de l'IHM. Le
 * modèle aura une liste d'instances implémentantant cette interface et les
 * notifiera lorsque c'est nécessaire.
 *
 * @author riviere
 */
public interface HotelListener extends EventListener {

    /**
     * Méthode à appeler par le modèle lors de l'initialisation de la vue, pour
     * transemttre les informations intéressantes du modèle.
     *
     * @param idsChambre Liste d'identifiants des chambres existantes
     * @param tailles Liste des tailles des chambres existantes
     * @param resa Liste des identifiants des réservations existantes ainsi que
     * la liste des chambres associées
     * @param datesDebut Liste des différentes dates de débuts de chacune des
     * réservations existantes
     * @param nbNuits Liste des différents nombres de nuits de chacune des
     * réservations existantes
     * @param idsClient Liste des différents identifiants des clients ayant
     * passé les réservations existantes
     */
    public void init(List<Integer> idsChambre, List<Integer> tailles, HashMap<Integer, List<Integer>> resa, List<Date> datesDebut, List<Integer> nbNuits, List<Integer> idsClient);

    /**
     * Méthode pour mettre à jour la liste des réservations
     *
     * @param resa Liste des identifiants des réservations existantes ainsi que
     * la liste des chambres associées
     * @param datesDebut Liste des différentes dates de débuts de chacune des
     * réservations existantes
     * @param nbNuits Liste des différents nombres de nuits de chacune des
     * réservations existantes
     * @param idsClient Liste des différents identifiants des clients ayant
     * passé les réservations existantes
     */
    public void reservationsModifiees(HashMap<Integer, List<Integer>> resa, List<Date> datesDebut, List<Integer> nbNuits, List<Integer> idsClient);

    /**
     * Méthode pour mettre à jour la liste des chambres existantes ou
     * correspondant aux critères de recherche entrés.
     *
     * @param idsChambre Liste d'identifiants des chambres existantes
     * @param tailles Liste des tailles des chambres existantes
     */
    public void chambresModifiees(List<Integer> idsChambre, List<Integer> tailles);

    /**
     *
     * Méthode pour transmettre les attributs du client recherché par
     * l'utilisateur
     *
     * @param nom du client recherché
     * @param prenom du client recherché
     * @param date de naissance du client recherché
     * @param id du client recherché
     */
    public void clientRecherche(String nom, String prenom, String date, int id);

}
