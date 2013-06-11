window.SM2_DEFER = true;

// for example purposes, we'll wait until window.onload before starting things.
window.onload = function() {

	/*
	 * Now that the SM2 constructor is defined, you can call the constructor,
	 * set the options and "kick-start" SM2's init process, and it should work
	 * as normal. WARNING: Do not call beginDelayedInit() before "DOM ready", or
	 * things will fail.
	 */

	// construct the instance (must be named soundManager, and scoped globally)
	window.soundManager = new SoundManager();

	// assign flash url, flashVersion and other SM2 options as usual
	soundManager.setup({
		url : "../swf/",
		preferFlash : false,
		flashVersion: 9, // optional: shiny features (default = 8)
		  // optional: ignore Flash where possible, use 100% HTML5 mode
		  // preferFlash: false,
		  onready: function() {
			  
			  console.log('Its ready..');
		    // Ready to use; soundManager.createSound() etc. can now be called.
			  soundManager.createSound({
				  url: 'http://localhost:8080/SpeechBrowser/fileServlet?path=DigitData-wavs/101/101_m07.wav',
				  // begin loading right away
				  autoLoad: true,
				  whileloading: function() {
				    soundManager._writeDebug(this.id + ': loading ' + this.bytesLoaded + ' / ' + this.bytesTotal);
				  }
				 });
		  }
	});

	// finally, kick-start init process.
	// (old IE etc. may miss domloaded/ready/window.load if they've already
	// fired.)
	

}
