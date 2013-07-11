package net.arunreddy.speechbrowser

import net.arunreddy.speechbrowser.asr.ASREndPoint

import com.pwnetics.metric.WordSequenceAligner
import com.pwnetics.metric.WordSequenceAligner.Alignment

class AsrService {

    def transcribeText() {
        ASREndPoint endPoint = new ASREndPoint();
        String str = endPoint.getTranscription("audiofile.wav");
        return str;
    }
	
	def getRandomUtterance(){
		ASREndPoint endPoint = new ASREndPoint();
		String str = endPoint.getRandomUtterance();
		return str;
	}
	
	def align(refArr,hypArr){
		String [] ref = refArr.split(" ");
		String [] hyp = hypArr.split(" ");
		WordSequenceAligner werEval = new WordSequenceAligner();
		Alignment a = werEval.align(ref, hyp);
		return a;
	}
}
