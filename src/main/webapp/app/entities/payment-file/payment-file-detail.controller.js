(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('PaymentFileDetailController', PaymentFileDetailController);

    PaymentFileDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PaymentFile'];

    function PaymentFileDetailController($scope, $rootScope, $stateParams, previousState, entity, PaymentFile) {
        var vm = this;

        vm.paymentFile = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('mosaicApp:paymentFileUpdate', function(event, result) {
            vm.paymentFile = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
