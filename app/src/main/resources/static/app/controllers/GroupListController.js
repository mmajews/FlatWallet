app.controller('GroupListCtrl', function ($scope, $rootScope, $http, $cookies, TokenStorage, GroupService) {
    $scope.newGroupName = "";
    $scope.allGroups = [];

    $scope.successCreating = function (data) {
        GroupService.getAllGroups(function (data) {
            $scope.allGroups = data.data;
        }, $scope.failure);
    };

    $scope.success = function (data) {
        console.log(data);
    };

    $scope.failure = function (data) {
        console.log(data);
    };

    $scope.createNewGroup = function () {
        GroupService.createNewGroup($scope.newGroupName, $scope.successCreating, $scope.failure);
    };

    $scope.successCreating(null);
});