var weatherCodes = new Array();
weatherCodes[395] = "SNOW";
weatherCodes[392] = "SNOW";
weatherCodes[389] = "RAIN";
weatherCodes[386] = "SLEET";
weatherCodes[377] = "RAIN";
weatherCodes[374] = "SLEET";
weatherCodes[371] = "SNOW";
weatherCodes[368] = "SNOW";
weatherCodes[365] = "SLEET";
weatherCodes[362] = "SLEET";
weatherCodes[359] = "RAIN";
weatherCodes[356] = "RAIN";
weatherCodes[353] = "RAIN";
weatherCodes[350] = "SNOW";
weatherCodes[338] = "SNOW";
weatherCodes[335] = "SNOW";
weatherCodes[332] = "SNOW";
weatherCodes[329] = "SNOW";
weatherCodes[326] = "SNOW";
weatherCodes[323] = "SNOW";
weatherCodes[320] = "SLEET";
weatherCodes[317] = "SLEET";
weatherCodes[314] = "SLEET";
weatherCodes[311] = "SLEET";
weatherCodes[308] = "RAIN";
weatherCodes[305] = "RAIN";
weatherCodes[302] = "RAIN";
weatherCodes[299] = "RAIN";
weatherCodes[296] = "RAIN";
weatherCodes[293] = "RAIN";
weatherCodes[284] = "SLEET";
weatherCodes[281] = "SLEET";
weatherCodes[266] = "SLEET";
weatherCodes[263] = "CLOUDY";
weatherCodes[260] = "FOG";
weatherCodes[248] = "FOG";
weatherCodes[230] = "SNOW";
weatherCodes[227] = "SNOW";
weatherCodes[200] = "RAIN";
weatherCodes[185] = "SLEET";
weatherCodes[182] = "SLEET";
weatherCodes[179] = "SLEET";
weatherCodes[176] = "RAIN";
weatherCodes[143] = "FOG";
weatherCodes[122] = "CLOUDY";
weatherCodes[119] = "CLOUDY";
weatherCodes[116] = "PARTLY_CLOUDY_DAY";
weatherCodes[113] = "CLEAR_DAY";

/*!
 * IE10 viewport hack for Surface/desktop Windows 8 bug
 * Copyright 2014-2015 Twitter, Inc.
 * Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 */

// See the Getting Started docs for more information:
// http://getbootstrap.com/getting-started/#support-ie10-width

(function () {
  "use strict";

  if (navigator.userAgent.match(/IEMobile\/10\.0/)) {
    var msViewportStyle = document.createElement("style");
    msViewportStyle.appendChild(
      document.createTextNode(
        "@-ms-viewport{width:auto!important}"
      )
    );
    document.querySelector("head").appendChild(msViewportStyle);
  }

})();
