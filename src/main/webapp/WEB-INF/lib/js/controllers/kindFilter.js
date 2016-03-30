angular.module('kindFilter', []).
filter('byKind', function () {
    return function (Events, kindfilters) {
        var items = {
            kinds: kindfilters,
            out: []
        };
        angular.forEach(Events, function (value, key) {
            if (this.kinds[value.kind] === true) {
                this.out.push(value);
            }
        }, items);
        return items.out;
    };
});