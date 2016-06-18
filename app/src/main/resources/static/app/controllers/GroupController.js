app.controller('GroupCtrl', function ($scope, $rootScope, $stateParams, TokenStorage, GroupService) {

    GroupService.getGroup($stateParams.groupId, function (data) {
        $scope.currentGroup = data.data;
        $rootScope.currentGroup = data.data;
    }, $scope.failure);

});