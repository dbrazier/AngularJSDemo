(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('client-risk', {
            parent: 'entity',
            url: '/client-risk',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.clientRisk.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/client-risk/client-risks.html',
                    controller: 'ClientRiskController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('clientRisk');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('client-risk-detail', {
            parent: 'entity',
            url: '/client-risk/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.clientRisk.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/client-risk/client-risk-detail.html',
                    controller: 'ClientRiskDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('clientRisk');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ClientRisk', function($stateParams, ClientRisk) {
                    return ClientRisk.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'client-risk',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('client-risk-detail.edit', {
            parent: 'client-risk-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/client-risk/client-risk-dialog.html',
                    controller: 'ClientRiskDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ClientRisk', function(ClientRisk) {
                            return ClientRisk.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('client-risk.new', {
            parent: 'client-risk',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/client-risk/client-risk-dialog.html',
                    controller: 'ClientRiskDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                gfcId: null,
                                listType: null,
                                temperature: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('client-risk', null, { reload: true });
                }, function() {
                    $state.go('client-risk');
                });
            }]
        })
        .state('client-risk.edit', {
            parent: 'client-risk',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/client-risk/client-risk-dialog.html',
                    controller: 'ClientRiskDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ClientRisk', function(ClientRisk) {
                            return ClientRisk.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('client-risk', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('client-risk.delete', {
            parent: 'client-risk',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/client-risk/client-risk-delete-dialog.html',
                    controller: 'ClientRiskDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ClientRisk', function(ClientRisk) {
                            return ClientRisk.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('client-risk', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
