var app = angular.module('ComicsApp', [
   'ngRoute',
   'ComicsController'
]).filter('range', function () {
   return function (input, start, end) {
      start = parseInt(start);
      end = parseInt(end);
      var direction = (start <= end) ? 1 : -1;
      while (start != end) {
         input.push(start);
         start += direction;
      }
      return input;
   };
});

app.config([
  '$routeProvider',
  function ($routeProvider) {
    $routeProvider.when('/', {
      templateUrl: 'views/home.html',
      controller: 'ComicsController'
    }).otherwise({redirectTo: '/'});
  }
]);







