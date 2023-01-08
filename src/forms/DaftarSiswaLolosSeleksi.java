/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import database.koneksi;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author lanz1
 */
public class DaftarSiswaLolosSeleksi extends javax.swing.JInternalFrame {

    /**
     * Creates new form MasterBarang
     */
    public DaftarSiswaLolosSeleksi() {
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
       
       String query = "SELECT * FROM `siswa` WHERE status=\"Lulus\"";
       
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
        
        Object[] row = new Object[11];
        int no = 1;
        for(int i=0; i < list.size(); i++){
            row[0] = no++;
            row[1] = list.get(i).getNomorPeserta();
            row[2] = list.get(i).getTahunAjaran();
            row[3] = list.get(i).getNama();
            row[4] = list.get(i).getJenisKelamin();
            row[5] = list.get(i).getTanggalLahir();
            row[6] = list.get(i).getTempatLahir();
            row[7] = list.get(i).getNoNik();
            row[8] = list.get(i).getAlamat();
            row[9] = list.get(i).getNamaOrtu();
            row[10] = list.get(i).getStatus();
            
            model.addRow(row);
        }       
    }
  

 
        
    public void executeSQLQuery(String query, String pesan)
    {
        Statement st;
        
        try{
           st = con.createStatement();
            ImageIcon iconSucceed = new ImageIcon(getClass().getResource("images/checked.png"));
            ImageIcon iconFailed = new ImageIcon(getClass().getResource("images/remove.png"));
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1050, 710));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1050, 710));
        jPanel1.setPreferredSize(new java.awt.Dimension(733, 579));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nomor Peserta", "Tahun Ajaran", "Nama Peserta", "Jenis Kelamin", "Tanggal Lahir", "Tempat Kelahiran", "No NIK", "Alamat", "Nama Orang Tua", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 1020, 550));

        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Dashboard/ Daftar Peserta Lolos Seleksi");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        btnPrint.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/KartuPeserta.png"))); // NOI18N
        btnPrint.setText("PRINT PDF");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel1.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 65, 160, 50));

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

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        try {
           HashMap hash = new HashMap();
//            File file = new File(namaFile);
//                        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file.getPath());
//                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, con);
//                        JasperViewer.viewReport(jasperPrint, false);
    InputStream file = getClass().getResourceAsStream("/reports/daftar-peserta-lolos.jrxml");
    JasperDesign jasperDesign = JRXmlLoader.load(file);
    JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, con);
    JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
