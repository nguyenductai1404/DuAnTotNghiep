app.controller("code-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};
	$scope.id = {};
	$scope.code = {};

	$scope.initialize = function() {
		$http.get("/rest/accounts/user").then(resp => {
			var user = resp.data.user;
			$http.get("/rest/store/find/" + user).then(resp => {
				$scope.id = resp.data;
			})
			$scope.code = Math.floor(((Math.random() * 8999999999) + 1000000000));
			$http.get("/rest/code/" + user).then(resp => {
				console.log(resp.data)
				$scope.items = resp.data;
			})
		})
	}
	$scope.initialize();
	$scope.edit = function() {
		$(".nav-tabs a:eq(0)").tab('show')
	}
	$scope.create = function() {
		var item = angular.copy($scope.form);
		item.cuahang = $scope.id;
		item.trangthai = true;
		item.code = $scope.code;
		$http.post(`/rest/code`, item).then(resp => {
			$scope.items.push(resp.data);
			$scope.initialize();
			alert("Thêm mới thành công");
		}).catch(error => {
			alert("Lỗi thêm mới sản phẩm");
			console.log("Error", error);
		});
	}
	$scope.delete = function(item) {
		$http.delete(`/rest/code/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.initialize();
			alert("Xóa thành công");
		}).catch(error => {
			alert("Lỗi xóa sản phẩm");
			console.log("Error", error);
		});
	}
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