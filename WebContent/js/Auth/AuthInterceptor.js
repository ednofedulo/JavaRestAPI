angular.module('javarestapi').factory('AuthInterceptor',function($location, $q){
	var interceptor = {
		'request': function(config) {
            config.headers = config.headers || {};
            if (localStorage.getItem('token')) {
                config.headers.Authorization = 'Bearer ' + localStorage.getItem('token');
            }
            return config || $q.when(config);
		},'responseError': function(res){
			if(res.status == 401){
				$location.path('/auth');
			}
			return $q.reject(res);
		}
	}
	
	return interceptor;
});