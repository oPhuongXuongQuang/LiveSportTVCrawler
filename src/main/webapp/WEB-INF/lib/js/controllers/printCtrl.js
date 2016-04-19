/**
 * Created by quangphuong on 4/19/16.
 */
module.controller('PrintController', function($http, $rootScope ,$scope, $service, $sce){
    var page = $rootScope.$nav.getCurrentPage();
    $http.post($service.back.printCalendarAjax.url, page.options.param)

        .success(function(response) {

            $scope.source = $sce.trustAsResourceUrl($service.back.printCalendar.url);
        })
        .error(function() {
            console.log("Error!");
        }); 
});