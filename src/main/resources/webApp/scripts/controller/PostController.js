/**
 * Created by marwen on 28/12/15.
 */

var cccBlog = angular.module('cccBlog');

cccBlog.controller('PostController',
    ['$scope', '$http', '$state', '$stateParams', '$location', '$sce', 'UtilService',
        function ($scope, $http, $state, $stateParams, $location, $sce, UtilService) {

            $scope.loadPost = function () {
                $http.get('api/posts/' + $stateParams.postId)
                    .success(function (data, status, headers, config) {
                        $scope.post = data;
                        $scope.post.content = $sce.trustAsHtml($scope.post.content);
                        var comments = data.comments;
                        angular.forEach(comments, function (comment) {
                            comment.content = $sce.trustAsHtml(comment.content);
                        });
                        $scope.post.comments = comments;
                    })
                    .error(function (data, status, headers, config) {
                        UtilService.notifyError('Problem in loading post details');
                    })
                ;
            };

            $scope.loadPost();
            $scope.newComment = {};

            $scope.createComment = function () {
                $scope.newComment.content = $('#contentEditor').code();
                $http.post('api/posts/' + $stateParams.postId + "/comments", $scope.newComment)
                    .success(function (data, status, headers, config) {
                        UtilService.notifyInfo('Comment saved successfully');

                        $scope.newComment = {};
                        $('#contentEditor').code('');
                        $scope.loadPost();
                    })
                    .error(function (data, status, headers, config) {
                        UtilService.notifyError('Problem in saving comment');
                    })
                ;
            };
        }]);

