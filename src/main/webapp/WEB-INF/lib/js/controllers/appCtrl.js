module.controller('AppController', function($scope) {
        $scope.kindfilters = [];
        $scope.kinds = {};
        $scope.kindName = ['Football','Basketball','Tennis','Baseball','Ice Hockey','Cricket','Table Tennis','Racing','Cycling',' Volleyball'
        ,'Beach Volleyball','Handball','Badminton','Equestrianism','Water Sports'];

        for(var pos in $scope.kindName){
            $scope.kinds[$scope.kindName[pos]] = true;
            $scope.kindfilters.push($scope.kindName[pos]);
        }

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

