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
   <c:if test="${not empty livraisons}">
        <table border="1">
            <thead>
                <tr>
                    <th>ID Livraison</th>
                    <th>ID Commande</th>
                    <th>Date de Ramassage</th>
                    <th>Date de Livraison</th>
                    <th>Statut Livraison</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="livraison" items="${livraisons}">
                    <tr>
                        <td>${livraison.idLivraison}</td>
                        <td>${livraison.idCommande}</td>
                        <td>    <fmt:formatDate value="${livraison.dateRamassage}" pattern="yyyy-MM-dd" />
</td>
                        <td>    <fmt:formatDate value="${livraison.dateLivraison}" pattern="yyyy-MM-dd" />
</td>
                        <td>${livraison.statutLivraison}</td>
                        <td>
                            <a href="VoirDetails.livraison?idLivraison=${livraison.idLivraison}">Voir les détails</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty livraisons}">
        <p>Aucune livraison n'est assignée pour le moment.</p>
    </c:if>
</body>
</html>
