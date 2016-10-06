(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('InquiryTypeDialogController', InquiryTypeDialogController);

    InquiryTypeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'InquiryType'];

    function InquiryTypeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, InquiryType) {
        var vm = this;

        vm.inquiryType = entity;
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
            if (vm.inquiryType.id !== null) {
                InquiryType.update(vm.inquiryType, onSaveSuccess, onSaveError);
            } else {
                InquiryType.save(vm.inquiryType, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('mosaicApp:inquiryTypeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
