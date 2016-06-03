var app = angular.module('flatWallet', ['ui.router', 'ngResource', 'ngCookies']).factory('TokenStorage', function() {
    var storageKey = 'auth_token';
    return {
        store : function(token) {
            return localStorage.setItem(storageKey, token);
        },
        retrieve : function() {
            return localStorage.getItem(storageKey);
        },
        clear : function() {
            return localStorage.removeItem(storageKey);
        }
    };
}).factory('TokenAuthInterceptor', function($q, $rootScope, TokenStorage) {
    return {
        request: function(config) {
            var authToken = TokenStorage.retrieve();
            if (authToken) {
                config.headers['X-AUTH-TOKEN'] = authToken;
            }
            return config;
        },
        responseError: function(error) {
            if (error.status === 401 || error.status === 403) {
                TokenStorage.clear();
                $rootScope.authenticated = false;
            }
            return $q.reject(error);
        }
    };
}).config(function($httpProvider, $urlRouterProvider, $stateProvider) {
    $httpProvider.interceptors.push('TokenAuthInterceptor');
    $urlRouterProvider.otherwise('/');
    $stateProvider.state('home', {
        url: '/',
        templateUrl: 'app/home/home.html'
    })

        .state('loginState', {
            url: '/loginState',
            templateUrl: 'app/login/login.html',
            controller: function ($scope, Authentication) {

                // console.log();
                // console.log(Authentication.getCurrent())
            }
        })

});

app.run(function ($rootScope, Authentication, $state, $cookies, TokenStorage, $http) {
    $rootScope.authenticated = false
    $rootScope.username = "";
    var authCookie = $cookies['AUTH-TOKEN'];
    if (authCookie) {
        TokenStorage.store(authCookie);
        delete $cookies['AUTH-TOKEN'];
    }
    $http.get('/api/user/current').success(function (user) {
        if (user.username) {
            $rootScope.authenticated = true;
            $rootScope.username = user.username;
            // console.log($rootScope.username);
            // For display purposes only
            $rootScope.token = JSON.parse(atob(TokenStorage.retrieve().split('.')[0]));
        }
    });

    if($rootScope.authenticated == false) $state.go('loginState')
    else $state.go('home')

})

