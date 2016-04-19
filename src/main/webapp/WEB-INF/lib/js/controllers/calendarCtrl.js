/**
 * Created by Quang on 17-Apr-16.
 */
module.controller('CalendarController', function($http, $rootScope ,$scope, $service, cfpLoadingBar, $mdDialog){
    $scope.querySearch = function(value) {
        return $service.querySearch(value);
    };

    $scope.tabs = [
        {name: "English Premier League", id: "england", content: ""},
        {name: "Spanish Segunda Division", id: "spain", content: ""},
        {name: "Italian Serie A", id: "italy", content: ""},
        {name: "Dutch Eredivisie", id: "dutch", content: ""},
        {name: "German Bundesliga", id: "german", content: ""},
        {name: "French Ligue 1", id: "france", content: ""},
        {name: "Brazilian Serie A", id: "brazil", content: ""}
    ];

    $scope.loadCalendar = function(tab){
        $http.post($service.back.loadCalendar.url, tab.id)

            .success(function(response) {
                console.log(response);

                tab.content = response;
                $scope.currentTab = tab;
            })
            .error(function() {
                console.log("Error!");
            });
    };

    $scope.goToHighlight = function(link) {
        cfpLoadingBar.start();
        if(link.indexOf("/en/showvideo") > -1) {
            //$http.post($service.back.getVideo.url, link).success(function (result) {
            //
            //
            //});
        } else if(link.indexOf("playwire.com") > -1) {

        }
        else {
            $rootScope.$nav.pushPage("video.html",{param:link});
        }
    };

    $scope.printCalendar = function() {
        var confirm = $mdDialog.confirm()
            .title('Do you want to print?')
            .textContent('Print? Nice choice!')
            .ok('Print')
            .cancel('Cancel');
        $mdDialog.show(confirm).then(function() {
            $rootScope.$nav.pushPage("printPage.html",{param:$scope.currentTab.id});
            
        });
    };
});