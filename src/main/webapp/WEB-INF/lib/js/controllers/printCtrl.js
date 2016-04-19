/**
 * Created by quangphuong on 4/19/16.
 */
module.controller('PrintController', function($http, $rootScope ,$scope, $service, $sce){
    var page = $rootScope.$nav.getCurrentPage();
    $http.post($service.back.printCalendar.url, page.options.param)

        .success(function(response) {

            $scope.source = $sce.trustAsResourceUrl("data:application/pdf;base64," + response);
        })
        .error(function() {
            console.log("Error!");
        }); 
});