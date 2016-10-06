(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('PaymentFileDialogController', PaymentFileDialogController);

    PaymentFileDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PaymentFile'];

    function PaymentFileDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PaymentFile) {
        var vm = this;

        vm.paymentFile = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.paymentFile.id !== null) {
                PaymentFile.update(vm.paymentFile, onSaveSuccess, onSaveError);
            } else {
                PaymentFile.save(vm.paymentFile, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('mosaicApp:paymentFileUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.fileStart = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }

    }
})();
