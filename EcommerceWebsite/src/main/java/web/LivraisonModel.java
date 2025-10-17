package web;
import java.util.ArrayList;
import java.util.List;

import entities.Livraison;

public class LivraisonModel {
		private String motCle;
		private List<Livraison> livraisons =new ArrayList<Livraison>();
		public String getMotCle() {
			return motCle;
		}
		public void setMotCle(String motCle) {
			this.motCle = motCle;
		}
		public List<Livraison> getLivraisons() {
			return livraisons;
		}
		public void setLivraisons(List<Livraison> livraisons) {
			this.livraisons = livraisons;
		}

}
