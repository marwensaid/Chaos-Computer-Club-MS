/**
 * Created by marwen on 28/12/15.
 */

var cccBlog = angular.module('cccBlog');

cccBlog.controller('LoginController',
    ['$rootScope', '$scope', '$http', '$state', '$location', '$cookies', 'UserService', 'UtilService',
        function ($rootScope, $scope, $http, $state, $location, $cookies, UserService, UtilService) {
            $scope.loginUser = {};

            $scope.authenticateUser = function () {

                $scope.loginFailed = false;
                $http({
                    method: 'POST',
                    url: 'authenticate',
                    data: $.param($scope.loginUser),
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                })

                    .then(function (response) {
                        //UtilService.notifyInfo('Login successful');
                        UserService.setUser(response.data);
                        $state.transitionTo('home');
                    }, function (response) {
                        console.log('Login failed');
                        $scope.loginFailed = true;
                        UtilService.notifyError('Invalid Login Credentials');
                    });
            };

        }
    ]);
