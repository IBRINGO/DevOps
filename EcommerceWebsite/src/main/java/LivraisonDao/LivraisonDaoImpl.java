package LivraisonDao;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import SingletonConnection.SingletonConnection;
import entities.Commande;
import entities.Livraison;
import entities.Utilisateur;

public class LivraisonDaoImpl implements ILivraisonDao {
    private Connection connection = SingletonConnection.getConnection();
    
   
    public void saveLivraison(Livraison livraison){
        String query = "INSERT INTO livraison (id_commande, id_livreur, date_ramassage) " +
                       "VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, livraison.getIdCommande());
            ps.setLong(2, livraison.getIdLivreur()); 
            ps.setDate(3, new java.sql.Date(livraison.getDateRamassage().getTime()));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Livraison findLivraisonWithDetailsById(Long idLivraison) {
        Livraison livraison = null;
        String query = "SELECT l.id_livraison, l.id_commande, l.id_livreur, l.date_ramassage, l.date_livraison, " +
                       "l.statut_livraison, c.adresse," +
                       "u.nom AS client_nom, u.contact AS client_contact, u.email AS client_email, " +
                       "u2.nom AS commercant_nom, u2.contact AS commercant_contact, u2.adresse AS commercant_adresse, u2.email AS commercant_email " +
                       
                       "FROM livraison l " +
                       "JOIN commande c ON l.id_commande = c.id_commande " +
                       "JOIN utilisateur u ON c.id_client = u.id_utilisateur " +
                       "JOIN utilisateur u2 ON c.id_commercant = u2.id_utilisateur " +
                       "WHERE l.id_livraison = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, idLivraison);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                livraison = new Livraison();
                livraison.setIdLivraison(rs.getLong("id_livraison"));
                livraison.setIdCommande(rs.getLong("id_commande"));
                livraison.setIdLivreur(rs.getLong("id_livreur"));
                livraison.setDateRamassage(rs.getDate("date_ramassage"));
                livraison.setDateLivraison(rs.getDate("date_livraison"));
                livraison.setStatutLivraison(rs.getString("statut_livraison"));

                Commande commande = new Commande();
                commande.setAdresse(rs.getString("adresse"));
                livraison.setCommande(commande);
                
                Utilisateur client = new Utilisateur();
                client.setNom(rs.getString("client_nom"));
                client.setContact(rs.getLong("client_contact"));
                client.setEmail(rs.getString("client_email"));
                client.setAdresse(rs.getString("adresse"));
                livraison.setClient(client);

                // Informations du commerçant
                Utilisateur commercant = new Utilisateur();
                commercant.setNom(rs.getString("commercant_nom"));
                commercant.setContact(rs.getLong("commercant_contact"));
                commercant.setEmail(rs.getString("commercant_email"));
                commercant.setAdresse(rs.getString("commercant_adresse"));
                livraison.setCommercant(commercant);

              
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livraison;
    }

    
    public Livraison ffindLivraisonWithDetailsById(Long idLivraison) {
        Livraison livraison = null;
        String query = "SELECT l.*, c.adresse, " +
                       "cl.nom AS client_nom, cl.contact AS client_contact, cl.email AS client_email, " +
                       "co.nom AS commercant_nom, co.contact AS commercant_contact, co.adresse AS commercant_adresse, co.email AS commercant_email " +
                       "FROM livraison l " +
                       "JOIN commande c ON l.id_commande = c.id_commande " +
                       "JOIN utilisateur cl ON c.id_client = cl.id_utilisateur " +
                       "JOIN utilisateur co ON c.id_commercant = co.id_utilisateur " +
                       "WHERE l.id_livraison = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, idLivraison);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                livraison = new Livraison();
                livraison.setIdLivraison(rs.getLong("id_livraison"));
                livraison.setIdCommande(rs.getLong("id_commande"));
                livraison.setDateRamassage(rs.getTimestamp("date_ramassage"));
                livraison.setDateLivraison(rs.getTimestamp("date_livraison"));
                livraison.setStatutLivraison(rs.getString("statut_livraison"));

                // Informations du client
                Utilisateur client = new Utilisateur();
                client.setNom(rs.getString("client_nom"));
                client.setContact(rs.getLong("client_contact"));
                client.setEmail(rs.getString("client_email"));
                client.setAdresse(rs.getString("adresse"));
                livraison.setClient(client);

                // Informations du commerçant
                Utilisateur commercant = new Utilisateur();
                commercant.setNom(rs.getString("commercant_nom"));
                commercant.setContact(rs.getLong("commercant_contact"));
                commercant.setEmail(rs.getString("commercant_email"));
                commercant.setAdresse(rs.getString("commercant_adresse"));
                livraison.setCommercant(commercant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livraison;
    }
    
    
    public void updateStatus(Long idLivraison, String statut) {
        String query;
        if ("livree".equals(statut)) {
            // Si le statut est "livree", on met à jour également la date de livraison
            query = "UPDATE livraison SET statut_livraison = ?, date_livraison = ? WHERE id_livraison = ?";
        } else {
            // Sinon, seule la mise à jour du statut est effectuée
            query = "UPDATE livraison SET statut_livraison = ? WHERE id_livraison = ?";
        }

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, statut);

            if ("livree".equals(statut)) {
                ps.setDate(2, new java.sql.Date(System.currentTimeMillis())); // Insère la date actuelle
                ps.setLong(3, idLivraison);
            } else {
                ps.setLong(2, idLivraison);
            }

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






	@Override
	public Livraison findLivraison(long idLivreur) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Livraison> getAllLivraison() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Livraison> getLivreurLivraisons(Long id) {
		 List<Livraison> livraisons = new ArrayList<>();
	        String query = "SELECT * FROM livraison WHERE id_livreur = ?";
	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setLong(1, id);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Livraison livraison = new Livraison();
	                livraison.setIdLivraison(rs.getLong("id_livraison"));
	                livraison.setIdCommande(rs.getLong("id_commande"));
	                livraison.setDateRamassage(rs.getTimestamp("date_ramassage"));
	                livraison.setDateLivraison(rs.getTimestamp("date_livraison"));
	                livraison.setStatutLivraison(rs.getString("statut_livraison"));
	                livraisons.add(livraison);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return livraisons;
	}

}
