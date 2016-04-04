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
  <head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Security-Policy"
        content="default-src * data:; style-src * 'unsafe-inline'; script-src * 'unsafe-inline' 'unsafe-eval'"/>
    
    <link rel="stylesheet" href="lib/onsen/css/onsenui.css"/>
    <link rel="stylesheet" href="lib/onsen/css/onsen-css-components.css"/>
    <link rel="stylesheet" href="lib/css/common.css"/>
    <link rel="stylesheet" href="lib/css/signup.css"/>
    <link rel="stylesheet" href="lib/css/login.css"/>
    <link rel="stylesheet" href="lib/css/loading-bar.css"/>
    <script src="lib/onsen/js/angular/angular.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.0/angular-animate.min.js"></script>
    <script src="lib/onsen/js/onsenui.js"></script>
    <script src="lib/js/underscore-min.js"></script>
    <script src="lib/js/loading-bar.js"></script>
    
    <script>
      var module = ons.bootstrap('app', ['onsen','kindFilter','chieffancypants.loadingBar', 'ngAnimate']);
      
      
    </script>
    <script>var ctx = "${pageContext.request.contextPath}"</script>
  </head>
  <body ng-controller="AppController">
      <ons-page>
        <ons-toolbar>
              <div class="left">
                  <ons-toolbar-button onclick="window.location.href=ctx +'/'">
                    <ons-icon icon="ion-android-arrow-back" style="font-size: 32px; width: 1em;"></ons-icon>
                  </ons-toolbar-button>
              </div>
              <div class="center">Dashboard</div>
        </ons-toolbar>
  
          <c:choose>
              <c:when test="${not empty sessionScope.USER}">
                  <c:set var="xmlVar">${xmlStr}</c:set>
                    <c:import url="/users.xsl" var="xslStr" /> 
                    <x:transform xml="${xmlVar}" xslt="${xslStr}"/>
              </c:when>
              <c:otherwise>
                  Error!
              </c:otherwise>
          </c:choose>
        
  </ons-page>
    <script src="lib/js/app.js"></script>
    <script src="lib/js/controllers/appCtrl.js"></script>
    <script src="lib/js/controllers/homeCtrl.js"></script>
    <script src="lib/js/controllers/signupCtrl.js"></script>
    <script src="lib/js/controllers/loginCtrl.js"></script>
    <script src="lib/js/controllers/kindFilter.js"></script>
  </body>
</html>