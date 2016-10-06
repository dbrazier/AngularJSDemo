(function() {
    'use strict';
    angular
        .module('mosaicApp')
        .factory('ClientRisk', ClientRisk);

    ClientRisk.$inject = ['$resource'];

    function ClientRisk ($resource) {
        var resourceUrl =  'api/client-risks/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
