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
import java.util.Map;

import CommandeDao.ICommandeDao;
import ProduitDao.IProduitDao;
import ProduitDao.ProduitDaoImpl;
import UtilisateurDao.IUtilisateurDao;
import UtilisateurDao.UtilisateurDaoImpl;
import CommandeDao.CommandeDaoImpl;
import entities.Commande;
import entities.Produit;
import entities.Utilisateur;

/**
 * Servlet implementation class CommandeServlet
 */
public class CommandeServlet extends HttpServlet {
	private ICommandeDao commandeDao;
	private IProduitDao produitDao;
	private IUtilisateurDao utilisateurDao;

    public void init() throws ServletException {
    	commandeDao = new CommandeDaoImpl();
    	produitDao = new ProduitDaoImpl();
    	utilisateurDao = new UtilisateurDaoImpl();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/commercant.commande")) {
	        
	    	HttpSession session = request.getSession(false); // Récupérer la session existante
            if (session != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                if (utilisateur != null && "commercant".equals(utilisateur.getRole())) {
            CommandeModel model=new CommandeModel();  // Assurez-vous que la classe ProduitDao contient la méthode getAllProduits
            List<Commande> commandes = commandeDao.getCommercantCommandes(utilisateur.getIdUtilisateur());
            model.setCommandes(commandes);
            request.setAttribute("model", model);
            request.setAttribute("commandes", commandes);
            request.getRequestDispatcher("CommercantCommandes.jsp").forward(request, response);
                } else {
                    response.sendRedirect("login.jsp"); // Redirige si non connecté ou pas un client
                }
            } else {
                response.sendRedirect("login.jsp"); // Redirige si pas de session
            }
		}else if (path.equals("/commercantlist.commande")) {
	        
	    	HttpSession session = request.getSession(false); // Récupérer la session existante
            if (session != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                if (utilisateur != null && "commercant".equals(utilisateur.getRole())) {
            CommandeModel model=new CommandeModel();  // Assurez-vous que la classe ProduitDao contient la méthode getAllProduits
            List<Commande> commandes = commandeDao.getCommercantCommandes(utilisateur.getIdUtilisateur());
            model.setCommandes(commandes);
            request.setAttribute("model", model);
            request.setAttribute("commandes", commandes);
            request.getRequestDispatcher("CommercantCommandeslist.jsp").forward(request, response);
                } else {
                    response.sendRedirect("login.jsp"); // Redirige si non connecté ou pas un client
                }
            } else {
                response.sendRedirect("login.jsp"); // Redirige si pas de session
            }
		} else if (path.equals("/client.commande")) {
	        
	    	HttpSession session = request.getSession(false); // Récupérer la session existante
            if (session != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                if (utilisateur != null && "client".equals(utilisateur.getRole())) {
            CommandeModel model=new CommandeModel();  // Assurez-vous que la classe ProduitDao contient la méthode getAllProduits
            List<Commande> commandes = commandeDao.getClientCommandes(utilisateur.getIdUtilisateur());
            model.setCommandes(commandes);
            request.setAttribute("model", model);
            request.setAttribute("commandes", commandes);
            request.getRequestDispatcher("ClientCommandes.jsp").forward(request, response);
                } else {
                    response.sendRedirect("login.jsp"); // Redirige si non connecté ou pas un client
                }
            } else {
                response.sendRedirect("login.jsp"); // Redirige si pas de session
            }
		}else if (path.equals("/clientlist.commande")) {
	        
	    	HttpSession session = request.getSession(false); // Récupérer la session existante
            if (session != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                if (utilisateur != null && "client".equals(utilisateur.getRole())) {
            CommandeModel model=new CommandeModel();  // Assurez-vous que la classe ProduitDao contient la méthode getAllProduits
            List<Commande> commandes = commandeDao.getClientCommandes(utilisateur.getIdUtilisateur());
            model.setCommandes(commandes);
            request.setAttribute("model", model);
            request.setAttribute("commandes", commandes);
            request.getRequestDispatcher("ClientCommandeslist.jsp").forward(request, response);
                } else {
                    response.sendRedirect("login.jsp"); // Redirige si non connecté ou pas un client
                }
            } else {
                response.sendRedirect("login.jsp"); // Redirige si pas de session
            }
		} else if (path.equals("/ModifierCommandeClient.commande")) {
    	    Long idCommande = Long.parseLong(request.getParameter("idCommande"));
    	    CommandeModel model=new CommandeModel();  // Assurez-vous que la classe ProduitDao contient la méthode getAllProduits
    	    Commande commande = commandeDao.getCommandeById(idCommande);
    	    
    	    model.setCommande(commande);
            request.setAttribute("model", model);
            request.setAttribute("commande", commande);
    	    request.getRequestDispatcher("ModifierCommandeparclient.jsp").forward(request, response);
    	}  else if (path.equals("/SupprimerCommande.commande")) {
    		HttpSession session = request.getSession(false); // Récupérer la session existante
            if (session != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                if (utilisateur != null && "client".equals(utilisateur.getRole())) {
     	    
    		Long idCommande = Long.parseLong(request.getParameter("idCommande"));
    	    commandeDao.deleteCommande(idCommande);
    	    
    	    response.sendRedirect("indexClient.jsp");
                }else {
                    response.sendRedirect("login.jsp"); // Redirige si non connecté ou pas un client
                }
            } else {
                response.sendRedirect("login.jsp"); // Redirige si pas de session
            }
    	} else if (path.equals("/dashboard.commande")) {
    		HttpSession session = request.getSession(false); // Récupérer la session existante
            if (session != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                if (utilisateur != null && "commercant".equals(utilisateur.getRole())) {
            
    	    Map<String, Integer> stats = commandeDao.getCommandeStatsByCommercant(utilisateur.getIdUtilisateur());

    	    // Ajouter les statistiques à la requête
    	    request.setAttribute("stats", stats);
    	    request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    	}
            }
            }
 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();

