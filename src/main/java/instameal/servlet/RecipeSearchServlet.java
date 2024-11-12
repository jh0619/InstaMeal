package instameal.servlet;

import instameal.dal.RecipesDao;
import instameal.model.Recipes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/recipesearch")
public class RecipeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    protected RecipesDao recipesDao;

    @Override
    public void init() throws ServletException {
        recipesDao = RecipesDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Display search page
        req.getRequestDispatcher("/RecipeIngredientSearch.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Get all input ingredients
        String[] ingredientsArray = req.getParameterValues("ingredients");
        List<String> ingredients = new ArrayList<>();
        
        if (ingredientsArray != null) {
            for (String ingredient : ingredientsArray) {
                if (ingredient != null && !ingredient.trim().isEmpty()) {
                    ingredients.add(ingredient.trim().toLowerCase());
                }
            }
        }

        try {
            if (!ingredients.isEmpty()) {
                List<Recipes> recipes = recipesDao.findRecipesByIngredients(ingredients);
                req.setAttribute("recipes", recipes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error searching recipes", e);
        }

        req.getRequestDispatcher("/RecipeSearch.jsp").forward(req, resp);
    }
}