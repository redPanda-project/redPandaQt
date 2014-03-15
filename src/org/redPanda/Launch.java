package org.redPanda;

import com.trolltech.qt.gui.*;
import java.io.IOException;
import java.security.SignatureException;
import org.redPandaLib.core.Saver;
import org.redPandaLib.crypt.Main;

/**
 *
 * @author user
 */
public class Launch { 
    
    public static void main(String[] args) throws IOException, SignatureException { 
        
        // Core
        Saver saver = new Saver();
        org.redPandaLib.Main.startUp(false, saver);
        Main.main(args); 
        
        // Application
        QApplication.initialize(args);
        
        Application rP = new Application();
        rP.show();
    
        QApplication.exec(); 
    
    }    
    
}
