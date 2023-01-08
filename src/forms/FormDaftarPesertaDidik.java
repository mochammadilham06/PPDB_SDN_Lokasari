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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;
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
public class FormDaftarPesertaDidik extends javax.swing.JInternalFrame {

   public FormDaftarPesertaDidik() {
   initComponents();

show_Siswa_inJTable();
autoIncrement();
limitCallendar();
tfNomorPeserta.setEditable(false);
setButtonConfig button = new setButtonConfig();
button.setMainButton(true);
button.setSecondaryButton(false, false);



        
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
       
       String query = "SELECT * FROM `siswa` WHERE status=\"Seleksi\"";
       
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

    //JDateChoser get Data
   
    
    public void clearData(){
//        tfNomorPeserta.setEditable(false);
        tfTahunAjaran.setText(null);
        tfNamaPeserta1.setText(null);
        cbJenisKelamin.setSelectedIndex(0);
        tfTanggalLahir.setDate(null);
        tfTempatLahir.setText(null);
        tfImportKK.setText(null);
        tfImportAkta.setText(null);
        tfNIK.setText(null);
        tfAlamat.setText(null);
        tfOrtu.setText(null);
        tfNomorPeserta.setEditable(true);
        setButtonConfig button = new setButtonConfig();
        button.setMainButton(true);
        button.setSecondaryButton(false, false);
    }
   
    public void autoIncrement(){
        try {
    String query = "SELECT MAX(nomor_peserta)FROM siswa";
    Statement s = con.createStatement();
    ResultSet rs = s.executeQuery(query);
    rs.next();
    rs.getString("MAX(nomor_peserta)");
    
            if (rs.getInt("MAX(nomor_peserta)")<1) {
                tfNomorPeserta.setText("1");
            }else{
                int id = (rs.getInt("MAX(nomor_peserta)"));
                id++;
                tfNomorPeserta.setText(""+id);
            }
         
        } catch (Exception e) {
            System.out.println(e);
        }    }
    
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
    
    class setButtonConfig{
        
       public void setMainButton(boolean x){
           BtnTambah.setEnabled(x);
       } 
       
       public void setSecondaryButton(boolean x, boolean y){
       btnUbah.setEnabled(x);
       btnCetak.setEnabled(y);
       }
    }
   
    class handling{
    public void setTahunAjaranHandler(){
    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(0);
    formatter.setMaximum(4);
    formatter.setAllowsInvalid(false);
    // If you want the value to be committed on each keystroke instead of focus lost
    formatter.setCommitsOnValidEdit(true);
    JFormattedTextField field = new JFormattedTextField(formatter);
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
        tfTanggalLahir = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfTempatLahir = new javax.swing.JTextField();
        cbJenisKelamin = new javax.swing.JComboBox<>();
        tfImportAkta = new javax.swing.JTextField();
        tfImportKK = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnCancel = new javax.swing.JButton();
        BtnTambah = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfNomorPeserta = new javax.swing.JTextField();
        tfTahunAjaran = new javax.swing.JTextField();
        tfNIK = new javax.swing.JTextField();
        tfOrtu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnUbah = new javax.swing.JButton();
        tfNamaPeserta1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tfAlamat = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1050, 710));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1050, 710));
        jPanel1.setPreferredSize(new java.awt.Dimension(733, 579));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tfTanggalLahir.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(tfTanggalLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 170, 40));

        jLabel1.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel1.setText("Tanggal Lahir");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 100, -1));

        jLabel2.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel2.setText("Tempat Lahir");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 100, -1));

        jLabel3.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel3.setText("Nomor AKTA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 100, -1));

        jLabel4.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel4.setText("Nomor KK");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 80, -1));

        jLabel6.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel6.setText("Jenis Kelamin");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        tfTempatLahir.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        tfTempatLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTempatLahirKeyTyped(evt);
            }
        });
        jPanel1.add(tfTempatLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 170, 40));

        cbJenisKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih Jenis Kelamin--", "Laki-laki", "Perempuan" }));
        jPanel1.add(cbJenisKelamin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 170, 40));

        tfImportAkta.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        tfImportAkta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfImportAktaKeyTyped(evt);
            }
        });
        jPanel1.add(tfImportAkta, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 170, 40));

        tfImportKK.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        tfImportKK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfImportKKKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfImportKKKeyTyped(evt);
            }
        });
        jPanel1.add(tfImportKK, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 170, 40));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 1020, 300));

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btnCancel.setText("Batal");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 120, 50));

        BtnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconTambah.png"))); // NOI18N
        BtnTambah.setText("Tambah Calon Peserta");
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });
        jPanel1.add(BtnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 280, 180, 50));

        btnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/KartuPeserta.png"))); // NOI18N
        btnCetak.setText("Cetak Kartu");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });
        jPanel1.add(btnCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 280, 140, 50));

        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Dashboard/ Form Input Calon Peserta");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel8.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel8.setText("No. Peserta");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel9.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel9.setText("Tahun Ajaran");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        tfNomorPeserta.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jPanel1.add(tfNomorPeserta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 100, 40));

        tfTahunAjaran.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        tfTahunAjaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfTahunAjaranKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTahunAjaranKeyTyped(evt);
            }
        });
        jPanel1.add(tfTahunAjaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 170, 40));

        tfNIK.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        tfNIK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNIKKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNIKKeyTyped(evt);
            }
        });
        jPanel1.add(tfNIK, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, 210, 40));

        tfOrtu.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        tfOrtu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfOrtuKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfOrtuKeyTyped(evt);
            }
        });
        jPanel1.add(tfOrtu, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 230, 210, 40));

        jLabel10.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel10.setText("NIK");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, -1, -1));

        jLabel11.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel11.setText("Alamat");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 140, -1, -1));

        jLabel12.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel12.setText("Nama Ortu");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 240, -1, -1));

        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        jPanel1.add(btnUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 110, 50));

        tfNamaPeserta1.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        tfNamaPeserta1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNamaPeserta1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNamaPeserta1KeyTyped(evt);
            }
        });
        jPanel1.add(tfNamaPeserta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 170, 40));

        jLabel13.setFont(new java.awt.Font(".SF NS Text", 0, 16)); // NOI18N
        jLabel13.setText("Nama");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        tfAlamat.setColumns(20);
        tfAlamat.setRows(5);
        tfAlamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfAlamatKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tfAlamat);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, 210, -1));

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

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
try {
//            String namaFile = "src/reports/kartu_peserta.jrxml";
            HashMap hash = new HashMap();
//            File file = new File(namaFile);
//                        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file.getPath());
//                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, con);
//                        JasperViewer.viewReport(jasperPrint, false);
InputStream file = getClass().getResourceAsStream("/reports/kartu_peserta.jrxml");
    JasperDesign jasperDesign = JRXmlLoader.load(file);
    JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, con);
    JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            System.out.println(e);
        }        
    }//GEN-LAST:event_btnCetakActionPerformed
