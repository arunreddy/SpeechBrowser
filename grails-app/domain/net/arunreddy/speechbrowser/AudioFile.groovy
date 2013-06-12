package net.arunreddy.speechbrowser

import java.util.Date;

class AudioFile {

    String path
    String name
    String mimetype
    int channels
    int sampleRate
    int bitDepth
    long frames
    int duration
    Date dateCreated
    Date lastUpdated

    static belongsTo = [corpusDir: CorpusDir]

    static constraints = {
        path(blank: false)
        name(blank: false)
        mimetype(blank:false)
    }

    static mapping = { autoTimestamp true }
}
