app.controller('AuthCtrl', function ($scope, $rootScope, $http, $cookies, TokenStorage) {
    $rootScope.authenticated = false;
    $scope.token; // For display purposes only

    $scope.init = function () {
        var authCookie = $cookies['AUTH-TOKEN'];
        if (authCookie) {
            TokenStorage.store(authCookie);
            delete $cookies['AUTH-TOKEN'];
        }
        $http.get('/api/user/current').success(function (user) {
            if (user.username) {
                $rootScope.authenticated = true;
                $scope.username = user.username;
                $scope.token = JSON.parse(atob(TokenStorage.retrieve().split('.')[0]));
            }
        });
    };

    $scope.logout = function () {
        // Just clear the local storage
        console.log("Logging out");
        TokenStorage.clear();
        $rootScope.authenticated = false;
        $rootScope.username = null;
    };

    $scope.getSocialDetails = function() {
        $http.get('/api/facebook/details').success(function (socialDetails) {
            $scope.socialDetails = socialDetails;
        });
    };
    
});
