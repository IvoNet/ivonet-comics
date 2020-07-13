/**
 * Created by ivonet on 22-11-14.
 */


/**
 * The RestClient for this application.
 *
 * Usage:
 *
 * - Add RestClient.js to your main html
 * - Add dependency to the angular module 'RestClient'
 * - Inject 'restService' into your controller
 * - Use the methods provided.
 */

angular.module("RestClient", []).factory('restService', [
   '$http',
   function ($http) {
      return {
         get: function (url) {
            if (window.location.protocol === 'https:') {
               url = url.replace("http:", "https:")
            }
            return $http({
                            method: 'GET',
                            params: {},
                            url   : url
                         });
         }
      }
   }
]);