(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .controller('SearchController', SearchController);

    SearchController.$inject = ['$scope', '$rootScope', '$state', 'entity', 'Search'];

    function SearchController ($scope, $rootScope, $state, entity, Search) {
        var vm = this;
        vm.search = entity;
        vm.performSearch = performSearch;
        vm.searches = [];

        function performSearch() {
            vm.isSearching = true;
            Search.save(vm.search, onSearchSuccess, onSearchError);
        }

        function onSearchSuccess (result) {
            $rootScope.result = result;
            vm.isSearching = false;
            $state.go("search-result");
        }

        function onSearchError () {
            vm.isSearching = false;
        }

        var unsubscribe = $rootScope.$on('mosaicApp:searchUpdate', function(event, result) {
            vm.search = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
