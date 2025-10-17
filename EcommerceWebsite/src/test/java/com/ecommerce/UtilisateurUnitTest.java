package com.ecommerce;

import entities.Utilisateur;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilisateurUnitTest {

    @Test
    public void testEstLivreurDisponible_Vrai() {
        Utilisateur u = new Utilisateur();
        u.setRole("livreur");
        u.setStatut("disponible");

        assertTrue(u.estLivreurDisponible(),
                "Un livreur avec le statut 'disponible' doit être reconnu comme disponible.");
    }

    @Test
    public void testEstLivreurDisponible_Faux_SiRoleDifferent() {
        Utilisateur u = new Utilisateur();
        u.setRole("client");
        u.setStatut("disponible");

        assertFalse(u.estLivreurDisponible(),
                "Un client ne doit pas être reconnu comme livreur disponible.");
    }

    @Test
    public void testEstLivreurDisponible_Faux_SiStatutNonDisponible() {
        Utilisateur u = new Utilisateur();
        u.setRole("livreur");
        u.setStatut("occupé");

        assertFalse(u.estLivreurDisponible(),
                "Un livreur avec un autre statut que 'disponible' ne doit pas être disponible.");
    }
}
