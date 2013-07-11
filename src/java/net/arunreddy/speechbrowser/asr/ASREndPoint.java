/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package net.arunreddy.speechbrowser.asr;

import java.io.File;

import net.arunreddy.speech.SpeechToText;

/**
 * @version $Id$
 */
public class ASREndPoint
{

    private SpeechToText stt;

    public static final String CONFIGS_PATH = System.getenv("configs_dir");

    public String getTranscription(String filePath)
    {

        try {
            File configFile =
                new File(CONFIGS_PATH + File.separator + "sphinx4" + File.separator + "DigitData-wavs" + File.separator
                    + "config.xml");
            stt = new SpeechToText(configFile.toURI().toURL());
            File audioFile = new File(filePath);
            if (audioFile.exists()) {
                String transcription = stt.speechToText(audioFile.toURI().toURL());
                return transcription;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
