/**
 * Created by marwen on 28/12/15.
 */

var cccBlog = angular.module('cccBlog');

cccBlog.controller('AdminCommentsController',
    [ '$scope', '$rootScope', '$http', '$location','$sce', 'PostsService','UtilService',
        function ($scope, $rootScope, $http, $location, $sce,PostsService, UtilService)
        {
            $scope.loadComments = function(page){
                var pageSize = 5;

                $http.get('api/admin/comments')
                    .then(
                        function(data, status, headers, config){
                            var comments = data.data.comments;
                            angular.forEach(comments, function(comment) {
                                //comment.contentPreview = $sce.trustAsHtml(comment.contentPreview);
                            });
                            $scope.comments = comments;
                            $scope.commentsPagination = {
                                hasNextPage : data.data.hasNextPage,
                                hasPrevPage: data.data.hasPrevPage,
                                currentPage: data.data.currentPage
                            };
                        },
                        function(data, status, headers, config){
                            UtilService.notifyError('Problem in loading comments');
                        }
                    );
            };

            $scope.loadComments(0);

            $scope.checkAll = function () {
                $scope.selectedAll = !!$scope.selectedAll;
                angular.forEach($scope.comments, function (comment) {
                    comment.Selected = $scope.selectedAll;
                });
            };

            $scope.deleteSelectedComments = function () {
                var commentIds = [];
                angular.forEach($scope.comments, function (comment) {
                    if (comment.Selected) {
                        commentIds.push(comment.id);
                    }
                });

                if (commentIds.length < 1) {
                    alert('Please select the comments to be deleted');
                    return;
                }
                var r = confirm("Are you sure to delete the comments?");
                if (r == true) {
                    $http.delete('api/admin/comments?commentIds=' + commentIds)
                        .then(
                            function (data, status, headers, config) {
                                UtilService.notifyInfo('Comments deleted successfully');
                                $scope.loadComments(0);
                            },
                            function (data, status, headers, config) {
                                UtilService.notifyError('Problem in deleteing comments');
                            }
                        );
                }
            };

            $scope.deleteComment = function(commentId)
            {
                var r = confirm("Are you sure to delete the comment?");
                if (r == true) {
                    $http.delete('api/admin/comments/'+commentId)
                        .then(
                            function(data, status, headers, config){
                                UtilService.notifyInfo('Comment deleted successfully');
                                $scope.loadComments(0);
                            },
                            function(data, status, headers, config){
                                UtilService.notifyError('Problem in deleteing comment');
                            }
                        );
                }
            }


        }
    ]);