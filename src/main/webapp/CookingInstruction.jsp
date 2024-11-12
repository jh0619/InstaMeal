<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Cooking Instruction</title>
    <style>
        label {
            display: inline-block;
            width: 150px;
            vertical-align: top;
        }
        input[type="number"],
        input[type="text"],
        textarea {
            width: 300px; 
            box-sizing: border-box;
        }
        textarea {
            resize: vertical; 
        }
        form p {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <h1>Cooking Instruction</h1>
    <form action="cookinginstruction" method="post">
        <input type="hidden" name="instructionId" value="${instruction.cookingInstructionId}">
        <p>
            <label><strong>Cooking Time (mins):</strong></label>
            <input type="number" name="cookingTimeMins" value="${instruction.cookingTimeMins}">
        </p>
        <p>
            <label><strong>Number of Steps:</strong></label>
            <input type="number" name="cookingSteps" value="${instruction.cookingSteps}">
        </p>
        <p>
            <label><strong>Difficulty:</strong></label>
            <input type="text" name="cookingDifficulty" value="${instruction.cookingDifficulty}">
        </p>
        <p>
            <label><strong>Steps Descriptions:</strong></label>
            <textarea name="stepsDescriptions" rows="4" cols="50">${instruction.stepsDescriptions}</textarea>
        </p>
        <button type="submit">Update</button>
    </form>

    <br>
    <a href="findrecipes">Back to Recipes</a>
</body>
</html>