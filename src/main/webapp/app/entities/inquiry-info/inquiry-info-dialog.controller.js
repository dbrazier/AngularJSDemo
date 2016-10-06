(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('InquiryInfoDialogController', InquiryInfoDialogController);

    InquiryInfoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'InquiryInfo'];

    function InquiryInfoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, InquiryInfo) {
        var vm = this;

        vm.inquiryInfo = entity;
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
            if (vm.inquiryInfo.id !== null) {
                InquiryInfo.update(vm.inquiryInfo, onSaveSuccess, onSaveError);
            } else {
                InquiryInfo.save(vm.inquiryInfo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('mosaicApp:inquiryInfoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.lastInteraction = false;
        vm.datePickerOpenStatus.caseStartDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
