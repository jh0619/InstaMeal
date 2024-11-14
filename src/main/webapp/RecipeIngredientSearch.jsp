<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Recipes by Ingredients</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Search Recipes by Ingredients</h1>
    <form action="findrecipebyingredients" method="post">
        <div id="ingredientContainer">
            <div class="form-group">
                <input type="text" class="form-control" name="ingredients" placeholder="Enter ingredient">
            </div>
        </div>
        <button type="button" class="btn btn-secondary" onclick="addIngredient()">Add Another Ingredient</button>
        <button type="submit" class="btn btn-primary">Search Recipes</button>
    </form>

    <c:if test="${not empty recipes}">
        <h2>Found Recipes</h2>
        <div class="row">
            <c:forEach items="${recipes}" var="recipe">
                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title">${recipe.recipeName}</h5>
                            <p class="card-text">${recipe.recipeDescription}</p>
                            <a href="recipedetail?recipeId=${recipe.recipeId}" class="btn btn-primary">View Details</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

<script>
  function addIngredient() {
    const container = document.getElementById('ingredientContainer');
    const div = document.createElement('div');
    div.className = 'form-group';
    div.innerHTML = '<input type="text" class="form-control" name="ingredients" placeholder="Enter ingredient">';
    container.appendChild(div);
  }
</script>
</body>
</html>
