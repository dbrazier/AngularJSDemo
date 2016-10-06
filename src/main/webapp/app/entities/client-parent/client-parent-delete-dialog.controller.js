(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('ClientParentDeleteController',ClientParentDeleteController);

    ClientParentDeleteController.$inject = ['$uibModalInstance', 'entity', 'ClientParent'];

    function ClientParentDeleteController($uibModalInstance, entity, ClientParent) {
        var vm = this;

        vm.clientParent = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ClientParent.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
