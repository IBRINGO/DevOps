package entities;

import java.util.Date;

public class Livraison {
    private Long idLivraison;
    private Long idCommande;
    private Long idLivreur;
    private Date dateRamassage; // String pour simplifier, utilisez LocalDateTime pour un meilleur contrôle
    private Date dateLivraison; // Même remarque que pour dateRamassage
    private String statutLivraison;
    
    private Utilisateur client;     // Informations sur le client
    private Utilisateur commercant;
    private Commande commande;      // Informations sur la commande

    // Getters et Setters


 // Constructeurs
    public Livraison() {
    	super();
    }

    public Livraison(long idCommande, long idLivreur, Date dateRamassage, Date dateLivraison, String statutLivraison) {
    	this.idCommande = idCommande;
        this.idLivreur = idLivreur;
        this.dateRamassage = dateRamassage;
        this.dateLivraison = dateLivraison;
        this.statutLivraison = statutLivraison;
    }

    public Livraison(long idLivraison, long idCommande, long idLivreur, Date dateRamassage, Date dateLivraison, String statutLivraison) {
    	this.idLivraison = idLivraison;
    	this.idCommande = idCommande;
        this.idLivreur = idLivreur;
        this.dateRamassage = dateRamassage;
        this.dateLivraison = dateLivraison;
        this.statutLivraison = statutLivraison;
    }

    // Getters et Setters
    public Long getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(Long idLivraison) {
        this.idLivraison = idLivraison;
    }

    public Long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Long idCommande) {
        this.idCommande = idCommande;
    }

    public Long getIdLivreur() {
        return idLivreur;
    }

    public void setIdLivreur(Long idLivreur) {
        this.idLivreur = idLivreur;
    }

    public Date getDateRamassage() {
        return dateRamassage;
    }

    public void setDateRamassage(Date dateRamassage) {
        this.dateRamassage = dateRamassage;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getStatutLivraison() {
        return statutLivraison;
    }

    public void setStatutLivraison(String statutLivraison) {
        this.statutLivraison = statutLivraison;
    }
    
    public Utilisateur getClient() {
        return client;
    }

    public void setClient(Utilisateur client) {
        this.client = client;
    }

    public Utilisateur getCommercant() {
        return commercant;
    }

    public void setCommercant(Utilisateur commercant) {
        this.commercant = commercant;
    }
    
    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    public String toString() {
        return "Livraison{" +
                "idLivraison=" + idLivraison +
                ", idCommande=" + idCommande +
                ", idLivreur=" + idLivreur +
                 ", dateRamassage=" + dateRamassage +
                ", dateLivraison=" + dateLivraison +
                ", statutLivraison='" + statutLivraison + '\'' +
                '}';
    }
}

