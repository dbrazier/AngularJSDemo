(function() {
    'use strict';
    angular
        .module('mosaicApp')
        .factory('InquiryInfo', InquiryInfo);

    InquiryInfo.$inject = ['$resource', 'DateUtils'];

    function InquiryInfo ($resource, DateUtils) {
        var resourceUrl =  'api/inquiry-infos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.lastInteraction = DateUtils.convertDateTimeFromServer(data.lastInteraction);
                        data.caseStartDate = DateUtils.convertDateTimeFromServer(data.caseStartDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
