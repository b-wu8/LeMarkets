<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Result</title>
</head>
<body>




There are ${total} farmer's markets found in the area :)<br>
<br>


<table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th>Name</th>
            <th>Address</th>
            <th>City</th>
            <th>State</th>
            <th>Session Time</th>
            <th>Credit Card Use</th>
            <th>Bakery</th>
            <th>Cheese</th>
            <th>Crafts</th>
            <th>Flowers</th>
            <th>Eggs</th>
            <th>Seafood</th>
            <th>Herbs</th>
            <th>Vegetables</th>
            <th>Honey</th>
            <th>Jams</th>
            <th>Fruit</th>
            <th>Wild Harvest</th>
        </tr>
 
        <c:forEach var="i" begin="${(current-1)*10}" end="${current*10}">
            <tr>
                <td>${list[i].split("   ")[0]}</td>
                <td>${list[i].split("   ")[1]}</td>
                <td>${list[i].split("   ")[2]}</td>
                <td>${list[i].split("   ")[3]}</td>
                <td>${list[i].split("   ")[4]}</td>
                <td>${list[i].split("   ")[5]}</td>
                <td>${list[i].split("   ")[6]}</td>
                <td>${list[i].split("   ")[7]}</td>
                <td>${list[i].split("   ")[8]}</td>
                <td>${list[i].split("   ")[9]}</td>
                <td>${list[i].split("   ")[10]}</td>
                <td>${list[i].split("   ")[11]}</td>
                <td>${list[i].split("   ")[12]}</td>
                <td>${list[i].split("   ")[13]}</td>
                <td>${list[i].split("   ")[14]}</td>
                <td>${list[i].split("   ")[15]}</td>
                <td>${list[i].split("   ")[16]}</td>
                <td>${list[i].split("   ")[17]}</td>
                
            </tr>
        </c:forEach>
</table>



    <%--For displaying Previous link except for the 1st page --%>
    <c:if test="${current != 1}">
        <td><a href="result?page=${current - 1}&&act=prev">Previous</a></td>
		<%--
			String mo = (String) session.getAttribute("move");
			out.println("prev "+mo);
			if (mo != null) {
				session.setAttribute("mo", mo);
			}
		--%>

    </c:if>
 
    <%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${pages}" var="i">
                <c:choose>
                    <c:when test="${current eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="result?page=${i}&&jumpto=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
     
    <%--For displaying Next link --%>
    <c:if test="${current lt pages}">    
        <td><a href="result?page=${current + 1}&&act=next">Next</a></td>
		<%--
			String mo = (String) session.getAttribute("move");
			out.println("next "+mo);
			if (mo != null) {
				session.setAttribute("mo", mo);
			}
		--%>

    </c:if>




</body>
</html>