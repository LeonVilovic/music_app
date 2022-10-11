/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author Leon
 */
public class AudioPlayerThread extends Thread {
    
    
    private final int BUFFER_SIZE = 128000;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    private volatile boolean flag = true;
    File soundFile;

    public File getSoundFile() {
        return soundFile;
    }

    public void setSoundFile(File soundFile) {
        this.soundFile = soundFile;
    }

    public AudioPlayerThread(File soundFile) {
        this.soundFile = soundFile;
    }
    
    
    public AudioPlayerThread() {
    }
    
    
    public void stopPlay(){
    
    flag = false;
    }
    
    @Override
    public void run(){

//        String strFilename = filename;
//
//        try {
//            soundFile = new File(strFilename);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
//        }

        try {
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e){
            e.printStackTrace();
          //  System.exit(1);
        }

        audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
         //   System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
         //   System.exit(1);
        }

        sourceLine.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1 && flag) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
    }
}
