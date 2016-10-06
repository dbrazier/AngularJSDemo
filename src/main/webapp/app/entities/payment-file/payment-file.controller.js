(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('PaymentFileController', PaymentFileController);

    PaymentFileController.$inject = ['$scope', '$state', 'PaymentFile'];

    function PaymentFileController ($scope, $state, PaymentFile) {
        var vm = this;
        
        vm.paymentFiles = [];

        loadAll();

        function loadAll() {
            PaymentFile.query(function(result) {
                vm.paymentFiles = result;
            });
        }
    }
})();
