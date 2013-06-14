package net.arunreddy.speechbrowser.groovy

import java.util.Date;

class AudioFile {

    String path
    String name
    String mimetype
    int channels
    int sampleRate
    int bitDepth
    long frames
    double duration
    Date dateCreated
    Date lastUpdated
    Corpus corpus

    static belongsTo = [corpus: Corpus]

    static constraints = {
        path(blank: false)
        name(blank: false)
        mimetype(blank:false)
    }

    static mapping = { autoTimestamp true }
}
