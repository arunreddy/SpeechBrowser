<html>
<head>
<meta name="layout" content="main" />
<r:require modules="bootstrap" />
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'inlineplayer.css')}"
	type="text/css">

<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'flashblock.css')}" type="text/css">
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
				<div class="row-fluid">
					<div class="span12 graphic pagination-centered">
						<a href="/SpeechBrowser/fileServlet?path=audiofile.wav&direct=0"
							type="audio/x-wav">Play </a>
					</div>
				</div>
			</div>
			<div class="span9">
				Speak:
				<div class="audio-graphic">
					<div class="pagination-centered">
						<div class="pull-right">
							<a id="refresh-btn" class="btn" href="javascript:"><i
								class="icon-refresh"></i></a>
						</div>
						<h3>
							<i><span id="utterance">train eight arrives at new
									orleans at five fifteen</span></i>
						</h3>
					</div>

				</div>
				<div>
					Transcribed text:
					<div class="audio-graphic">
						<div class="pagination-centered">
							<h3>
								<i><span id="transciption">train eight arrives at new
										orleans at five fifteen</span></i>
							</h3>
						</div>
					</div>
					<div class="pagination-centered">
						<div>
							WER <span id="wer">0</span> %</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<g:javascript library="voicerecorder" />


	<g:javascript library="recorderjs" />
	<g:javascript library="recorderflash" />

	<g:javascript library="soundmanager2" />
	<g:javascript library="inline" />
</body>
</html>