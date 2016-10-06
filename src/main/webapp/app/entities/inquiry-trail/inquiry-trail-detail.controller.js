(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('InquiryTrailDetailController', InquiryTrailDetailController);

    InquiryTrailDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'InquiryTrail'];

    function InquiryTrailDetailController($scope, $rootScope, $stateParams, previousState, entity, InquiryTrail) {
        var vm = this;

        vm.inquiryTrail = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('mosaicApp:inquiryTrailUpdate', function(event, result) {
            vm.inquiryTrail = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
