package instameal.servlet;

import instameal.dal.ReviewsDao;
import instameal.model.Reviews;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/reviews")
public class RecipeReviews extends HttpServlet {

    protected ReviewsDao reviewsDao;

    @Override
    public void init() throws ServletException {
        reviewsDao = ReviewsDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String recipeIdStr = req.getParameter("recipeId");

        if (recipeIdStr == null || recipeIdStr.trim().isEmpty()) {
            req.setAttribute("error", "Invalid Recipe ID.");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }

        try {
            int recipeId = Integer.parseInt(recipeIdStr);
            List<Reviews> reviews = reviewsDao.getReviewsByRecipeId(recipeId);

            req.setAttribute("reviews", reviews);
            req.getRequestDispatcher("/RecipeReviews.jsp").forward(req, resp);

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("error", "Unable to retrieve reviews.");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}