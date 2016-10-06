(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('PaymentTransactionController', PaymentTransactionController);

    PaymentTransactionController.$inject = ['$scope', '$state', 'PaymentTransaction'];

    function PaymentTransactionController ($scope, $state, PaymentTransaction) {
        var vm = this;
        
        vm.paymentTransactions = [];

        loadAll();

        function loadAll() {
            PaymentTransaction.query(function(result) {
                vm.paymentTransactions = result;
            });
        }
    }
})();
