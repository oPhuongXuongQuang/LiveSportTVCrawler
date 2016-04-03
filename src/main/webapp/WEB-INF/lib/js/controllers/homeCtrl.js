module.controller('HomeController', function($scope, $timeout, $http) {
    var url = "comingup.htm";

    var req = {
    method: 'POST',
    url: url,
    headers: {
        'Accept': '*/*',
        'Content-Type': 'application/json; charset=utf-8'
        },
    };

    $http(req).then( function(response) {
      var Events = response.data;
      $scope.Events = _.groupBy(Events,'kind');
    });
    
    $scope.load = function($done) {
      $timeout(function() {
        $http.post(url)
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
});
