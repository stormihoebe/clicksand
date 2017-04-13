var clicksandWhisper = new Audio('clicksand.m4a');
clicksandWhisper.play();

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
          display = $('#time20000');
      startTimer(twentySeconds, display);
});
///Thirtyfive Second Timer
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
      var thirtyfiveSeconds = 35,
          display = $('#time35000');
      startTimer(thirtyfiveSeconds, display);
});

///twentyfive Second Timer
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
      var twentyfiveSeconds = 25,
          display = $('#time25000');
      startTimer(twentyfiveSeconds, display);
});
