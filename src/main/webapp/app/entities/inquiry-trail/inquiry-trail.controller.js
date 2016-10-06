(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('InquiryTrailController', InquiryTrailController);

    InquiryTrailController.$inject = ['$scope', '$state', 'InquiryTrail'];

    function InquiryTrailController ($scope, $state, InquiryTrail) {
        var vm = this;
        
        vm.inquiryTrails = [];

        loadAll();

        function loadAll() {
            InquiryTrail.query(function(result) {
                vm.inquiryTrails = result;
            });
        }
    }
})();
