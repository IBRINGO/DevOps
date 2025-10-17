package UtilisateurDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import SingletonConnection.SingletonConnection;
import entities.Utilisateur;
public class UtilisateurDaoImpl implements IUtilisateurDao {
    private Connection connection = SingletonConnection.getConnection();
    //private Connection connection;

    // Constructeur pour injecter la connection
    //public UtilisateurDaoImpl(Connection connection) {
     //   this.connection = connection;
    //}


    @Override
    public void saveUtilisateur(Utilisateur utilisateur) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO utilisateur (nom, email, mot_de_passe, contact, adresse, role) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, utilisateur.getNom());
            ps.setString(2, utilisateur.getEmail());
            ps.setString(3, utilisateur.getMotDePasse());
            ps.setLong(4, utilisateur.getContact());
            ps.setString(5, utilisateur.getAdresse());
            ps.setString(6, utilisateur.getRole());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Utilisateur findByEmailAndPassword(String email, String password) {
        Utilisateur utilisateur = null;
        try {
            PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                utilisateur = new Utilisateur();
                utilisateur.setIdUtilisateur(rs.getLong("id_utilisateur"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setContact(rs.getLong("contact"));
                utilisateur.setAdresse(rs.getString("adresse"));
                utilisateur.setRole(rs.getString("role"));
                utilisateur.setStatut(rs.getString("statut"));
                
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }
    public List<Utilisateur> findLivreursDisponibles() {
        List<Utilisateur> livreurs = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM utilisateur WHERE role = 'livreur' AND statut = 'disponible'");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setIdUtilisateur(rs.getLong("id_utilisateur"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setContact(rs.getLong("contact"));
                utilisateur.setAdresse(rs.getString("adresse"));
                utilisateur.setRole(rs.getString("role"));
                utilisateur.setStatut(rs.getString("statut"));
                livreurs.add(utilisateur); // Ajouter le livreur à la liste
            }
            
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livreurs; // Retourner la liste des livreurs
    }

    public Utilisateur findlivreur() {
        Utilisateur utilisateur = null;
        try {
            PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM utilisateur WHERE role = 'livreur' AND statut = 'disponible'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                utilisateur = new Utilisateur();
                utilisateur.setIdUtilisateur(rs.getLong("id_utilisateur"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setContact(rs.getLong("contact"));
                utilisateur.setAdresse(rs.getString("adresse"));
                utilisateur.setRole(rs.getString("role"));
                utilisateur.setStatut(rs.getString("statut"));
                
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }
    public void updateLivreurStatut(Long idLivreur, String nouveauStatut) {
        String query = "UPDATE utilisateur SET statut = ? WHERE id_utilisateur = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nouveauStatut);
            ps.setLong(2, idLivreur);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


	@Override
	public List<Utilisateur> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

