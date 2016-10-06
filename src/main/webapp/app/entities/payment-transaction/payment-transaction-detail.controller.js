(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('PaymentTransactionDetailController', PaymentTransactionDetailController);

    PaymentTransactionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PaymentTransaction'];

    function PaymentTransactionDetailController($scope, $rootScope, $stateParams, previousState, entity, PaymentTransaction) {
        var vm = this;

        vm.paymentTransaction = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('mosaicApp:paymentTransactionUpdate', function(event, result) {
            vm.paymentTransaction = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
