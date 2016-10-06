(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('client-account', {
            parent: 'entity',
            url: '/client-account',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.clientAccount.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/client-account/client-accounts.html',
                    controller: 'ClientAccountController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('clientAccount');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('client-account-detail', {
            parent: 'entity',
            url: '/client-account/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.clientAccount.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/client-account/client-account-detail.html',
                    controller: 'ClientAccountDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('clientAccount');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ClientAccount', function($stateParams, ClientAccount) {
                    return ClientAccount.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'client-account',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('client-account-detail.edit', {
            parent: 'client-account-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/client-account/client-account-dialog.html',
                    controller: 'ClientAccountDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ClientAccount', function(ClientAccount) {
                            return ClientAccount.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('client-account.new', {
            parent: 'client-account',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/client-account/client-account-dialog.html',
                    controller: 'ClientAccountDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                accountNo: null,
                                gfcId: null,
                                branch: null,
                                accountName: null,
                                currency: null,
                                balance: null,
                                restriction: null,
                                status: null,
                                iban: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('client-account', null, { reload: true });
                }, function() {
                    $state.go('client-account');
                });
            }]
        })
        .state('client-account.edit', {
            parent: 'client-account',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/client-account/client-account-dialog.html',
                    controller: 'ClientAccountDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ClientAccount', function(ClientAccount) {
                            return ClientAccount.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('client-account', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('client-account.delete', {
            parent: 'client-account',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/client-account/client-account-delete-dialog.html',
                    controller: 'ClientAccountDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ClientAccount', function(ClientAccount) {
                            return ClientAccount.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('client-account', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
