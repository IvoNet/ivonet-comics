/**
 * Created by ivonet on 22-11-14.
 */


angular.module('ComicsController', [
   'RestClient',
   'ngResource',
   'FocusModule',
   'bootstrapLightbox'
]).config(function (LightboxProvider) {
   LightboxProvider.templateUrl = 'views/lightbox.html';
}).controller("ComicsController", [
   '$scope',
   'focus',
   'restService',
   'Lightbox',
   function ($scope, focus, restService, Lightbox) {
      $scope.debug = false;

      restService.get('resources/folders').success(function (data) {
         $scope.data = data;
      });

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