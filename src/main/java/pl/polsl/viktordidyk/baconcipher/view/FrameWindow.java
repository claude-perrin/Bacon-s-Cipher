/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pl.polsl.viktordidyk.baconcipher.view;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pl.polsl.viktordidyk.baconcipher.controller.Controller;

/**
 *
 * @author viktor
 */
public class FrameWindow extends javax.swing.JFrame {
    private final Controller controller;

    /**
     * Creates new form FrameWindow
     * @param controller
     */
    public FrameWindow(Controller controller) {
        initComponents();
        this.chooseStrategyComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"A","B"}));
        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Time", "Log"
            }));
        historyTable.getColumnModel().getColumn(0).setMaxWidth(100);
        this.controller = controller;
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        transcriptorPanel = new javax.swing.JPanel();
        encryptionButton = new javax.swing.JButton();
        decryptedMessageTextField = new javax.swing.JTextField();
        decryptionButton = new javax.swing.JButton();
        encryptedMessageTextField = new javax.swing.JTextField();
        chooseStrategyComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        close = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        man = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        splitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitPane.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                splitPaneAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        transcriptorPanel.setMaximumSize(new java.awt.Dimension(32767, 400));
        transcriptorPanel.setMinimumSize(new java.awt.Dimension(0, 400));
        transcriptorPanel.setPreferredSize(new java.awt.Dimension(636, 400));
        transcriptorPanel.setSize(new java.awt.Dimension(0, 400));

        encryptionButton.setText("Choose file to encrypt");
        encryptionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptionButtonActionPerformed(evt);
            }
        });

        decryptionButton.setText("Decrypt ");
        decryptionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptionButtonActionPerformed(evt);
            }
        });

        chooseStrategyComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        chooseStrategyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseStrategyComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Choose Transcription Strategy");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Decryption");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Encryption");

        javax.swing.GroupLayout transcriptorPanelLayout = new javax.swing.GroupLayout(transcriptorPanel);
        transcriptorPanel.setLayout(transcriptorPanelLayout);
        transcriptorPanelLayout.setHorizontalGroup(
            transcriptorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(transcriptorPanelLayout.createSequentialGroup()
                .addGroup(transcriptorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transcriptorPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(transcriptorPanelLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(chooseStrategyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transcriptorPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(transcriptorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(encryptedMessageTextField)
                    .addComponent(encryptionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(152, 152, 152))
            .addGroup(transcriptorPanelLayout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addGroup(transcriptorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(decryptedMessageTextField)
                    .addComponent(decryptionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        transcriptorPanelLayout.setVerticalGroup(
            transcriptorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transcriptorPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(chooseStrategyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(encryptionButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(encryptedMessageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(decryptedMessageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decryptionButton)
                .addGap(59, 59, 59))
        );

        splitPane.setTopComponent(transcriptorPanel);

        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        historyTable.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        historyTable.setMinimumSize(new java.awt.Dimension(60, 40));
        historyTable.setPreferredSize(new java.awt.Dimension(300, 30));
        jScrollPane1.setViewportView(historyTable);

        splitPane.setRightComponent(jScrollPane1);

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        jMenu1.add(close);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        man.setText("Show help window");
        man.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manActionPerformed(evt);
            }
        });
        jMenu2.add(man);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void encryptionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptionButtonActionPerformed
        JFileChooser fileUpload = new JFileChooser();
        fileUpload.setCurrentDirectory(new File("."));
        int chosenFile = fileUpload.showOpenDialog(null);
        if (chosenFile == JFileChooser.APPROVE_OPTION) {
            String filePath = fileUpload.getSelectedFile().getAbsolutePath();
            this.controller.startEncryption(filePath);
        }    }//GEN-LAST:event_encryptionButtonActionPerformed

    private void splitPaneAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_splitPaneAncestorAdded
    }//GEN-LAST:event_splitPaneAncestorAdded
    
    private void decryptionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptionButtonActionPerformed
        String messageToDecrypt = decryptedMessageTextField.getText();
        this.controller.startDecryption(messageToDecrypt);
    }//GEN-LAST:event_decryptionButtonActionPerformed

    private void chooseStrategyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseStrategyComboBoxActionPerformed
        this.appendHistoryAction(String.format("Strategy is set to %s", this.getChosenStrategy()));
    }//GEN-LAST:event_chooseStrategyComboBoxActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void manActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manActionPerformed
        showPopUpMessage("Choose transription strategy, then perform either encryption or decryption using the prefered strategy");
    }//GEN-LAST:event_manActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        System.exit(0);
    }//GEN-LAST:event_closeActionPerformed

    public void showPopUpMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
    public void setEncryptionMessageTextField(String message) {
        this.encryptedMessageTextField.setText(message);
    }
    
    public void setDecryptionMessageTextField(String message) {
        this.encryptedMessageTextField.setText(message);
    }
    
    public String getChosenStrategy() {
        return this.chooseStrategyComboBox.getSelectedItem().toString();
    }
    
    public void appendHistoryAction(String actionMessage) {
        if (actionMessage.length() > 50) {
            actionMessage = actionMessage.substring(0, 50);
            actionMessage += "...";
        }
        DefaultTableModel model = (DefaultTableModel) historyTable.getModel();
        model.addRow(new String []{new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()), actionMessage});    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> chooseStrategyComboBox;
    private javax.swing.JMenuItem close;
    private javax.swing.JTextField decryptedMessageTextField;
    private javax.swing.JButton decryptionButton;
    private javax.swing.JTextField encryptedMessageTextField;
    private javax.swing.JButton encryptionButton;
    private javax.swing.JTable historyTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem man;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JPanel transcriptorPanel;
    // End of variables declaration//GEN-END:variables
}
