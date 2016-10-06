(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('ClientRiskDetailController', ClientRiskDetailController);

    ClientRiskDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ClientRisk'];

    function ClientRiskDetailController($scope, $rootScope, $stateParams, previousState, entity, ClientRisk) {
        var vm = this;

        vm.clientRisk = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('mosaicApp:clientRiskUpdate', function(event, result) {
            vm.clientRisk = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
