<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Commandes du Commerçant</title>
    <link rel="stylesheet" href="styles.css"> <!-- Ajoutez votre CSS -->
	<style>body {
  font-family: 'lato', sans-serif;
}
.container {
  max-width: 1000px;
  margin-left: auto;
  margin-right: auto;
  padding-left: 10px;
  padding-right: 10px;
}

h2 {
  font-size: 26px;
  margin: 20px 0;
  text-align: center;
  small {
    font-size: 0.5em;
  }
}

.responsive-table {
  li {
    border-radius: 3px;
    padding: 25px 30px;
    display: flex;
    justify-content: space-between;
    margin-bottom: 25px;
  }
  .table-header {
    background-color: #95A5A6;
    font-size: 14px;
    text-transform: uppercase;
    letter-spacing: 0.03em;
  }
  .table-row {
    background-color: #ffffff;
    box-shadow: 0px 0px 9px 0px rgba(0,0,0,0.1);
  }
  .col-1 {
    flex-basis: 10%;
  }
  .col-2 {
    flex-basis: 40%;
  }
  .col-3 {
    flex-basis: 25%;
  }
  .col-4 {
    flex-basis: 25%;
  }
  
  @media all and (max-width: 767px) {
    .table-header {
      display: none;
    }
    .table-row{
      
    }
    li {
      display: block;
    }
    .col {
      
      flex-basis: 100%;
      
    }
    .col {
      display: flex;
      padding: 10px 0;
      &:before {
        color: #6C7A89;
        padding-right: 10px;
        content: attr(data-label);
        flex-basis: 50%;
        text-align: right;
      }
    }
  }
}</style>
</head>
<body>
<div class="container">
  <h2>Commandes <small>Triggers on 767px</small></h2>
  <ul class="responsive-table">
    <li class="table-header">
      <div class="col col-1">Id commande</div>
      <div class="col col-2">Id client</div>
      <div class="col col-3">Id produit</div>
      <div class="col col-4">quantity</div>
      <div class="col col-5">adresse</div>
      <div class="col col-6">date de commande</div>
      <div class="col col-7">status</div>
    </li>
    <c:forEach var="commande" items="${commandes}">
    <li class="table-row">
      <div class="col col-1" data-label="Job Id">${commande.idCommande}</div>
      <div class="col col-2" data-label="Customer Name">${commande.idClient}</div>
      <div class="col col-3" data-label="Amount">${commande.idProduit}</div>
      <div class="col col-4" data-label="Payment Status">${commande.quantite}</div>
            <div class="col col-5" data-label="Payment Staatus">${commande.adresse}</div>
      <div class="col col-6" data-label="Customer Namae">${commande.dateCommande}</div>
      <div class="col col-7" data-label="Amouant">${commande.statut}</div>
      
    </li>
    </c:forEach>
  </ul>
</div>
    <header>
        <h1>Bienvenue, Commerçant</h1>
        <a href="logout.utilisateur">Déconnexion</a>
    </header>
    <main>
        <h2>Liste des Commandes</h2>
        <c:if test="${empty commandes}">
            <p>Aucune commande pour le moment.</p>
        </c:if>
        <c:if test="${!empty commandes}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID Commande</th>
                        <th>ID Client</th>
                        <th>ID Produit</th>
                        <th>Quantité</th>
                        <th>Adresse</th>
                        <th>Date Commande</th>
                        <th>Statut</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="commande" items="${commandes}">
                        <tr>
                            <td>${commande.idCommande}</td>
                            <td>${commande.idClient}</td>
                            <td>${commande.idProduit}</td>
                            <td>${commande.quantite}</td>
                            <td>${commande.adresse}</td>
                            <td>${commande.dateCommande}</td>
                            <td>${commande.statut}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </main>
</body>
</html>
