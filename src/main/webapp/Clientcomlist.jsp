<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tableau Rouge Clair</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;600&display=swap">
  <style>
    body {
      font-family: 'Montserrat', sans-serif;
      margin: 0;
      padding: 20px;
      background-color: #f7f7f7;
      color: #333;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin: 20px 0;
      background-color: #fff;
      border-radius: 8px;
      overflow: hidden;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }
    thead {
      background-color: #f7444e;
      color: #fff;
    }
    th, td {
      padding: 15px 20px;
      text-align: left;
      font-size: 16px;
    }
    th {
      font-weight: 600;
    }
    tbody tr {
      border-bottom: 1px solid #ddd;
    }
    tbody tr:nth-child(even) {
      background-color: #f9f9f9;
    }
    tbody tr:hover {
      background-color: #ffe3e3;
    }
    caption {
      margin: 10px 0;
      font-size: 1.5rem;
      font-weight: bold;
      color: #f7444e;
      }
	.collapsible {
	  background-color: #777;
	  color: white;
	  cursor: pointer;
	  padding: 18px;
	  width: 100%;
	  border: none;
	  text-align: left;
	  outline: none;
	  font-size: 15px;
	}
	
	
	
	.content {
	  padding: 0 18px;
	  display: none;
	  overflow: hidden;
	  background-color: #f1f1f1;
	}
  </style>
</head>
<body>
   <c:if test="${not empty commandes}">
        <table border="1">
            <thead>
                <tr>
                    <th>numero Commande</th>
                    <th>Statut Livraison</th>
                    <th>nom du produit</th>
                    <th>prix</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Adresse client</th>
                    <th>Editer</th>
                    <th>Supprimer</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="commande" items="${commandes}">
                    <tr>
                        <td>${commande.idCommande}</td>
                        <td>${commande.statut}</td>
                      <td>  ${commande.produit.nom}</td>
                      <td>${commande.produit.prix}</td>
                      <td>${commande.quantite}</td>
                      <td>${commande.quantite * commande.produit.prix}  DHS</td>
                      <td>${commande.adresse}</td>
                      
                    <c:choose>
                        <c:when test="${commande.statut == 'en_attente'}">
                           <td> <a href="ModifierCommandeClient.commande?idCommande=${commande.idCommande}"> Modifier </a></td>
                          <td>  <a href="SupprimerCommande.commande?idCommande=${commande.idCommande}" 
                               onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette commande ?')">Supprimer</a></td>
                        </c:when>
                        <c:when test="${commande.statut == 'en_traitement' || commande.statut == 'livree'}">
                      <td colspan="2">  <button type="button" class="collapsible">immodifiable</button>
							<div class="content">
							<p>Votre commande est en route. Vous ne pouvez pas la modifier.</p>
							</div> </td>


                        </c:when>
                    </c:choose>
            
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty commandes}">
        <p>Aucune livraison n'est assignée pour le moment.</p>
    </c:if>
    
    <script>
var coll = document.getElementsByClassName("collapsible");
var i;

for (i = 0; i < coll.length; i++) {
  coll[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var content = this.nextElementSibling;
    if (content.style.display === "block") {
      content.style.display = "none";
    } else {
      content.style.display = "block";
    }
  });
}
</script>
</body>
</html>
