package fr.diginamic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnexionJpa {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu_essai");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Vérification de la connexion
        System.out.println("Connexion à la base de données réussie !");

        // Créer une nouvelle région
        Region nouvelleRegion = new Region();
        nouvelleRegion.setNom("Franche-comté");

        entityManager.getTransaction().begin();
        entityManager.persist(nouvelleRegion);
        entityManager.getTransaction().commit();

        // Extraire une région par ID
        Region region = entityManager.find(Region.class, 1);
        if (region != null) {
            System.out.println("Région trouvée : " + region);
        } else {
            System.out.println("Région non trouvée");
        }

        // Vérifier que la nouvelle région est bien présente
        Region verifRegion = entityManager.find(Region.class, nouvelleRegion.getId());
        if (verifRegion != null) {
            System.out.println("Nouvelle région insérée : " + verifRegion);
        } else {
            System.out.println("Insertion échouée");
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}