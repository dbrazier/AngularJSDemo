(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('InquiryTypeDeleteController',InquiryTypeDeleteController);

    InquiryTypeDeleteController.$inject = ['$uibModalInstance', 'entity', 'InquiryType'];

    function InquiryTypeDeleteController($uibModalInstance, entity, InquiryType) {
        var vm = this;

        vm.inquiryType = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            InquiryType.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
