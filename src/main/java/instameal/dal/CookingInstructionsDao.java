package instameal.dal;

import instameal.model.CookingInstructions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CookingInstructionsDao {
    protected ConnectionManager connectionManager;
    private static CookingInstructionsDao instance = null;

    protected CookingInstructionsDao() {
        connectionManager = new ConnectionManager();
    }

    public static CookingInstructionsDao getInstance() {
        if (instance == null) {
            instance = new CookingInstructionsDao();
        }
        return instance;
    }

    public CookingInstructions create(CookingInstructions instruction) throws SQLException {
        String insertInstruction = "INSERT INTO CookingInstructions(CookingTimeMins, CookingSteps, StepsDescriptions, CookingDifficulty) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertInstruction, PreparedStatement.RETURN_GENERATED_KEYS);
            insertStmt.setInt(1, instruction.getCookingTimeMins());
            insertStmt.setInt(2, instruction.getCookingSteps());
            insertStmt.setString(3, instruction.getStepsDescriptions());
            insertStmt.setString(4, instruction.getCookingDifficulty());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int instructionId = -1;
            if (resultKey.next()) {
                instructionId = resultKey.getInt(1);
            }
            instruction.setCookingInstructionId(instructionId);
            return instruction;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public CookingInstructions getInstructionById(int instructionId) throws SQLException {
        String selectInstruction = "SELECT CookingInstructionId, CookingTimeMins, CookingSteps, StepsDescriptions, CookingDifficulty FROM CookingInstructions WHERE CookingInstructionId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectInstruction);
            selectStmt.setInt(1, instructionId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                int cookingTimeMins = results.getInt("CookingTimeMins");
                int cookingSteps = results.getInt("CookingSteps");
                String stepsDescriptions = results.getString("StepsDescriptions");
                String cookingDifficulty = results.getString("CookingDifficulty");
                CookingInstructions instruction = new CookingInstructions(instructionId, cookingTimeMins, cookingSteps, stepsDescriptions, cookingDifficulty);
                return instruction;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    public List<CookingInstructions> getAllInstructions() throws SQLException {
        List<CookingInstructions> instructionsList = new ArrayList<>();
        String selectAll = "SELECT CookingInstructionId, CookingTimeMins, CookingSteps, StepsDescriptions, CookingDifficulty FROM CookingInstructions;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAll);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int instructionId = results.getInt("CookingInstructionId");
                int cookingTimeMins = results.getInt("CookingTimeMins");
                int cookingSteps = results.getInt("CookingSteps");
                String stepsDescriptions = results.getString("StepsDescriptions");
                String cookingDifficulty = results.getString("CookingDifficulty");
                CookingInstructions instruction = new CookingInstructions(instructionId, cookingTimeMins, cookingSteps, stepsDescriptions, cookingDifficulty);
                instructionsList.add(instruction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return instructionsList;
    }

    public CookingInstructions updateInstruction(CookingInstructions instruction, String newDescription) throws SQLException {
        String updateInstruction = "UPDATE CookingInstructions SET StepsDescriptions=? WHERE CookingInstructionId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateInstruction);
            updateStmt.setString(1, newDescription);
            updateStmt.setInt(2, instruction.getCookingInstructionId());
            updateStmt.executeUpdate();
            instruction.setStepsDescriptions(newDescription);
            return instruction;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public CookingInstructions delete(CookingInstructions instruction) throws SQLException {
        String deleteInstruction = "DELETE FROM CookingInstructions WHERE CookingInstructionId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteInstruction);
            deleteStmt.setInt(1, instruction.getCookingInstructionId());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
}
