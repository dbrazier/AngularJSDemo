(function() {
    'use strict';

    angular
        .module('mosaicApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('inquiry-trail', {
            parent: 'entity',
            url: '/inquiry-trail',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.inquiryTrail.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/inquiry-trail/inquiry-trails.html',
                    controller: 'InquiryTrailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('inquiryTrail');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('inquiry-trail-detail', {
            parent: 'entity',
            url: '/inquiry-trail/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mosaicApp.inquiryTrail.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/inquiry-trail/inquiry-trail-detail.html',
                    controller: 'InquiryTrailDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('inquiryTrail');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'InquiryTrail', function($stateParams, InquiryTrail) {
                    return InquiryTrail.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'inquiry-trail',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('inquiry-trail-detail.edit', {
            parent: 'inquiry-trail-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inquiry-trail/inquiry-trail-dialog.html',
                    controller: 'InquiryTrailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['InquiryTrail', function(InquiryTrail) {
                            return InquiryTrail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('inquiry-trail.new', {
            parent: 'inquiry-trail',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inquiry-trail/inquiry-trail-dialog.html',
                    controller: 'InquiryTrailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                caseId: null,
                                csoName: null,
                                csoEmail: null,
                                clientName: null,
                                clientEmail: null,
                                clientContactNo: null,
                                clientContactType: null,
                                inquiryDate: null,
                                responseDate: null,
                                notes: null,
                                csoContactType: null,
                                attachment: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('inquiry-trail', null, { reload: true });
                }, function() {
                    $state.go('inquiry-trail');
                });
            }]
        })
        .state('inquiry-trail.edit', {
            parent: 'inquiry-trail',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inquiry-trail/inquiry-trail-dialog.html',
                    controller: 'InquiryTrailDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['InquiryTrail', function(InquiryTrail) {
                            return InquiryTrail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('inquiry-trail', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('inquiry-trail.delete', {
            parent: 'inquiry-trail',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/inquiry-trail/inquiry-trail-delete-dialog.html',
                    controller: 'InquiryTrailDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['InquiryTrail', function(InquiryTrail) {
                            return InquiryTrail.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('inquiry-trail', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
