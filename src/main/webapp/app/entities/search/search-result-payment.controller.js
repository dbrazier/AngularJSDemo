(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('SearchResultPaymentController', SearchResultPaymentController);


    SearchResultPaymentController.$inject = ['$uibModalInstance','$scope', '$rootScope', '$stateParams', ];

    function SearchResultPaymentController($uibModalInstance, $scope, $rootScope, $stateParams) {
        var vm = this;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;

        vm.options = {
            maxDate: new Date(),
            showWeeks: false,
            todayHighlight: true
        };

        vm.datePickerOpenStatus.fromDate = false;
        vm.datePickerOpenStatus.toDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }

        function clear () {
            $uibModalInstance.dismiss('close');
        }
    }
})();
