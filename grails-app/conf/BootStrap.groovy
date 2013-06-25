import net.arunreddy.speechbrowser.User
import net.arunreddy.speechbrowser.groovy.AudioFile
import net.arunreddy.speechbrowser.groovy.Corpus

import org.apache.shiro.crypto.hash.Sha256Hash



class BootStrap {

    def init = { servletContext ->

        
        //Add corpus.
        def corpus = new Corpus(path:"DigitData-wavs",name:"DigitData-wavs",description:"DigitData-wavs").save(failOnError: true)
        
        //Add audio files.
        new AudioFile(path:"DigitData-wavs/101/101_m01.wav",name:"101_m01.wav",mimetype:"audio/x-wav",corpus:corpus).save(failOnError: true);
        new AudioFile(path:"DigitData-wavs/101/101_m02.wav",name:"101_m02.wav",mimetype:"audio/x-wav",corpus:corpus).save(failOnError: true);
        new AudioFile(path:"DigitData-wavs/101/101_m03.wav",name:"101_m03.wav",mimetype:"audio/x-wav",corpus:corpus).save(failOnError: true);
        new AudioFile(path:"DigitData-wavs/101/101_m04.wav",name:"101_m04.wav",mimetype:"audio/x-wav",corpus:corpus).save(failOnError: true);
        new AudioFile(path:"DigitData-wavs/101/101_m05.wav",name:"101_m05.wav",mimetype:"audio/x-wav",corpus:corpus).save(failOnError: true);
        new AudioFile(path:"DigitData-wavs/101/101_m06.wav",name:"101_m06.wav",mimetype:"audio/x-wav",corpus:corpus).save(failOnError: true);
        new AudioFile(path:"DigitData-wavs/101/101_m07.wav",name:"101_m07.wav",mimetype:"audio/x-wav",corpus:corpus).save(failOnError: true);
        new AudioFile(path:"DigitData-wavs/101/101_m08.wav",name:"101_m08.wav",mimetype:"audio/x-wav",corpus:corpus).save(failOnError: true);

        def user = new User(username: "user123", passwordHash: new Sha256Hash("password").toHex())
        user.addToPermissions("*:*")
        user.save()
    }
    def destroy = {

    }
}
