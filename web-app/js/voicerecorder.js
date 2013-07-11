function toggleRecording(e) {

	var recorder = $('#' + e.id);

	if (!recorder.hasClass('recording')) {
		console.log("Recording..");
		$('#record-label').html('Recording : <span id="time">0:00</span>');
		record();
	} else {
		console.log("Saving..")
		stop();
		$('#record-label').html('Playing...');
		play();
		upload();
		$('#record-label').html('Click to record.');
	}

	recorder.toggleClass('recording');
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
		url : "voice/upload",
		audioParam : "your_file",
		success : function() {
			alert("your file was uploaded!");
			$.get('voice/transcribe', function(data) {
				alert('Load was performed.');
			});
		}
	});
}