/**
 * Created by Shiang Jin, Chin
 * 2024-10-30
 */
package instameal.tool;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import instameal.dal.UsersDao;
import instameal.dal.CookingInstructionsDao;
import instameal.dal.CuisinesDao;
import instameal.dal.IngredientsDao;
import instameal.dal.RecipeIngredientsDao;
import instameal.dal.RecipeTagsDao;
import instameal.dal.RecipesDao;
import instameal.dal.ResharesDao;
import instameal.dal.ReviewsDao;
import instameal.dal.UserStocksDao;
import instameal.model.*;
import java.sql.SQLException;


/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/PKJHtm / https://docs.google.com/document/d/14hLK4ujAlpNBZ8Rqk3T5VJMg9q4F1xPyI6S90TbCTIE/edit?tab=t.0#heading=h.gkypo3pglwi9
 * 2. Insert sample data from the given solution, part 2
 * 3. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		UsersDao usersDao = UsersDao.getInstance();
		IngredientsDao ingredientsDao = IngredientsDao.getInstance();
		
		// Operation with UsersDao Create, retrieve, update and delete
		System.out.println("Testing CRUD operations with UsersDao");
		Users user1 = new Users("username","password","fname","lname","fl@hotmail.com");
		Users createdUser = usersDao.create(user1);
		System.out.println("User created, now retrieving user");
		Users retrieveUser = usersDao.getUserByUserId(createdUser.getUserId());
		System.out.println(retrieveUser);
		System.out.println("Test updating user, change email to test_last@hotmail.com");
		retrieveUser.setEmail("test_last@hotmail.com");
		usersDao.updateUser(retrieveUser);
		System.out.println(usersDao.getUserByUserId(retrieveUser.getUserId()));
		System.out.println("Try retrieving list of users for username");
		for(Users user:usersDao.getUserByUserName(retrieveUser.getUserName())) {
			System.out.println(user);
		}
		System.out.println("Deleting user, should return null when call get again");
		usersDao.delete(retrieveUser);
		System.out.println(usersDao.getUserByUserName("username"));

		// Operation with Ingredients Create, retrieve, update and delete
		System.out.println("Testing CRUD operations with IngredientsDao");
		System.out.println("Retrieving record with null value for null handling");
		Ingredients existingIng = ingredientsDao.getIngredientByIngredientName("1% fat buttermilk");
		System.out.println(existingIng);
		Ingredients newIngredient = new Ingredients("testIngredient", "test");
		Ingredients newIngredient2 = new Ingredients("testIngredient2", "test");
		Ingredients createdIngredient = ingredientsDao.create(newIngredient);
		ingredientsDao.create(newIngredient2);
		System.out.println("Ingredient created, now retrieving it");
		Ingredients retrieveIngredient = ingredientsDao.getIngredientByIngredientName(createdIngredient.getIngredientName());
		System.out.println(retrieveIngredient);
		System.out.println("Test retrieve ingredient by AliasName");
		for (Ingredients ingredient:ingredientsDao.getIngredientByAliasName(retrieveIngredient.getAliasName())) {
			System.out.println(ingredient);
		}
		System.out.println("Test updating ingredient, change FatPerG to 8.0");
		retrieveIngredient.setFatPerG(BigDecimal.valueOf(8.0));
		ingredientsDao.updateIngredient(retrieveIngredient);
		retrieveIngredient = ingredientsDao.getIngredientByIngredientName(retrieveIngredient.getIngredientName());
		System.out.println(retrieveIngredient);
		
		System.out.println("Deleting ingredient, should return null when call get again");
		ingredientsDao.delete(retrieveIngredient);
		ingredientsDao.delete(newIngredient2);
		retrieveIngredient = ingredientsDao.getIngredientByIngredientName(retrieveIngredient.getIngredientName());
		System.out.println(retrieveIngredient);
		System.out.println("");
		
		// Test Cuisines
		CuisinesDao cuisinesDao = CuisinesDao.getInstance();
        
        Cuisines cuisine1 = new Cuisines("Italian", "Traditional Italian cuisine with a variety of pastas and pizzas.");
        Cuisines cuisine2 = new Cuisines("Japanese", "Authentic Japanese cuisine, known for sushi and ramen.");

        System.out.println("Testing CRUD operations with CuisinesDao");

        System.out.println("Creating Cuisines:");
        // in case for duplicates
        cuisinesDao.delete(cuisine1);
        cuisinesDao.delete(cuisine2);
        cuisinesDao.create(cuisine1);
        cuisinesDao.create(cuisine2);
        System.out.println("Created cuisine1: " + cuisine1);
        System.out.println("Created cuisine2: " + cuisine2);

        System.out.println("Retrieving Cuisines by name:");
        Cuisines retrievedCuisine1 = cuisinesDao.getCuisineByName("Italian");
        System.out.println("Retrieved cuisine1: " + retrievedCuisine1);
        Cuisines retrievedCuisine2 = cuisinesDao.getCuisineByName("Japanese");
        System.out.println("Retrieved cuisine2: " + retrievedCuisine2);

        System.out.println("Retrieving all Cuisines:");
        for (Cuisines cuisine : cuisinesDao.getAllCuisines()) {
            System.out.println(cuisine);
        }

        System.out.println("Updating Cuisines:");
        retrievedCuisine1.setDescription("Updated Italian cuisine description.");
        Cuisines updatedCuisine1 = cuisinesDao.updateCuisineDescription(retrievedCuisine1, "Updated Italian cuisine description.");
        System.out.println("Updated cuisine1: " + updatedCuisine1);

        System.out.println("Deleting Cuisines:");
        cuisinesDao.delete(retrievedCuisine1);
        cuisinesDao.delete(retrievedCuisine2);
        System.out.println("Deleted cuisine1 and cuisine2. Attempting to retrieve them should return null:");
        System.out.println("Retrieve deleted cuisine1: " + cuisinesDao.getCuisineByName("Italian"));
        System.out.println("Retrieve deleted cuisine2: " + cuisinesDao.getCuisineByName("Japanese"));
        System.out.println("");
        
        // Test CookingInstructions
        CookingInstructionsDao instructionsDao = CookingInstructionsDao.getInstance();
        
        CookingInstructions instruction1 = new CookingInstructions(30, 3, "Mix ingredients, bake, serve.", "Easy");
        CookingInstructions instruction2 = new CookingInstructions(45, 5, "Chop, sauté, mix, cook, and serve.", "Normal");

        System.out.println("Testing CRUD operations with CookingInstructionsDao");

        System.out.println("Creating Instructions:");
        instructionsDao.create(instruction1);
        instructionsDao.create(instruction2);
        System.out.println("Created instruction1: " + instruction1);
        System.out.println("Created instruction2: " + instruction2);

        System.out.println("Retrieving Instruction by ID:");
        CookingInstructions retrievedInstruction = instructionsDao.getInstructionById(instruction1.getCookingInstructionId());
        System.out.println("Retrieved instruction1: " + retrievedInstruction);

        System.out.println("Retrieving all Instructions:");
        for (CookingInstructions instruction : instructionsDao.getAllInstructions()) {
            System.out.println(instruction);
        }

        System.out.println("Updating Instruction:");
        retrievedInstruction.setStepsDescriptions("Updated steps: Mix thoroughly, bake at 350F, then serve.");
        CookingInstructions updatedInstruction = instructionsDao.updateInstruction(retrievedInstruction, "Updated steps: Mix thoroughly, bake at 350F, then serve.");
        System.out.println("Updated instruction1: " + updatedInstruction);

        System.out.println("Deleting Instructions:");
        instructionsDao.delete(instruction1);
        instructionsDao.delete(instruction2);
        System.out.println("Deleted instruction1 and instruction2. Attempting to retrieve them should return null:");
        System.out.println("Retrieve deleted instruction1: " + instructionsDao.getInstructionById(instruction1.getCookingInstructionId()));
        System.out.println("Retrieve deleted instruction2: " + instructionsDao.getInstructionById(instruction2.getCookingInstructionId()));
        System.out.println("");
        
        // Test Recipes
        RecipesDao recipesDao = RecipesDao.getInstance();
        instructionsDao = CookingInstructionsDao.getInstance();
        cuisinesDao = CuisinesDao.getInstance();

        // Step 1: Ensure dependent Cuisine and CookingInstructions records exist
        // Creating Cuisine entries
        Cuisines italianCuisine = new Cuisines("Italian", "Traditional Italian cuisine with a variety of pastas and pizzas.");
        Cuisines japaneseCuisine = new Cuisines("Japanese", "Authentic Japanese cuisine, known for sushi and ramen.");
        // in case for duplicate
        cuisinesDao.delete(italianCuisine);
        cuisinesDao.delete(japaneseCuisine);
        italianCuisine = cuisinesDao.create(italianCuisine);
        japaneseCuisine = cuisinesDao.create(japaneseCuisine);

        // Creating CookingInstructions entries
        instruction1 = new CookingInstructions(30, 3, "Mix ingredients, bake, serve.", "Easy");
        instruction2 = new CookingInstructions(45, 5, "Chop, sauté, mix, cook, and serve.", "Normal");
        instruction1 = instructionsDao.create(instruction1);
        instruction2 = instructionsDao.create(instruction2);

        // Step 2: Create Recipes entries with valid foreign key references

        System.out.println("Testing CRUD operations with RecipesDao");

        // Creating Recipes
        Recipes recipe1 = new Recipes("Spaghetti", "Delicious spaghetti recipe", instruction1.getCookingInstructionId(),
                new Timestamp(System.currentTimeMillis()), italianCuisine.getCuisineName(), new BigDecimal("300.0"),
                new BigDecimal("10.0"), new BigDecimal("5.0"), new BigDecimal("2.0"), new BigDecimal("8.0"),
                new BigDecimal("3.0"), new BigDecimal("20.0"));

        Recipes recipe2 = new Recipes("Sushi", "Fresh sushi with rice and fish", instruction2.getCookingInstructionId(),
                new Timestamp(System.currentTimeMillis()), japaneseCuisine.getCuisineName(), new BigDecimal("200.0"),
                new BigDecimal("5.0"), new BigDecimal("2.0"), new BigDecimal("1.0"), new BigDecimal("15.0"),
                new BigDecimal("2.0"), new BigDecimal("30.0"));

        System.out.println("Creating Recipes:");
        recipe1 = recipesDao.create(recipe1);
        System.out.println("Created recipe1: " + recipe1);
        recipe2 = recipesDao.create(recipe2);
        System.out.println("Created recipe2: " + recipe2);

        // Retrieving Recipes by ID
        System.out.println("Retrieving Recipe by ID:");
        Recipes retrievedRecipe1 = recipesDao.getRecipeById(recipe1.getRecipeId());
        System.out.println("Retrieved recipe1: " + retrievedRecipe1);

        Recipes retrievedRecipe2 = recipesDao.getRecipeById(recipe2.getRecipeId());
        System.out.println("Retrieved recipe2: " + retrievedRecipe2);

        // Retrieving all Recipes
        System.out.println("Retrieving all Recipes:");
        for (Recipes recipe : recipesDao.getAllRecipes()) {
            System.out.println(recipe);
        }

        // Updating Recipe Description
        System.out.println("Updating Recipe Description:");
        retrievedRecipe1.setRecipeDescription("Updated spaghetti recipe description.");
        Recipes updatedRecipe = recipesDao.updateRecipeDescription(retrievedRecipe1, "Updated spaghetti recipe description.");
        System.out.println("Updated recipe1: " + updatedRecipe);
        
        //Test RecipeTagsDao CRUD operations
        System.out.println("Testing CRUD operations with RecipeTagsDao");
        RecipeTagsDao recipeTagsDao = RecipeTagsDao.getInstance();

        // Create RecipeTags
        RecipeTags tag1 = new RecipeTags(recipe1.getRecipeId(), "Pasta");
        RecipeTags tag2 = new RecipeTags(recipe1.getRecipeId(), "Italian");

        recipeTagsDao.create(tag1);
        recipeTagsDao.create(tag2);
        System.out.println("Created tags for recipe: " + recipe1.getRecipeName());

        // Retrieve RecipeTags by RecipeId
        List<RecipeTags> tags = recipeTagsDao.getTagsByRecipeId(recipe1.getRecipeId());
        System.out.println("Retrieved tags for recipe ID " + recipe1.getRecipeId() + ":");
        for (RecipeTags tag : tags) {
            System.out.println(tag);
        }

        // Update a RecipeTag
        System.out.println("Updating tag 'Pasta' to 'Main Course' for recipe: " + recipe1.getRecipeName());
        RecipeTags updatedTag = recipeTagsDao.updateTag(tag1, "Main Course");
        System.out.println("Updated tag: " + updatedTag);

        // Retrieve RecipeTags by RecipeId after update
        tags = recipeTagsDao.getTagsByRecipeId(recipe1.getRecipeId());
        System.out.println("Retrieved tags after update for recipe ID " + recipe1.getRecipeId() + ":");
        for (RecipeTags tag : tags) {
            System.out.println(tag);
        }

        // Delete RecipeTags
        recipeTagsDao.delete(updatedTag);
        recipeTagsDao.delete(tag2);
        System.out.println("Deleted tags for recipe: " + recipe1.getRecipeName());

        // Deleting Recipes
        System.out.println("Deleting Recipes:");
        recipesDao.delete(recipe1);
        recipesDao.delete(recipe2);
        System.out.println("Deleted recipe1 and recipe2. Attempting to retrieve them should return null:");
        System.out.println("Retrieve deleted recipe1: " + recipesDao.getRecipeById(recipe1.getRecipeId()));
        System.out.println("Retrieve deleted recipe2: " + recipesDao.getRecipeById(recipe2.getRecipeId()));

        // Cleanup: Deleting CookingInstructions and Cuisines
        System.out.println("Deleting CookingInstructions:");
        instructionsDao.delete(instruction1);
        instructionsDao.delete(instruction2);
        System.out.println("Deleted instruction1 and instruction2.");

        System.out.println("Deleting Cuisines:");
        cuisinesDao.delete(italianCuisine);
        cuisinesDao.delete(japaneseCuisine);
        System.out.println("Deleted italianCuisine and japaneseCuisine.");
        System.out.println();
        
        // Test RecipeIngredients
        recipesDao = RecipesDao.getInstance();
        RecipeIngredientsDao recipeIngredientsDao = RecipeIngredientsDao.getInstance();
        ingredientsDao = IngredientsDao.getInstance();
        instructionsDao = CookingInstructionsDao.getInstance();
        cuisinesDao = CuisinesDao.getInstance();

        italianCuisine = new Cuisines("Italian", "Traditional Italian cuisine with a variety of pastas and pizzas.");
        italianCuisine = cuisinesDao.create(italianCuisine);

        CookingInstructions instruction = new CookingInstructions(30, 3, "Mix ingredients, bake, serve.", "Easy");
        instruction = instructionsDao.create(instruction);

        Ingredients tomato = new Ingredients("Tomato", "Tomato", new BigDecimal("0.2"), new BigDecimal("2.0"), new BigDecimal("5.0"), new BigDecimal("1.0"), new BigDecimal("0.1"));
        Ingredients pasta = new Ingredients("Pasta", "Pasta", new BigDecimal("0.1"), new BigDecimal("0.5"), new BigDecimal("3.0"), new BigDecimal("1.5"), new BigDecimal("0.2"));
        ingredientsDao.create(tomato);
        ingredientsDao.create(pasta);

        Recipes recipe = new Recipes("Spaghetti", "Delicious spaghetti recipe", instruction.getCookingInstructionId(),
                new Timestamp(System.currentTimeMillis()), italianCuisine.getCuisineName(), new BigDecimal("300.0"),
                new BigDecimal("10.0"), new BigDecimal("5.0"), new BigDecimal("2.0"), new BigDecimal("8.0"),
                new BigDecimal("3.0"), new BigDecimal("20.0"));

        recipe = recipesDao.create(recipe);
        System.out.println("Created recipe: " + recipe);

        System.out.println("Testing CRUD operations with RecipeIngredientsDao");

        RecipeIngredients ingredient1 = new RecipeIngredients(recipe.getRecipeId(), "Tomato", new BigDecimal("100.0"));
        RecipeIngredients ingredient2 = new RecipeIngredients(recipe.getRecipeId(), "Pasta", new BigDecimal("200.0"));

        recipeIngredientsDao.create(ingredient1);
        recipeIngredientsDao.create(ingredient2);
        System.out.println("Created ingredients for recipe: " + recipe.getRecipeName());

        List<RecipeIngredients> ingredientsByRecipeId = recipeIngredientsDao.getIngredientsByRecipeId(recipe.getRecipeId());
        System.out.println("Retrieved ingredients for recipe ID " + recipe.getRecipeId() + ":");
        for (RecipeIngredients ingredient : ingredientsByRecipeId) {
            System.out.println(ingredient);
        }

        List<RecipeIngredients> ingredientsByName = recipeIngredientsDao.getIngredientsByName("Tomato");
        System.out.println("Retrieved recipes with ingredient name 'Tomato':");
        for (RecipeIngredients ingredient : ingredientsByName) {
            System.out.println(ingredient);
        }

        System.out.println("Updating quantity of 'Tomato' to 150g for recipe: " + recipe.getRecipeName());
        RecipeIngredients updatedIngredient = recipeIngredientsDao.updateQuantity(ingredient1, new BigDecimal("150.0"));
        System.out.println("Updated ingredient: " + updatedIngredient);

        recipeIngredientsDao.delete(ingredient1);
        recipeIngredientsDao.delete(ingredient2);
        recipesDao.delete(recipe);
        instructionsDao.delete(instruction);
        cuisinesDao.delete(italianCuisine);
        ingredientsDao.delete(tomato);
        ingredientsDao.delete(pasta);
        System.out.println("Deleted all entries after test.");
        System.out.println();
        
        // Test UserStocks
        usersDao = UsersDao.getInstance();
        UserStocksDao userStocksDao = UserStocksDao.getInstance();
        ingredientsDao = IngredientsDao.getInstance();

        Users user = new Users("johndoe", "password123", "John", "Doe", "johndoe@example.com");
        user = usersDao.create(user);

        tomato = new Ingredients("Tomato", "Tomato", new BigDecimal("0.2"), new BigDecimal("2.0"), new BigDecimal("5.0"), new BigDecimal("1.0"), new BigDecimal("0.1"));
        pasta = new Ingredients("Pasta", "Pasta", new BigDecimal("0.1"), new BigDecimal("0.5"), new BigDecimal("3.0"), new BigDecimal("1.5"), new BigDecimal("0.2"));
        ingredientsDao.create(tomato);
        ingredientsDao.create(pasta);

        System.out.println("Testing CRUD operations with UserStocksDao");

        UserStocks stock1 = new UserStocks(user.getUserId(), "Tomato", new BigDecimal("500.0"), Date.valueOf("2024-12-31"));
        UserStocks stock2 = new UserStocks(user.getUserId(), "Pasta", new BigDecimal("1000.0"), Date.valueOf("2025-01-31"));

        stock1 = userStocksDao.create(stock1);
        stock2 = userStocksDao.create(stock2);
        System.out.println("Created UserStocks entries for user: " + user.getUserName());

        List<UserStocks> stocksByUserId = userStocksDao.getStocksByUserId(user.getUserId());
        System.out.println("Retrieved stocks for user ID " + user.getUserId() + ":");
        for (UserStocks stock : stocksByUserId) {
            System.out.println(stock);
        }

        System.out.println("Updating quantity of 'Tomato' stock to 600g for user: " + user.getUserName());
        UserStocks updatedStock = userStocksDao.updateQuantity(stock1, new BigDecimal("600.0"));
        System.out.println("Updated stock: " + updatedStock);

        userStocksDao.delete(stock1);
        userStocksDao.delete(stock2);
        usersDao.delete(user);
        ingredientsDao.delete(tomato);
        ingredientsDao.delete(pasta);
        System.out.println("Deleted all entries after test.");
        System.out.println();
        
        usersDao = UsersDao.getInstance();
        ReviewsDao reviewsDao = ReviewsDao.getInstance();
        recipesDao = RecipesDao.getInstance();
        instructionsDao = CookingInstructionsDao.getInstance();
        cuisinesDao = CuisinesDao.getInstance();

        user = new Users("johndoe", "password123", "John", "Doe", "johndoe@example.com");
        user = usersDao.create(user);

        italianCuisine = new Cuisines("Italian", "Traditional Italian cuisine with a variety of pastas and pizzas.");
        italianCuisine = cuisinesDao.create(italianCuisine);

        instruction = new CookingInstructions(30, 3, "Mix ingredients, bake, serve.", "Easy");
        instruction = instructionsDao.create(instruction);

        recipe = new Recipes("Spaghetti", "Delicious spaghetti recipe", instruction.getCookingInstructionId(),
                new Timestamp(System.currentTimeMillis()), italianCuisine.getCuisineName(), new BigDecimal("300.0"),
                new BigDecimal("10.0"), new BigDecimal("5.0"), new BigDecimal("2.0"), new BigDecimal("8.0"),
                new BigDecimal("3.0"), new BigDecimal("20.0"));

        recipe = recipesDao.create(recipe);

        System.out.println("Testing CRUD operations with ReviewsDao");

        Reviews review1 = new Reviews(user.getUserId(), recipe.getRecipeId(), Date.valueOf("2024-11-09"), new BigDecimal("4.5"), "Very tasty!");
        Reviews review2 = new Reviews(user.getUserId(), recipe.getRecipeId(), Date.valueOf("2024-11-10"), new BigDecimal("4.0"), "Could use more seasoning.");

        review1 = reviewsDao.create(review1);
        review2 = reviewsDao.create(review2);
        System.out.println("Created reviews for recipe: " + recipe.getRecipeName());

        List<Reviews> reviewsByRecipeId = reviewsDao.getReviewsByRecipeId(recipe.getRecipeId());
        System.out.println("Retrieved reviews for recipe ID " + recipe.getRecipeId() + ":");
        for (Reviews review : reviewsByRecipeId) {
            System.out.println(review);
        }

        System.out.println("Updating review text for review1 to 'Absolutely delicious!'");
        Reviews updatedReview = reviewsDao.updateReviewText(review1, "Absolutely delicious!");
        System.out.println("Updated review: " + updatedReview);

        reviewsDao.delete(review1);
        reviewsDao.delete(review2);
        usersDao.delete(user);
        recipesDao.delete(recipe);
        instructionsDao.delete(instruction);
        cuisinesDao.delete(italianCuisine);
        System.out.println("Deleted all entries after test.");
        System.out.println();
        
        // Test Reshares
        usersDao = UsersDao.getInstance();
        ResharesDao resharesDao = ResharesDao.getInstance();
        recipesDao = RecipesDao.getInstance();
        instructionsDao = CookingInstructionsDao.getInstance();
        cuisinesDao = CuisinesDao.getInstance();

        user = new Users("johndoe", "password123", "John", "Doe", "johndoe@example.com");
        user = usersDao.create(user);

        italianCuisine = new Cuisines("Italian", "Traditional Italian cuisine with a variety of pastas and pizzas.");
        italianCuisine = cuisinesDao.create(italianCuisine);

        instruction = new CookingInstructions(30, 3, "Mix ingredients, bake, serve.", "Easy");
        instruction = instructionsDao.create(instruction);

        recipe = new Recipes("Spaghetti", "Delicious spaghetti recipe", instruction.getCookingInstructionId(),
                new Timestamp(System.currentTimeMillis()), italianCuisine.getCuisineName(), new BigDecimal("300.0"),
                new BigDecimal("10.0"), new BigDecimal("5.0"), new BigDecimal("2.0"), new BigDecimal("8.0"),
                new BigDecimal("3.0"), new BigDecimal("20.0"));

        recipe = recipesDao.create(recipe);

        System.out.println("Testing CRUD operations with ResharesDao");

        Reshares reshare1 = new Reshares(user.getUserId(), recipe.getRecipeId(), new Timestamp(System.currentTimeMillis()));
        Reshares reshare2 = new Reshares(user.getUserId(), recipe.getRecipeId(), new Timestamp(System.currentTimeMillis()));

        reshare1 = resharesDao.create(reshare1);
        reshare2 = resharesDao.create(reshare2);
        System.out.println("Created reshares for recipe: " + recipe.getRecipeName());

        List<Reshares> resharesByUserId = resharesDao.getResharesByUserId(user.getUserId());
        System.out.println("Retrieved reshares for user ID " + user.getUserId() + ":");
        for (Reshares reshare : resharesByUserId) {
            System.out.println(reshare);
        }

        resharesDao.delete(reshare1);
        resharesDao.delete(reshare2);
        usersDao.delete(user);
        recipesDao.delete(recipe);
        instructionsDao.delete(instruction);
        cuisinesDao.delete(italianCuisine);
        System.out.println("Deleted all entries after test.");
	}
}
