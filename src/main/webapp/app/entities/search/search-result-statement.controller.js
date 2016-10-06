(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('SearchResultStatementController', SearchResultStatementController);


    SearchResultStatementController.$inject = ['$uibModalInstance','$scope', '$rootScope', '$stateParams', ];

    function SearchResultStatementController($uibModalInstance, $scope, $rootScope, $stateParams) {
        var vm = this;
        vm.clear = clear;

        vm.options = {
            maxDate: new Date(),
            showWeeks: false,
            todayHighlight: true
        };

        function clear () {
            $uibModalInstance.dismiss('close');
        }
    }
})();
