/**
 * Created by Quang on 18-Apr-16.
 */
module.controller('VideoController', function($rootScope, $scope, $sce, $http, $service, cfpLoadingBar) {
    var page = $rootScope.$nav.getCurrentPage();
    $scope.link = $sce.trustAsResourceUrl(page.options.param);
});