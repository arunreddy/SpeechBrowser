import net.arunreddy.speechbrowser.groovy.AudioFile;
import net.arunreddy.speechbrowser.groovy.Corpus;

class BootStrap {

	def init = { servletContext ->

//		Corpus cvar
//		//if corpus doesnt exist.
//		if(!Corpus.count()){
//			cvar = new Corpus(name: "DigitData-wavs", path: "DigitData-wavs",description:"A new description" ).save(failOnError: true)
//		}
//
//		if(!AudioFile.count()){
//			new AudioFile(name: "101_m01.wav",path:"DigitData-wavs/101",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "101_m02.wav",path:"DigitData-wavs/101",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//		}
	}
	def destroy = {

		Corpus.where{ }.deleteAll()
		CorpusDir.where {}.deleteAll()
	}
}
