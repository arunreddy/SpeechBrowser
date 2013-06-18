/**
 * 
 */
package net.arunreddy.speechbrowser.sync;

import net.arunreddy.speechbrowser.groovy.sync.SyncAudioFileService;

/**
 * @author arun
 */
public class SyncStatus
{

    private int counter;

    private int totalFiles;

    /**
     * @return the totalFiles
     */
    public int getTotalFiles()
    {
        return totalFiles;
    }

    /**
     * @param totalFiles the totalFiles to set
     */
    public void setTotalFiles(int totalFiles)
    {
        this.totalFiles = totalFiles;
    }

    private SyncAudioFileService syncAudioFileService;

    /**
     * @return the syncAudioFileService
     */
    public SyncAudioFileService getSyncAudioFileService()
    {
        return syncAudioFileService;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "SyncStatus [counter=" + counter + ", totalFiles=" + totalFiles + "]";
    }

    /**
     * @param syncAudioFileService the syncAudioFileService to set
     */
    public void setSyncAudioFileService(SyncAudioFileService syncAudioFileService)
    {
        this.syncAudioFileService = syncAudioFileService;
    }

    /**
     * 
     */
    public void increment()
    {
        // TODO Auto-generated method stub
        counter++;
    }

    /**
     * @return the counter
     */
    public int getCounter()
    {
        return counter;
    }

}
