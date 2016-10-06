(function() {
    'use strict';
    angular
        .module('mosaicApp')
        .factory('PaymentFile', PaymentFile);

    PaymentFile.$inject = ['$resource', 'DateUtils'];

    function PaymentFile ($resource, DateUtils) {
        var resourceUrl =  'api/payment-files/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.fileStart = DateUtils.convertDateTimeFromServer(data.fileStart);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
