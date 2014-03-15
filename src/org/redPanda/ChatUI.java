package org.redPanda;

import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

/**
 *
 * @author user
 */
public class ChatUI implements com.trolltech.qt.QUiForm<QDialog> {
    
    public QVBoxLayout vboxLayout;
    public QHBoxLayout hboxLayout;
    public QTextEdit textEdit;
    public QListWidget listWidget;
    public QHBoxLayout hboxLayout1;
    public QLabel label;
    public QLineEdit lineEdit;
    
    public ChatUI() { 
        super(); 
    }
    
    public void setupUi(QDialog ChatDialog)
    {
        ChatDialog.setObjectName("ChatDialog");
        ChatDialog.resize(new QSize(513, 349).expandedTo(ChatDialog.minimumSizeHint()));
        vboxLayout = new QVBoxLayout(ChatDialog);
        vboxLayout.setSpacing(6);
        vboxLayout.setMargin(9);
        vboxLayout.setObjectName("vboxLayout");
        hboxLayout = new QHBoxLayout();
        hboxLayout.setSpacing(6);
        hboxLayout.setMargin(0);
        hboxLayout.setObjectName("hboxLayout");
        textEdit = new QTextEdit(ChatDialog);
        textEdit.setObjectName("textEdit");
        textEdit.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.NoFocus);
        textEdit.setReadOnly(true);

        hboxLayout.addWidget(textEdit);

        listWidget = new QListWidget(ChatDialog);
        listWidget.setObjectName("listWidget");
        listWidget.setMaximumSize(new QSize(180, 16777215));
        listWidget.setFocusPolicy(com.trolltech.qt.core.Qt.FocusPolicy.NoFocus);

        hboxLayout.addWidget(listWidget);


        vboxLayout.addLayout(hboxLayout);

        hboxLayout1 = new QHBoxLayout();
        hboxLayout1.setSpacing(6);
        hboxLayout1.setMargin(0);
        hboxLayout1.setObjectName("hboxLayout1");
        label = new QLabel(ChatDialog);
        label.setObjectName("label");

        hboxLayout1.addWidget(label);

        lineEdit = new QLineEdit(ChatDialog);
        lineEdit.setObjectName("lineEdit");

        hboxLayout1.addWidget(lineEdit);


        vboxLayout.addLayout(hboxLayout1);

        retranslateUi(ChatDialog);
        
        ChatDialog.connectSlotsByName();
    }
    
    void retranslateUi(QDialog ChatDialog)
    {
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("ChatDialog", "Message:", null));
    }
    
}
