package ProduitDao;

import java.util.List;

import entities.Produit;

public interface IProduitDao {
	public List<Produit> getAllProduits();
	public List<Produit> getCommercantProduits(Long id);
	
	public Produit getProduit(Long id);
}
