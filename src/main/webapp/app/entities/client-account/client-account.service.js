(function() {
    'use strict';
    angular
        .module('mosaicApp')
        .factory('ClientAccount', ClientAccount);

    ClientAccount.$inject = ['$resource'];

    function ClientAccount ($resource) {
        var resourceUrl =  'api/client-accounts/:id';

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
