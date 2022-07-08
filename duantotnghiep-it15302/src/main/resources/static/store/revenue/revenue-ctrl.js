app.controller("revenue-ctrl", function($scope, $http) {
	$scope.items = [];

	$scope.initialize = {
		load() {
			$http.get("/rest/orders/report").then(resp => {
				$scope.items = resp.data;
			})
		}
	}
	$scope.timkiem = function() {
		if ($scope.thang == null && $scope.nam == null) {
			alert("Thiếu thông tin ở thanh tìm kiếm !!!")
		} else {
			if ($scope.thang == null) {
				$http.get("/rest/orders/nam/" + $scope.nam).then(resp => {
					$scope.items = resp.data;
					console.log(resp.data)
				})
			} else if ($scope.nam == null) {
				$http.get("/rest/orders/thang/" + $scope.thang).then(resp => {
					$scope.items = resp.data;
					console.log(resp.data)
				})
			} else {
				$http.get("/rest/orders/" + $scope.thang + "/" + $scope.nam).then(resp => {
					$scope.items = resp.data;
					console.log(resp.data)
				})
			}
		}

	}
	$scope.initialize.load();

});