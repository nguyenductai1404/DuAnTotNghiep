app.controller("info-ctrl", function($scope, $http) {

	$scope.store = {
		loadstore() {
			$http.get("/rest/accounts/user").then(resp => {
				var user = resp.data.user;
				$http.get("/rest/store/find/" + user).then(resp => {
					if(resp.data.trangthai==true){
						resp.data.trangthai = "Mở";
					}else{
						resp.data.trangthai = "Khóa";
					}
					$scope.store = resp.data;
				})
			})
		}
	}
	$scope.store.loadstore();
});