/**
 * Created by marwen on 28/12/15.
 */

var cccBlog = angular.module('cccBlog');

cccBlog.controller('HomeController',
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

        }]);

