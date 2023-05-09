<%-- 
    Document   : search
    Created on : Feb 17, 2023, 1:26:33 PM
    Author     : DucAnh
--%>

<%--<%@page import="anhnd.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>--%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Search Page</h1>
    <font color="red">
        Welcome, ${sessionScope.USER.fullName};
    </font>
    <form action="DispatchServlet">
        Search Text <input type="text" name="txtSearchValue" value="${param.txtSearchValue}"/>
        <input type="submit" value="Search" name="btAction"/>
    </form><br/>

    <c:set var="searchValue" value="${param.txtSearchValue}"/>
    <c:if test="${not empty searchValue}">
        <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
        <c:if test="${not empty result}"> 
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Full name</th>
                        <th>Role</th>
                        <th>Delete</th>
                        <th>Update</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="DispatchServlet" method="POST">
                    
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.username}
                                <input type="hidden" name="txtUsername" value="${dto.username}"/>
                            </td>
                            <td>
                               
                                <input type="text" name="txtPassword" value="${dto.password}"/>
                            </td>
                            <td>
                                ${dto.fullName}
                            </td>
                            <td>
                                ${dto.role}
                                <input type="checkbox" name="isAdmin" value="ON"
                                       <c:if test="${dto.role}"> 
                                           checked="checked"
                                       </c:if>
                                       />
                            </td>
                            <td>
                                <input type="submit" value="Update" name="btAction"/>
                                <input type="hidden" name="lastSearchValue" 
                                       value="${searchValue}"/>
                            </td>
                            <td>
                                <c:url var="deleteLink" value="DispatchServlet">
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="pk" value="${dto.username}"/>
                                    <c:param name="lastSearchValue" value="${searchValue}"/>
                                </c:url>
                                <a href="${deleteLink}">Delete</a></td>
                        </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty result}">
            <h2>No record</h2>
        </c:if> 
    </c:if>
            
            <h2>
                <a href="${pageContext.request.contextPath}/Logout">LogOut</a>
            </h2>
            
</body>
</html>

