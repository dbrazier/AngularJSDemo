(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('SearchResultRateController', SearchResultRateController);


    SearchResultRateController.$inject = ['$uibModalInstance','$scope', '$rootScope', '$stateParams', ];

    function SearchResultRateController($uibModalInstance, $scope, $rootScope, $stateParams) {
        var vm = this;

        vm.result = $scope.$parent.result;
        vm.rateIndex = 0;
        vm.rateBalance = vm.result.clientAccount.balance;
        vm.clear = clear;
        vm.setDate = setDate;

        today();
        tomorrow();
        yesterday();

        vm.options = {
            maxDate: new Date(),
            showWeeks: false,
            todayHighlight: true
        };

        function today() {
            vm.today = new Date();
            vm.dt = vm.today;
        }

        function tomorrow() {
            var tomorrow = new Date();
            tomorrow.setDate(tomorrow.getDate() + 1);
            vm.tomorrow = tomorrow;
        }

        function yesterday() {
            var yesterday = new Date();
            yesterday.setDate(yesterday.getDate() - 1);
            vm.yesterday = yesterday;
        }


        function setDate() {
            vm.tomorrow.setTime(vm.dt.getTime() + 1 * 86400000 );
            vm.yesterday.setTime(vm.dt.getTime() - 1 * 86400000 );
            vm.rateBalance = vm.rateBalance + getRandomInt(500,1000);
            vm.rateIndex = days_between(vm.today, vm.dt);
        }

        function days_between(date1, date2) {

            // The number of milliseconds in one day
            var ONE_DAY = 1000 * 60 * 60 * 24;

            // Convert both dates to milliseconds
            var date1_ms = date1.getTime();
            var date2_ms = date2.getTime();

            // Calculate the difference in milliseconds
            var difference_ms = Math.abs(date1_ms - date2_ms);

            // Convert back to days and return
            return Math.round(difference_ms/ONE_DAY);

        }


        function getRandomInt(min, max) {
            return Math.floor(Math.random() * (max - min + 1)) + min;
        }

        function clear () {
            $uibModalInstance.dismiss('close');
        }
    }
})();
