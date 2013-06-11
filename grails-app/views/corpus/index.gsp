<html>
<head>
<meta name="layout" content="main" />
<r:require modules="bootstrap" />
<link rel="stylesheet" type="text/css"
	href="https://raw.github.com/scottschiller/SoundManager2/master/demo/play-mp3-links/css/inlineplayer.css" />
<link rel="stylesheet" type="text/css"
	href="https://raw.github.com/scottschiller/SoundManager2/master/demo/flashblock/flashblock.css" />
<script type="text/javascript">
	window.SM2_DEFER = true;
</script>
<script type="text/javascript"
	src="https://raw.github.com/scottschiller/SoundManager2/master/script/soundmanager2.js"></script>
</head>
<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3" id="left-container">
				<div id="left-container-top">
					<div>
						<h4>Corpus</h4>
					</div>
					<div>
						<select>
							<% corpus.each{ corpora -> %>
						
								<option><%="${corpora.name}"%></option>
					
							<% } %>
						</select>
					</div>
				</div>
				<div id="left-container-bottom">
					<div>
						<h4>Directory</h4>
						<ul class="nav nav-tabs nav-stacked">
							<% corpusDirs.each{ cdir -> %>
						
								<li><a href="./<%="${cdir.id}"%>"><%="${cdir.name}"%></a></li>
					
							<% } %>
						</ul>
					</div>
				</div>
			</div>
			<div class="span9" id="right-container">
				<ul class="nav nav-tabs">
					<li><a href="#home" data-toggle="tab">Corpus</a></li>
					<li><a href="#home" data-toggle="tab">Settings</a></li>
				</ul>
				<div>
					<table class="table">
						<thead>
							<tr>
								<th>File Name</th>
								<th>Mimetype</th>
							</tr>
						</thead>
						<tbody>
							<% audioFiles.each{ audioFile -> %>
						<tr>
								<td><%="${audioFile.name}"%></td>
	 							<td><%="${audioFile.mimetype}"%></td> 
								<td><%="${corpus.path}/${corpusDir.path}/${audioFile.path}"%></td>
								<td><a href="/SpeechBrowser/fileServlet?path=<%="${corpus.path}/${corpusDir.path}/${audioFile.path}"%>"   type="audio/x-wav"><%="${audioFile.name}"%></a></td>
						</tr>
						<%  } %>
						</tbody>
					</table>
				</div>
				<ul class="graphic">
					<li><a href="../_mp3/rain.mp3">Rain</a></li>
					<li><a href="../_mp3/walking.mp3">Walking</a></li>
					<!-- files from the web (note that ID3 information will *not* load from remote domains without permission, Flash restriction) -->
					<li><a
						href="http://www.freshly-ground.com/misc/music/carl-3-barlp.mp3">Barrlping
							with Carl (featureblend.com)</a></li>
					<li><a
						href="http://www.freshly-ground.com/data/audio/binaural/Mak.mp3">Angry
							cow sound?</a></li>
					<li><a
						href="http://www.freshly-ground.com/data/audio/binaural/Things that open, close and roll.mp3">Things
							that open, close and roll</a></li>
					<li><a
						href="http://www.freshly-ground.com/misc/music/20060826%20-%20Armstrong.mp3">20060826
							- Armstrong</a></li>
					<li><a
						href="http://freshly-ground.com/data/video/Rain%20on%20Car%20Roof.aac">Rain
							On Car Roof (AAC Audio)</a></li>
				</ul>
			</div>
		</div>
	</div>

	<!-- "Some time later", window.onload() may have fired and you now want to start SM2, etc... -->
	<script type="text/javascript">
		// for example purposes, we'll wait until window.onload before starting things.
		window.onload = function() {

			/*
			 * Now that the SM2 constructor is defined, you can call the constructor,
			 * set the options and "kick-start" SM2's init process, and it should work as normal.
			 * WARNING: Do not call beginDelayedInit() before "DOM ready", or things will fail.
			 */

			// construct the instance (must be named soundManager, and scoped globally)
			window.soundManager = new SoundManager();

			// assign flash url, flashVersion and other SM2 options as usual
			soundManager.setup({
				// path to directory containing SM2 SWF
				url : '/SpeechBrowser/swf/',
				flashVersion : 9,
				onready: function() {
				    // SM2 is ready to play audio!
				    console.log('SM2 is ready for playback')
				 // create "mySound"...
				    var mySound = soundManager.createSound({
				      url: 'http://localhost:8080/SpeechBrowser/fileServlet?path=DigitData-wavs/101/101_m01.wav'
				    });

				    // ...and play it
				    mySound.play();
				  }
			// etc...
			});

			// finally, kick-start the init process.
			// (old IE etc. may miss domloaded/ready/window.load if they've already fired.)
			soundManager.beginDelayedInit();

			// create "mySound"...
	

		}
	</script>

	<%--
	<g:javascript library="soundmanager2" />

--%>
</body>
</html>