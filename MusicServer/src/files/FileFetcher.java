/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.io.File;

/**
 *
 * @author Leon
 */
public class FileFetcher {
    
    private File soundFile;

    public FileFetcher() {
    }
    
    
    
    public File fetchAudioFile(String soundFilePath){
                try {
            soundFile = new File(soundFilePath);
        } catch (Exception e) {
            e.printStackTrace();
           // System.exit(1);
        }
                
     return soundFile;
    
    }
}
