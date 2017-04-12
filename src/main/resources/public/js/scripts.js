///Twenty Second Timer


function startTimer(duration, display) {
    var timer = duration, seconds;
    setInterval(function () {
        seconds = parseInt(timer % 60, 10);
        seconds = seconds < 10 ? "0" + seconds : seconds;
        display.text("00:" + seconds);

        if (--timer < 0) {
            timer = 0;
        }
    }, 1000);
}
  jQuery(function ($) {
      var twentySeconds = 20,
          display = $('#time20');
      startTimer(twentySeconds, display);
});
///Thirty Second Timer
function startTimer(duration, display) {
    var timer = duration, seconds;
    setInterval(function () {
        seconds = parseInt(timer % 60, 10);
        seconds = seconds < 10 ? "0" + seconds : seconds;
        display.text("00:" + seconds);

        if (--timer < 0) {
            timer = 0;
        }
    }, 1000);
}
  jQuery(function ($) {
      var twentySeconds = 50,
          display = $('#time30');
      startTimer(twentySeconds, display);
});
