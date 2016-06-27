app.controller('ShoppingListCtrl', function ($scope, $rootScope, GroupService) {
    $scope.groupId = 0;
    $scope.shoppingList = [];

    $scope.success = function (data) {
        // console.log(data);
        // console.log("succes");
    };

    $scope.failure = function (data) {
        // console.log(data);
        // console.log("failure");
    };

    $scope.getList = function () {
        GroupService.getGroupShoppingList($scope.groupId, $scope.success, $scope.failure);
    };
})