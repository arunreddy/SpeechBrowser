import net.arunreddy.speechbrowser.AudioFile;
import net.arunreddy.speechbrowser.Corpus;
import net.arunreddy.speechbrowser.CorpusDir;

class BootStrap {

	def init = { servletContext ->
		
		Corpus cvar
		//if corpus doesnt exist.
		if(!Corpus.count()){
			cvar = new Corpus(name: "Uncertainity", path: "/home/arun/devel/datasets/speech/DigitData-wavs" ).save(failOnError: true)
		}

		CorpusDir cdir1,cdir2,cdir3
		
		if(!CorpusDir.count()){
			cdir1 = new CorpusDir(name:"103",path:"101", corpus: cvar ).save(failOnError: true)
			cdir2 = new CorpusDir(name:"102",path:"102", corpus: cvar).save(failOnError: true)
			cdir3 = new CorpusDir(name:"103",path:"103", corpus: cvar).save(failOnError: true)
			new CorpusDir(name:"104",path:"104", corpus: cvar).save(failOnError: true)
			new CorpusDir(name:"105",path:"105", corpus: cvar).save(failOnError: true)
			new CorpusDir(name:"106",path:"106", corpus: cvar).save(failOnError: true)
			new CorpusDir(name:"107",path:"107", corpus: cvar).save(failOnError: true)
			new CorpusDir(name:"108",path:"108", corpus: cvar).save(failOnError: true)
			new CorpusDir(name:"109",path:"109", corpus: cvar).save(failOnError: true)
			new CorpusDir(name:"110",path:"110", corpus: cvar).save(failOnError: true)
		}

		if(!AudioFile.count()){
			new AudioFile(name: "101_m01",path:"101_m01.wav",mimetype: "audio/x-wav",corpusDir:cdir1).save(failOnError: true)
			new AudioFile(name: "101_m02",path:"101_m02.wav",mimetype: "audio/x-wav",corpusDir:cdir1).save(failOnError: true)
			new AudioFile(name: "101_m03",path:"101_m03.wav",mimetype: "audio/x-wav",corpusDir:cdir1).save(failOnError: true)
			new AudioFile(name: "101_m04",path:"101_m04.wav",mimetype: "audio/x-wav",corpusDir:cdir1).save(failOnError: true)
			new AudioFile(name: "101_m05",path:"101_m05.wav",mimetype: "audio/x-wav",corpusDir:cdir1).save(failOnError: true)
			new AudioFile(name: "101_m06",path:"101_m06.wav",mimetype: "audio/x-wav",corpusDir:cdir1).save(failOnError: true)
			new AudioFile(name: "101_m07",path:"101_m07.wav",mimetype: "audio/x-wav",corpusDir:cdir1).save(failOnError: true)

			new AudioFile(name: "102_m01",path:"102_m01.wav",mimetype: "audio/x-wav",corpusDir:cdir2).save(failOnError: true)
			new AudioFile(name: "102_m02",path:"102_m02.wav",mimetype: "audio/x-wav",corpusDir:cdir2).save(failOnError: true)
			new AudioFile(name: "102_m03",path:"102_m03.wav",mimetype: "audio/x-wav",corpusDir:cdir2).save(failOnError: true)
			new AudioFile(name: "102_m04",path:"102_m04.wav",mimetype: "audio/x-wav",corpusDir:cdir2).save(failOnError: true)
			new AudioFile(name: "102_m05",path:"102_m05.wav",mimetype: "audio/x-wav",corpusDir:cdir2).save(failOnError: true)
			new AudioFile(name: "102_m06",path:"102_m06.wav",mimetype: "audio/x-wav",corpusDir:cdir2).save(failOnError: true)
			new AudioFile(name: "102_m07",path:"102_m07.wav",mimetype: "audio/x-wav",corpusDir:cdir2).save(failOnError: true)

			new AudioFile(name: "103_m01",path:"103_m01.wav",mimetype: "audio/x-wav",corpusDir:cdir3).save(failOnError: true)
			new AudioFile(name: "103_m02",path:"103_m02.wav",mimetype: "audio/x-wav",corpusDir:cdir3).save(failOnError: true)
			new AudioFile(name: "103_m03",path:"103_m03.wav",mimetype: "audio/x-wav",corpusDir:cdir3).save(failOnError: true)
			new AudioFile(name: "103_m04",path:"103_m04.wav",mimetype: "audio/x-wav",corpusDir:cdir3).save(failOnError: true)
			new AudioFile(name: "103_m05",path:"103_m05.wav",mimetype: "audio/x-wav",corpusDir:cdir3).save(failOnError: true)
			new AudioFile(name: "103_m06",path:"103_m06.wav",mimetype: "audio/x-wav",corpusDir:cdir3).save(failOnError: true)
			new AudioFile(name: "103_m07",path:"103_m07.wav",mimetype: "audio/x-wav",corpusDir:cdir3).save(failOnError: true)

		}
	}
	def destroy = {

		Corpus.where{ }.deleteAll()
		CorpusDir.where {}.deleteAll()
	}
}
