<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <html lang="en">
  <head>
  <meta charset="utf-8">
  <meta http-equiv="Access-Control-Allow-Origin"
  content="*"/>

  <link rel="stylesheet" href="lib/onsen/css/onsenui.css"/>
  <link rel="stylesheet" href="lib/onsen/css/onsen-css-components.css"/>
  <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/onsen/1.3.15/css/onsen-css-components.css"/>-->
  <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/onsen/1.3.15/css/onsenui.css"/>-->


  <link rel="stylesheet" href="lib/css/common.css"/>
  <link rel="stylesheet" href="lib/css/signup.css"/>
  <link rel="stylesheet" href="lib/css/login.css"/>
  <link rel="stylesheet" href="lib/css/loading-bar.css"/>

  <!-- Angular Material style sheet -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <!--<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0-rc3/angular-material.min.css">-->
  <link rel="stylesheet" href="https://cdn.gitcdn.link/cdn/angular/bower-material/v1.1.0-rc4/angular-material.css">

  <!--<script src="lib/onsen/js/angular/angular.js"></script>-->
  <!-- Angular Material requires Angular.js Libraries -->
  <!--<script src="http://code.jquery.com/jquery-1.10.0.js"></script>-->
  <!--<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular.js"></script>-->
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.js"></script>

  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-aria.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-messages.min.js"></script>
  <script src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/t-114/svg-assets-cache.js"></script>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/onsen/1.3.15/js/onsenui.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/onsen/1.3.15/js/onsenui_all.js"></script>
  <!--<script src="lib/onsen/js/onsenui.js"></script>-->
  <!-- Angular Material Library -->
  <script src="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0-rc2/angular-material.min.js"></script>
  <!--<script src="lib/js/angular-material.js"></script>-->



  <!--<script src="lib/js/underscore-min.js"></script>-->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>
  <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.map"></script>-->
  <script src="lib/js/loading-bar.js"></script>

  <!--<script src="lib/js/polyfiller.js"></script>-->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/webshim/1.15.10/minified/polyfiller.js"></script>

  <script>
  var module = ons.bootstrap('app', ['onsen','kindFilter','chieffancypants.loadingBar', 'ngAnimate', 'ngMaterial', 'material.svgAssetsCache']);

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
  <script src="lib/js/app.js"></script>
  <script src="lib/js/service/baseService.js"></script>
  <script src="lib/js/controllers/appCtrl.js"></script>
  <script src="lib/js/controllers/homeCtrl.js"></script>
  <script src="lib/js/controllers/signupCtrl.js"></script>
  <script src="lib/js/controllers/loginCtrl.js"></script>
  <script src="lib/js/controllers/kindFilter.js"></script>
  <script src="lib/js/controllers/eventDetailCtrl.js"></script>
  <script src="lib/js/controllers/liveStreamCtrl.js"></script>
  <script src="lib/js/controllers/videosCtrl.js"></script>
  <script src="lib/js/controllers/searchResultCtrl.js"></script>
  <script src="lib/js/controllers/calendarCtrl.js"></script>
  <script src="lib/js/controllers/videoCtrl.js"></script>
  </body>
  </html>
