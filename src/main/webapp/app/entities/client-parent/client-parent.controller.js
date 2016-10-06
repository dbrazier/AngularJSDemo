(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('ClientParentController', ClientParentController);

    ClientParentController.$inject = ['$scope', '$state', 'ClientParent'];

    function ClientParentController ($scope, $state, ClientParent) {
        var vm = this;
        
        vm.clientParents = [];

        loadAll();

        function loadAll() {
            ClientParent.query(function(result) {
                vm.clientParents = result;
            });
        }
    }
})();
