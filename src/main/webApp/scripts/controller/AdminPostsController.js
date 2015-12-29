/**
 * Created by marwen on 28/12/15.
 */

var cccBlog = angular.module('cccBlog');

cccBlog.controller('AdminPostsController',
    ['$scope', '$rootScope', '$http', '$location', '$sce', 'PostsService', 'UtilService',
        function ($scope, $rootScope, $http, $location, $sce, PostsService, UtilService) {
            $scope.loadPosts = function (page) {
                var pageSize = 5;

                PostsService.loadPosts(page, pageSize)
                    .then(
                        function (data, status, headers, config) {
                            var posts = data.posts;
                            angular.forEach(posts, function (post) {
                                post.contentPreview = $sce.trustAsHtml(post.contentPreview);
                            });
                            $scope.posts = posts;
                            $scope.postsPagination = {
                                hasNextPage: data.hasNextPage,
                                hasPrevPage: data.hasPrevPage,
                                currentPage: data.currentPage
                            };
                        },
                        function (data, status, headers, config) {
                            UtilService.notifyError('Problem in loading posts');
                        }
                    );
            };

            $scope.loadPosts(0);

            $scope.editPost = function (postId) {
                alert('edit post :' + postId);
            };
            $scope.deletePost = function (postId) {
                var r = confirm("Are you sure to delete the post?");
                if (r == true) {
                    $http.delete('api/admin/posts/' + postId)
                        .then(
                            function (data, status, headers, config) {
                                UtilService.notifyInfo('Post deleted successfully');
                                $scope.loadPosts(0);
                            },
                            function (data, status, headers, config) {
                                UtilService.notifyError('Problem in deleteing post');
                            }
                        );
                }
            };
            $scope.sharePost = function (postId) {
                alert('share post :' + postId);
            }

        }
    ]);
