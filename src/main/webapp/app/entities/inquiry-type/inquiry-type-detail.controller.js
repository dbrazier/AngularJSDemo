(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('InquiryTypeDetailController', InquiryTypeDetailController);

    InquiryTypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'InquiryType'];

    function InquiryTypeDetailController($scope, $rootScope, $stateParams, previousState, entity, InquiryType) {
        var vm = this;

        vm.inquiryType = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('mosaicApp:inquiryTypeUpdate', function(event, result) {
            vm.inquiryType = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
