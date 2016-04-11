/**
 * Created by Quang on 09-Apr-16.
 */
module.controller('EventDetailController', function($http, $rootScope ,$scope, $service, cfpLoadingBar){
    var link = {
        value: $rootScope.item.link,
        live: ($rootScope.item.live !== "") ? true : false
    };
    $http.post($service.back.eventDetail.url,link).success(function(result){
        console.log(result);
        $scope.eventDetail = result;

        if ($rootScope.team1 != undefined && $rootScope.team2 != undefined) {
            if ($rootScope.images == undefined) {
                $rootScope.images = {};
                $rootScope.images[result.team1.name] = result.team1.image;
                $rootScope.images[result.team2.name] = result.team2.image;
            } else {
                if ($rootScope.images[result.team1.name] == null) {
                    $rootScope.images[result.team1.name] = result.team1.image;
                }
                if ($rootScope.images[result.team2.name] == null) {
                    $rootScope.images[result.team2.name] = result.team2.image;
                }
            }
            try {
                window.localStorage.setItem("images", JSON.stringify($rootScope.images));
            } catch (e) {
                window.localStorage.clear();
            }
        }
        cfpLoadingBar.complete();
    });

    $scope.goToVideo = function(link) { 
        cfpLoadingBar.start();
        $http.post($service.back.getVideo.url, link).success(function(result) {

            console.log(result);
            $rootScope.$nav.pushPage("livestream.html",{param:result});
            cfpLoadingBar.complete();
        });
    };
});