/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import database.koneksi;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author lanz1
 */
public class DaftarCalonSiswa extends javax.swing.JInternalFrame {

    /**
     * Creates new form MasterBarang
     */
    public DaftarCalonSiswa() {
        initComponents();

        show_Siswa_inJTable();
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        jTable1.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,12 ));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(32,136,203));
        jTable1.getTableHeader().setForeground(new Color(255,255,255));
        jTable1.setRowHeight(25);
    }
    
    koneksi k = new koneksi();
    Connection con = k.connection;
    
    public ArrayList<siswa> getSiswaList()
    {
       ArrayList<siswa> siswaList = new ArrayList<siswa>();
       
       String query = "SELECT * FROM `siswa` WHERE id=id";
       
       Statement st;
       ResultSet rs;
       
       try{
           st = con.createStatement();
           rs = st.executeQuery(query);
           
           siswa Siswa;           
           while(rs.next()){
               
               Siswa = new siswa(rs.getInt("nomor_peserta"),rs.getInt("tahun_ajaran"),rs.getString("nama"),
                       rs.getString("jenis_kelamin"),rs.getDate("tanggal_lahir"), rs.getString("tempat_lahir"), 
                       rs.getString("akta_kelahiran"), rs.getString("kartu_keluarga"),
               rs.getString("no_nik"),rs.getString("alamat"),rs.getString("nama_ortu"),rs.getString("status"), rs.getInt("user_id")
               );
               siswaList.add(Siswa);
           }        
       }catch(Exception e){
           e.printStackTrace();
       }       
       return siswaList;
    }
    
    
    public void show_Siswa_inJTable(){
        
        ArrayList<siswa> list = getSiswaList();
        
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        
        Object[] row = new Object[13];
        int no = 1;
        for(int i=0; i < list.size(); i++){
           row[0] = no++;
            row[1] = list.get(i).getNomorPeserta();
            row[2] = list.get(i).getTahunAjaran();
            row[3] = list.get(i).getNama();
            row[4] = list.get(i).getJenisKelamin();
            row[5] = list.get(i).getTanggalLahir();
            row[6] = list.get(i).getTempatLahir();
            row[7] = list.get(i).getAktaKelahiran();
            row[8] = list.get(i).getKartuKeluarga();
            row[9] = list.get(i).getNoNik();
            row[10] = list.get(i).getAlamat();
            row[11] = list.get(i).getNamaOrtu();
            row[12] = list.get(i).getStatus();
            
            model.addRow(row);
        }       
    }

 public void clearData(){
     tfNomorPeserta.setText(null);
      cbStatus.setSelectedIndex(0);
       tfSearch.setText(null);
        tfNomorPeserta.setEditable(true);
 }
        
    public void executeSQLQuery(String query, String pesan)
    {
        Statement st;
        
        try{
           st = con.createStatement();
            ImageIcon iconSucceed = new ImageIcon(getClass().getClassLoader().getResource("images/checked.png"));
            ImageIcon iconFailed = new ImageIcon(getClass().getClassLoader().getResource("images/remove.png"));
           if(st.executeUpdate(query) == 1){
               
               //refresh jtable data
               DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
               model.setRowCount(0);
               show_Siswa_inJTable();
               
                JOptionPane.showMessageDialog(null, "Berhasil " +pesan, "Information", JOptionPane.INFORMATION_MESSAGE, iconSucceed);
           }
           else{
               JOptionPane.showMessageDialog(null, "Gagal" +pesan, "Information", JOptionPane.INFORMATION_MESSAGE, iconFailed);          
           }
                 
       }catch(Exception e){
           e.printStackTrace();
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
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        tfNomorPeserta = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        refresh = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1050, 710));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1050, 710));
        jPanel1.setPreferredSize(new java.awt.Dimension(733, 579));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Dashboard/ Daftar Calon Peserta Didik");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/changeStatys.png"))); // NOI18N
        jButton1.setText("Ubah Status");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 130, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 110, 50));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton3.setText("Hapus Data");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 150, 50));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nomor Peserta", "Tahun Ajaran", "Nama Calon Peserta", "Jenis Kelamin", "Tanggal Lahir", "Tempat Lahir", "Akta Kelahiran", "Kartu Keluarga", "Nomor NIK", "Alamat", "Nama Orang Tua", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 1050, 420));

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabel1.setText("Ubah Status");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        cbStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Ubah Status--", "Seleksi", "Lulus" }));
        jPanel1.add(cbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 140, 40));

        jLabel8.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabel8.setText("No. Peserta");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        tfNomorPeserta.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jPanel1.add(tfNomorPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 140, 40));

        jLabel9.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabel9.setText("Cari Peserta");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, -1, 20));

        tfSearch.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        tfSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSearchActionPerformed(evt);
            }
        });
        tfSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchKeyReleased(evt);
            }
        });
        jPanel1.add(tfSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 240, 40));

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ref.png"))); // NOI18N
        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        jPanel1.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 140, 150, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int testSelected = cbStatus.getSelectedIndex();
        
        try {
         if (testSelected<1) {
         JOptionPane.showMessageDialog(this, "Silahkan Pilih Status yang akan dirubah","Pesan Error",
         JOptionPane.ERROR_MESSAGE);
            }
 
        } catch (Exception e) {
            System.out.println(e);
        }
        String query = "UPDATE `siswa` SET `status` = '"+cbStatus.getSelectedItem()+"' WHERE `siswa`.`nomor_peserta`='"+tfNomorPeserta.getText()+"'";
        
        executeSQLQuery(query, "Merubah Status Siswa");        
        clearData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        tfNomorPeserta.setEditable(false);

        //menampilkan data yang di klik pada table ke dalam form

        int i = jTable1.getSelectedRow();

        TableModel model = jTable1.getModel();

          tfNomorPeserta.setText(model.getValueAt(i, 1).toString());
          cbStatus.getModel().setSelectedItem(model.getValueAt(i, 12).toString());


    }//GEN-LAST:event_jTable1MouseClicked

    public void ShowSearch(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        ImageIcon iconFailed = new ImageIcon(getClass().getClassLoader().getResource("images/remove.png"));
        int jumlahRow = jTable1.getRowCount();
        Statement st;
       ResultSet rs;
        for(int n = 0; n<jumlahRow; n++){ 
        model.removeRow(0);
        }   
            try {
           st = con.createStatement();
           rs = st.executeQuery("SELECT * FROM siswa WHERE nama like '%"+tfSearch.getText()+"%'");
           int i = 1;
           while(rs.next()){
            Object[] obj = new Object[13];
            obj[0] = i++;
            obj[1] = rs.getString("nomor_peserta");
            obj[2] = rs.getString("tahun_ajaran");
            obj[3] = rs.getString("nama");
            obj[4] = rs.getString("jenis_kelamin");
            obj[5] = rs.getString("tanggal_lahir");
            obj[6] = rs.getString("tempat_lahir");
            obj[7] = rs.getString("akta_kelahiran");
            obj[8] = rs.getString("kartu_keluarga");
            obj[9] = rs.getString("no_nik");
            obj[10] = rs.getString("alamat");
            obj[11] = rs.getString("nama_ortu");
            obj[12] = rs.getString("status");
            model.addRow(obj);
           }
            } catch (Exception e) {
                System.out.println(e);
            }
       
//       
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        clearData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        show_Siswa_inJTable();
        clearData();
    }//GEN-LAST:event_refreshActionPerformed

    private void tfSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSearchActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tfSearchActionPerformed

    private void tfSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchKeyReleased
        // TODO add your handling code here:
     ShowSearch();
    }//GEN-LAST:event_tfSearchKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int NotSelected = cbStatus.getSelectedIndex();
        try {
            if (NotSelected<1) {
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!","Pesan Error",
            JOptionPane.ERROR_MESSAGE);
            }else{
           int pilih = JOptionPane.showConfirmDialog(null, "Yakin ingin dihapus?", "Confirm",  JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
           if(pilih == JOptionPane.OK_OPTION){
               String query = "DELETE FROM `siswa` WHERE nomor_peserta="+tfNomorPeserta.getText();
               executeSQLQuery(query, "Menghapus Data");
               clearData();     
            }
           
        }  
        } catch (Exception e) {
            
            System.out.println(e);
        }
   
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton refresh;
    private javax.swing.JTextField tfNomorPeserta;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
