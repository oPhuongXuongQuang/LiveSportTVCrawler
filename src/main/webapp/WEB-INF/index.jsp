<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script src="http://code.jquery.com/jquery-1.10.0.js"></script>
    <script src="lib/js/polyfiller.js"></script>
    
    <script>
      var module = ons.bootstrap('app', ['onsen','kindFilter','chieffancypants.loadingBar', 'ngAnimate']);
      
      (function () {
          webshim.setOptions('forms', {
        lazyCustomMessages: true,
        iVal: {
            sel: '.ws-validate',
            handleBubble: 'hide', // hide error bubble

            //add bootstrap specific classes
            errorMessageClass: 'help-block',
            successWrapperClass: 'has-success',
            errorWrapperClass: 'has-error'

        }
    });
        webshims.polyfill('forms');
      })();
    </script>
    <script>var ctx = "${pageContext.request.contextPath}"</script>
  </head>
  <body ng-controller="AppController">
    
  
    <ons-navigator title="Navigator" page="home.html" var="$nav" modifier="material">
    </ons-navigator>
    <ons-template id="page2.html">
      <ons-page>
        <ons-toolbar>
          <div class="left">
            <ons-toolbar-button onclick="menu.toggleMenu()">
              <ons-icon icon="ion-navicon" style="font-size: 32px; width: 1em;"></ons-icon>
            </ons-toolbar-button>
          </div>
          <div class="center">Page 2</div>
        </ons-toolbar>

        <p style="text-align: center; color: #999; padding-top: 100px;">Page2 Contents</p>
      </ons-page>
    </ons-template>
    <script src="lib/js/app.js"></script>
    <script src="lib/js/service/baseService.js"></script>
    <script src="lib/js/controllers/appCtrl.js"></script>
    <script src="lib/js/controllers/homeCtrl.js"></script>
    <script src="lib/js/controllers/signupCtrl.js"></script>
    <script src="lib/js/controllers/loginCtrl.js"></script>
    <script src="lib/js/controllers/kindFilter.js"></script>
    <script src="lib/js/controllers/eventDetailCtrl.js"></script>
    <script src="lib/js/controllers/liveStreamCtrl.js"></script>
  </body>
</html>
