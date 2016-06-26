app.service('GroupService', function ($http, $cookies) {
    this.createNewGroup = function (groupName, success, failure) {
        $http({
            method: 'POST',
            url: 'api/group/create?groupName=' + groupName,
            headers: {'X-AUTH-TOKEN': $cookies.get('AUTH-TOKEN')}
        }).then(success, failure);
    };
    
    this.getAllGroups = function (success, failure) {
        $http({
            method: 'GET',
            url: 'api/group/all',
            headers: {'X-AUTH-TOKEN': $cookies.get('AUTH-TOKEN')}
        }).then(success, failure);
    };
    
    this.getGroup = function (groupId, success, failure) {
        $http({
            method: 'GET',
            url: 'api/group/'+ groupId,
            headers: {'X-AUTH-TOKEN': $cookies.get('AUTH-TOKEN')}
        }).then(success, failure);
    }

    this.getGroupShoppingList = function (groupId, success, failure) {
        $http({
            method: 'GET',
            url: 'api/group/' + groupId + '/shoppinglist',
            headers: {'X-AUTH-TOKEN': $cookies.get('AUTH-TOKEN')}
        }).then(success, failure);
    }

    this.addItemToShoppingList = function (groupId, item,  success, failure) {
        $http({
            method: 'POST',
            url: 'api/group/' + groupId + '/addItemToList?item=' + item,
            headers: {'X-AUTH-TOKEN': $cookies.get('AUTH-TOKEN')}
        }).then(success, failure);
    }
    
    this.setItemAsBought = function (itemId, success, failure) {
        $http({
            method: 'POST',
            url: 'api/group/setItemAsBought?itemId=' + itemId,
            headers: {'X-AUTH-TOKEN': $cookies.get('AUTH-TOKEN')}
        }).then(success, failure);
    }
});
