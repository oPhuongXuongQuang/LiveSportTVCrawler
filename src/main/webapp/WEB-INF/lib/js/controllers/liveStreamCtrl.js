/**
 * Created by quangphuong on 4/10/16.
 */
module.controller('LiveStreamController', function($http, $sce, $rootScope ,$scope, $service, cfpLoadingBar){
    var page = $rootScope.$nav.getCurrentPage();
    $scope.videoObj = $sce.trustAsHtml(page.options.param);
});