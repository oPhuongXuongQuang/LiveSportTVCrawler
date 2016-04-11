module.controller('HomeController', function($rootScope, $scope, $timeout, $http, $service, cfpLoadingBar) {

    $http.post($service.back.comingUp.url).success( function(response) {
      var Events = response;
      $scope.Events = _.groupBy(Events,'kind');
    });
    
    $scope.load = function($done) {
      $timeout(function() {
        $http.post($service.back.comingUp.url)
          .success(function(response) {
            $scope.Events.unshift({
              desc: response.data,
              rand: Math.random()
            });
          })
          .error(function() {
            $scope.Events.unshift({
              desc: 'No data',
              rand: Math.random()
            });
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
});
