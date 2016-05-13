var app = angular.module('flatWallet', ['ui.router', 'ngResource']);

// app.factory('AuthService',function ($resource) {
//     return $resource('/',{},{
//         isAuthenticated: {
//             method: 'GET',
//             url: '/isAuthenticated'
//         }
//     })
// })

app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    $httpProvider.defaults.withCredentials = true;
    delete $httpProvider.defaults.headers.common["X-Requested-With"];
    $httpProvider.defaults.headers.common["Accept"] = "application/json";
    $httpProvider.defaults.headers.common["Content-Type"] = "application/json";
}
]);

app.config(function ($urlRouterProvider, $stateProvider, $httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
    $urlRouterProvider.otherwise('/');
    $stateProvider.state('home', {
            url: '/',
            templateUrl: 'app/home/home.html'
        })

        .state('loginState', {
            url: '/loginState',
            templateUrl: 'app/login/login.html'
            // controller: function (AuthService) {
            //     AuthService.isAuthenticated();
            // }
        })

        .state('externalAuth',{
            url: '/login',
            controller: function () {
                $(location).attr('href', '/login')
            }
        })

})