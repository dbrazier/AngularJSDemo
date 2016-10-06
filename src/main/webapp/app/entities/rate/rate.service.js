(function() {
    'use strict';
    angular
        .module('mosaicApp')
        .factory('Rate', Rate);

    Rate.$inject = ['$resource', 'DateUtils'];

    function Rate ($resource, DateUtils) {
        var resourceUrl =  'api/rates/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.rateDate = DateUtils.convertDateTimeFromServer(data.rateDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
