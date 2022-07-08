app = angular.module("admin-app", ["ngRoute"]);
app.config(function($routeProvider) {
	$routeProvider
		.when("/product", {
			templateUrl: "/admin/product/indexProduct.html",
			controller: "product-ctrl"
		})
		.when("/account", {
			templateUrl: "/admin/account/indexAccount.html",
			controller: "account-ctrl"
		})
		.when("/authorize", {
			templateUrl: "/admin/authorize/indexAuthority.html",
			controller: "ctrl"
		})
		.when("/store", {
			templateUrl: "/admin/store/indexStore.html",
			controller: "store-ctrl"
		})
		.otherwise({
			templete: "<h1 class='text-center'> FPT Polytecnich Administration</h1>"
		});
})