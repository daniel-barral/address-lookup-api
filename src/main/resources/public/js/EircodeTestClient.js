(function(){
	
	angular.module('addressLookup').controller("EircodeTestClient", EircodeTestClient);
	
	function EircodeTestClient($scope, $http) {
		
		var vm = this;
		
		vm.lookupTypes = [
	          {name:'address'},
	          {name:'addressgeo'},
	    	  {name:'position'}
	          ];
		
		vm.lookupType = vm.lookupTypes[0];
		
		vm.resultList = null;
		
		vm.lookup = lookup;
		
		function lookup() {
			if (vm.lookupType.name=='address') {
				loadAdresses();
			} else if (vm.lookupType.name=='addressgeo') {
				loadAdressesGeo();
			} else if (vm.lookupType.name=='position') {
				loadCoordinates();
			}
		}
		
		function loadAdresses() {
			$http({
				  method: 'GET',
				  url: '/cache/key/address/ie/search',
				  params: {
				  }
			}).then(onSuccess, onError);
		}
		
		function loadAdressesGeo() {
			$http({
				  method: 'GET',
				  url: '/cache/key/addressgeo/ie/search',
				  params: {
				  }
			}).then(onSuccess, onError);
		}
		
		function loadCoordinates() {
			$http({
				  method: 'GET',
				  url: '/cache/key/position/ie/search',
				  params: {
				  }
			}).then(onSuccess, onError);
		}
		
		function onSuccess(response) {
			vm.resultList = response.data;
		}
		function onError(response) {
			alert('Error: ' + response.status + " " + response.statusText);
		}
		
	}


})();
