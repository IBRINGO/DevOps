package CommandeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import SingletonConnection.SingletonConnection;
import entities.Commande;
import entities.Produit;

public class CommandeDaoImpl implements ICommandeDao{
    private Connection connection = SingletonConnection.getConnection();
    

	@Override
	public void saveCommande(Commande commande) {
		try {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO commande (id_client,id_produit,id_commercant,quantite,adresse) VALUES (?,?, ?, ?, ?)");
            ps.setLong(1, commande.getIdClient());
            ps.setLong(2, commande.getIdProduit());
            ps.setLong(3, commande.getIdCommercant());
            ps.setLong(4, commande.getQuantite());
            ps.setString(5, commande.getAdresse());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public Commande findCommande(long idClient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commande> getAllCommande() {
		// TODO Auto-generated method stub
		return null;
	}
	/**	public List<Commande> getCommercantCommandes(Long id) {
		List<Commande> commandes = new ArrayList<>();
	    Connection connection = SingletonConnection.getConnection();
	    try {
	        // Préparation de la requête SELECT
	        PreparedStatement ps = connection.prepareStatement("SELECT * FROM commande WHERE id_commercant = ?");
	        ps.setLong(1, id);
	        ResultSet rs = ps.executeQuery();
	        
	        // Parcours des résultats
	        while (rs.next()) {
	            Commande commande = new Commande();
	            commande.setIdCommande(rs.getLong("id_commande"));
	            commande.setIdClient(rs.getLong("id_client"));
	            commande.setIdProduit(rs.getLong("id_produit"));
	            commande.setIdCommercant(rs.getLong("id_commercant"));
	            commande.setQuantite(rs.getLong("quantite"));
	            commande.setAdresse(rs.getString("adresse"));
	            commande.setDateCommande(rs.getTimestamp("date_commande").toLocalDateTime());
	            commande.setStatut(rs.getString("statut"));
	            
	            
	            // Ajout du commande à la liste
	            commandes.add(commande);
	        }

	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return commandes;
	}
	*/
	public List<Commande> getCommercantCommandes(Long id) {
	    List<Commande> commandes = new ArrayList<>();
	    Connection connection = SingletonConnection.getConnection();
	    try {
	        // Requête avec jointure pour récupérer les détails du produit
	        String sql = "SELECT c.*, p.nom, p.prix, p.image " +
	                     "FROM commande c " +
	                     "JOIN produit p ON c.id_produit = p.id_produit " +
	                     "WHERE c.id_commercant = ?";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setLong(1, id);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            Commande commande = new Commande();
	            commande.setIdCommande(rs.getLong("id_commande"));
	            commande.setIdClient(rs.getLong("id_client"));
	            commande.setIdProduit(rs.getLong("id_produit"));
	            commande.setQuantite(rs.getLong("quantite"));
	            commande.setAdresse(rs.getString("adresse"));
	            commande.setDateCommande(rs.getTimestamp("date_commande").toLocalDateTime());
	            commande.setStatut(rs.getString("statut"));
	            
	            // Détails du produit
	            Produit produit = new Produit();
	            produit.setNom(rs.getString("nom"));
	            produit.setPrix(rs.getDouble("prix"));
	            produit.setImage(rs.getString("image"));
	            commande.setProduit(produit); // Ajoutez un champ Produit dans votre classe Commande
	            
	            commandes.add(commande);
	        }
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return commandes;
	}
	
	
	public void updateCommandeStatut(Long idCommande, String nouveauStatut) {
	    String query = "UPDATE commande SET statut = ? WHERE id_commande = ?";
	    try (PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setString(1, nouveauStatut);
	        ps.setLong(2, idCommande);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public List<Commande> getClientCommandes(Long id) {
	    List<Commande> commandes = new ArrayList<>();
	    Connection connection = SingletonConnection.getConnection();
	    try {
	        // Requête avec jointure pour récupérer les détails du produit
	        String sql = "SELECT c.*, p.nom, p.prix, p.image " +
	                     "FROM commande c " +
	                     "JOIN produit p ON c.id_produit = p.id_produit " +
	                     "WHERE c.id_client = ?";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setLong(1, id);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            Commande commande = new Commande();
	            commande.setIdCommande(rs.getLong("id_commande"));
	            commande.setIdClient(rs.getLong("id_client"));
	            commande.setIdProduit(rs.getLong("id_produit"));
	            commande.setQuantite(rs.getLong("quantite"));
	            commande.setAdresse(rs.getString("adresse"));
	            commande.setDateCommande(rs.getTimestamp("date_commande").toLocalDateTime());
	            commande.setStatut(rs.getString("statut"));
	            
	            // Détails du produit
	            Produit produit = new Produit();
	            produit.setNom(rs.getString("nom"));
	            produit.setPrix(rs.getDouble("prix"));
	            produit.setImage(rs.getString("image"));
	            commande.setProduit(produit); // Ajoutez un champ Produit dans votre classe Commande
	            
	            commandes.add(commande);
	        }
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return commandes;
	}
	public Commande getCommandeById(Long idCommande) {
        Commande commande = new Commande();
	    Connection connection = SingletonConnection.getConnection();
	    
	    try {
	    	String sql = "SELECT c.*, p.nom, p.prix, p.image " +
                    "FROM commande c " +
                    "JOIN produit p ON c.id_produit = p.id_produit " +
                    "WHERE c.id_commande = ?";
		    //String query = "SELECT * FROM commande WHERE id_commande = ?";
		    PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setLong(1, idCommande);
	        ResultSet rs = ps.executeQuery();
{
	            if (rs.next()) {
	            	
		            commande.setIdCommande(rs.getLong("id_commande"));
		            commande.setIdClient(rs.getLong("id_client"));
		            commande.setIdProduit(rs.getLong("id_produit"));
		            commande.setQuantite(rs.getLong("quantite"));
		            commande.setAdresse(rs.getString("adresse"));
		            commande.setDateCommande(rs.getTimestamp("date_commande").toLocalDateTime());
		            commande.setStatut(rs.getString("statut"));
		            
		            // Détails du produit
		            Produit produit = new Produit();
		            produit.setNom(rs.getString("nom"));
		            produit.setPrix(rs.getDouble("prix"));
		            produit.setImage(rs.getString("image"));
		            commande.setProduit(produit); // Ajoutez un champ Produit dans votre classe Commande
		            	            }
	            ps.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return commande;
	}

	
	public void updateCommande(Commande commande) {
		Connection connection = SingletonConnection.getConnection();
	    try {
		String query = "UPDATE commande SET quantite = ?, adresse = ? WHERE id_commande = ?";
		PreparedStatement ps = connection.prepareStatement(query);	        
	        ps.setLong(1, commande.getQuantite());
	        ps.setString(2, commande.getAdresse());
	        ps.setLong(3, commande.getIdCommande());
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	} 
	
	public void deleteCommande(Long idCommande) {
		Connection connection = SingletonConnection.getConnection();
	    try  {
		    String query = "DELETE FROM commande WHERE id_commande = ?";
			PreparedStatement ps = connection.prepareStatement(query);	        

	        ps.setLong(1, idCommande);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public Map<String, Integer> getCommandeStatsByCommercant(long idCommercant) {
	    Map<String, Integer> stats = new HashMap<>();
	    String query = "SELECT statut, COUNT(*) as total " +
	                   "FROM commande " +
	                   "WHERE id_commercant = ? " +
	                   "GROUP BY statut";
	    try (PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setLong(1, idCommercant);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            stats.put(rs.getString("statut"), rs.getInt("total"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return stats;
	}







}
