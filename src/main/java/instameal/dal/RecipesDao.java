package instameal.dal;

import instameal.model.CookingInstructions;
import instameal.model.Cuisines;
import instameal.model.Recipes;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipesDao {
    protected ConnectionManager connectionManager;
    private static RecipesDao instance = null;
    private CookingInstructionsDao instructionsDao;
    private CuisinesDao cuisinesDao;

    protected RecipesDao() {
        connectionManager = new ConnectionManager();
        instructionsDao = CookingInstructionsDao.getInstance();
        cuisinesDao = CuisinesDao.getInstance();
    }

    public static RecipesDao getInstance() {
        if (instance == null) {
            instance = new RecipesDao();
        }
        return instance;
    }

    public Recipes create(Recipes recipe) throws SQLException {
    	// If instructions or cuisines does not exist, we will just generate a default one
    	if (cuisinesDao.getCuisineByName(recipe.getCuisineName()) == null) {
            Cuisines newCuisine = new Cuisines(recipe.getCuisineName(), "Default description for " + recipe.getCuisineName());
            cuisinesDao.create(newCuisine);
        }
    	if (instructionsDao.getInstructionById(recipe.getCookingInstructionId()) == null) {
            CookingInstructions newInstruction = new CookingInstructions(30, 3, "Default instructions", "Easy");
            newInstruction = instructionsDao.create(newInstruction);
            recipe.setCookingInstructionId(newInstruction.getCookingInstructionId());  // 设置新的ID
        }
    	
        String insertRecipe = "INSERT INTO Recipes(RecipeName, RecipeDescription, CookingInstructionId, Created, " +
                "CuisineName, Calories, TotalFatPDV, SugarPDV, SodiumPDV, ProteinPDV, SaturatedFatPDV, CarbohydratesPDV) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRecipe, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, recipe.getRecipeName());
            insertStmt.setString(2, recipe.getRecipeDescription());
            insertStmt.setInt(3, recipe.getCookingInstructionId());
            insertStmt.setTimestamp(4, recipe.getCreated());
            insertStmt.setString(5, recipe.getCuisineName());
            insertStmt.setBigDecimal(6, recipe.getCalories());
            insertStmt.setBigDecimal(7, recipe.getTotalFatPDV());
            insertStmt.setBigDecimal(8, recipe.getSugarPDV());
            insertStmt.setBigDecimal(9, recipe.getSodiumPDV());
            insertStmt.setBigDecimal(10, recipe.getProteinPDV());
            insertStmt.setBigDecimal(11, recipe.getSaturatedFatPDV());
            insertStmt.setBigDecimal(12, recipe.getCarbohydratesPDV());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int recipeId = -1;
            if (resultKey.next()) {
                recipeId = resultKey.getInt(1);
            }
            recipe.setRecipeId(recipeId);
            return recipe;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }
    
    public Recipes getRecipeByName(String recipeName) throws SQLException {
        String selectRecipe = "SELECT * FROM Recipes WHERE RecipeName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecipe);
            selectStmt.setString(1, recipeName);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int recipeId = results.getInt("RecipeId");
                String recipeDescription = results.getString("RecipeDescription");
                int cookingInstructionId = results.getInt("CookingInstructionId");
                Timestamp created = results.getTimestamp("Created");
                String cuisineName = results.getString("CuisineName");
                BigDecimal calories = results.getBigDecimal("Calories");
                BigDecimal totalFatPDV = results.getBigDecimal("TotalFatPDV");
                BigDecimal sugarPDV = results.getBigDecimal("SugarPDV");
                BigDecimal sodiumPDV = results.getBigDecimal("SodiumPDV");
                BigDecimal proteinPDV = results.getBigDecimal("ProteinPDV");
                BigDecimal saturatedFatPDV = results.getBigDecimal("SaturatedFatPDV");
                BigDecimal carbohydratesPDV = results.getBigDecimal("CarbohydratesPDV");
                Recipes recipe = new Recipes(recipeId, recipeName, recipeDescription, cookingInstructionId, created,
                        cuisineName, calories, totalFatPDV, sugarPDV, sodiumPDV, proteinPDV, saturatedFatPDV, carbohydratesPDV);
                return recipe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }


    public Recipes getRecipeById(int recipeId) throws SQLException {
        String selectRecipe = "SELECT * FROM Recipes WHERE RecipeId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecipe);
            selectStmt.setInt(1, recipeId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String recipeName = results.getString("RecipeName");
                String recipeDescription = results.getString("RecipeDescription");
                int cookingInstructionId = results.getInt("CookingInstructionId");
                Timestamp created = results.getTimestamp("Created");
                String cuisineName = results.getString("CuisineName");
                BigDecimal calories = results.getBigDecimal("Calories");
                BigDecimal totalFatPDV = results.getBigDecimal("TotalFatPDV");
                BigDecimal sugarPDV = results.getBigDecimal("SugarPDV");
                BigDecimal sodiumPDV = results.getBigDecimal("SodiumPDV");
                BigDecimal proteinPDV = results.getBigDecimal("ProteinPDV");
                BigDecimal saturatedFatPDV = results.getBigDecimal("SaturatedFatPDV");
                BigDecimal carbohydratesPDV = results.getBigDecimal("CarbohydratesPDV");
                Recipes recipe = new Recipes(recipeId, recipeName, recipeDescription, cookingInstructionId, created,
                        cuisineName, calories, totalFatPDV, sugarPDV, sodiumPDV, proteinPDV, saturatedFatPDV, carbohydratesPDV);
                return recipe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    public List<Recipes> getAllRecipes() throws SQLException {
        List<Recipes> recipesList = new ArrayList<>();
        String selectAll = "SELECT * FROM Recipes;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAll);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int recipeId = results.getInt("RecipeId");
                String recipeName = results.getString("RecipeName");
                String recipeDescription = results.getString("RecipeDescription");
                int cookingInstructionId = results.getInt("CookingInstructionId");
                Timestamp created = results.getTimestamp("Created");
                String cuisineName = results.getString("CuisineName");
                BigDecimal calories = results.getBigDecimal("Calories");
                BigDecimal totalFatPDV = results.getBigDecimal("TotalFatPDV");
                BigDecimal sugarPDV = results.getBigDecimal("SugarPDV");
                BigDecimal sodiumPDV = results.getBigDecimal("SodiumPDV");
                BigDecimal proteinPDV = results.getBigDecimal("ProteinPDV");
                BigDecimal saturatedFatPDV = results.getBigDecimal("SaturatedFatPDV");
                BigDecimal carbohydratesPDV = results.getBigDecimal("CarbohydratesPDV");
                Recipes recipe = new Recipes(recipeId, recipeName, recipeDescription, cookingInstructionId, created,
                        cuisineName, calories, totalFatPDV, sugarPDV, sodiumPDV, proteinPDV, saturatedFatPDV, carbohydratesPDV);
                recipesList.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return recipesList;
    }

    public Recipes updateRecipeDescription(Recipes recipe, String newDescription) throws SQLException {
        String updateRecipe = "UPDATE Recipes SET RecipeDescription=? WHERE RecipeId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateRecipe);
            updateStmt.setString(1, newDescription);
            updateStmt.setInt(2, recipe.getRecipeId());
            updateStmt.executeUpdate();
            recipe.setRecipeDescription(newDescription);
            return recipe;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public Recipes delete(Recipes recipe) throws SQLException {
        String deleteRecipe = "DELETE FROM Recipes WHERE RecipeId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteRecipe);
            deleteStmt.setInt(1, recipe.getRecipeId());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
    
    public Recipes updateRecipe(Recipes recipe, String newName, String newDescription, String newCuisineName, BigDecimal newCalories) throws SQLException {
        String updateRecipe = "UPDATE Recipes SET RecipeName=?, RecipeDescription=?, CuisineName=?, Calories=? WHERE RecipeId=?";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateRecipe);
            
            // Set the values for the update query
            updateStmt.setString(1, newName);
            updateStmt.setString(2, newDescription);
            updateStmt.setString(3, newCuisineName);
            updateStmt.setBigDecimal(4, newCalories);
            updateStmt.setInt(5, recipe.getRecipeId());
            
            // Execute the update
            updateStmt.executeUpdate();
            
            // Update the recipe object with new values
            recipe.setRecipeName(newName);
            recipe.setRecipeDescription(newDescription);
            recipe.setCuisineName(newCuisineName);
            recipe.setCalories(newCalories);
            
            return recipe;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) connection.close();
            if (updateStmt != null) updateStmt.close();
        }
    }


    public List<Recipes> findRecipesByIngredients(String ingredientsString) throws SQLException {
        List<String> ingredients = Arrays.asList(ingredientsString.split("\\s*,\\s*"));
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT r.RecipeId, r.RecipeName, r.RecipeDescription, r.CookingInstructionId, r.Created, ")
            .append("r.CuisineName, r.Calories, r.TotalFatPDV, r.SugarPDV, r.SodiumPDV, r.ProteinPDV, ")
            .append("r.SaturatedFatPDV, r.CarbohydratesPDV ")
            .append("FROM Recipes r ")
            .append("INNER JOIN RecipeIngredients ri ON r.RecipeId = ri.RecipeId ")
            .append("WHERE ri.IngredientName IN (");

        // Dynamically build the placeholders for the ingredients list
        for (int i = 0; i < ingredients.size(); i++) {
            queryBuilder.append("?");
            if (i < ingredients.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(") ")
            .append("GROUP BY r.RecipeId ")
            .append("HAVING COUNT(DISTINCT ri.IngredientName) = ?;");

        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        List<Recipes> recipesList = new ArrayList<>();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(queryBuilder.toString());

            // Set each ingredient as a parameter in the prepared statement
            for (int i = 0; i < ingredients.size(); i++) {
                selectStmt.setString(i + 1, ingredients.get(i));
            }
            // Set the count of ingredients as the last parameter
            selectStmt.setInt(ingredients.size() + 1, ingredients.size());

            results = selectStmt.executeQuery();

            // Process the results
            while (results.next()) {
                int recipeId = results.getInt("RecipeId");
                String recipeName = results.getString("RecipeName");
                String recipeDescription = results.getString("RecipeDescription");
                int cookingInstructionId = results.getInt("CookingInstructionId");
                Timestamp created = results.getTimestamp("Created");
                String cuisineName = results.getString("CuisineName");
                BigDecimal calories = results.getBigDecimal("Calories");
                BigDecimal totalFatPDV = results.getBigDecimal("TotalFatPDV");
                BigDecimal sugarPDV = results.getBigDecimal("SugarPDV");
                BigDecimal sodiumPDV = results.getBigDecimal("SodiumPDV");
                BigDecimal proteinPDV = results.getBigDecimal("ProteinPDV");
                BigDecimal saturatedFatPDV = results.getBigDecimal("SaturatedFatPDV");
                BigDecimal carbohydratesPDV = results.getBigDecimal("CarbohydratesPDV");

                Recipes recipe = new Recipes(recipeId, recipeName, recipeDescription, cookingInstructionId, created,
                    cuisineName, calories, totalFatPDV, sugarPDV, sodiumPDV, proteinPDV, saturatedFatPDV, carbohydratesPDV);
                recipesList.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return recipesList;
    }

}
