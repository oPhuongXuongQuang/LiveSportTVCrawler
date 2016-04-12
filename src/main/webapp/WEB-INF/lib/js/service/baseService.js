/**
 * Created by Quang on 09-Apr-16.
 */
module.service('$service', function ($http, $q) {
    this.isReady = false;
    this._d = $q.defer();
    this._canceller = $q.defer();
    /** CORE DEFFER **/
    //this.waitForCoreStart = function () {
    //    if (this._d.promise.$$state.status) this._d = $q.defer();
    //    if (this.isReady)
    //        this._d.resolve(this.networkList);
    //    return this._d.promise;
    //};

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
        url: "http://192.168.1.104:8080/",
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
            }
        }
    };

    var config = new ConfigService();
    var service = angular.extend(config, this);

    return service;
});
