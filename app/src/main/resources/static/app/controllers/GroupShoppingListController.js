app.controller('ShoppingListCtrl', function ($scope, $rootScope, GroupService) {
    $scope.groupId = 0;
    $scope.shoppingList = [];

    $scope.success = function (data) {
        // $scope.shoppingList = data.data;
        console.log(data);
    };

    $scope.failure = function (data) {
        console.log(data);
    };

    $scope.getList = function () {
        GroupService.getGroupShoppingList($scope.groupId, $scope.success, $scope.failure);
    };
})