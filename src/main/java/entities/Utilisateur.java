package entities;

public class Utilisateur {
    private long idUtilisateur;
    private String nom;
    private String email;
    private String motDePasse;
    private long contact;
    private String adresse;
    private String role; // Peut contenir "client", "commercant" ou "livreur"
    private String statut; // Peut contenir "actif" ou "inactif"

    // Constructeur par défaut
    public Utilisateur() {
    	super();
    }

    // Constructeur avec tous les champs
    public Utilisateur(long idUtilisateur, String nom, String email, String motDePasse,long contact ,String adresse, String role, String statut) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.contact = contact;
        this.adresse = adresse;
        this.role = role;
        this.statut = statut;
    }

    // Getters et Setters
    public long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }
    
    public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    // 👉 Méthode métier pour test unitaire
    public boolean estLivreurDisponible() {
        return "livreur".equalsIgnoreCase(this.role) && "disponible".equalsIgnoreCase(this.statut);
    }

    // Méthode toString (pour faciliter les débogages)
    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", contact=" + contact +
                ", adresse='" + adresse + '\'' +
                ", role='" + role + '\'' +
                ", statut='" + statut + '\'' +
                '}';
    }
}
