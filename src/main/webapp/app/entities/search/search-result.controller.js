(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('SearchResultController', SearchResultController);


    angular.module('mosaicApp').filter('moment', function () {
        return function (input, momentFn /*, param1, param2, ...param n */) {
            var args = Array.prototype.slice.call(arguments, 2),
                momentObj = moment(input);
            return momentObj[momentFn].apply(momentObj, args);
        };
    });


    SearchResultController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', '$window'];

    function SearchResultController($scope, $rootScope, $stateParams, previousState, entity, $window) {
        var vm = this;

        vm.search = entity;
        vm.previousState = previousState.name;
        vm.result = $scope.$parent.result;
        vm.today = new Date();
        vm.openInNewWindow = openInNewWindow;

        $scope.$emit('mosaicApp:searchUpdate', vm.result);

        function openInNewWindow(url){
            $window.open(url,"", "width=640, height=480");
        }

    }

})();
