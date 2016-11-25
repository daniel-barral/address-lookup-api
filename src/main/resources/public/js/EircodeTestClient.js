(function(){
	
	angular.module('addressLookup').controller("EircodeTestClient", EircodeTestClient);
	
	function EircodeTestClient($scope, $http) {
		
		loadAdresses();
		loadAdressesGeo();
		loadCoordinates();
		
		function loadAdresses() {
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
		
		function loadAdressesGeo() {
			$http({
				  method: 'GET',
				  url: '/cache/key/addressgeo/ie/search',
				  params: {
					  //api: controller.api,
					  //queryString: controller.queryString
				  }
			}).then(onSuccess, onError);
			
			function onSuccess(response) {
				$scope.addressGeoList = response.data;
			}
			function onError(response) {
				alert('Error: ' + response.status + " " + response.statusText);
			}
		}
		
		function loadCoordinates() {
			$http({
				  method: 'GET',
				  url: '/cache/key/position/ie/search',
				  params: {
					  //api: controller.api,
					  //queryString: controller.queryString
				  }
			}).then(onSuccess, onError);
			
			function onSuccess(response) {
				$scope.coordinateList = response.data;
			}
			function onError(response) {
				alert('Error: ' + response.status + " " + response.statusText);
			}
		}
		
	}


})();
