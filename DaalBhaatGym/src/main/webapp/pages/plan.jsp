<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="utils.StringUtils"%>
<%@ page import="model.PlanModel"%>
<%@page import="java.util.ArrayList"%>

<%@include file="../pages/header.jsp"%>
<!DOCTYPE html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Printer</title>

    <!-- internal css used to style body -->
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/plan.css" />

    <style>
        a:link,
        a:visited,
        a:hover,
        a:active,
        a.split:link,
        a.split:visited,
        a.split:hover,
        a.split:active,
        a.buttonlink:link,
        a.buttonlink:visited,
        a.buttonlink:active {
            color: white;
        }

        .mysection {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between; /* Adjust the alignment of cards */
            align-items: flex-start; /* Adjust the alignment of cards */
            margin: 0 auto; /* Center the content */
            max-width: 1200px; /* Limit the maximum width */
            padding: 20px; /* Add some padding */
        }

        .container {
            width: calc(33.33% - 20px); /* Adjust the width of each card */
            margin-bottom: 20px; /* Add some space between rows */
        }

        .card1 {
            border: 1px solid #ccc; /* Add borders to cards */
            border-radius: 5px; /* Add rounded corners */
            padding: 20px; /* Add some padding */
            background-color: #f9f9f9; /* Add background color */
        }

        .cards {
            display: flex;
            justify-content: space-between; /* Adjust the alignment of cards */
        }

        /* Adjust the styles for text and images */
        .stylecard {
            margin-top: -10px;
            font-weight: bold;
        }

        img {
            max-width: 100%;
            height: auto;
        }

        /* Adjust the styles for responsiveness */
        @media only screen and (max-width: 768px) {
            .container {
                width: calc(50% - 20px);
            }
        }

        @media only screen and (max-width: 480px) {
            .container {
                width: 100%;
            }
        }
    </style>
</head>

<body>
    <!-- Section for promotional cards -->
    <div class="mysection">
        <c:if test="${empty planLists}">
            <p>No plans found.</p>
        </c:if>
        <c:if test="${not empty planLists}">
            <c:forEach var="plan" items="${planLists}">
                <div class="container">
                    <h1>Buy What You Always Searched For.</h1>
                    <div class="cards">
                        <!-- First product card -->
                        <div class="card2">
                            <h1>Plan Duration: ${plan.planDurationDays} Days</h1>
                            <h1 class="label2">Daal Bhat Power 24 Hour</h1>
                            <!-- <img class="lifting" src="../images/card1.png" style="height: 60%; width: 60%;"> -->
                            <p class="stylecard">Plan Price: Rs.${plan.planPrice}</p>
                            <p class="stylecard">${plan.planDescription}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</body>
</html>
