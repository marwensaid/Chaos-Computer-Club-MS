/**
 * Created by marwen on 28/12/15.
 */

var cccBlog = angular.module('cccBlog', ['ui.router', 'ngResource', 'ngCookies', 'ngSanitize', 'ui-notification']);

cccBlog.factory('responseObserver',
    function responseObserver($q, $window, $rootScope) {

        return {
            request: function (config) {
                return config || $q.when(config);
            },
            requestError: function (request) {
                return $q.reject(request);
            },
            response: function (response) {
                return response || $q.when(response);
            },
            responseError: function (response) {
                if (response && response.status === 412) {
                    //noinspection JSDuplicatedDeclaration
                    var message = {type: 'error', 'msg': 'Problem in processing your request.'};
                    $rootScope.$emit('NotificationEvent', message);
                    $rootScope.logout();
                }
                if (response && response.status === 401) {
                    //noinspection JSDuplicatedDeclaration
                    var message = {type: 'error', 'msg': 'Invalid Login Credentials or Session Expired.'};
                    $rootScope.$emit('NotificationEvent', message);
                    $rootScope.logout();
                }
                return $q.reject(response);
            }
        };

    });

cccBlog.config(function ($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider) {
        $httpProvider.interceptors.push('responseObserver');

        $stateProvider
            .state('login', {
                url: '/login',
                templateUrl: 'views/login.html',
                controller: 'LoginController',
                access: 'public'
            })
            .state('home', {
                url: '/home',
                templateUrl: 'views/home.html',
                controller: 'HomeController',
                access: 'public'
            })
            .state('newpost', {
                url: '/newpost',
                templateUrl: 'views/newpost.html',
                controller: 'NewPostController'
            })
            .state('viewpost', {
                url: '/posts/:postId',
                templateUrl: 'views/post.html',
                controller: 'PostController',
                access: 'public'
            })
            .state('admin', {
                url: '/admin',
                templateUrl: 'views/admin.html',
                controller: 'AdminController'
            })
            .state('bo.posts', {
                url: '/posts',
                templateUrl: 'views/bo/posts.html',
                controller: 'AdminPostsController'
            })
            .state('bo.comments', {
                url: '/comments',
                templateUrl: 'views/bo/comments.html',
                controller: 'AdminCommentsController'
            })
            .state('bo.tags', {
                url: '/tags',
                templateUrl: 'views/bo/tags.html',
                controller: 'AdminTagsController'
            })
            .state('bo.emailSubscribers', {
                url: '/emailSubscribers',
                templateUrl: 'views/bo/emailSubscribers.html',
                controller: 'AdminEmailSubscribersController'
            })
            .state('bo.settings', {
                url: '/settings',
                templateUrl: 'views/bo/settings.html',
                controller: 'AdminController'
            })
            .state('bo.statistics', {
                url: '/statistics',
                templateUrl: 'views/bo/statistics.html',
                controller: 'AdminController'
            })
        ;

        $urlRouterProvider.otherwise('/home');
    })

    .run(['$rootScope', '$state', '$timeout', 'UserService', 'UtilService',
        function ($rootScope, $state, $timeout, UserService, UtilService) {

            $rootScope.$on('$stateChangeStart', function (evt, toState, toParams, fromState, fromParams) {
                var access = toState.access;
                if (access != 'public') {
                    if (UserService.isUserLoggedIn()) {
                        $rootScope.currentNavLink = toState.name;
                    } else {
                        evt.preventDefault();
                        console.log('redirect to login');
                        $state.go("login");
                    }
                }

                //$rootScope.currentNavLink=toState.name;
            });

            $rootScope.$on('NotificationEvent', function (event, message) {
                //console.log(message);
                $rootScope.message = message;
                if (message.type == 'error') {
                    UtilService.notifyError(message.msg);
                } else {
                    UtilService.notifyInfo(message.msg);
                }

                $timeout(function () {
                    delete $rootScope.message;
                }, 3000);
            });

            $rootScope.isUserLoggedIn = function () {
                return UserService.isUserLoggedIn();
            };

            $rootScope.logout = function () {
                console.log('Logging out..');
                UserService.logout();
                $state.transitionTo("login");
            }

        }]);
