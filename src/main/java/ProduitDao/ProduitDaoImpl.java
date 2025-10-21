package ProduitDao;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import SingletonConnection.SingletonConnection;
import entities.Produit;
import entities.Produit;


public class ProduitDaoImpl implements IProduitDao{
	private Connection connection = SingletonConnection.getConnection();
	public List<Produit> getAllProduits() {
	    List<Produit> produits = new ArrayList<>();
	    Connection connection = SingletonConnection.getConnection();
	    try {
	        // Préparation de la requête SELECT
	        PreparedStatement ps = connection.prepareStatement("SELECT * FROM produit");
	        ResultSet rs = ps.executeQuery();
	        
	        // Parcours des résultats
	        while (rs.next()) {
	            Produit produit = new Produit();
	            produit.setIdProduit(rs.getLong("id_produit"));
	            produit.setNom(rs.getString("nom"));
	            produit.setDescription(rs.getString("description"));
	            produit.setPrix(rs.getLong("prix"));
	            produit.setIdCommercant(rs.getLong("id_commercant"));
	            produit.setImage(rs.getString("image"));
	            
	            
	            // Ajout du produit à la liste
	            produits.add(produit);
	        }

	        // Fermeture des ressources
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return produits;
	}
	@Override
	public Produit getProduit(Long id) {
		Produit produit = null;
	    Connection connection = SingletonConnection.getConnection();
	    try {
	        PreparedStatement ps = connection.prepareStatement("SELECT * FROM produit WHERE id_produit = ?");
	        ps.setLong(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	        	produit = new Produit();
	        	produit.setIdProduit(rs.getLong("id_produit"));
	            produit.setNom(rs.getString("nom"));
	            produit.setDescription(rs.getString("description"));
	            produit.setPrix(rs.getLong("prix"));
	            produit.setIdCommercant(rs.getLong("id_commercant"));
	            produit.setImage(rs.getString("image"));
	        }
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return produit;
	}
	@Override
	public List<Produit> getCommercantProduits(Long id) {
		List<Produit> produits = new ArrayList<>();
	    Connection connection = SingletonConnection.getConnection();
	    try {
	        // Préparation de la requête SELECT
	        PreparedStatement ps = connection.prepareStatement("SELECT * FROM produit WHERE id_commercant = ?");
	        ps.setLong(1, id);
	        ResultSet rs = ps.executeQuery();
	        
	        // Parcours des résultats
	        while (rs.next()) {
	            Produit produit = new Produit();
	            produit.setIdProduit(rs.getLong("id_produit"));
	            produit.setNom(rs.getString("nom"));
	            produit.setDescription(rs.getString("description"));
	            produit.setPrix(rs.getLong("prix"));
	            produit.setIdCommercant(rs.getLong("id_commercant"));
	            produit.setImage(rs.getString("image"));
	            
	            
	            // Ajout du produit à la liste
	            produits.add(produit);
	        }

	        // Fermeture des ressources
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return produits;
	}


}
