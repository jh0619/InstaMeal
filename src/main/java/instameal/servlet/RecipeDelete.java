package instameal.servlet;

import instameal.dal.*;
import instameal.model.*;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/recipedelete")
public class RecipeDelete extends HttpServlet {

    protected RecipesDao recipesDao;

    @Override
    public void init() throws ServletException {
        recipesDao = RecipesDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/RecipeDelete.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        String recipeIdStr = req.getParameter("recipeId");
        if (recipeIdStr == null || recipeIdStr.trim().isEmpty()) {
            messages.put("error", "Invalid Recipe ID.");
        } else {
            try {
                int recipeId = Integer.parseInt(recipeIdStr);
                Recipes recipe = recipesDao.getRecipeById(recipeId);

                if (recipe != null) {
                    recipesDao.delete(recipe);
                    messages.put("success", "Recipe deleted successfully.");
                } else {
                    messages.put("error", "Recipe not found.");
                }
            } catch (SQLException | NumberFormatException e) {
                e.printStackTrace();
                messages.put("error", "Failed to delete recipe. Please try again.");
            }
        }

        // Redirect to the main recipe listing page or reload the delete page with a message
        resp.sendRedirect("findrecipes");
    }
}