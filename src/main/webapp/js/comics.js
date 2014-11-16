var app = angular.module('ComicsApp', [
   'ngResource',
   'ngRoute',
   'bootstrapLightbox'
]);


app.config([
              '$routeProvider',
              function ($routeProvider) {
                 $routeProvider.
                       when('/', {
                               templateUrl: 'views/home.html',
                               controller : 'ComicsController'
                            }).
                       otherwise({redirectTo: '/'});
              }
           ]);

app.directive('focusOn', function () {
   return function (scope, elem, attr) {
      scope.$on('focusOn', function (e, name) {
         if (name === attr.focusOn) {
            elem[0].focus();
         }
      });
   };
});

app.factory('focus', function ($rootScope, $timeout) {
   return function (name) {
      $timeout(function () {
         $rootScope.$broadcast('focusOn', name);
      });
   }
});

app.factory('folderService', [
   '$resource',
   function ($resource) {
      return $resource('resource/folder/', {}, {
                          query: {
                             method : 'GET',
                             params : {},
                             isArray: false
                          }
                       }
      )
   }
]);

app.factory('restService', [
   '$http',
   function ($http) {
      return {
         get: function (url) {
            if (window.location.protocol === 'https:') {
               url = url.replace("http:", "https:")
            }
            return $http({
                            method: 'GET',
                            url   : url
                         });
         }
      }
   }
]);

app.controller("ComicsController", [
   '$scope',
   'folderService',
   'focus',
   'restService',
   'Lightbox',
   function ($scope, folderService, focus, restService, Lightbox) {
      $scope.debug = false;

      $scope.data = folderService.query();
      focus('searchBox');


      $scope.readComic = function (filename) {
         var url = ($scope.data.downloadUri + $scope.data.folder.path
                    + "/" + filename).replace("#", "%23");

         restService.get(url).success(function (data) {
            $scope.images = data;
            Lightbox.openModal($scope.images, 0);
         });
      };


      $scope.createFileUri = function (filename) {
         return ($scope.data.fileUri + $scope.data.folder.path
                 + "/" + filename).replace("#", "%23");
      };

      $scope.browseFolder = function (folder) {
         var url = $scope.data.browseUri;

         if (folder === '/') {
            url = $scope.data.baseUri;
         } else if (folder === '..') {
            var pth = $scope.data.folder.path.split("/");
            pth.pop();
            pth = pth.join('/');
            if (pth === '') {
               url = $scope.data.baseUri;
            }
            url += pth;
         } else {
            url += ($scope.data.folder.path + "/" + folder).replace("//", "/");
         }

         restService.get(url.replace("#", "%23")).success(function (data) {
            $scope.data = data;
         });
         $scope.clearSearchBox()
      };

      $scope.clearSearchBox = function () {
         $scope.nameText = "";
         focus('searchBox');
      };


   }
]);
