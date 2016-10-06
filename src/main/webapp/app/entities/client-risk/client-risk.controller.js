(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('ClientRiskController', ClientRiskController);

    ClientRiskController.$inject = ['$scope', '$state', 'ClientRisk'];

    function ClientRiskController ($scope, $state, ClientRisk) {
        var vm = this;
        
        vm.clientRisks = [];

        loadAll();

        function loadAll() {
            ClientRisk.query(function(result) {
                vm.clientRisks = result;
            });
        }
    }
})();
