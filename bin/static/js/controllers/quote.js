'use strict'
var temp1;
angular.module('app.controllers', [])

.controller('RandomQuoteCtrl', function($scope, QuoteService) {
	QuoteService.random().$promise.then(function(quote) {
		$scope.quote = quote;
		$scope.authorid = quote.author.id;
	});
}).controller('SaveQuoteCtrl', function($scope, $state, QuoteService) {

	$scope.saveQuote = function() {
		QuoteService.save($scope.quote, function(response) {
			$state.go("quote", {});
		}, function(err) {
			console.log(err);
		});
	};
}).controller('AuthorQuotesCtrl', function($scope, $location, AuthorService) {
	console.log($location);
    var authorid = $location.$$search.authorid;
    console.log("authorid: " + authorid);
	temp1 = $scope;

	AuthorService.query({
		id : authorid
	}, function() {
	}).$promise.then(function(quotes) {
		var author = quotes[0].author.name;		
		$scope.quotes = quotes;
		$scope.authorname = author;
	});

});