public void limitCallendar(){
Calendar cal = Calendar.getInstance();
cal.add(Calendar.YEAR, -7);//7 year before
Date min = cal.getTime();
    Date max = new Date();
    tfTanggalLahir.setSelectableDateRange(min, max);
}
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        
        tfNomorPeserta.setEditable(false);
        ;
        //menampilkan data yang di klik pada table ke dalam form      
        
        int i = jTable1.getSelectedRow();
        
        TableModel model = jTable1.getModel();
       
        setButtonConfig btn = new setButtonConfig();
        btn.setMainButton(false);
        btn.setSecondaryButton(true,true);
   
        
        tfNomorPeserta.setText(model.getValueAt(i, 1).toString());
        tfTahunAjaran.setText(model.getValueAt(i, 2).toString());
        tfNamaPeserta1.setText(model.getValueAt(i,3).toString());
        cbJenisKelamin.getModel().setSelectedItem(model.getValueAt(i, 4).toString());
        tfTanggalLahir.setDate((Date) jTable1.getModel().getValueAt(i, 5));
        tfTempatLahir.setText(model.getValueAt(i, 6).toString());
        tfImportAkta.setText(model.getValueAt(i, 7).toString());
        tfImportKK.setText(model.getValueAt(i, 8).toString());
        tfNIK.setText(model.getValueAt(i, 9).toString());
        tfAlamat.setText(model.getValueAt(i, 10).toString());
        tfOrtu.setText(model.getValueAt(i, 11).toString());

        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
       String tampilan = "yyyy-MM-dd";
       SimpleDateFormat fm = new SimpleDateFormat(tampilan);
       String tanggal = String.valueOf(fm.format(tfTanggalLahir.getDate()));
       
        try {
            if (tfTanggalLahir == null) {
            JOptionPane.showMessageDialog(this, "Masukan Data terlebih dahulu!","Pesan Error",
            JOptionPane.ERROR_MESSAGE);
            System.out.println("Null");
            }
            else{
            String query = "INSERT INTO `siswa`(`tahun_ajaran`,`nomor_peserta`,`nama`, `jenis_kelamin`, `tanggal_lahir`, `tempat_lahir`, `akta_kelahiran`, `kartu_keluarga`, `no_nik`, `alamat`, `nama_ortu`, `status`) VALUES ('"+tfTahunAjaran.getText()+"', '"+tfNomorPeserta.getText()+"','"+tfNamaPeserta1.getText()+"','"+cbJenisKelamin.getSelectedItem()+"','"+tanggal+"','"+tfTempatLahir.getText()+"','"+tfImportAkta.getText()+"','"+tfImportKK.getText()+"','"+tfNIK.getText()+"','"+tfAlamat.getText()+"','"+tfOrtu.getText()+"','Seleksi')";
            executeSQLQuery(query, "Menambahkan Data Peserta");
            clearData();
            autoIncrement();
            tfNomorPeserta.setEditable(false);
            setButtonConfig btn = new setButtonConfig();
            btn.setMainButton(false);
            btn.setSecondaryButton(false, true);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
       

    }//GEN-LAST:event_BtnTambahActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        clearData();
        autoIncrement();
        tfNomorPeserta.setEditable(false);

    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        
       String tampilan = "yyyy-MM-dd";
       SimpleDateFormat fm = new SimpleDateFormat(tampilan);
       String tanggal = String.valueOf(fm.format(tfTanggalLahir.getDate()));
        int NotSelected = cbJenisKelamin.getSelectedIndex();
        try {
            if (NotSelected<1) {
            JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!","Pesan Error",
            JOptionPane.ERROR_MESSAGE);
            }else{
                String query = "UPDATE `siswa` SET `tahun_ajaran`='"+tfTahunAjaran.getText()+"',`nama`='"+tfNamaPeserta1.getText()+"',`jenis_kelamin`='"+cbJenisKelamin.getSelectedItem()+"',`tanggal_lahir`='"+tanggal+"',`tempat_lahir`='"+tfTempatLahir.getText()+"',`akta_kelahiran`='"+tfImportAkta.getText()+"',`kartu_keluarga`='"+tfImportKK.getText()+"',`no_nik`='"+tfNIK.getText()+"',`alamat`='"+tfAlamat.getText()+"',`nama_ortu`='"+tfOrtu.getText()+"' WHERE nomor_peserta ="+tfNomorPeserta.getText();
                executeSQLQuery(query, " Mengubah Data");
                clearData();
                autoIncrement();
                btnCetak.setEnabled(true);
                BtnTambah.setEnabled(false);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void tfTahunAjaranKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTahunAjaranKeyReleased
        int x;
        try {
            x = Integer.parseInt(tfTahunAjaran.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data harus berupa angka","Pesan Error",JOptionPane.ERROR_MESSAGE);
            tfTahunAjaran.setText(null);
        }
    }//GEN-LAST:event_tfTahunAjaranKeyReleased

    private void tfNamaPeserta1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNamaPeserta1KeyReleased
        int x; 
        try {
            x= Integer.parseInt(tfNamaPeserta1.getText());
            JOptionPane.showMessageDialog(this, "Data harus berupa Huruf","Pesan Error",JOptionPane.ERROR_MESSAGE);
            tfNamaPeserta1.setText(null);
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_tfNamaPeserta1KeyReleased

    private void tfImportKKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfImportKKKeyReleased
        long x;
        try {
            x = Long.parseLong(tfImportKK.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data harus berupa angka","Pesan Error",JOptionPane.ERROR_MESSAGE);
            tfImportKK.setText(null);
        }

    }//GEN-LAST:event_tfImportKKKeyReleased

    private void tfNIKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNIKKeyReleased
        long x;
        try {
            x = Long.parseLong(tfNIK.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data harus berupa angka","Pesan Error",JOptionPane.ERROR_MESSAGE);
            tfNIK.setText(null);
        }   
    }//GEN-LAST:event_tfNIKKeyReleased

    private void tfTahunAjaranKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTahunAjaranKeyTyped
    boolean max = tfTahunAjaran.getText().length() > 3;
    if ( max ){
        evt.consume();
        System.out.println("maximum");
    }      
    }//GEN-LAST:event_tfTahunAjaranKeyTyped

    private void tfNamaPeserta1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNamaPeserta1KeyTyped
    boolean max = tfNamaPeserta1.getText().length() > 29;
    if ( max ){
        evt.consume();
        System.out.println("maximum");
    }      
    }//GEN-LAST:event_tfNamaPeserta1KeyTyped

    private void tfTempatLahirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTempatLahirKeyTyped
    boolean max = tfTempatLahir.getText().length() > 39;
    if ( max ){
        evt.consume();
        System.out.println("maximum");
    }  
    }//GEN-LAST:event_tfTempatLahirKeyTyped

    private void tfImportAktaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfImportAktaKeyTyped
    boolean max = tfImportAkta.getText().length() > 11;
    if ( max ){
        evt.consume();
        System.out.println("maximum");
    }  
    }//GEN-LAST:event_tfImportAktaKeyTyped

    private void tfImportKKKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfImportKKKeyTyped
    boolean max = tfImportKK.getText().length() > 15;
    if ( max ){
        evt.consume();
        System.out.println("maximum");
    }      
    }//GEN-LAST:event_tfImportKKKeyTyped

    private void tfNIKKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNIKKeyTyped
    boolean max = tfNIK.getText().length() > 15;
    if ( max ){
        evt.consume();
        System.out.println("maximum");
    }  
    }//GEN-LAST:event_tfNIKKeyTyped

    private void tfAlamatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAlamatKeyTyped
     boolean max = tfAlamat.getText().length() > 39;
    if ( max ){
        evt.consume();
        System.out.println("maximum");
    } 
    }//GEN-LAST:event_tfAlamatKeyTyped

    private void tfOrtuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfOrtuKeyTyped
    boolean max = tfOrtu.getText().length() > 29;
    if ( max ){
        evt.consume();
        System.out.println("maximum");
    } 

    }//GEN-LAST:event_tfOrtuKeyTyped

    private void tfOrtuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfOrtuKeyReleased
        int x; 
        try {
            x= Integer.parseInt(tfOrtu.getText());
            JOptionPane.showMessageDialog(this, "Data harus berupa Huruf","Pesan Error",JOptionPane.ERROR_MESSAGE);
            tfOrtu.setText(null);
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_tfOrtuKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnTambah;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbJenisKelamin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea tfAlamat;
    private javax.swing.JTextField tfImportAkta;
    private javax.swing.JTextField tfImportKK;
    private javax.swing.JTextField tfNIK;
    private javax.swing.JTextField tfNamaPeserta1;
    private javax.swing.JTextField tfNomorPeserta;
    private javax.swing.JTextField tfOrtu;
    private javax.swing.JTextField tfTahunAjaran;
    private com.toedter.calendar.JDateChooser tfTanggalLahir;
    private javax.swing.JTextField tfTempatLahir;
    // End of variables declaration//GEN-END:variables
}
