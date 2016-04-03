module.controller('signupCtrl', function($scope, $http, cfpLoadingBar) {
    var url = "register.htm";
    var userInfo;
    $scope.register = function() {
        userInfo = $scope.form;
        cfpLoadingBar.start();
        $http.post(url, JSON.stringify(userInfo)).success(function(){/*success callback*/
            cfpLoadingBar.complete();
        });
    };

    
});

