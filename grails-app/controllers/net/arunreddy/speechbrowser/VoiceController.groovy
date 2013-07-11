package net.arunreddy.speechbrowser

import com.pwnetics.metric.WordSequenceAligner.Alignment

class VoiceController {

	def asrService

	def index() {
	}

	def upload(){
		log.info "Processing upload request";
		log.info params

		def f = request.getFile('your_file')
		f.transferTo(new File('audiofile.wav'))
		response.sendError(200, 'Done')
	}

	def transcribe(){
		log.info params
		String text = asrService.transcribeText();
		Alignment alignment = asrService.align(params['utterance'],text);
		log.info "Text is "+text;
		log.info alignment

		render(contentType: 'text/json') {[ 'result': text, 'wer': alignment.getWER() ]}
	}


	def utterance(){
		String text = asrService.getRandomUtterance();
		render(contentType: 'text/json') {[ 'result': text ]}
	}
}
