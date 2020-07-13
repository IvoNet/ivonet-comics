/**
 * Created by ivonet on 22-11-14.
 */

/**
 * Puts the focus on an element of your choosing:
 *
 * Usage:
 *
 *  Add this js file to your main html
 *
 *  In html something like:
 *  <input type="text" data-ng-model="searchText" focus-on="searchBox" class="ivonet-search"/>
 *
 *  In javascript controller:
 *  - Add dependency 'FocusModule' to the angular app
 *  - Inject 'focus' into your controller
 *  - put code like focusOn('searchBox'); into your code.
 *
 */
angular.module('FocusModule', []).directive('focusOn', function () {
   return function (scope, elem, attr) {
      scope.$on('focusOn', function (e, name) {
         if (name === attr.focusOn) {
            elem[0].focus();
         }
      });
   };
});

angular.module('FocusModule').factory('focus', function ($rootScope, $timeout) {
   return function (name) {
      $timeout(function () {
         $rootScope.$broadcast('focusOn', name);
      });
   }
});