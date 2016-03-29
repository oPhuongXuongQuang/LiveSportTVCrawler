<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Security-Policy"
        content="default-src * data:; style-src * 'unsafe-inline'; script-src * 'unsafe-inline' 'unsafe-eval'"/>
    
    <link rel="stylesheet" href="lib/onsen/css/onsenui.css"/>
    <link rel="stylesheet" href="lib/onsen/css/onsen-css-components.css"/>
    <link rel="stylesheet" href="lib/css/common.css"/>
    <script src="lib/onsen/js/angular/angular.js"></script>
    <script src="lib/onsen/js/onsenui.js"></script>
    <script src="lib/js/underscore-min.js"></script>
    <script>
      var module = ons.bootstrap('app', ['onsen']);
      module.controller('AppController', function($scope) { });
      
    </script>
  </head>
  <body ng-controller="AppController">
    <ons-sliding-menu main-page="home.html" menu-page="menu.html" side="left" max-slide-distance="250px" var="menu">
    </ons-sliding-menu>

    


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

   
    <script src="lib/js/controllers/homeCtrl.js"></script>
  </body>
</html>
