(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('payment-file', {
            parent: 'entity',
            url: '/payment-file',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.paymentFile.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/payment-file/payment-files.html',
                    controller: 'PaymentFileController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('paymentFile');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('payment-file-detail', {
            parent: 'entity',
            url: '/payment-file/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.paymentFile.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/payment-file/payment-file-detail.html',
                    controller: 'PaymentFileDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('paymentFile');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'PaymentFile', function($stateParams, PaymentFile) {
                    return PaymentFile.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'payment-file',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('payment-file-detail.edit', {
            parent: 'payment-file-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/payment-file/payment-file-dialog.html',
                    controller: 'PaymentFileDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PaymentFile', function(PaymentFile) {
                            return PaymentFile.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('payment-file.new', {
            parent: 'payment-file',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/payment-file/payment-file-dialog.html',
                    controller: 'PaymentFileDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                gfcId: null,
                                fileId: null,
                                profileId: null,
                                fileName: null,
                                fileStart: null,
                                gksEnd: null,
                                lastStep: null,
                                noTxns: null,
                                failedTxns: null,
                                fileStatus: null,
                                fileProfile: null,
                                sourceSystem: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('payment-file', null, { reload: true });
                }, function() {
                    $state.go('payment-file');
                });
            }]
        })
        .state('payment-file.edit', {
            parent: 'payment-file',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/payment-file/payment-file-dialog.html',
                    controller: 'PaymentFileDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PaymentFile', function(PaymentFile) {
                            return PaymentFile.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('payment-file', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('payment-file.delete', {
            parent: 'payment-file',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/payment-file/payment-file-delete-dialog.html',
                    controller: 'PaymentFileDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PaymentFile', function(PaymentFile) {
                            return PaymentFile.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('payment-file', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
