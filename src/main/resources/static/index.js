angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/happy';

    // $scope.fillTable = function () {
    //     $http.get(contextPath + '/products')
    //         .then(function (response) {
    //             console.log(response);
    //             $scope.ProductsList = response.data;
    //         });
    // };

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsList = response.data;
        });
    };

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                // console.log('sended:');
                // console.log($scope.newProduct);
                // console.log('received');
                // console.log(response.data);
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

//    $scope.deleteProductById = function (p) {
//        $http({
//            url: contextPath + '/delete/' + p,
//            method: 'GET'
//            })
//        .then(function (response) {
//            $scope.fillTable();
//        });
//    };

//    $scope.deleteProductById = function (p) {
//        $http({
//            url: contextPath + '/' + p,
//            method: 'DELETE'
//            })
//        .then(function (response) {
//            $scope.fillTable();
//        });
//    };

        $scope.deleteProductById = function (productId) {
            $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.fillTable();
            });
        };


//scope.incrementItem = function (bookId) {
//$http({
//url: contextPath + '/api/v1/cart/add/' + bookId,
//method: 'GET'
//}).then(function (response) {
//$scope.cartContentRequest();
//});
//};


//        $scope.deleteProductById = function (p) {
//            $http.get(`${contextPath}/delete/${p.id}`)
//                .then(function (response) {
//                    $scope.fillTable();
//                });
//        };




    $scope.fillTable();
});
