/**
 * Created by quangpx1 on 4/19/16.
 */
module.controller('VideoSuggestController', function($rootScope, $scope, $sce, $timeout, $http, $service, cfpLoadingBar) {
    var page = $rootScope.$nav.getCurrentPage();
    $scope.link = $sce.trustAsResourceUrl(page.options.param.highlightLink);

    $http.post($service.back.getSuggestVideos.url, page.options.param2).success(function(result){
        $scope.videos = result;
    });

    $http.post($service.back.updateSeen.url, page.options.param).success(function(result){
        console.log("success");
    });
});