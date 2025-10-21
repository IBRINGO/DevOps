package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import CommandeDao.CommandeDaoImpl;
import CommandeDao.ICommandeDao;
import LivraisonDao.LivraisonDaoImpl;
import UtilisateurDao.IUtilisateurDao;
import UtilisateurDao.UtilisateurDaoImpl;
import entities.Commande;
import entities.Livraison;
import entities.Utilisateur;
import LivraisonDao.ILivraisonDao;

/**
 * Servlet implementation class LivraisonServlet
 */
public class LivraisonServlet extends HttpServlet {
	private ILivraisonDao livraisonDao;
	private ICommandeDao commandeDao;

    private IUtilisateurDao utilisateurDao;

    public void init() throws ServletException {
    	livraisonDao = new LivraisonDaoImpl();
    	commandeDao = new CommandeDaoImpl();
    	utilisateurDao = new UtilisateurDaoImpl();
    }
    public LivraisonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/livreur.livraison")) {
			HttpSession session = request.getSession(false); // Récupérer la session existante
            if (session != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                if (utilisateur != null && "livreur".equals(utilisateur.getRole())) {
                            List<Livraison> livraisons = livraisonDao.getLivreurLivraisons(utilisateur.getIdUtilisateur());

                            request.setAttribute("livraisons", livraisons);
                            request.getRequestDispatcher("LivraisonLivreur.jsp").forward(request, response);
                        } else {
                            response.sendRedirect("login.jsp");
                        }
                    
                }
		} else if (path.equals("/VoirDetails.livraison")) {
		    try {
		        Long idLivraison = Long.parseLong(request.getParameter("idLivraison"));

		        Livraison livraison = livraisonDao.findLivraisonWithDetailsById(idLivraison);

		        if (livraison != null) {
		            request.setAttribute("livraison", livraison);
		            request.getRequestDispatcher("DetailLivraison.jsp").forward(request, response);
		        } else {
		            request.setAttribute("error", "Livraison non trouvée.");
		            request.getRequestDispatcher("error.jsp").forward(request, response);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("error", "Une erreur est survenue lors de la récupération des détails.");
		        request.getRequestDispatcher("error.jsp").forward(request, response);
		    }
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/save.livraison"))  {
			HttpSession session = request.getSession(false); // Récupérer la session existante
            if (session != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                if (utilisateur != null && "commercant".equals(utilisateur.getRole())) {
			Long idCommande = Long.parseLong(request.getParameter("idCommande"));
	        Long idLivreur = Long.parseLong(request.getParameter("idLivreur"));
            Date dateRamassage = Date.valueOf(request.getParameter("dateRamassage"));
            
            Livraison livraison = new Livraison();
	        livraison.setIdCommande(idCommande);
	        livraison.setIdLivreur(idLivreur);
	        livraison.setDateRamassage(dateRamassage);
	        
	        commandeDao.updateCommandeStatut(idCommande, "en_traitement");
	        utilisateurDao.updateLivreurStatut(idLivreur, "indisponible");

	        livraisonDao.saveLivraison(livraison);
	        request.setAttribute("livraison", livraison);
	        response.sendRedirect("indexCommercant.jsp");
		        } else {
		            response.sendRedirect("login.jsp"); // Redirige si non connecté ou pas un client
		        } } else {
		        response.sendRedirect("login.jsp"); // Redirige si pas de sessio  
		        }
		} else if (path.equals("/updateStatus.livraison")) {
		    try {
		        Long idLivreur = Long.parseLong(request.getParameter("idLivreur"));

		        Long idLivraison = Long.parseLong(request.getParameter("idLivraison"));
		        Long idCommande = Long.parseLong(request.getParameter("idCommande"));
		        String action = request.getParameter("action");

		        if ("start".equals(action)) {
		            livraisonDao.updateStatus(idLivraison, "en_cours");
		        } else if ("complete".equals(action)) {
		            livraisonDao.updateStatus(idLivraison, "livree");
		            commandeDao.updateCommandeStatut(idCommande, "livree");
		        }

		        response.sendRedirect("livreur.livraison");

		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("error", "Erreur lors de la mise à jour du statut.");
		        request.getRequestDispatcher("error.jsp").forward(request, response);
		    }
		}

	}
}
