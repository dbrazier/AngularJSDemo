(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('PaymentTransactionDialogController', PaymentTransactionDialogController);

    PaymentTransactionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PaymentTransaction'];

    function PaymentTransactionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PaymentTransaction) {
        var vm = this;

        vm.paymentTransaction = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.paymentTransaction.id !== null) {
                PaymentTransaction.update(vm.paymentTransaction, onSaveSuccess, onSaveError);
            } else {
                PaymentTransaction.save(vm.paymentTransaction, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('mosaicApp:paymentTransactionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
