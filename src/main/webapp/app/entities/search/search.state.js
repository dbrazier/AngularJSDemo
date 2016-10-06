(function() {
    'use strict';
    angular
        .module('mosaicApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('search', {
            parent: 'entity',
            url: '/search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.search.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/search/searches.html',
                    controller: 'SearchController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('search');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }],
                entity: function () {
                    return {
                        accountNo: null,
                        caseId: null,
                        id: null
                    };
                }
            }
        })
        .state('search-result', {
            parent: 'search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.search.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/search/search-result.html',
                    controller: 'SearchResultController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('search');
                    return $translate.refresh();
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'search',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('statement-select', {
            parent: 'search-result',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/search/search-result-statement.html',
                    controller: 'SearchResultStatementController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        previousState: ["$state", function ($state) {
                            var currentStateData = {
                                name: $state.current.name || 'search-result',
                                params: $state.params,
                                url: $state.href($state.current.name, $state.params)
                            };
                            return currentStateData;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('payment-search', {
            parent: 'search-result',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/search/search-result-payment.html',
                    controller: 'SearchResultPaymentController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        previousState: ["$state", function ($state) {
                            var currentStateData = {
                                name: $state.current.name || 'search-result',
                                params: $state.params,
                                url: $state.href($state.current.name, $state.params)
                            };
                            return currentStateData;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('rate-history', {
            parent: 'search-result',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/search/search-result-rate.html',
                    controller: 'SearchResultRateController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'md',
                    resolve: {
                        previousState: ["$state", function ($state) {
                            var currentStateData = {
                                name: $state.current.name || 'search-result',
                                params: $state.params,
                                url: $state.href($state.current.name, $state.params)
                            };
                            return currentStateData;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
