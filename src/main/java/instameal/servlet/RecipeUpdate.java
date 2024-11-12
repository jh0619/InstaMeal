package instameal.servlet;

import instameal.dal.CuisinesDao;
import instameal.dal.RecipesDao;
import instameal.model.Cuisines;
import instameal.model.Recipes;

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

@WebServlet("/recipeupdate")
public class RecipeUpdate extends HttpServlet {

    protected RecipesDao recipesDao;

    @Override
    public void init() throws ServletException {
        recipesDao = RecipesDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String recipeIdStr = req.getParameter("recipeId");
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        try {
            int recipeId = Integer.parseInt(recipeIdStr);
            Recipes recipe = recipesDao.getRecipeById(recipeId);
            if (recipe == null) {
                messages.put("error", "Recipe not found.");
            } else {
                req.setAttribute("recipe", recipe);
                req.getRequestDispatcher("/RecipeUpdate.jsp").forward(req, resp);
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            messages.put("error", "Error retrieving recipe.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        try {
            int recipeId = Integer.parseInt(req.getParameter("recipeid"));
            String recipeName = req.getParameter("recipename");
            String description = req.getParameter("description");
            String cuisineName = req.getParameter("cuisine");
            BigDecimal calories = new BigDecimal(req.getParameter("calories"));

            // Retrieve the existing recipe to ensure it exists
            Recipes recipeToUpdate = recipesDao.getRecipeById(recipeId);
            if (recipeToUpdate == null) {
                messages.put("error", "Recipe ID not found.");
                req.getRequestDispatcher("/RecipeUpdate.jsp").forward(req, resp);
                return;
            }

            // Check if the cuisine exists; if not, create it
            CuisinesDao cuisinesDao = CuisinesDao.getInstance();
            Cuisines cuisine = cuisinesDao.getCuisineByName(cuisineName);
            if (cuisine == null) {
                // If the cuisine does not exist, create it with a default description
                cuisine = new Cuisines(cuisineName, "Newly added cuisine description"); 
                cuisinesDao.create(cuisine);
            }

            // Update the recipe with the verified or newly created cuisine
            recipesDao.updateRecipe(recipeToUpdate, recipeName, description, cuisineName, calories);
            messages.put("success", "Recipe successfully updated.");
            
            resp.sendRedirect("findrecipes");  // Redirect after successful update
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            messages.put("error", "Error updating recipe.");
            req.getRequestDispatcher("/RecipeUpdate.jsp").forward(req, resp);
        }
    }
}