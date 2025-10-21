package web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.util.List;

import ProduitDao.IProduitDao;
import ProduitDao.ProduitDaoImpl;
import entities.Produit;
import entities.Utilisateur;

/**
 * Servlet implementation class ProduitServlet
 */
public class ProduitServlet extends HttpServlet {
	private IProduitDao metier;
    public void init() throws ServletException {
    	metier = new ProduitDaoImpl();
    }

       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   String path = request.getServletPath();
	        if (path.equals("/produit.produit")) {
	            HttpSession session = request.getSession(false); // Récupérer la session existante
	            if (session != null) {
	                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
	                if (utilisateur != null && "client".equals(utilisateur.getRole())) {
	            ProduitModel model=new ProduitModel();  // Assurez-vous que la classe ProduitDao contient la méthode getAllProduits
	            List<Produit> produits = metier.getAllProduits();
	            model.setProduits(produits);
	            request.setAttribute("model", model);
	            request.setAttribute("produits", produits);
	            request.getRequestDispatcher("produits.jsp").forward(request, response);
	                } else {
	                    response.sendRedirect("login.jsp"); // Redirige si non connecté ou pas un client
	                }
	            } else {
	                response.sendRedirect("login.jsp"); // Redirige si pas de session
	            }
	        
	    } else if (path.equals("/ConfirmationCommande.produit")) {
	        
	    	Long id = Long.parseLong(request.getParameter("id"));
    	    String nom = request.getParameter("nom");
    	    double prix = Double.parseDouble(request.getParameter("prix"));

	        request.setAttribute("idProduit", id);
	        request.setAttribute("nomProduit", nom);
	        request.setAttribute("prix", prix);
	        request.getRequestDispatcher("ConfirmationCommande.jsp").forward(request, response);
	
       }
	    else if (path.equals("/commercant.produit")) {
	        
	    	HttpSession session = request.getSession(false); // Récupérer la session existante
            if (session != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                if (utilisateur != null && "commercant".equals(utilisateur.getRole())) {
            ProduitModel model=new ProduitModel();  // Assurez-vous que la classe ProduitDao contient la méthode getAllProduits
            List<Produit> produits = metier.getCommercantProduits(utilisateur.getIdUtilisateur());
            model.setProduits(produits);
            request.setAttribute("model", model);
            request.setAttribute("produits", produits);
            request.getRequestDispatcher("produitsCommercant.jsp").forward(request, response);
                } else {
                    response.sendRedirect("login.jsp"); // Redirige si non connecté ou pas un client
                }
            } else {
                response.sendRedirect("login.jsp"); // Redirige si pas de session
            }
       }
       }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
