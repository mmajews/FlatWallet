var app = angular.module('flatWallet', ['ui.router', 'ngResource']);

app.config(function ($urlRouterProvider, $stateProvider) {
    $urlRouterProvider.otherwise('/loginState');
    $stateProvider.state('home', {
            url: '/',
            templateUrl: 'app/home/home.html'
        })

        .state('loginState', {
            url: '/loginState',
            templateUrl: 'app/login/login.html',
        })

        .state('externalAuth', {
            url: 'localhost:8080/login',
            controller: function () {
                $(location).attr('href', 'http://localhost:8080/login')
            }
        })

})