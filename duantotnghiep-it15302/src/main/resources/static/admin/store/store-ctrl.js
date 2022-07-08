app.controller("store-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.acc = [];
	$scope.form = {};

	$scope.initialize = function() {
		$http.get("/rest/store").then(resp => {
			$scope.items = resp.data;
		})
		$http.get("/rest/accounts/user").then(resp => {
			$scope.acc = resp.data;
		})
	}
	$scope.initialize();

	$scope.reset = function() {
		$scope.form = {
			image: 'cloud-upload.jpg',
			available: true,
		};
	}
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
	}
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/store`, item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm mới thành công");
		}).catch(error => {
			alert("Lỗi thêm mới sản phẩm");
			console.log("Error", error);
		});
		$scope.reset();
	}
	$scope.update = function() {
		if ($scope.acc.user == $scope.form.account.username) {
			alert("Không được sửa thông tin tài khoản đang đăng nhập")
		} else {
			var item = angular.copy($scope.form);
			$http.put(`/rest/store/${item.id}`, item).then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items[index] = item;
				alert("Cập nhật thành công");
			}).catch(error => {
				alert("Lỗi cập nhật sản phẩm");
				console.log("Error", error);
			});
			$scope.reset();
		}
	}
	$scope.delete = function(item) {
		if ($scope.acc.user == item.account.username) {
			alert("Không được xóa tài khoản đang đăng nhập")
		} else {
			$http.delete(`/rest/store/${item.id}`).then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items.splice(index, 1);
				$scope.reset();
				alert("Xóa thành công");
			}).catch(error => {
				alert("Lỗi xóa sản phẩm");
				console.log("Error", error);
			});
			$scope.reset();
		}
	}
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.indentity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
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
	$scope.timkiem = function() {
		var item = angular.copy($scope.form);
		if (item.name != null) {
			$http.get(`/rest/store/timkiem/${item.name}`).then(resp => {
				$scope.items = angular.copy(resp.data);
			})
		}
	}
});