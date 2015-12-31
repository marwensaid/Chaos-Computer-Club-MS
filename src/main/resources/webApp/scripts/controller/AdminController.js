/**
 *
 * Created by marwen on 28/12/15.
 */

var cccBlog = angular.module('cccBlog');

cccBlog.controller('AdminController',
    [ '$scope', '$rootScope', '$http', '$location','$sce', 'PostsService','UtilService',
        function ($scope, $rootScope, $http, $location, $sce,PostsService, UtilService)
        {
            console.log('admin controller');


        }
    ]
);