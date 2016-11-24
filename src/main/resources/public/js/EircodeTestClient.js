function EircodeTestClient($scope, $http) {
	
	$http({
		  method: 'GET',
		  url: '/cache/key/address/ie/search',
		  params: {
			  //api: controller.api,
			  //queryString: controller.queryString
		  }
	}).then(onSuccess, onError);
	
	function onSuccess(response) {
		$scope.addresses = response.data;
	}
	function onError(response) {
		alert('Error: ' + response.status + " " + response.statusText);
	}
	
}
