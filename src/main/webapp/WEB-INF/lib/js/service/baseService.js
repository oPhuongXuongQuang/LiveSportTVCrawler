/**
 * Created by Quang on 09-Apr-16.
 */
module.service('$service', function ($http, $q, $timeout) {
    this.isReady = false;
    this.querySearch = function(value) {
        return $http.post(this.back.globalSearch.url, value)

            .then(function(response) {
                console.log(response);

                var deferred = $q.defer();
                $timeout(function () { deferred.resolve(response.data); }, 0, false);
                return deferred.promise;
            });
    };

    var ConfigService = function () {
        this.back.url = this.url + 'LiveSportTVCrawler';
        function fillUrl(obj, root) {
            for (var i in obj) {
                var item = obj[i];
                if (item instanceof Object) {
                    item.path && (item.url = root + item.path);
                    fillUrl.call(this, item, root);
                }
            }
        }

        fillUrl.call(this, this.back, this.back.url);
    };

    ConfigService.prototype = {
        url: "http://192.168.1.102:8080/",
        back: {
            comingUp: {
                path: "/comingup.htm"
            },
            eventDetail: {
                path: "/getEventDetail.htm"
            },
            getVideo: {
                path: "/getVideo.htm"
            },
            getHighlights: {
                path: "/getHighlights.htm"
            },
            globalSearch: {
                path: "/globalSearch.htm"
            },
            advanceSearch: {
                path: "/advanceSearch.htm"
            },
            loadCalendar: {
                path: "/loadCalendar.htm"
            }
        }
    };

    var config = new ConfigService();
    var service = angular.extend(config, this);

    return service;
});
