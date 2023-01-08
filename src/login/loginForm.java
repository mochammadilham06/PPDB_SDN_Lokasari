/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import database.koneksi;
import forms.DashboardKS;
import forms.DashboardOperator;
import forms.KepalaSekolah;
import forms.Operator;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 *
 * @author lanz1
 */
public class loginForm extends javax.swing.JFrame {
    
    Color penDefault, penEnter, penClick, fontColorClicked,fontColorDefault;

    koneksi k = new koneksi();
    Connection con = k.connection;
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    Object set;

    
    public loginForm() {
        initComponents();
        
        penDefault = new Color(34, 47, 63);
        fontColorDefault = new Color(255,255,255);
        penEnter = new Color(50, 81, 120);
        fontColorClicked = new Color(34, 47, 63);
        penClick = new Color(255,255,255);
        
        jPanelLogin.setBackground(penDefault);
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
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        jPanelLogin = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(700, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(780, 400));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUsername.setFont(new java.awt.Font(".SF NS Text", 1, 14)); // NOI18N
        txtUsername.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 340, 30));

        txtPassword.setFont(new java.awt.Font(".SF NS Text", 1, 18)); // NOI18N
        txtPassword.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 340, 30));

        jLabel8.setFont(new java.awt.Font(".SF NS Text", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(34, 47, 63));
        jLabel8.setText("Username");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, -1, -1));

        jPanelLogin.setBackground(new java.awt.Color(34, 47, 63));
        jPanelLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelLoginMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelLoginMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelLoginMouseReleased(evt);
            }
        });
        jPanelLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanelLoginKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font(".SF NS Text", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Login");

        javax.swing.GroupLayout jPanelLoginLayout = new javax.swing.GroupLayout(jPanelLogin);
        jPanelLogin.setLayout(jPanelLoginLayout);
        jPanelLoginLayout.setHorizontalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        jPanelLoginLayout.setVerticalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, 130, 40));

        jPanel2.setBackground(new java.awt.Color(34, 47, 63));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/250.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font(".SF NS Text", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Version 1.0");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, -1, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 440));

        jLabel9.setFont(new java.awt.Font(".SF NS Text", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(34, 47, 63));
        jLabel9.setText("Password");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void jPanelLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelLoginMouseClicked
        // TODO add your handling code here:
        String sql = "SELECT * FROM login WHERE username=? AND password=?";

        try{
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, txtUsername.getText());
            ps.setString(2, txtPassword.getText());
            rs = ps.executeQuery();
            //
            String kepalaSekolah = "Asep Saepudin M.Pd";
            String operator = "Deri Supriadi";
            ImageIcon iconChecked = new ImageIcon(getClass().getClassLoader().getResource("images/checked.png"));
            ImageIcon iconUnchecked = new ImageIcon(getClass().getClassLoader().getResource("images/remove.png"));

            if(rs.next())
            {
                switch (rs.getString("role")) {
                    
                    case "Operator":
                        JOptionPane.showMessageDialog(null, "Selamat Datang "+operator,"Message",JOptionPane.INFORMATION_MESSAGE,iconChecked);
                        Operator g = new Operator();
                        g.setVisible(true);
                        this.dispose();
                        break;
                    case "Kepala Sekolah":
                        JOptionPane.showMessageDialog(null, "Selamat Datang "+kepalaSekolah,"Message",JOptionPane.INFORMATION_MESSAGE,iconChecked);
                        KepalaSekolah o = new KepalaSekolah();
                        o.setVisible(true);
                        this.dispose();
                        break;
                    default:
                        break;
                }

            }else{
                JOptionPane.showMessageDialog(null, "Usernama atau Password Tidak Sesuai!","Message",
                    JOptionPane.ERROR_MESSAGE, iconUnchecked);
                txtUsername.setText("");
                txtPassword.setText("");
                txtUsername.requestFocus();
            }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jPanelLoginMouseClicked

    private void jPanelLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelLoginMouseEntered
        // TODO add your handling code here:

        jPanelLogin.setBackground(penEnter);
        jLabel3.setForeground(fontColorDefault);
    }//GEN-LAST:event_jPanelLoginMouseEntered

    private void jPanelLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelLoginMouseExited
        // TODO add your handling code here:
        jPanelLogin.setBackground(penDefault);
        jLabel3.setForeground(fontColorDefault);
    }//GEN-LAST:event_jPanelLoginMouseExited

    private void jPanelLoginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelLoginMousePressed
        // TODO add your handling code here:
        jPanelLogin.setBackground(penDefault);
        jLabel3.setForeground(fontColorDefault);
    }//GEN-LAST:event_jPanelLoginMousePressed

    private void jPanelLoginMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelLoginMouseReleased
        // TODO add your handling code here:

        jPanelLogin.setBackground(penDefault);
        jLabel3.setForeground(fontColorDefault);
    }//GEN-LAST:event_jPanelLoginMouseReleased

    private void jPanelLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanelLoginKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelLoginKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
          
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/250.png"));
   setIconImage(icon.getImage());
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelLogin;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
