(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('InquiryTrailDeleteController',InquiryTrailDeleteController);

    InquiryTrailDeleteController.$inject = ['$uibModalInstance', 'entity', 'InquiryTrail'];

    function InquiryTrailDeleteController($uibModalInstance, entity, InquiryTrail) {
        var vm = this;

        vm.inquiryTrail = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            InquiryTrail.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
