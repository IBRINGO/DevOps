package web;
import java.util.ArrayList;
import java.util.List;

import entities.Utilisateur;

public class UtilisateurModel {
		private String motCle;
		private List<Utilisateur> utilisateurs =new ArrayList<Utilisateur>();
		public String getMotCle() {
			return motCle;
		}
		public void setMotCle(String motCle) {
			this.motCle = motCle;
		}
		public List<Utilisateur> getUtilisateurs() {
			return utilisateurs;
		}
		public void setUtilisateurs(List<Utilisateur> utilisateurs) {
			this.utilisateurs = utilisateurs;
		}

}
