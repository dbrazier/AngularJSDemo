/* globals $ */
'use strict';

angular.module('mosaicApp')
    .directive('slaExpired', function() {
        return {
            restrict: 'A',
            link: function(scope, element, attributes){
                if (scope.caseInfoItem.slaExpired) {
                    element.addClass('colred');
                } else {
                    element.addClass('colgreen');
                }
            }
        };
    })
    .directive('capitalize', function() {
        return {
            require: 'ngModel',
            link: function(scope, element, attrs, modelCtrl) {
                var capitalize = function(inputValue) {
                    if(inputValue == undefined) inputValue = '';
                    var capitalized = inputValue.toUpperCase();
                    if(capitalized !== inputValue) {
                        modelCtrl.$setViewValue(capitalized);
                        modelCtrl.$render();
                    }
                    return capitalized;
                }
                modelCtrl.$parsers.push(capitalize);
                capitalize(scope[attrs.ngModel]);  // capitalize initial value
            }
        };
    });


