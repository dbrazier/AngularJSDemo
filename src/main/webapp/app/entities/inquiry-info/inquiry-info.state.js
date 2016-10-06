(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('inquiry-info', {
            parent: 'entity',
            url: '/inquiry-info',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.inquiryInfo.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/inquiry-info/inquiry-infos.html',
                    controller: 'InquiryInfoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('inquiryInfo');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('inquiry-info-detail', {
            parent: 'entity',
            url: '/inquiry-info/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.inquiryInfo.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/inquiry-info/inquiry-info-detail.html',
                    controller: 'InquiryInfoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('inquiryInfo');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'InquiryInfo', function($stateParams, InquiryInfo) {
                    return InquiryInfo.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'inquiry-info',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('inquiry-info-detail.edit', {
            parent: 'inquiry-info-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inquiry-info/inquiry-info-dialog.html',
                    controller: 'InquiryInfoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['InquiryInfo', function(InquiryInfo) {
                            return InquiryInfo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('inquiry-info.new', {
            parent: 'inquiry-info',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inquiry-info/inquiry-info-dialog.html',
                    controller: 'InquiryInfoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                caseId: null,
                                gfcId: null,
                                inquiryTypeId: null,
                                caseOwner: null,
                                status: null,
                                lastInteraction: null,
                                sla: null,
                                caseStartDate: null,
                                urgency: null,
                                notes: null,
                                accountNo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('inquiry-info', null, { reload: true });
                }, function() {
                    $state.go('inquiry-info');
                });
            }]
        })
        .state('inquiry-info.edit', {
            parent: 'inquiry-info',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inquiry-info/inquiry-info-dialog.html',
                    controller: 'InquiryInfoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['InquiryInfo', function(InquiryInfo) {
                            return InquiryInfo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('inquiry-info', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('inquiry-info.delete', {
            parent: 'inquiry-info',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inquiry-info/inquiry-info-delete-dialog.html',
                    controller: 'InquiryInfoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['InquiryInfo', function(InquiryInfo) {
                            return InquiryInfo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('inquiry-info', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
