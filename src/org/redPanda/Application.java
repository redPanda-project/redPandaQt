package org.redPanda;

import com.trolltech.qt.gui.*;
import org.redPandaLib.*;

/**
 *
 * @author user
 */
public class Application extends QMainWindow {
    
    private QMenu fileMenu;
    private QMenu todoMenu;
    private QMenu helpMenu;
    
    
    private QAction exitAct;
    private QAction aboutAct;
    /*private QAction aboutQtJambiAct;
    private QAction aboutQtAct;*/
    
    public Signal0 exitNow = new Signal0();
    
    public Application ()
    {
        QMenuBar menuBar = new QMenuBar ();
        setMenuBar(menuBar);
        
        Chat chat = new Chat(); 
        setCentralWidget(chat);
        
        setWindowTitle(tr("redPanda"));
        
        try {
            createActions();
        } catch (Exception e) {
            e.printStackTrace();
        }
        createMenus();
    }
    
    public void exit() {
        close();
        Main.shutdown();
        
        exitNow.emit();
    }
    
    public void about()
    {
        QMessageBox.about(this,
                         tr("About redPanda"),
                         tr("<b>redPanda</b> v. XXX"));
    }
    
    private void createActions()
    {
        exitAct = new QAction(tr("E&xit"), this);
        exitAct.setShortcut(tr("Ctrl+Q"));
        exitAct.setStatusTip(tr("Exit redPanda"));
        exitAct.triggered.connect(this, "exit()");
        
        aboutAct = new QAction(tr("&About"), this);
        aboutAct.setStatusTip(tr("Show redPanda's About box"));
        aboutAct.triggered.connect(this, "about()");
        
        /*aboutQtJambiAct = new QAction(tr("About &Qt Jambi"), this);
        aboutQtJambiAct.setStatusTip(tr("Show the Qt Jambi library's About box"));
        aboutQtJambiAct.triggered.connect(QApplication.instance(), "aboutQtJambi()");

        aboutQtAct = new QAction(tr("About Q&t"), this);
        aboutQtAct.setStatusTip(tr("Show the Qt library's About box"));
        aboutQtAct.triggered.connect(QApplication.instance(), "aboutQt()"); */
    }
    
    private void createMenus()
    {
        fileMenu = menuBar().addMenu(tr("&File"));
        fileMenu.addAction(exitAct);
        
        todoMenu = menuBar().addMenu(tr("&ToDo"));
        
        menuBar().addSeparator();
        helpMenu = menuBar().addMenu(tr("&Help"));
        helpMenu.addAction(aboutAct);
        /*helpMenu.addSeparator();
        helpMenu.addAction(aboutQtJambiAct);
        helpMenu.addAction(aboutQtAct);*/
    }
    
}
