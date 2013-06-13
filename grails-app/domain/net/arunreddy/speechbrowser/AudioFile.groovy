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
    Corpus corpus

    static belongsTo = [corpus: Corpus]

    static constraints = {
        path(blank: false)
        name(blank: false)
        mimetype(blank:false)
    }

    static mapping = { autoTimestamp true }
}
