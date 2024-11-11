package instameal.servlet;

import instameal.dal.CookingInstructionsDao;
import instameal.dal.RecipeIngredientsDao;
import instameal.dal.RecipesDao;
import instameal.dal.IngredientsDao;
import instameal.model.CookingInstructions;
import instameal.model.RecipeIngredients;
import instameal.model.Recipes;
import instameal.model.Ingredients;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/recipecreate")
public class RecipeCreate extends HttpServlet {

    protected RecipesDao recipesDao;
    protected CookingInstructionsDao cookingInstructionsDao;
    protected RecipeIngredientsDao recipeIngredientsDao;
    protected IngredientsDao ingredientsDao;

    @Override
    public void init() throws ServletException {
        recipesDao = RecipesDao.getInstance();
        cookingInstructionsDao = CookingInstructionsDao.getInstance();
        recipeIngredientsDao = RecipeIngredientsDao.getInstance();
        ingredientsDao = IngredientsDao.getInstance();
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/RecipeCreate.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        // Get recipe and cooking instruction details
        String recipeName = req.getParameter("recipename");
        String description = req.getParameter("description");
        String cuisine = req.getParameter("cuisine");
        BigDecimal calories = new BigDecimal(req.getParameter("calories"));

        int cookingTime = Integer.parseInt(req.getParameter("cookingTime"));
        int cookingSteps = Integer.parseInt(req.getParameter("cookingSteps"));
        String difficulty = req.getParameter("difficulty");
        String stepsDescription = req.getParameter("stepsDescription");

        // Get ingredient details as arrays
        String[] ingredientNames = req.getParameterValues("ingredientName[]");
        String[] quantitiesGrams = req.getParameterValues("quantityGram[]");

        try {
            // Insert cooking instructions
            CookingInstructions cookingInstruction = new CookingInstructions(cookingTime, cookingSteps, stepsDescription, difficulty);
            cookingInstruction = cookingInstructionsDao.create(cookingInstruction);

            // Create recipe
            Recipes recipe = new Recipes(recipeName, description, cookingInstruction.getCookingInstructionId(),
                    new Timestamp(System.currentTimeMillis()), cuisine, calories, BigDecimal.ZERO, BigDecimal.ZERO, 
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
            recipe = recipesDao.create(recipe);

            // Loop through ingredients and add each to the database
            for (int i = 0; i < ingredientNames.length; i++) {
                String ingredientName = ingredientNames[i];
                BigDecimal quantityGram = new BigDecimal(quantitiesGrams[i]);

                // Check if the ingredient exists; if not, insert it
                Ingredients ingredient = ingredientsDao.getIngredientByIngredientName(ingredientName);
                if (ingredient == null) {
                    ingredient = new Ingredients(ingredientName, ingredientName); // Set defaults if not in form
                    ingredientsDao.create(ingredient);
                }

                // Add ingredient to recipe
                RecipeIngredients recipeIngredient = new RecipeIngredients(recipe.getRecipeId(), ingredientName, quantityGram);
                recipeIngredientsDao.create(recipeIngredient);
            }

            messages.put("success", "Recipe successfully created.");
            resp.sendRedirect("FindRecipes.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }
}