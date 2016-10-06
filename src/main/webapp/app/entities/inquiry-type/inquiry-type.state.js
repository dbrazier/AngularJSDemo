(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('inquiry-type', {
            parent: 'entity',
            url: '/inquiry-type',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.inquiryType.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/inquiry-type/inquiry-types.html',
                    controller: 'InquiryTypeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('inquiryType');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('inquiry-type-detail', {
            parent: 'entity',
            url: '/inquiry-type/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.inquiryType.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/inquiry-type/inquiry-type-detail.html',
                    controller: 'InquiryTypeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('inquiryType');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'InquiryType', function($stateParams, InquiryType) {
                    return InquiryType.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'inquiry-type',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('inquiry-type-detail.edit', {
            parent: 'inquiry-type-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inquiry-type/inquiry-type-dialog.html',
                    controller: 'InquiryTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['InquiryType', function(InquiryType) {
                            return InquiryType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('inquiry-type.new', {
            parent: 'inquiry-type',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inquiry-type/inquiry-type-dialog.html',
                    controller: 'InquiryTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                inquiryType: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('inquiry-type', null, { reload: true });
                }, function() {
                    $state.go('inquiry-type');
                });
            }]
        })
        .state('inquiry-type.edit', {
            parent: 'inquiry-type',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inquiry-type/inquiry-type-dialog.html',
                    controller: 'InquiryTypeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['InquiryType', function(InquiryType) {
                            return InquiryType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('inquiry-type', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('inquiry-type.delete', {
            parent: 'inquiry-type',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inquiry-type/inquiry-type-delete-dialog.html',
                    controller: 'InquiryTypeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['InquiryType', function(InquiryType) {
                            return InquiryType.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('inquiry-type', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
