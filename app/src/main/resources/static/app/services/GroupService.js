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
    }
});
