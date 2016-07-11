angular.module('javarestapi', ['ngRoute']).config(function($routeProvider, $httpProvider){
	$httpProvider.interceptors.push('AuthInterceptor');
	
	$routeProvider.when('/user', {
		templateUrl: 'partials/user.html',
        controller: 'UserController'
	}).when('/auth', {
		templateUrl: 'partials/auth.html'
		,controller: 'AuthController'
	}).otherwise({redirectTo: '/user'});
	
});