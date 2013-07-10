<html>
<head>
<meta name="layout" content="main" />
<r:require modules="bootstrap" />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="span2">
				<div class="row-fluid">
					<div class="span12 pagination-centered">
						<img id="record" width="128" height="128" src="images/mic128.png"
							onclick="toggleRecording(this);">
					</div>
				</div>
				<div class="row-fluid">

					<div class="span12 pagination-centered">
						<button class="btn" onclick="speak();">Speak!</button>
						<button class="btn" onclick="javascript:play();">Save!</button>
					</div>
				</div>

				<div class="row-fluid">

					<div class="span12 alert alert-info">
						<ul>
							<li>Click Speak button.</li>
							<li>Speak the given sentence</li>
							<li>Click on Save button</li>
						</ul>
					</div>

					<div>
						<a href="javascript:record()" id="record">Record</a> <a
							href="javascript:play()" id="play">Play</a> <a
							href="javascript:stop()" id="stop">Stop</a> <a
							href="javascript:upload()" id="upload">Upload (faked)</a>
					</div>
				</div>
			</div>
			<div class="span9">
				<div class="audio-graphic">
					<div>
						Speak: <span id="time">0:00</span>
					</div>
					<h3>Hello, How are you doing ?</h3>
				</div>
			</div>
		</div>

		<br> <img src="images/save.svg" onclick="saveAudio();">
	</div>
	<g:javascript library="voicerecorder" />


	<g:javascript library="recorderjs" />
	<g:javascript library="recorderflash" />
</body>
</html>