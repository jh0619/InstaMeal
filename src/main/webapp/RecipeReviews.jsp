<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Recipe Reviews</title>
</head>
<body>
    <h1>Recipe Reviews</h1>

    <c:choose>
        <c:when test="${not empty reviews}">
            <table border="1">
                <tr>
                    <th>Review Date</th>
                    <th>Rating</th>
                    <th>Review Text</th>
                </tr>
                <c:forEach var="review" items="${reviews}">
                    <tr>
                        <td><c:out value="${review.reviewDate}" /></td>
                        <td><c:out value="${review.rating}" /></td>
                        <td><c:out value="${review.reviewText}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>No reviews found for this recipe.</p>
        </c:otherwise>
    </c:choose>

    <br/>
    <a href="findrecipes">Back to Recipes</a>
</body>
</html>