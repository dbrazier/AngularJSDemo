(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('InquiryInfoDeleteController',InquiryInfoDeleteController);

    InquiryInfoDeleteController.$inject = ['$uibModalInstance', 'entity', 'InquiryInfo'];

    function InquiryInfoDeleteController($uibModalInstance, entity, InquiryInfo) {
        var vm = this;

        vm.inquiryInfo = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            InquiryInfo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
