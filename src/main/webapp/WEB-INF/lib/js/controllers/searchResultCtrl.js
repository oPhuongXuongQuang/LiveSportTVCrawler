/**
 * Created by Quang on 17-Apr-16.
 */
module.controller('SearchResultController',function($rootScope, $scope, $http, $service) {

    $scope.search = function (value) {
        $http.post($service.back.advanceSearch.url, value)
            .success(function (result) {
                var videosByKind = _.groupBy(result, 'kind');
                var videos = {};
                for (var kind in videosByKind) {
                    var videosByTour = _.groupBy(videosByKind[kind], 'tournament');
                    videos[kind] = videosByTour;
                }
                $scope.videos = videos;
            })
            .error(function () {
                console.log("Error!");
            });
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