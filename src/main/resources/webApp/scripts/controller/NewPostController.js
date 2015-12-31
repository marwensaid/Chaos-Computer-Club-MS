/**
 * Created by marwen on 28/12/15.
 */

var cccBlog = angular.module('cccBlog');

cccBlog.controller('NewPostController', [ '$scope','$http', '$stateParams', '$location', 'UtilService',
    function ($scope, $http, $stateParams, $location, UtilService) {

        $scope.newPost = {};

        $scope.createPost = function(){
            $scope.newPost.content = $('#contentEditor').code();
            $http.post('api/admin/posts/', $scope.newPost)
                .success(function(data, status, headers, config){
                    UtilService.notifyInfo('Post saved successfully');
                    $scope.newPost = {};
                    $('#contentEditor').code('');
                })
                .error(function(data, status, headers, config){
                    UtilService.notifyError('Problem in saving post');
                });
        }

    }]);
