<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Create Recipe</title>
    <style>
        label { display: inline-block; width: 100px; vertical-align: top; padding-top: 5px; }
        input[type="text"], input[type="number"], textarea { width: 300px; }
        form p { margin-bottom: 15px; }
    </style>
</head>
<body>
    <h1>Create a New Recipe</h1>
    <form action="recipecreate" method="post">
        <p><label>Recipe Name:</label><input name="recipename" type="text"></p>
        <p><label>Description:</label><textarea name="description" rows="4" cols="50"></textarea></p>
        <p><label>Cuisine:</label><input name="cuisine" type="text"></p>
        <p><label>Calories:</label><input name="calories" type="number" step="0.1"></p>

        <h3>Cooking Instructions</h3>
        <p><label>Cooking Time (mins):</label><input name="cookingTime" type="number" step="1"></p>
        <p><label>Number of Steps:</label><input name="cookingSteps" type="number" step="1"></p>
        <p><label>Difficulty:</label><input name="difficulty" type="text"></p>
        <p><label>Steps Description:</label><textarea name="stepsDescription" rows="4" cols="50"></textarea></p>

        <h3>Ingredients</h3>
        <div id="ingredients">
            <p><label>Ingredient Name:</label><input name="ingredientName[]" type="text"></p>
            <p><label>Quantity (grams):</label><input name="quantityGram[]" type="number" step="0.1"></p>
        </div>
        <div id="moreIngredients"></div>
        
        <p><button type="button" onclick="addIngredient()">Add Another Ingredient</button></p>
        
        <p><button type="submit">Create</button></p>
    </form>
    <p><span style="color:green"><b>${messages.success}</b></span></p>

    <script>
        function addIngredient() {
            const ingredientHTML = `
                <p><label>Ingredient Name:</label><input name="ingredientName[]" type="text"></p>
                <p><label>Quantity (grams):</label><input name="quantityGram[]" type="number" step="0.1"></p>
            `;
            document.getElementById("moreIngredients").insertAdjacentHTML("beforeend", ingredientHTML);
        }
    </script>
</body>
</html>