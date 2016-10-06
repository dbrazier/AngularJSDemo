(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('client-parent', {
            parent: 'entity',
            url: '/client-parent',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.clientParent.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/client-parent/client-parents.html',
                    controller: 'ClientParentController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('clientParent');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('client-parent-detail', {
            parent: 'entity',
            url: '/client-parent/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.clientParent.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/client-parent/client-parent-detail.html',
                    controller: 'ClientParentDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('clientParent');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ClientParent', function($stateParams, ClientParent) {
                    return ClientParent.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'client-parent',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('client-parent-detail.edit', {
            parent: 'client-parent-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/client-parent/client-parent-dialog.html',
                    controller: 'ClientParentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ClientParent', function(ClientParent) {
                            return ClientParent.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('client-parent.new', {
            parent: 'client-parent',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/client-parent/client-parent-dialog.html',
                    controller: 'ClientParentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                gfpId: null,
                                gfpName: null,
                                gfcId: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('client-parent', null, { reload: true });
                }, function() {
                    $state.go('client-parent');
                });
            }]
        })
        .state('client-parent.edit', {
            parent: 'client-parent',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/client-parent/client-parent-dialog.html',
                    controller: 'ClientParentDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ClientParent', function(ClientParent) {
                            return ClientParent.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('client-parent', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('client-parent.delete', {
            parent: 'client-parent',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/client-parent/client-parent-delete-dialog.html',
                    controller: 'ClientParentDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ClientParent', function(ClientParent) {
                            return ClientParent.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('client-parent', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
