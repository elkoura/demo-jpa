package fr.diginamic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Date;

public class ConnexionJpa2 {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu_essai");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Vérification de la connexion
        System.out.println("Connexion à la base de données réussie !");

        // Créer et insérer un nouveau client
        Client client = new Client();
        client.setNom("Dupont");
        client.setPrenom("Jean");

        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();

        // Créer et insérer un nouveau livre
        Livre livre = new Livre();
        livre.setTitre("Le Petit Prince");
        livre.setAuteur("Antoine de Saint-Exupéry");

        entityManager.getTransaction().begin();
        entityManager.persist(livre);
        entityManager.getTransaction().commit();

        // Créer et insérer un nouvel emprunt
        Emprunt emprunt = new Emprunt();
        emprunt.setDateDebut(new Date());
        emprunt.setDelaiMax(30);
        emprunt.setClient(client);
        emprunt.getLivres().add(livre);

        entityManager.getTransaction().begin();
        entityManager.persist(emprunt);
        entityManager.getTransaction().commit();

        // Vérifier l'insertion
        Emprunt verifEmprunt = entityManager.find(Emprunt.class, emprunt.getId());
        if (verifEmprunt != null) {
            System.out.println("Nouvel emprunt inséré : " + verifEmprunt);
        } else {
            System.out.println("Insertion échouée");
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
