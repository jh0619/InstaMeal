package instameal.servlet;

import instameal.dal.*;
import instameal.model.*;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/findrecipes")
public class FindRecipes extends HttpServlet {

    protected RecipesDao recipesDao;

    @Override
    public void init() throws ServletException {
        recipesDao = RecipesDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messagesbyname", messages);

        List<Recipes> recipes = new ArrayList<>();
        String recipeName = req.getParameter("recipename");

        if (recipeName == null || recipeName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid recipe name.");
        } else {
            try {
                Recipes recipe = recipesDao.getRecipeByName(recipeName);
                if (recipe != null) {
                    recipes.add(recipe);
                    messages.put("success", "Displaying results for: " + recipeName);
                } else {
                    messages.put("success", "No recipes found for " + recipeName);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.setAttribute("recipesByName", recipes);
        req.getRequestDispatcher("/FindRecipes.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}