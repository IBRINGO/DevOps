package com.ecommerce;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import SingletonConnection.SingletonConnection;
import UtilisateurDao.UtilisateurDaoImpl;
import entities.Utilisateur;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SampleTest {

    private Connection connection;
    private UtilisateurDaoImpl utilisateurDao;

    @BeforeAll
    public void init() throws Exception {
        // ✅ Activer le mode test (H2)
        System.setProperty("TEST_ENV", "true");

        // ✅ Connexion à H2
        connection = SingletonConnection.getConnection();

        // ✅ Créer la table utilisateur avec la colonne 'statut'
        Statement stmt = connection.createStatement();
        stmt.execute("""
            CREATE TABLE utilisateur (
                id_utilisateur INT AUTO_INCREMENT PRIMARY KEY,
                nom VARCHAR(100),
                email VARCHAR(100),
                mot_de_passe VARCHAR(100),
                contact BIGINT,
                adresse VARCHAR(255),
                role VARCHAR(50),
                statut VARCHAR(50)
            );
        """);
        stmt.close();

        utilisateurDao = new UtilisateurDaoImpl();
        System.out.println("✅ Base H2 initialisée pour les tests !");
    }

    @BeforeEach
    public void setupData() {
        // Insérer un client
        Utilisateur client = new Utilisateur();
        client.setNom("Abdullah");
        client.setEmail("abdullah@example.com");
        client.setMotDePasse("12345678");
        client.setContact(822828828L);
        client.setAdresse("Harhoura");
        client.setRole("client");
        client.setStatut("disponible");
        utilisateurDao.saveUtilisateur(client);

        // Insérer un livreur
        Utilisateur livreur = new Utilisateur();
        livreur.setNom("Mohamed");
        livreur.setEmail("livreur@example.com");
        livreur.setMotDePasse("livreur123");
        livreur.setContact(811111111L);
        livreur.setAdresse("Rabat");
        livreur.setRole("livreur");
        livreur.setStatut("disponible");
        utilisateurDao.saveUtilisateur(livreur);
    }

    @Test
    public void testSaveUtilisateur() {
        Utilisateur u = new Utilisateur();
        u.setNom("Sara");
        u.setEmail("sara@example.com");
        u.setMotDePasse("password");
        u.setContact(833333333L);
        u.setAdresse("Casablanca");
        u.setRole("client");
        u.setStatut("disponible");

        assertDoesNotThrow(() -> {
            utilisateurDao.saveUtilisateur(u);
        }, "L'enregistrement d'un utilisateur ne doit pas lever d'exception.");
    }

    @Test
    public void testFindByEmailAndPassword() {
        Utilisateur u = utilisateurDao.findByEmailAndPassword("abdullah@example.com", "12345678");
        assertNotNull(u);
        assertEquals("Abdullah", u.getNom());
        assertEquals("client", u.getRole());
    }

    @AfterAll
    public void tearDown() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("🔒 Connexion H2 fermée.");
        }
    }
}
