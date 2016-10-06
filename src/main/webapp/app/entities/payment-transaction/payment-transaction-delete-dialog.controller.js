(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('PaymentTransactionDeleteController',PaymentTransactionDeleteController);

    PaymentTransactionDeleteController.$inject = ['$uibModalInstance', 'entity', 'PaymentTransaction'];

    function PaymentTransactionDeleteController($uibModalInstance, entity, PaymentTransaction) {
        var vm = this;

        vm.paymentTransaction = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PaymentTransaction.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
