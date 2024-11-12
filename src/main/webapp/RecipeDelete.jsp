<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Delete Recipe</title>
</head>
<body>
    <h1>Delete Recipe</h1>
    <c:if test="${messages.success != null}">
        <p style="color:green;">${messages.success}</p>
    </c:if>
    <c:if test="${messages.error != null}">
        <p style="color:red;">${messages.error}</p>
    </c:if>
    <form action="recipedelete" method="post">
        <input type="hidden" name="recipeId" value="${param.recipeId}" />
        <p>Are you sure you want to delete this recipe?</p>
        <button type="submit">Delete</button>
        <a href="findrecipes">Cancel</a>
    </form>
</body>
</html>