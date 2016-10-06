(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('InquiryTypeController', InquiryTypeController);

    InquiryTypeController.$inject = ['$scope', '$state', 'InquiryType'];

    function InquiryTypeController ($scope, $state, InquiryType) {
        var vm = this;
        
        vm.inquiryTypes = [];

        loadAll();

        function loadAll() {
            InquiryType.query(function(result) {
                vm.inquiryTypes = result;
            });
        }
    }
})();
