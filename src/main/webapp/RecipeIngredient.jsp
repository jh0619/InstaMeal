<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Update Recipe Ingredients</title>
</head>
<body>
    <h1>Update Recipe Ingredients</h1>

    <c:choose>
        <c:when test="${not empty ingredients}">
           <form action="recipeingredients" method="post">
    <input type="hidden" name="recipeId" value="${recipeId}">
    <table border="1">
        <tr>
            <th>Ingredient Name</th>
            <th>Quantity (grams)</th>
            <th>Update</th>
        </tr>
        <c:forEach var="ingredient" items="${ingredients}">
            <tr>
                <td>
                    <!-- Allow updating the ingredient name -->
                    <input type="text" name="ingredientName" value="${ingredient.ingredientName}">
                </td>
                <td>
                    <!-- Allow updating the quantity -->
                    <input type="number" name="quantityGram" step="0.1" value="${ingredient.quantityGram}">
                </td>
                <td>
                    <button type="submit" name="action" value="update">Update</button>
                    <input type="hidden" name="originalIngredientName" value="${ingredient.ingredientName}">
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
        </c:when>
        <c:otherwise>
            <p>No ingredients found for this recipe.</p>
        </c:otherwise>
    </c:choose>

    <br/>
    <a href="findrecipes">Back to Recipes</a>
</body>
</html>