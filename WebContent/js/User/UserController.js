angular.module('javarestapi').controller('UserController', function($scope, $http,$routeParams){
	$scope.message = "not authenticated!";
	
	$http.get('rest/user').success(function(data,status){
		$scope.message = "user "+data+" authenticated!";
	});
	
});