(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('ClientAccountDeleteController',ClientAccountDeleteController);

    ClientAccountDeleteController.$inject = ['$uibModalInstance', 'entity', 'ClientAccount'];

    function ClientAccountDeleteController($uibModalInstance, entity, ClientAccount) {
        var vm = this;

        vm.clientAccount = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ClientAccount.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
