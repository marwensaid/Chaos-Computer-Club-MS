/**
 * Created by marwen on 31/12/15.
 */

var cccBlog = angular.module('cccBlog');

cccBlog.service('UtilService',['$rootScope', '$cookieStore', 'Notification',
    function($rootScope, $cookieStore, Notification)
    {
        var self = this;

        self.fireInfoEvent = function(msg){
            var message = {type: 'info', 'msg':msg};
            $rootScope.$emit('NotificationEvent', message);
        };

        self.fireErrorEvent = function(msg){
            var message = {type: 'error', 'msg':msg};
            $rootScope.$emit('NotificationEvent', message);
        };

        self.notifyInfo = function(msg){
            Notification.primary(msg);
        };

        self.notifyError = function(msg){
            Notification.error(msg);
        };

    }
]);
