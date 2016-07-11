angular.module('javarestapi').controller('AuthController', function($scope, $http, $routeParams, $location){
	$scope.username = "";
	$scope.password = "";
	$scope.auth = function(){
		var data = JSON.stringify({username:$scope.username, password:$scope.password});
        $http.post('rest/authentication', data).success(function(data, status) {
			localStorage.setItem("token", data);
			$location.path('/user');
		});
	};
});