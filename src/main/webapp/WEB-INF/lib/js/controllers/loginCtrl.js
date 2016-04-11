module.controller('loginCtrl', function($scope, $http, cfpLoadingBar) {
    var url = "login.htm";
    var userInfo;
    $scope.login = function() {
        userInfo = $scope.loginForm;
        cfpLoadingBar.start();
        $http.post(url, JSON.stringify(userInfo)).success(function(result){/*success callback*/
            if(result) {
                window.location.href = ctx + "/dashboard.htm";
            }
            cfpLoadingBar.complete();
        });
    };

    
});

