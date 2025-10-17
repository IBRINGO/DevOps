package UtilisateurDao;

import java.util.List;
import entities.Utilisateur;

public interface IUtilisateurDao {
	    void saveUtilisateur(Utilisateur utilisateur); // Inscription
	    Utilisateur findByEmailAndPassword(String email, String password);
	    List<Utilisateur> findLivreursDisponibles();
	    Utilisateur findlivreur();
	    void updateLivreurStatut(Long idLivreur, String nouveauStatut);// Authentification
	    List<Utilisateur> findAll(); // Optionnel : Liste des utilisateurs

}
