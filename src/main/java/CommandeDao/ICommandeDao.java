package CommandeDao;

import java.util.List;
import java.util.Map;

import entities.Commande;

public interface ICommandeDao {
	    void saveCommande(Commande commande);
	    void updateCommandeStatut(Long idCommande, String nouveauStatut);
// Inscription
	    Commande findCommande(long idClient); // Authentification
	    List<Commande> getAllCommande(); // Optionnel : Liste des commandes
	    List<Commande> getCommercantCommandes(Long id);
	    List<Commande> getClientCommandes(Long id);
	    Commande getCommandeById(Long idCommande);
	    void updateCommande(Commande commande);
	    void deleteCommande(Long idCommande);
	    Map<String, Integer> getCommandeStatsByCommercant(long idCommercant);
}
