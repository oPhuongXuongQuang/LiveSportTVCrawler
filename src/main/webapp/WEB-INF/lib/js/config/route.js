/**
 * Created by Quang on 09-Apr-16.
 */
module.config(['$routeProvider',
    function($routeProvider) {
        var prefix = "http://localhost:8080/LiveSportTVCrawler/";
        $routeProvider.
        when('home.html', {
            controller: 'HomeController',
            templateUrl: 'home.html'
        });

    }]
);