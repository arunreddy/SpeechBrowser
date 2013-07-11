package net.arunreddy.speechbrowser

import net.arunreddy.speechbrowser.asr.ASREndPoint;

class AsrService {

    def transcribeText() {
        ASREndPoint endPoint = new ASREndPoint();
        String str = endPoint.getTranscription("/Users/venus/arun/audiofile.wav");
        return str;
    }
}
