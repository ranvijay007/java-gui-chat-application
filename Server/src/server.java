/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kakarot
 */
import java.net.*;
import java.io.*;
import java.util.*;
public class server extends javax.swing.JFrame {

    ServerSocket ss;
    HashMap clientColl=new HashMap();
    public server() {
        try{
            initComponents();
            ss=new ServerSocket(2089);
            this.jLabel2.setText("Server Started....");
            new clientAccept().start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    class clientAccept extends Thread{
        public void run() {
            while (true) {
                try {
                    Socket s=ss.accept();
                    //System.out.println(s);
                    String i=new DataInputStream(s.getInputStream()).readUTF();
                    if(i.equalsIgnoreCase("refresh")){
                        new PrepareClientList().start();
                    }
                    else {
                        if (clientColl.containsKey(i)) {
                            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                            dout.writeUTF("You Are Already Registered....!!");
                        } else {
                            clientColl.put(i, s);
                            jTextArea1.append(i + " Joined !\n");
                            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                            dout.writeUTF("");
                            new MsgRead(s, i).start();
                            new PrepareClientList().start();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    class MsgRead extends Thread{
        Socket s;
        String ID;
        private MsgRead(Socket s, String i) {
            this.s=s;
            this.ID=i;
        }
        public void run(){
            while(!clientColl.isEmpty()){
                try{
                    String i=new DataInputStream(s.getInputStream()).readUTF();
                    System.out.println(i);
                    if(i.equals("mkoihgteazdcvgyhujb096785542AXTY")){
                        clientColl.remove(ID);
                        jTextArea1.append(ID+" Removed !\n");
                        new PrepareClientList().start();
                        Set k=clientColl.keySet();
                        Iterator itr=k.iterator();
                        while(itr.hasNext()){
                            String key=(String)itr.next();
                            if(!key.equalsIgnoreCase(ID)){
                                try{
                                    new DataOutputStream(((Socket)clientColl.get(key)).getOutputStream()).writeUTF("< "+ID+" to "+key+" > +i");
                                }
                                catch(Exception e){
                                    clientColl.remove(key);
                                    jTextArea1.append(ID+" Removed !\n");
                                    new PrepareClientList().start();
                                }
                            }
                        }
                    }
                    else if(i.contains("#4344554@@@@@67667@@")){
                        i=i.substring(20);
                        StringTokenizer st=new StringTokenizer(i,":");
                        String id=st.nextToken();
                        i=st.nextToken();
                        try{
                            new DataOutputStream(((Socket)clientColl.get(id)).getOutputStream()).writeUTF("< "+ID+" to "+id+" > "+i);
                        }
                        catch(Exception e){
                            clientColl.remove(id);
                            jTextArea1.append(id+" Remove !\n");
                            new PrepareClientList().start();
                        }
                    }
                    else{
                        Set k = clientColl.keySet();
                        Iterator itr = k.iterator();
                        while (itr.hasNext()) {
                            String key = (String)itr.next();
                            if (!key.equalsIgnoreCase(ID)) {
                                try {
                                    new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF("< " + ID + " to all > "+i);
                                } catch (Exception e) {
                                    clientColl.remove(key);
                                    jTextArea1.append(key + " Remove !\n");
                                    new PrepareClientList().start();
                                }
                            }
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    class PrepareClientList extends Thread{
        public void run(){
            try {
                String ids = "";
                Set k = clientColl.keySet();
                Iterator itr = k.iterator();
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    ids += key + ",";
                }
                if (ids.length() != 0) {
                    ids = ids.substring(0, ids.length() - 1);
                }
                itr = k.iterator();
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    try {
                        new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF(":;.,/="+ids);
                    } catch (Exception e) {
                        clientColl.remove(key);
                        jTextArea1.append(key + " Remove !\n");
                        
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Server Status :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
