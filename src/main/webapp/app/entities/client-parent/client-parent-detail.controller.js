(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('ClientParentDetailController', ClientParentDetailController);

    ClientParentDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ClientParent'];

    function ClientParentDetailController($scope, $rootScope, $stateParams, previousState, entity, ClientParent) {
        var vm = this;

        vm.clientParent = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('mosaicApp:clientParentUpdate', function(event, result) {
            vm.clientParent = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
