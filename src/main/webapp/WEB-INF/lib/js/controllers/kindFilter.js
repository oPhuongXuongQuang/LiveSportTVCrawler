angular.module('kindFilter', ['app']).
filter('byKind', function () {
    return function (Events, kindfilters) {
        var result = {};
        angular.forEach(Events, function (value, key) {
            if (kindfilters.indexOf(key) !== -1) {
                result[key] = value;
            }
        });
        return result;
    };
}); 