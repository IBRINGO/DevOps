package entities;

import java.time.LocalDateTime;

public class Commande {
    private long idCommande;
    private long idClient;
    private long idProduit;
    private long idCommercant;
    private long quantite;
    private String adresse;
    private LocalDateTime dateCommande;
    private String statut;
    private Produit produit; // Nouveau champ

    // Getters et setters
    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    // Constructeurs
    public Commande() {
    	super();
    }

    public Commande(long idClient, long idProduit,long idCommercant, long quantite, String adresse, String statut) {
        this.idClient = idClient;
        this.idProduit = idProduit;
        this.idCommercant = idCommercant;
        this.quantite = quantite;
        this.adresse = adresse;
        this.dateCommande = LocalDateTime.now(); // Date actuelle par défaut
        this.statut = statut;
    }
    public Commande(long idClient, long idProduit, long idCommercant, long quantite, String adresse, String statut, Produit produit) {
        this(idClient, idProduit, idCommercant, quantite, adresse, statut);
        this.produit = produit;
    }

    // Getters et Setters
    public long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(long idCommande) {
        this.idCommande = idCommande;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(long idProduit) {
        this.idProduit = idProduit;
    }

    public long getIdCommercant() {
        return idCommercant;
    }

    public void setIdCommercant(long idCommercant) {
        this.idCommercant = idCommercant;
    }

    public long getQuantite() {
        return quantite;
    }

    public void setQuantite(long quantite) {
        this.quantite = quantite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    // Méthode toString() pour affichage
    @Override
    public String toString() {
        return "Commande{" +
                "idCommande=" + idCommande +
                ", idClient=" + idClient +
                ", idProduit=" + idProduit +
                ", idCommercant=" + idCommercant +
                ", quantite=" + quantite +
                ", adresse='" + adresse + '\'' +
                ", dateCommande=" + dateCommande +
                ", statut='" + statut + '\'' +
                '}';
    }
}
