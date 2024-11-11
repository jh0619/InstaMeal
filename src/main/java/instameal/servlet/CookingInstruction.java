package instameal.servlet;

import instameal.dal.CookingInstructionsDao;
import instameal.dal.RecipesDao;
import instameal.model.CookingInstructions;
import instameal.model.Recipes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cookinginstruction")
public class CookingInstruction extends HttpServlet {

    protected CookingInstructionsDao cookingInstructionsDao;
    protected RecipesDao recipesDao;

    @Override
    public void init() throws ServletException {
        cookingInstructionsDao = CookingInstructionsDao.getInstance();
        recipesDao = RecipesDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String recipeId = req.getParameter("recipeId");
        if (recipeId == null || recipeId.trim().isEmpty()) {
            req.setAttribute("error", "Invalid Recipe ID.");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }

        try {
            int parsedRecipeId = Integer.parseInt(recipeId);
            Recipes recipe = recipesDao.getRecipeById(parsedRecipeId);

            if (recipe == null) {
                req.setAttribute("error", "Recipe not found.");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
                return;
            }

            int instructionId = recipe.getCookingInstructionId();
            CookingInstructions instruction = cookingInstructionsDao.getInstructionById(instructionId);

            if (instruction == null) {
                req.setAttribute("error", "Cooking instruction not found.");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
                return;
            }

            req.setAttribute("instruction", instruction);
            req.getRequestDispatcher("/CookingInstruction.jsp").forward(req, resp);

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("error", "Unable to retrieve cooking instruction.");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String instructionIdStr = req.getParameter("instructionId");
        String cookingTimeStr = req.getParameter("cookingTimeMins");
        String cookingStepsStr = req.getParameter("cookingSteps");
        String difficulty = req.getParameter("cookingDifficulty");
        String stepsDescriptions = req.getParameter("stepsDescriptions");

        try {
            int instructionId = Integer.parseInt(instructionIdStr);
            int cookingTimeMins = Integer.parseInt(cookingTimeStr);
            int cookingSteps = Integer.parseInt(cookingStepsStr);

            CookingInstructions instruction = cookingInstructionsDao.getInstructionById(instructionId);
            if (instruction == null) {
                req.setAttribute("error", "Cooking instruction not found.");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
                return;
            }

            cookingInstructionsDao.updateInstruction(instruction, cookingTimeMins, cookingSteps, difficulty, stepsDescriptions);
            req.setAttribute("success", "Cooking instruction updated successfully.");
            resp.sendRedirect("findrecipes");

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("error", "Unable to update cooking instruction.");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}