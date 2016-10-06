(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('ClientRiskDialogController', ClientRiskDialogController);

    ClientRiskDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ClientRisk'];

    function ClientRiskDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ClientRisk) {
        var vm = this;

        vm.clientRisk = entity;
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
            if (vm.clientRisk.id !== null) {
                ClientRisk.update(vm.clientRisk, onSaveSuccess, onSaveError);
            } else {
                ClientRisk.save(vm.clientRisk, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('mosaicApp:clientRiskUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
