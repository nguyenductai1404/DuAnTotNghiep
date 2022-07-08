app.controller("order-ctrl", function($scope, $http) {
	$scope.items = [];

	$scope.initialize = function() {
		$http.get("/rest/accounts/user").then(resp => {
			var user = resp.data.user;
			$http.get("/rest/orders/" + user).then(resp => {
				$scope.items = resp.data;
			})
		})
	}
	$scope.trangthai = function(id) {
		var order = this.items.find(order => order.id == id);
		if (order.trangthai == "Đã đặt hàng") {
			order.trangthai = "Đang chuẩn bị đơn hàng";
			$http.post("/rest/orders/trangthai", order).then(resp => {
				$scope.initialize();
			})
		}else if(order.trangthai == "Đang chuẩn bị đơn hàng"){
			order.trangthai = "Đang giao hàng";
			$http.post("/rest/orders/trangthai", order).then(resp => {
				$scope.initialize();
			})
		}else if(order.trangthai == "Đang giao hàng"){
			order.trangthai = "Đã giao hàng";
			$http.post("/rest/orders/trangthai", order).then(resp => {
				$scope.initialize();
			})
		}else if(order.trangthai == "Đã giao hàng"){
			alert("Đơn hàng đã hoàn thành")
		}else{
			alert("Đơn hàng đã hủy hoặc chưa được thanh toán");
		}
		
	}
	$scope.initialize();

	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}
});