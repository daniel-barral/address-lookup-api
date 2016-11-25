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
	
	
	$http({
		  method: 'GET',
		  url: '/cache/key/addressgeo/ie/search',
		  params: {
			  //api: controller.api,
			  //queryString: controller.queryString
		  }
	}).then(onSuccess2, onError2);
	
	function onSuccess2(response) {
		$scope.addressGeoList = response.data;
	}
	function onError2(response) {
		alert('Error: ' + response.status + " " + response.statusText);
	}
	
	
	$http({
		  method: 'GET',
		  url: '/cache/key/position/ie/search',
		  params: {
			  //api: controller.api,
			  //queryString: controller.queryString
		  }
	}).then(onSuccess3, onError3);
	
	function onSuccess3(response) {
		$scope.coordinateList = response.data;
	}
	function onError3(response) {
		alert('Error: ' + response.status + " " + response.statusText);
	}
	
}
