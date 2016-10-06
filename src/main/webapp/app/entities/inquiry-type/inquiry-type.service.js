(function() {
    'use strict';
    angular
        .module('mosaicApp')
        .factory('InquiryType', InquiryType);

    InquiryType.$inject = ['$resource'];

    function InquiryType ($resource) {
        var resourceUrl =  'api/inquiry-types/:id';

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
