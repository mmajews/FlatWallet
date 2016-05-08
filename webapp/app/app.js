
var app = angular.module('flatWallet',['ui.router', 'ngResource']);

app.config(function ($urlRouterProvider, $stateProvider) {
    $urlRouterProvider.otherwise('/');
    $stateProvider.state('home', {
        url: '/',
        templateUrl: 'app/home/home.html'
    })

})