module.controller('HomeController', function($rootScope, $scope, $timeout, $http, $service, cfpLoadingBar) {
    $scope.querySearch = null;
    $http.post($service.back.comingUp.url).success( function(response) {
      var Events = response;
      $scope.Events = _.groupBy(Events,'kind');
    });
    
    $scope.load = function($done) {
      $timeout(function() {
        $http.post($service.back.comingUp.url)
          .success(function(response) {
            $scope.Events = _.groupBy(response,'kind');
          })
          .error(function() {
            console.log("Error!");
          })
          .finally(function() {
            $done();
          });
      }, 1000);
    };
    
    $scope.onSelectItem = function(item) {
        //cfpLoadingBar.start();
        //var link = {
        //    value: item.link,
        //    live: (item.live !== "") ? true : false
        //};
        //$http.post($service.back.eventDetail.url,link).then(function(result){
        //    console.log(result);
        //    $rootScope.item = item;
        //    $rootScope.eventDetail = result;
        //    $rootScope.$nav.pushPage("eventDetail.html");
        //    cfpLoadingBar.complete();
        //});
        $rootScope.item = item;
        if(item.match.search("–") != -1) {
            $rootScope.team1 = item.match.split("–")[0].trim();
            $rootScope.team2 = item.match.split("–")[1].trim();
        }
        if(window.localStorage.getItem("images") != null && window.localStorage.getItem("images") != "") {
            $rootScope.images = JSON.parse(window.localStorage.getItem("images"));
        }
        console.log($rootScope.team1);
        console.log($rootScope.team2);
        $rootScope.$nav.pushPage("eventDetail.html");
    };

    $scope.isDisabled = false;

    $scope.querySearch = function(value) {
        $scope.searchVal = value;
        return $service.querySearch(value);
    };

    $scope.goToSuggest = function(video) {
        cfpLoadingBar.start();
        if(video.highlightLink.indexOf("/en/showvideo") > -1) {
            //$http.post($service.back.getVideo.url, link).success(function (result) {
            //
            //
            //});
        } else if(video.highlightLink.indexOf("playwire.com") > -1) {

        }
        else {
            $rootScope.$nav.pushPage("videoSuggest.html",{param:video, param2:$scope.searchVal});
        }
    };
});
