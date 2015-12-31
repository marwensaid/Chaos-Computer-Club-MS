/**
 * Created by marwen on 28/12/15.
 */

var cccBlog = angular.module('cccBlog');

cccBlog.controller('AdminEmailSubscribersController',
    [ '$scope', '$rootScope', '$http', '$location','$sce', 'PostsService','UtilService',
        function ($scope, $rootScope, $http, $location, $sce,PostsService, UtilService)
        {
            console.log('AdminEmailSubscribersController');

            $scope.subscribers = [];

            $scope.loadSubscribers = function()
            {
                $http.get('api/admin/emailSubscribers')
                    .success(function(data, status, headers, config){
                        $scope.subscribers = data;
                    })
                    .error(function(data, status, headers, config){
                        UtilService.notifyError('Problem in loading subscribers');
                    });
            }
            $scope.newSubscriber = {};

            $scope.addSubscriber = function()
            {
                $http.post('api/emailSubscribers', $scope.newSubscriber)
                    .success(function(data, status, headers, config){
                        UtilService.notifyInfo('Subscriber added successfully');
                        $scope.loadSubscribers();
                        $scope.newSubscriber = {};
                    })
                    .error(function(data, status, headers, config){
                        UtilService.notifyError('Problem in adding subscriber');
                    });
            }

            $scope.loadSubscribers();

        }
    ]
);
