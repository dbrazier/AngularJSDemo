(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('ClientParentDialogController', ClientParentDialogController);

    ClientParentDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ClientParent'];

    function ClientParentDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ClientParent) {
        var vm = this;

        vm.clientParent = entity;
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
            if (vm.clientParent.id !== null) {
                ClientParent.update(vm.clientParent, onSaveSuccess, onSaveError);
            } else {
                ClientParent.save(vm.clientParent, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('mosaicApp:clientParentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
