(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('InquiryTrailDialogController', InquiryTrailDialogController);

    InquiryTrailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'InquiryTrail'];

    function InquiryTrailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, InquiryTrail) {
        var vm = this;

        vm.inquiryTrail = entity;
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
            if (vm.inquiryTrail.id !== null) {
                InquiryTrail.update(vm.inquiryTrail, onSaveSuccess, onSaveError);
            } else {
                InquiryTrail.save(vm.inquiryTrail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('mosaicApp:inquiryTrailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.inquiryDate = false;
        vm.datePickerOpenStatus.responseDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
