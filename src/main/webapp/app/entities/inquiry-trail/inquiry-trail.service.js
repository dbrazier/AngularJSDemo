(function() {
    'use strict';
    angular
        .module('mosaicApp')
        .factory('InquiryTrail', InquiryTrail);

    InquiryTrail.$inject = ['$resource', 'DateUtils'];

    function InquiryTrail ($resource, DateUtils) {
        var resourceUrl =  'api/inquiry-trails/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.inquiryDate = DateUtils.convertDateTimeFromServer(data.inquiryDate);
                        data.responseDate = DateUtils.convertDateTimeFromServer(data.responseDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
