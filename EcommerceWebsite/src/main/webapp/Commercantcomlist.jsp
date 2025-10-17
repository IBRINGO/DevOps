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
                    <th>Lancer La livraison</th>
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
                      <td>
                      <form action="ConfirmationLivraison.commande" method="POST" class="option2">
	                    <input type="hidden" name="idCommande" value="${commande.idCommande}">
	                    <button type="submit" class="option2"><a>Livrer</a></button>
               		</form>
               		
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty commandes}">
        <p>Aucune livraison n'est assignée pour le moment.</p>
    </c:if>
</body>
</html>
