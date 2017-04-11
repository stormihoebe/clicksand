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
          display = $('#time');
      startTimer(twentySeconds, display);
});
