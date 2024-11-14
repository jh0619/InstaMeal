package instameal.servlet;
import java.util.HashMap;

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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet("/findrecipebyingredients")
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
        req.getRequestDispatcher("/FindRecipes.jsp").forward(req, resp);
    }
/**
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // 使用与FindRecipes相同的messages key
        Map<String, String> messages = new HashMap<>(); 
        req.setAttribute("messages", messages);

        String ingredients = req.getParameter("ingredients");
        
        try {
            List<Recipes> recipes = new ArrayList<>();
            if (ingredients != null && !ingredients.trim().isEmpty()) {
                recipes = recipesDao.findRecipesByIngredients(ingredients);
                if (recipes.isEmpty()) {
                    // 修改为使用success key而不是ingredient
                    messages.put("success", "No recipes found with these ingredients.");
                } else {
                    messages.put("success", "Found " + recipes.size() + " matching recipes.");
                }
            } else {
                messages.put("success", "Please enter at least one ingredient.");
            }
            // 使用与FindRecipes相同的attribute名
            req.setAttribute("recipes", recipes);
        } catch (SQLException e) {
            e.printStackTrace();
            messages.put("success", "Error searching recipes: " + e.getMessage());
        }

        req.getRequestDispatcher("/FindRecipes.jsp").forward(req, resp);
    }**/
   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>(); 
        req.setAttribute("messagesbyingredients", messages);

        String ingredients = req.getParameter("ingredients");
       
        
        try {
            List<Recipes> recipes = new ArrayList<>();
            if (ingredients != null && !ingredients.trim().isEmpty()) {
                recipes = recipesDao.findRecipesByIngredients(ingredients);
                if (recipes.isEmpty()) {
                    messages.put("success", "No recipes found with these ingredients.");
                } else {
                    messages.put("success", "Found " + recipes.size() + " matching recipes.");
                }
            } else {
                messages.put("success", "Please enter at least one ingredient.");
            }
            req.setAttribute("recipesByIngredients", recipes);
        } catch (SQLException e) {
            e.printStackTrace();
            messages.put("success", "Error searching recipes: " + e.getMessage());
        }

        req.getRequestDispatcher("/FindRecipes.jsp").forward(req, resp);
    }
  
   
}

    
    
