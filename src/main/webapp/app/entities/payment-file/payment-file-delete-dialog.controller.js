(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('PaymentFileDeleteController',PaymentFileDeleteController);

    PaymentFileDeleteController.$inject = ['$uibModalInstance', 'entity', 'PaymentFile'];

    function PaymentFileDeleteController($uibModalInstance, entity, PaymentFile) {
        var vm = this;

        vm.paymentFile = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PaymentFile.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
