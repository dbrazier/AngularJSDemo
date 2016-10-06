(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('ClientAccountDetailController', ClientAccountDetailController);

    ClientAccountDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ClientAccount'];

    function ClientAccountDetailController($scope, $rootScope, $stateParams, previousState, entity, ClientAccount) {
        var vm = this;

        vm.clientAccount = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('mosaicApp:clientAccountUpdate', function(event, result) {
            vm.clientAccount = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
