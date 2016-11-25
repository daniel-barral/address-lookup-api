(function(){
	
	angular.module('addressLookup').controller("EircodeTestClient", EircodeTestClient);
	
	function EircodeTestClient($scope, $http) {
		
		var vm = this;
		
		vm.apiKey = 'PCW45-12345-12345-1234X';
		vm.search = '';
		
		vm.lookupTypes = [
	          {name:'address'},
	          {name:'addressgeo'},
	    	  {name:'position'}
	          ];
		
		vm.lookupType = vm.lookupTypes[0];
		
		vm.countries = [
	          {code:'ie'},
	          {code:'uk'}
	          ];
		
		vm.country = vm.countries[0];
		
		vm.resultList = null;
		
		vm.lookup = lookup;
		
		
		function lookup() {
			
			var url = '/cache/' + vm.apiKey + '/' + vm.lookupType.name + '/' + vm.country.code + '/' + vm.search;
			
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
