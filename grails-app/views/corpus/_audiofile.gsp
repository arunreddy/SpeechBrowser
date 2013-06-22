<div class="audio-file row-fluid">
	<div class="span1 graphic">
		<a
			href="/SpeechBrowser/fileServlet?path=<%="${audioFile.path}"%>"
			type="audio/x-wav"> ${audioFile.id}
		</a>
	</div>
	<div class="span2">
		${audioFile.name}
	</div>
	<div class="span1">
		<g:formatNumber number="${audioFile.duration}" type="number"
			maxFractionDigits="2" roundingMode="HALF_DOWN" />
		s
	</div>
	<div class="span5">
		${audioFile.utterance}
	</div>
	<div class="span2 pull-right">
		<div class="pull-right">
			<button class="btn btn-mini segment" type="button" audio="${audioFile.id}" file="${audioFile.name}">
				<i class="icon-th-list"></i>&nbsp;Segment
			</button>
		</div>
	</div>

</div>