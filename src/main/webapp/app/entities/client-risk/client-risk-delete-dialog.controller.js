(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('ClientRiskDeleteController',ClientRiskDeleteController);

    ClientRiskDeleteController.$inject = ['$uibModalInstance', 'entity', 'ClientRisk'];

    function ClientRiskDeleteController($uibModalInstance, entity, ClientRisk) {
        var vm = this;

        vm.clientRisk = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ClientRisk.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
