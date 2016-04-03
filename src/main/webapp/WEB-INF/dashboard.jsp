<%-- 
    Document   : doashboard
    Created on : Apr 3, 2016, 5:17:22 PM
    Author     : quangphuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html lang="en">
            <c:set var="xmlVar" value="${output}" />
            ${xmlVar}
            <%--<x:transform xml="${xmlVar}" xslt="WEB-INF/users.xsl"/>--%>
</html>
