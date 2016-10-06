(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('RateDetailController', RateDetailController);

    RateDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Rate'];

    function RateDetailController($scope, $rootScope, $stateParams, previousState, entity, Rate) {
        var vm = this;

        vm.rate = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('mosaicApp:rateUpdate', function(event, result) {
            vm.rate = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
