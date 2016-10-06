(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('ClientAccountDialogController', ClientAccountDialogController);

    ClientAccountDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ClientAccount'];

    function ClientAccountDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ClientAccount) {
        var vm = this;

        vm.clientAccount = entity;
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
            if (vm.clientAccount.id !== null) {
                ClientAccount.update(vm.clientAccount, onSaveSuccess, onSaveError);
            } else {
                ClientAccount.save(vm.clientAccount, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('mosaicApp:clientAccountUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
