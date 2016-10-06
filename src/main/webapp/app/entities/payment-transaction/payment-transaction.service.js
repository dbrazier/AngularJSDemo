(function() {
    'use strict';
    angular
        .module('mosaicApp')
        .factory('PaymentTransaction', PaymentTransaction);

    PaymentTransaction.$inject = ['$resource'];

    function PaymentTransaction ($resource) {
        var resourceUrl =  'api/payment-transactions/:id';

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
