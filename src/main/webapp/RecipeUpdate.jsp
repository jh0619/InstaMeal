<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Update Recipe</title>
    <style>
        label {
            display: inline-block;
            width: 100px; 
            vertical-align: top;
            padding-top: 5px; 
        }
        input[type="text"],
        input[type="number"],
        textarea {
            width: 300px;
        }
        form p {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <h1>Update Recipe</h1>
    <form action="recipeupdate" method="post">
        <input type="hidden" name="recipeid" value="${recipe.recipeId}">
        <p>
            <label>Recipe Name:</label>
            <input name="recipename" type="text" value="${recipe.recipeName}">
        </p>
        <p>
            <label>Description:</label>
            <textarea name="description" rows="4" cols="50">${recipe.recipeDescription}</textarea>
        </p>
        <p>
            <label>Cuisine:</label>
            <input name="cuisine" type="text" value="${recipe.cuisineName}">
        </p>
        <p>
            <label>Calories:</label>
            <input name="calories" type="number" step="0.1" value="${recipe.calories}">
        </p>
        <p>
            <button type="submit">Update</button>
        </p>
    </form>
    <p><span style="color:green"><b>${messages.success}</b></span></p>
</body>
</html>