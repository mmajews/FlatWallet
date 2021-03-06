app.controller('HomeCtrl', function ($scope, $rootScope, $http, $cookies, TokenStorage, GroupService) {
    $scope.newItem = "";

    $scope.successCreating = function (data) {
        GroupService.getGroup($rootScope.currentGroup.id, function (data) {
            $scope.currentGroup = data.data;
            $rootScope.currentGroup = data.data;
            $scope.newItem = "";
        }, $scope.failure);
    };

    $scope.success = function (data) {
        // console.log(data);
    };

    $scope.failure = function (data) {
        // console.log(data);
    };

    $scope.addItemToList = function () {
        GroupService.addItemToShoppingList($rootScope.currentGroup.id, $scope.newItem, $scope.successCreating, $scope.failure);
        // console.log("added " + $scope.newItem);
    };

    $scope.successCreating(null);

    // console.log($rootScope.currentGroup);
});