import net.arunreddy.speechbrowser.AudioFile;
import net.arunreddy.speechbrowser.Corpus;

class BootStrap {

	def init = { servletContext ->
		
//		Corpus cvar
//		//if corpus doesnt exist.
//		if(!Corpus.count()){
//			cvar = new Corpus(name: "DigitData-wavs", path: "/home/arun/devel/datasets/speech/DigitData-wavs",description:"A new description" ).save(failOnError: true)
//		}
//
//		if(!AudioFile.count()){
//			new AudioFile(name: "101_m01",path:"101_m01.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "101_m02",path:"101_m02.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "101_m03",path:"101_m03.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "101_m04",path:"101_m04.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "101_m05",path:"101_m05.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "101_m06",path:"101_m06.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "101_m07",path:"101_m07.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//
//			new AudioFile(name: "102_m01",path:"102_m01.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "102_m02",path:"102_m02.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "102_m03",path:"102_m03.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "102_m04",path:"102_m04.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "102_m05",path:"102_m05.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "102_m06",path:"102_m06.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "102_m07",path:"102_m07.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//
//			new AudioFile(name: "103_m01",path:"103_m01.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "103_m02",path:"103_m02.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "103_m03",path:"103_m03.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "103_m04",path:"103_m04.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "103_m05",path:"103_m05.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "103_m06",path:"103_m06.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//			new AudioFile(name: "103_m07",path:"103_m07.wav",mimetype: "audio/x-wav",corpus:cvar).save(failOnError: true)
//
//		}
	}
	def destroy = {

		Corpus.where{ }.deleteAll()
		CorpusDir.where {}.deleteAll()
	}
}
