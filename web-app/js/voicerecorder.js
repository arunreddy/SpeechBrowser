/**
 * Voice recorder Speak function
 */

function speak() {
	console.log("Speaking..");
	Recorder.record({
		start : function() {
			 alert("recording starts now. press stop when youre done. and then play or upload if you want.");
		},
		progress : function(milliseconds) {
			document.getElementById("time").innerHTML = timecode(milliseconds);
		}
	});
}

/**
 * Voice recorder Save function
 */
function saveAudio() {
	console.log("Saving audio.")
}

function timecode(ms) {
	var hms = {
		h : Math.floor(ms / (60 * 60 * 1000)),
		m : Math.floor((ms / 60000) % 60),
		s : Math.floor((ms / 1000) % 60)
	};
	var tc = []; // Timecode array to be joined with '.'

	if (hms.h > 0) {
		tc.push(hms.h);
	}

	tc.push((hms.m < 10 && hms.h > 0 ? "0" + hms.m : hms.m));
	tc.push((hms.s < 10 ? "0" + hms.s : hms.s));

	return tc.join(':');
}

$(document).ready(function() {
	Recorder.initialize({
		swfSrc : "static/recorder.swf"
	});
})

function record() {
	Recorder.record({
		start : function() {
			// alert("recording starts now. press stop when youre done. and then
			// play or upload if you want.");
		},
		progress : function(milliseconds) {
			document.getElementById("time").innerHTML = timecode(milliseconds);
		}
	});
}

function play() {
	Recorder.stop();
	Recorder.play({
		progress : function(milliseconds) {
			document.getElementById("time").innerHTML = timecode(milliseconds);
		}
	});
}

function stop() {
	Recorder.stop();
}

function upload() {
	Recorder.upload({
		url : "https://example.com/upload",
		audioParam : "your_file",
		success : function() {
			alert("your file was uploaded!");
		}
	});
}