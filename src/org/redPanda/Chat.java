package org.redPanda;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;
import org.redPandaLib.*;
import org.redPandaLib.core.Channel;

/**
 *
 * @author user
 */
public class Chat extends QDialog {
    
    private ChatUI ui = new ChatUI();
    private Client client = new Client();
    private String myNickName;
    private int currentChannelID;
    private QTextTableFormat tableFormat = new QTextTableFormat();

    public Chat() {
        this(null);
    }
    
    public Chat(QWidget parent) {
     
        super(parent);

        ui.setupUi(this);
        
        ui.lineEdit.setFocusPolicy(Qt.FocusPolicy.StrongFocus);
        ui.textEdit.setFocusPolicy(Qt.FocusPolicy.NoFocus);
        ui.textEdit.setReadOnly(true);
        ui.listWidget.setFocusPolicy(Qt.FocusPolicy.NoFocus);
        
        ui.lineEdit.returnPressed.connect(this, "returnPressed()");
        
        client.newMessage__from__message.connect(this, "appendMessage(String, String)");
        
        myNickName = client.nickName();
        currentChannelID = 1;
        tableFormat.setBorder(0);
    }
    
    public String tr(String str, Object... arguments) {
        return String.format(tr(str), arguments);
    }
    
    private void appendMessage(final String from, final String message) {
        if (from.equals("") || message.equals(""))
            return;

        QTextCursor cursor = new QTextCursor(ui.textEdit.textCursor());
        cursor.movePosition(QTextCursor.MoveOperation.End);
        QTextTable table = cursor.insertTable(1, 2, tableFormat);
        table.cellAt(0, 0).firstCursorPosition().insertText("<" + from + "> ");
        table.cellAt(0, 1).firstCursorPosition().insertText(message);
        QScrollBar bar = ui.textEdit.verticalScrollBar();
        bar.setValue(bar.maximum());
    }
    
    void returnPressed() {
        String text = ui.lineEdit.text();
        if (text.equals(""))
            return;

        if (text.startsWith("/")) {
            QColor color = ui.textEdit.textColor();
            ui.textEdit.setTextColor(QColor.red);
            ui.textEdit.append(tr("! Unknown command: ") + text.substring(text.indexOf(' ')));
            ui.textEdit.setTextColor(color);
        } else {
            client.sendMessageToChannel(currentChannelID, text);
            appendMessage(myNickName, text);
        }

        ui.lineEdit.clear();
    }
    
    class Client extends QObject {
        
        // from, message
        Signal2<String, String> newMessage__from__message = new Signal2<>();  
        
        Client() {
                        
        }          
            
        public synchronized void sendMessageToChannel(int channelID, String message) {
            
            if (message.equals("")) {
                return;
                }
                    
            Channel channel = Channel.getChannelById(currentChannelID);
            
            Main.sendMessageToChannel(channel, message);
            
        } 
        
        public String nickName() {
            return "unknown";
        }
            
    }   
    
}
