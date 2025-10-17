package entities;

public class Produit {
    private long idProduit;
    private String nom;
    private String description;
    private double prix;
    private long idCommercant;
    private String image; // Chemin ou URL de l'image

    // Constructeur, getters et setters
    public Produit(long idProduit,String nom, String description, double prix, long idCommercant, String image) {
        this.idProduit=idProduit;
    	this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.idCommercant = idCommercant;
        this.image = image;
    }
    
    public Produit() {
    	super();
    }

	public long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public long getIdCommercant() {
		return idCommercant;
	}

	public void setIdCommercant(long idCommercant) {
		this.idCommercant = idCommercant;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Produit{" +
        "idProduit=" + idProduit +
        ", nom='" + nom + '\'' +
        ", description='" + description + '\'' +
        ", prix='" + prix + '\'' +
        ", idCommercant=" + idCommercant +
        ", image='" + image + '\'' +
        '}';
		
	}

    // Autres getters et setters
}
