(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('payment-transaction', {
            parent: 'entity',
            url: '/payment-transaction',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.paymentTransaction.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/payment-transaction/payment-transactions.html',
                    controller: 'PaymentTransactionController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('paymentTransaction');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('payment-transaction-detail', {
            parent: 'entity',
            url: '/payment-transaction/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.paymentTransaction.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/payment-transaction/payment-transaction-detail.html',
                    controller: 'PaymentTransactionDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('paymentTransaction');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'PaymentTransaction', function($stateParams, PaymentTransaction) {
                    return PaymentTransaction.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'payment-transaction',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('payment-transaction-detail.edit', {
            parent: 'payment-transaction-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/payment-transaction/payment-transaction-dialog.html',
                    controller: 'PaymentTransactionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PaymentTransaction', function(PaymentTransaction) {
                            return PaymentTransaction.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('payment-transaction.new', {
            parent: 'payment-transaction',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/payment-transaction/payment-transaction-dialog.html',
                    controller: 'PaymentTransactionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                gfcId: null,
                                fileId: null,
                                txnRefId: null,
                                txnId: null,
                                txnStatus: null,
                                currency: null,
                                amount: null,
                                debitAccNo: null,
                                creditAccNo: null,
                                beneficiaryName: null,
                                statusDescription: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('payment-transaction', null, { reload: true });
                }, function() {
                    $state.go('payment-transaction');
                });
            }]
        })
        .state('payment-transaction.edit', {
            parent: 'payment-transaction',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/payment-transaction/payment-transaction-dialog.html',
                    controller: 'PaymentTransactionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PaymentTransaction', function(PaymentTransaction) {
                            return PaymentTransaction.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('payment-transaction', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('payment-transaction.delete', {
            parent: 'payment-transaction',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/payment-transaction/payment-transaction-delete-dialog.html',
                    controller: 'PaymentTransactionDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PaymentTransaction', function(PaymentTransaction) {
                            return PaymentTransaction.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('payment-transaction', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
