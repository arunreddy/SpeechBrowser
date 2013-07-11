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
							onclick="toggleRecording(this);" class="btn">
					</div>
					<div id="record-label" class="span12 pagination-centered">
						Click to record.</div>
				</div>
				<div class="row-fluid"></div>
			</div>
			<div class="span9">
				Speak:
				<div class="audio-graphic">
					<div class="pagination-centered">
						<h3>
							<i>Train number 4 leaves phoenix at 5:35</i>
						</h3>
					</div>

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