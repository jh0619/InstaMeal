package instameal.servlet;

import instameal.dal.IngredientsDao;
import instameal.dal.RecipeIngredientsDao;
import instameal.model.Ingredients;
import instameal.model.RecipeIngredients;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/recipeingredients")
public class RecipeIngredient extends HttpServlet {
    protected RecipeIngredientsDao recipeIngredientsDao;
    protected IngredientsDao ingredientsDao;

    @Override
    public void init() throws ServletException {
        recipeIngredientsDao = RecipeIngredientsDao.getInstance();
        ingredientsDao = IngredientsDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        displayIngredients(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int recipeId = Integer.parseInt(req.getParameter("recipeId"));

        try {
        	if ("update".equals(action)) {
                String originalIngredientName = req.getParameter("originalIngredientName");
                String ingredientName = req.getParameter("ingredientName");
                BigDecimal quantityGram = new BigDecimal(req.getParameter("quantityGram"));

                // Check if ingredient name has changed
                if (!ingredientName.equals(originalIngredientName)) {
                    // Delete old ingredient entry with originalIngredientName
                    RecipeIngredients oldIngredient = new RecipeIngredients(recipeId, originalIngredientName, BigDecimal.ZERO);
                    recipeIngredientsDao.delete(oldIngredient);

                    // Check if the new ingredient exists in the Ingredients table
                    Ingredients ingredient = ingredientsDao.getIngredientByIngredientName(ingredientName);
                    if (ingredient == null) {
                        // Create it in the Ingredients table if it doesn't exist
                        ingredient = new Ingredients(ingredientName, "Description"); 
                        ingredientsDao.create(ingredient);
                    }

                    // Add new ingredient entry with updated name and quantity
                    RecipeIngredients newIngredient = new RecipeIngredients(recipeId, ingredientName, quantityGram);
                    recipeIngredientsDao.create(newIngredient);
                } else {
                    RecipeIngredients recipeIngredient = new RecipeIngredients(recipeId, ingredientName, quantityGram);
                    recipeIngredientsDao.updateQuantity(recipeIngredient, quantityGram);
                }
            }
 else if ("add".equals(action)) {
                String ingredientName = req.getParameter("newIngredientName");
                BigDecimal quantityGram = new BigDecimal(req.getParameter("newQuantityGram"));

                // Check if ingredient exists in the ingredients table
                Ingredients ingredient = ingredientsDao.getIngredientByIngredientName(ingredientName);
                if (ingredient == null) {
                    // If the ingredient doesn't exist, add it to the ingredients table first
                    ingredient = new Ingredients(ingredientName, "Description"); // Add a description if needed
                    ingredientsDao.create(ingredient);
                }

                // Now add it to RecipeIngredients
                RecipeIngredients newIngredient = new RecipeIngredients(recipeId, ingredientName, quantityGram);
                recipeIngredientsDao.create(newIngredient);

            } else if ("delete".equals(action)) {
                String ingredientName = req.getParameter("ingredientName");
                RecipeIngredients ingredient = new RecipeIngredients(recipeId, ingredientName, BigDecimal.ZERO);
                recipeIngredientsDao.delete(ingredient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error processing request.");
        }

        displayIngredients(req, resp);
    }


    private void displayIngredients(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int recipeId = Integer.parseInt(req.getParameter("recipeId"));
            List<RecipeIngredients> ingredients = recipeIngredientsDao.getIngredientsByRecipeId(recipeId);
            req.setAttribute("recipeId", recipeId);
            req.setAttribute("ingredients", ingredients);
            req.getRequestDispatcher("/RecipeIngredient.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Unable to retrieve ingredients.");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}