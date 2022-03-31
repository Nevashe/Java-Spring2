angular.module('market').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';
    const cartContextPath = 'http://localhost:5555/cart/';
    curr_page = 1;

    $scope.loadProducts = function (page) {
        $http.get(contextPath + 'api/v1/products?p=' + page).then(function (response) {
            $scope.productsList = response.data.content
                var range = [];
                for(var i=1;i<response.data.totalPages+1;i++) {
                     range.push(i);
                    }
            $scope.pages = range;
        });
    }

    $scope.showProductInfo = function (productId) {
        $http.get(contextPath + 'api/v1/products/' + productId).then(function (response) {
            alert(response.data.title);
        });
    }

    $scope.addToCart = function (productId) {
        $http.get(cartContextPath + 'api/v1/cart/add/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.addPage = function(){
        if (curr_page < $scope.pages.length){
            curr_page++;
            $scope.loadProducts(curr_page);
        }
    };
    $scope.removePage = function(){
        if (curr_page > $scope.pages[0]){
            curr_page--;
            $scope.loadProducts(curr_page);
        }
    };
    $scope.loadProducts(curr_page);
});