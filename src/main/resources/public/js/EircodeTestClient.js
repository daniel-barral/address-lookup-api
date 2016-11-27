(function(){
	
	angular.module('addressLookup').controller("EircodeTestClient", EircodeTestClient);
	
	function EircodeTestClient($scope, $http) {
		
		var vm = this;
		
		vm.apiKey = 'PCW45-12345-12345-1234X';
		vm.search = '';
		vm.what3words = false;
		
		vm.lookupTypes = [
	          {name:'address'},
	          {name:'addressgeo'},
	          {name:'rgeo'},
	    	  {name:'position'}
	          ];
		
		vm.lookupType = vm.lookupTypes[0];
		
		vm.countries = [
	          {code:'ie'},
	          {code:'uk'}
	          ];
		
		vm.country = vm.countries[0];
		
		vm.latitude = null;
		vm.longitude = null;
		vm.distance = null;
		
		vm.resultList = null;
		
		vm.lookup = lookup;
		
		
		function lookup() {
			
			var url = buildUrl();
			
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
		
		function buildUrl() {
			var url = '/proxy/' + vm.apiKey + '/' + vm.lookupType.name + '/' + vm.country.code + '/';
			
			if (vm.lookupType.name=='rgeo') {
				url += vm.latitude + '/' + vm.longitude;
			} else {
				url += vm.search;
			}
			
			url += '?format=json';
			
			if (vm.lookupType.name=='rgeo') {
				url += '&distance=' + vm.distance;
			}
			
			if (vm.what3words) {
				url += '&addtags=w3w';
			}
			
			if (vm.lines) {
				url += '&lines=' + vm.lines;
			}
			
			if (vm.include) {
				url += '&include=' + vm.include;
			}
			
			if (vm.exclude) {
				url += '&exclude=' + vm.exclude;
			}
			
			if (vm.page) {
				url += '&page=' + vm.page;
			}
			
			if (vm.addTags) {
				url += '&addtags=' + vm.addTags;
			}
			
			return url;
		}
		
	}


})();
