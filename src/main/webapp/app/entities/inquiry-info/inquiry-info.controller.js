(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('InquiryInfoController', InquiryInfoController);

    InquiryInfoController.$inject = ['$scope', '$state', 'InquiryInfo'];

    function InquiryInfoController ($scope, $state, InquiryInfo) {
        var vm = this;
        
        vm.inquiryInfos = [];

        loadAll();

        function loadAll() {
            InquiryInfo.query(function(result) {
                vm.inquiryInfos = result;
            });
        }
    }
})();
