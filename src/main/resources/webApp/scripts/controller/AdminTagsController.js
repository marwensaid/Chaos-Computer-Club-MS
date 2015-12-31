/**
 * Created by marwen on 28/12/15.
 */

var cccBlog = angular.module('cccBlog');

cccBlog.controller('AdminTagsController',
    [ '$scope', '$rootScope', '$http', '$location','$sce', 'PostsService','UtilService',
        function ($scope, $rootScope, $http, $location, $sce,PostsService, UtilService)
        {
            console.log('AdminTagsController');
            $scope.tags = [];

            $scope.loadTags = function()
            {
                $http.get('api/tags')
                    .success(function(data, status, headers, config){
                        $scope.tags = data;
                    })
                    .error(function(data, status, headers, config){
                        UtilService.notifyError('Problem in loading tags');
                    });
            }
            $scope.newTag = {};

            $scope.createTag = function()
            {
                $http.post('api/admin/tags', $scope.newTag)
                    .success(function(data, status, headers, config){
                        UtilService.notifyInfo('Tag saved successfully');
                        $scope.loadTags();
                        $scope.newTag = {};
                    })
                    .error(function(data, status, headers, config){
                        UtilService.notifyError('Problem in saving tag');
                    });
            }

            $scope.loadTags();

        }
    ]
);
