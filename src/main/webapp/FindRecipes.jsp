<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find Recipes</title>
</head>
<body>
    <form action="findrecipes" method="post">
        <h1>Search for a Recipe by Name</h1>
        <p>
            <label for="recipename">Recipe Name</label>
            <input id="recipename" name="recipename" value="${fn:escapeXml(param.recipename)}">
        </p>
        <p>
            <button type="submit">Search</button>
            <br/><br/><br/>
            <span id="successMessage"><b>${messagesbyname.success}</b></span>
        </p>
	</form>
	<!--     <form action="findrecipebyingredients" method="post">
        <h1>Find Recipe by Ingredients</h1>
        <div id="ingredientContainer">
            <p>
                <input type="text" name="ingredients" placeholder="Enter ingredient">
            </p>
        </div>
        <p>
            <button type="button" onclick="addIngredient()">Add Another Ingredient</button>
            <button type="submit">Search</button>
            <br/><br/><br/>
            <span id="ingredientMessage"><b>${messages.ingredient}</b></span>
        </p>
    </form> -->

    
    <h1>Matching Recipes by Name</h1>
    <table border="1">
        <tr>
            <th>Recipe Name</th>
            <th>Description</th>
            <th>Cuisine</th>
            <th>Cooking Instruction</th>
            <th>Calories</th>
            <th>Recipe Ingredients</th>
            <th>Reviews</th>
            <th>Delete Recipe</th>
            <th>Update Recipe</th>
        </tr>
        <c:forEach items="${recipesByName}" var="recipe">
            <tr>
                <td><c:out value="${recipe.recipeName}"/></td>
                <td><c:out value="${recipe.recipeDescription}"/></td>
                <td><c:out value="${recipe.cuisineName}"/></td>
                <td><a href="cookinginstruction?recipeId=<c:out value='${recipe.recipeId}'/>">View Instruction</a></td>
                <td><c:out value="${recipe.calories}"/></td>
                <td><a href="recipeingredients?recipeId=<c:out value='${recipe.recipeId}'/>">View Ingredients</a></td>
                <td><a href="reviews?recipeId=<c:out value='${recipe.recipeId}'/>">View Reviews</a></td>
                <td><a href="recipedelete?recipeId=<c:out value='${recipe.recipeId}'/>">Delete</a></td>
                <td><a href="recipeupdate?recipeId=<c:out value='${recipe.recipeId}'/>">Update</a></td>
            </tr>
        </c:forEach>
    </table>
    
    
    <br/>
    <h1>Create New Recipes</h1>
    <div id="recipeCreate"><a href="recipecreate">Create New Recipe</a></div>
    <br/>
    <form action="findrecipebyingredients" method="post">
        <h1>Find Recipe by Ingredients</h1>
        <div id="ingredientContainer">
            <p>
                <input type="text" name="ingredients" placeholder="Enter ingredient">
            </p>
        </div>
        <p>
            <button type="button" onclick="addIngredient()">Add Another Ingredient</button>
            <button type="submit">Search</button>
            <br/><br/><br/>
            <span id="ingredientMessage"><b>${messagesbyingredients.success}</b></span>
        </p>
    </form>
   

    <h1>Matching Recipes by Ingredients</h1>
    <table border="1">
    <tr>
        <th>Recipe Name</th>
        <th>Description</th>
        <th>Cuisine</th>
        <th>Cooking Instruction</th>
        <th>Calories</th>
        <th>Recipe Ingredients</th>
        <th>Reviews</th>
        <th>Delete Recipe</th>
        <th>Update Recipe</th>
    </tr>
    <c:if test="${not empty recipesByIngredients}">
        <c:forEach items="${recipesByIngredients}" var="recipe">
            <tr>
                <td><c:out value="${recipe.recipeName}"/></td>
                <td><c:out value="${recipe.recipeDescription}"/></td>
                <td><c:out value="${recipe.cuisineName}"/></td>
                <td><a href="cookinginstruction?recipeId=<c:out value='${recipe.recipeId}'/>">View Instruction</a></td>
                <td><c:out value="${recipe.calories}"/></td>
                <td><a href="recipeingredients?recipeId=<c:out value='${recipe.recipeId}'/>">View Ingredients</a></td>
                <td><a href="reviews?recipeId=<c:out value='${recipe.recipeId}'/>">View Reviews</a></td>
                <td><a href="recipedelete?recipeId=<c:out value='${recipe.recipeId}'/>">Delete</a></td>
                <td><a href="recipeupdate?recipeId=<c:out value='${recipe.recipeId}'/>">Update</a></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
    
    
    
    <script>
        function addIngredient() {
            const container = document.getElementById('ingredientContainer');
            const p = document.createElement('p');
            p.innerHTML = '<input type="text" name="ingredients" placeholder="Enter ingredient">';
            container.appendChild(p);
        }
    </script>
</body>
</html>