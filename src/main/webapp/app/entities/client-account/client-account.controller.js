(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('ClientAccountController', ClientAccountController);

    ClientAccountController.$inject = ['$scope', '$state', 'ClientAccount'];

    function ClientAccountController ($scope, $state, ClientAccount) {
        var vm = this;
        
        vm.clientAccounts = [];

        loadAll();

        function loadAll() {
            ClientAccount.query(function(result) {
                vm.clientAccounts = result;
            });
        }
    }
})();
