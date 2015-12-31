/**
 * Created by marwen on 31/12/15.
 */

var cccBlog = angular.module('cccBlog');

cccBlog.service('PostsService',['$resource', '$http',
    function($resource, $http){

        this.loadPosts = function(page, pageSize){
            var promise = $http.get('api/posts/?pageNo='+page+'&pageSize='+pageSize)
                .then(function(response){
                    return response.data;
                });
            return promise;
        };



    }
]);
