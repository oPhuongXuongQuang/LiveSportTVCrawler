module.controller('HomeController', function($scope, $http) {
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
});
