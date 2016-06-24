app.controller('GroupCtrl', function ($scope, $rootScope, $stateParams, TokenStorage, GroupService) {
    $scope.newItem = "";


    GroupService.getGroup($stateParams.groupId, function (data) {
        $scope.currentGroup = data.data;
        $rootScope.currentGroup = data.data;
    }, $scope.failure);

    $scope.successAdding = function (data) {
        GroupService.getGroup($scope.currentGroup.id, function (data) {
            $scope.currentGroup = data.data;
            $rootScope.currentGroup = data.data;
            $scope.newItem = "";
        }, $scope.failure);
    };

    $scope.success = function (data) {
        console.log(data);
    };

    $scope.failure = function (data) {
        console.log(data);
        $scope.newItem = "";
    };

    $scope.addItemToList = function () {
        GroupService.addItemToShoppingList($scope.currentGroup.id, $scope.newItem, $scope.successAdding, $scope.failure);
        console.log("added " + $scope.newItem);
    };

});
