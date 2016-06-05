var app = angular.module('flatWallet', ['ui.router', 'ngResource', 'ngCookies']).factory('TokenStorage', function () {
    var storageKey = 'auth_token';
    return {
        store: function (token) {
            return localStorage.setItem(storageKey, token);
        },
        retrieve: function () {
            return localStorage.getItem(storageKey);
        },
        clear: function () {
            return localStorage.removeItem(storageKey);
        }
    };
}).factory('TokenAuthInterceptor', function ($q, $rootScope, TokenStorage) {
    return {
        request: function (config) {
            var authToken = TokenStorage.retrieve();
            if (authToken) {
                config.headers['X-AUTH-TOKEN'] = authToken;
            }
            return config;
        },
        responseError: function (error) {
            if (error.status === 401 || error.status === 403) {
                TokenStorage.clear();
                $rootScope.authenticated = false;
            }
            return $q.reject(error);
        }
    };
}).config(function ($httpProvider, $urlRouterProvider, $stateProvider) {
    $httpProvider.interceptors.push('TokenAuthInterceptor');
    $urlRouterProvider.otherwise('/');
    $stateProvider.state('home', {
        url: '/',
        templateUrl: 'app/home/home.html'
    })

        .state('loginState', {
            url: '/loginState',
            templateUrl: 'app/login/login.html'
        })

        .state('group', {
            url: '/group',
            templateUrl: 'app/group/groups.html'
        })
});

app.run(function ($rootScope, Authentication, $state, $cookies, TokenStorage, $http) {
    $rootScope.username = null;
    // var authCookie = $cookies['AUTH-TOKEN'];
    // if (authCookie) {
    //     TokenStorage.store(authCookie);
    //     delete $cookies['AUTH-TOKEN'];
    // }
    console.log($cookies.get('AUTH-TOKEN'));
    $http({
        method: 'GET', url: '/api/user/current', headers: {
            'X-AUTH-TOKEN': $cookies.get('AUTH-TOKEN')
        }
    }).success(function (user) {
        console.log(user);
        if (user.username) {
            $rootScope.authenticated = true;
            $rootScope.username = user.username;
            console.log("if");
            $state.go('home');
            // $rootScope.token = JSON.parse(atob(TokenStorage.retrieve().split('.')[0]));
        } else {
            console.log("else")
            $rootScope.authenticated = false;
            $state.go('loginState');
        }
    });
});

app.controller('IndexCtrl', function ($rootScope, $cookies, TokenStorage) {
    $rootScope.logout = function () {
        console.log("Logout");
        // Just clear the local storage
        TokenStorage.clear();
        $rootScope.authenticated = false;
        $rootScope.username = null;
        $cookies.remove('AUTH-TOKEN');
    };
})

