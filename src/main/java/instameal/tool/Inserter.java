/**
 * Created by Shiang Jin, Chin
 * 2024-10-30
 */
package instameal.tool;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import instameal.dal.UsersDao;
import instameal.dal.IngredientsDao;
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
	}
}
