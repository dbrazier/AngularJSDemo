(function() {
    'use strict';
    angular
        .module('mosaicApp')
        .factory('ClientParent', ClientParent);

    ClientParent.$inject = ['$resource'];

    function ClientParent ($resource) {
        var resourceUrl =  'api/client-parents/:id';

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
