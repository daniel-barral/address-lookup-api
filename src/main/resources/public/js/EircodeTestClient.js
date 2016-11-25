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
			
			var url = '/cache/key/' + vm.lookupType.name + '/ie/search';
			
			$http({
				  method: 'GET',
				  url: url,
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
