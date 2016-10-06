(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('InquiryInfoDetailController', InquiryInfoDetailController);

    InquiryInfoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'InquiryInfo'];

    function InquiryInfoDetailController($scope, $rootScope, $stateParams, previousState, entity, InquiryInfo) {
        var vm = this;

        vm.inquiryInfo = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('mosaicApp:inquiryInfoUpdate', function(event, result) {
            vm.inquiryInfo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
