module.controller('AppController', function($scope) {
        $scope.kindfilters = [];
        $scope.kinds = ['Football'];
        $scope.addFilter = function (value) {
            if($scope.kinds[value]) {
                $scope.kindfilters.push(value);
            } else {
                var index = $scope.kindfilters.indexOf(value);
                if (index > -1) {
                    $scope.kindfilters.splice(index, 1);
                }
            }
        };
      });

