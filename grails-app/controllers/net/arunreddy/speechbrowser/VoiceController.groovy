package net.arunreddy.speechbrowser

class VoiceController {

    def asrService
    
    def index() {
    }

    def upload(){
        log.info "Processing upload request";
        log.info params

        def f = request.getFile('your_file')
        f.transferTo(new File('/Users/venus/arun/audiofile.wav'))
        response.sendError(200, 'Done')
    }
    
    def transcribe(){
        
        String text = asrService.transcribeText();
        log.info "Text is "+text;
        
        render(contentType: 'text/json') {[
            'result': text
        ]}
    }
}
