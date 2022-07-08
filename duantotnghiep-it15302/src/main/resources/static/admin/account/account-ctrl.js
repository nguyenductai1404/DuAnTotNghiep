app.controller("account-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.acc = [];
	$scope.form = {};

	$scope.initialize = function() {
		$http.get("/rest/accounts/load").then(resp => {
			$scope.items = resp.data;
		})
		$http.get("/rest/accounts/user").then(resp => {
			$scope.acc = resp.data;
		})
	}
	$scope.reset = function() {
		$scope.form = {
			photo: 'cloud-upload.jpg',
		};
	}
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show')
	}
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/accounts`, item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			$scope.initialize();
			alert("Thêm mới thành công");
		}).catch(error => {
			alert("Lỗi thêm mới tài khoản");
			console.log("Error", error);
		});
	}
	$scope.update = function() {
		if ($scope.acc.user == $scope.form.username) {
			alert("Không được sửa thông tin tài khoản đang đăng nhập")
		} else {
			var item = angular.copy($scope.form);
			$http.put(`/rest/accounts/${item.username}`, item).then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items[index] = item;
				$scope.initialize();
				alert("Cập nhật thành công");
			}).catch(error => {
				alert("Lỗi cập nhật tài khoản");
				console.log("Error", error);
			});
		}
	}
	$scope.delete = function(item) {
		if ($scope.acc.user == item.username) {
			alert("Không được xóa tài khoản đang đăng nhập")
		} else {
			$http.delete(`/rest/accounts/${item.username}`).then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.username);
				$scope.items.splice(index, 1);
				$scope.reset();
				$scope.initialize();
				alert("Xóa thành công");
			}).catch(error => {
				alert("Lỗi xóa tài khoản");
				console.log("Error", error);
			});
		}
	}
	$scope.stttrue = function(item) {
		if ($scope.acc.user == item.username) {
			alert("Không được chuyển trạng thái tài khoản đang đăng nhập")
		} else {
			item.trangthai = true;
			$http.put(`/rest/accounts/${item.username}`, item).then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items[index] = item;
				$scope.initialize();
				alert("Chuyển trạng thái thành công");
			}).catch(error => {
				alert("Lỗi cập nhật tài khoản");
				console.log("Error", error);
			});
		}
	}
	$scope.sttfalse = function(item) {
		if ($scope.acc.user == item.username) {
			alert("Không được chuyển trạng thái tài khoản đang đăng nhập")
		} else {
			item.trangthai = false;
			$http.put(`/rest/accounts/${item.username}`, item).then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items[index] = item;
				$scope.initialize();
				alert("Chuyển trạng thái thành công");
			}).catch(error => {
				alert("Lỗi cập nhật tài khoản");
				console.log("Error", error);
			});
		}
	}
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.indentity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.photo = resp.data.name;
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
	$scope.initialize();

	$scope.timkiem = function() {
		var item = angular.copy($scope.form);
		if (item.name != null) {
			$http.get(`/rest/accounts/timkiem/${item.name}`).then(resp => {
				$scope.items = angular.copy(resp.data);
			})
		}
	}
});