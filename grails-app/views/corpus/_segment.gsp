<div class="row-fluid">
	<div class="span2 graphic">
		<a href="/SpeechBrowser/fileServlet?path=<%="${audioSegment.path}"%>"
			type="audio/x-wav">&nbsp;
		</a>
	</div>
	<div class="span6">
		${audioSegment.name}&nbsp;
	</div>
	<div class="span3">
		<g:formatNumber number="${audioSegment.duration}" type="number"
			maxFractionDigits="2" roundingMode="HALF_DOWN" />&nbsp;ms
	</div>
</div>