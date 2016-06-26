app.controller('GroupCtrl', function ($scope, $rootScope, $stateParams, TokenStorage, GroupService) {
    $scope.newItem = "";
    $scope.boughtItems = [];
    $scope.shoppingList = [];


    GroupService.getGroup($stateParams.groupId, function (data) {
        $scope.currentGroup = data.data;
        $rootScope.currentGroup = data.data;
        // console.log($rootScope.currentGroup);
        // console.log($scope.currentGroup);
    }, $scope.failure);

    GroupService.getGroupShoppingList($stateParams.groupId, function (data) {
        // console.log(data);
        $scope.shoppingList = data.data;
    },$scope.failure);


    $scope.successAdding = function (data) {
        GroupService.getGroup($scope.currentGroup.id, function (data) {
            $scope.currentGroup = data.data;
            $rootScope.currentGroup = data.data;
            $scope.newItem = "";
        }, $scope.failure);

        GroupService.getGroupShoppingList($scope.currentGroup.id, function (data) {
            $scope.shoppingList = data.data;
        }, $scope.failure)
    };  
    
    $scope.successBuying = function (data) {
        GroupService.getGroupShoppingList($scope.currentGroup.id, function (data) {
            $scope.shoppingList = data.data;
        }, $scope.failure)
        
    };

    $scope.success = function (data) {
        // console.log(data);
    };

    $scope.failure = function (data) {
        console.log(data);
        $scope.newItem = "";
    };

    $scope.addItemToList = function () {
        GroupService.addItemToShoppingList($scope.currentGroup.id, $scope.newItem, $scope.successAdding, $scope.failure);
        console.log("added " + $scope.newItem);
    };

    $scope.checkAsBought = function () {
        for (i = 0; i < $scope.boughtItems.length; i++) {
            GroupService.setItemAsBought($scope.boughtItems[i], $scope.successBuying, $scope.failure);
            console.log($scope.boughtItems[i] + " bought ");
        }
        $scope.boughtItems = [];
    }
    
    $scope.test = function () {
        console.log($scope.shoppingList);
        
    }
});