        if (path.equals("/save.commande")) {
        	HttpSession session = request.getSession(false); // Récupérer la session existante
            if (session != null) {
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                if (utilisateur != null && "client".equals(utilisateur.getRole())) {
        	Long idClient = utilisateur.getIdUtilisateur();
        	Long idProduit = Long.parseLong(request.getParameter("idProduit"));
        	Long quantite = Long.parseLong(request.getParameter("quantite"));
            String adresse = request.getParameter("adresse");
            
            Produit produit = produitDao.getProduit(idProduit);
            Long idCommercant = produit.getIdCommercant(); // Assure-toi que Produit contient `idCommercant`

            
            Commande commande = new Commande();
            commande.setIdClient(idClient);
            commande.setIdProduit(idProduit);
            commande.setIdCommercant(idCommercant);
            commande.setQuantite(quantite);
            commande.setAdresse(adresse);
            

            commandeDao.saveCommande(commande);

            response.sendRedirect("indexClient.jsp");
                } else {
                    response.sendRedirect("login.jsp"); // Redirige si non connecté ou pas un client
                }
            } else {
                response.sendRedirect("login.jsp"); // Redirige si pas de session
            }
            } else if (path.equals("/ConfirmationLivraison.commande")) {

    	    	Long idCommande = Long.parseLong(request.getParameter("idCommande"));
        	    UtilisateurModel model=new UtilisateurModel();  // Assurez-vous que la classe ProduitDao contient la méthode getAllProduits
        	    List<Utilisateur> utilisateurs = utilisateurDao.findLivreursDisponibles();
        	    model.setUtilisateurs(utilisateurs);
        	    request.setAttribute("model", model);
        	    request.setAttribute("utilisateurs", utilisateurs);    
    	        request.setAttribute("idCommande", idCommande);
    	        request.getRequestDispatcher("ConfirmationLivraison.jsp").forward(request, response);
                   
           } else if (path.equals("/update.commande")) {
        	   HttpSession session = request.getSession(false); // Récupérer la session existante
               if (session != null) {
                   Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
                   if (utilisateur != null && "client".equals(utilisateur.getRole())) {
        	    Long idCommande = Long.parseLong(request.getParameter("idCommande"));
        	    Long quantite = Long.parseLong(request.getParameter("quantite"));
        	    String adresse = request.getParameter("adresse");

        	    // Création de l'objet Commande
        	    Commande commande = new Commande();
        	    commande.setIdCommande(idCommande);
        	    commande.setQuantite(quantite);
        	    commande.setAdresse(adresse);

        	    commandeDao.updateCommande(commande);
        	    response.sendRedirect("indexClient.jsp");
                   }else {
                       response.sendRedirect("login.jsp"); // Redirige si non connecté ou pas un client
                   }
               } else {
                   response.sendRedirect("login.jsp"); // Redirige si pas de session
               }
           }

	}

}
