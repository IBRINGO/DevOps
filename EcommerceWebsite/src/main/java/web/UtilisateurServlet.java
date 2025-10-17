package web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import UtilisateurDao.IUtilisateurDao;
import UtilisateurDao.UtilisateurDaoImpl;
import entities.Utilisateur;

/**
 * Servlet implementation class UtilisateurServlet
 */
    public class UtilisateurServlet extends HttpServlet {
        private IUtilisateurDao utilisateurDao;
        RequestDispatcher dispatcher = null;

        public void init() throws ServletException {
        	utilisateurDao = new UtilisateurDaoImpl();
        }
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	 String path = request.getServletPath();
        	if (path.equals("/logout.utilisateur")) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate(); // Supprime la session
                }
                response.sendRedirect("login.jsp");
            }
        
        }
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String path = request.getServletPath();

            if (path.equals("/inscription.utilisateur")) {
                String nom = request.getParameter("nom");
                String email = request.getParameter("email");
                String motDePasse = request.getParameter("motDePasse");
        	    long contact = Long.parseLong(request.getParameter("contact"));
                String adresse = request.getParameter("adresse");
                String role = request.getParameter("role");
                
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setNom(nom);
                utilisateur.setEmail(email);
                utilisateur.setMotDePasse(motDePasse);
                utilisateur.setContact(contact);
                utilisateur.setAdresse(adresse);
                utilisateur.setRole(role);
                

                utilisateurDao.saveUtilisateur(utilisateur);

                response.sendRedirect("login.jsp");
            } else if (path.equals("/authentification.utilisateur")) {
                String email = request.getParameter("email");
                String motDePasse = request.getParameter("motDePasse");

                Utilisateur utilisateur = utilisateurDao.findByEmailAndPassword(email, motDePasse);
                if (utilisateur != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("utilisateur", utilisateur);
                    request.setAttribute("status", "success");


                    // Redirection selon le rôle
                    String role = utilisateur.getRole();
                    if ("client".equals(role)) {
                        dispatcher = request.getRequestDispatcher("indexClient.jsp");
                        request.setAttribute("nomClient", utilisateur.getNom());
                        request.setAttribute("idClient", utilisateur.getIdUtilisateur());
                    } else if ("commercant".equals(role)) {
                        dispatcher = request.getRequestDispatcher("indexCommercant.jsp");
                    } else if ("livreur".equals(role)) {
                        dispatcher = request.getRequestDispatcher("indexLivreur.jsp");
                    }
                } else {
                	dispatcher = request.getRequestDispatcher("login.jsp");
                    request.setAttribute("status", "failed");
                }
                dispatcher.forward(request, response);

            }
}

}