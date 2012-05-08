/*
 * Class PanelUtama extends JPanelimplements ActionListener
 * Version:
 * -1.0 (20-04-2012) develop class PanelUtama
 *
 * Copyright 2010 Omar Abdillah, Prahesa Kusuma Setia, Yahya Muhammad
 */
package kripto.tugas1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EtchedBorder;


/** Class PanelUtama
  * Class ini berfungsi untuk membuat panel yang merupakan tampilan badan dari program ini.
  *
  * @author Omar Abdillah (0906510451)
  * @author Prahesa Kusuma  Setia (0906510470)
  * @author Yahya Muhammad (0906510565)
  */
class PanelUtama extends JPanel implements ActionListener  {
    // lebel-label yang digunakan dalam program ini
    private JLabel judul, plaintext, key, ciphertext;
    // textfield yang digunakan
    private JTextField source, keyField, target;
    // button-button yang digunakan
    private JButton dekripsi, enkripsi, openKey, openFile, saveFile;
    // data-data bertipe String yang digunakan dalam program ini
    String namaSource, namaKey, namaTarget;
    
    //Constructor

    /** Constructor PanelUtama() merupakan sebuah
    * constructor dari class PanelUtama yang melakukan
    * pembuatan objek panel berisi label, button, dan textfiled yang ada pada tampilan program.
    */
    public PanelUtama () {
        setLayout(null);
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        initComponents();
        setLocation(10,100);
        setSize(692,300);
        addTampilan();
       
    }

     // Method
    
    /** Method addTampilan() merupakan sebuah method
    * yang berisi perintah-perintah add() dari semua atribut 
    * yang digunakan dalam kelas ini
    */
    private void addTampilan() {
        add(judul);
        add(plaintext);
        add(key);
        add(ciphertext);
        add(source);
        add(keyField);
        add(target);
        add(dekripsi);
        add(enkripsi);
        add(openKey);
        add(openFile);
        add(saveFile);
    }
    
    // Method
    
    /*
     * Method initComponents() berisi pengaturan dari tata letak dan ukuran dari 
     * atribut-atribut yang digunakan dalam kelas ini.
     */
    private void initComponents() {
        judul = new JLabel("EKRIPSI DAN DEKRIPSI XTS-AES");
        judul.setHorizontalAlignment(SwingConstants.CENTER);
        judul.setSize(680,40);
        judul.setFont(new Font("Times New Roman",0,25));
        judul.setHorizontalTextPosition(SwingConstants.CENTER);
        judul.setVerticalTextPosition(SwingConstants.CENTER);
        
        plaintext = new JLabel("Source         :");
        plaintext.setHorizontalAlignment(SwingConstants.LEFT);
        plaintext.setSize(220,20);
        plaintext.setFont(new Font("Times New Roman",0,20));
        plaintext.setHorizontalTextPosition(SwingConstants.CENTER);
        plaintext.setVerticalTextPosition(SwingConstants.CENTER);
        plaintext.setLocation(10,70);
        
        key = new JLabel("Key             :");
        key.setHorizontalAlignment(SwingConstants.LEFT);
        key.setSize(220,20);
        key.setFont(new Font("Times New Roman",0,20));
        key.setHorizontalTextPosition(SwingConstants.CENTER);
        key.setVerticalTextPosition(SwingConstants.CENTER);
        key.setLocation(10,120);
        
        ciphertext = new JLabel("Target       :");
        ciphertext.setHorizontalAlignment(SwingConstants.LEFT);
        ciphertext.setSize(220,20);
        ciphertext.setFont(new Font("Times New Roman",0,20));
        ciphertext.setHorizontalTextPosition(SwingConstants.CENTER);
        ciphertext.setVerticalTextPosition(SwingConstants.CENTER);
        ciphertext.setLocation(10,170);

        source =new JTextField(20);
        source.setSize(source.getPreferredSize());
        source.setLocation(130,70);
        source.setSize(220,20);
        
        keyField =new JTextField(20);
        keyField.setSize(keyField.getPreferredSize());
        keyField.setLocation(130,120);
        keyField.setSize(220,20);
        
        target =new JTextField(20);
        target.setSize(target.getPreferredSize());
        target.setLocation(130,170);
        target.setSize(220,20);

        dekripsi = new JButton("Dekripsi");
        dekripsi.setSize(100,20);
        dekripsi.setLocation(190,230);
        dekripsi.addActionListener(this);
        
        enkripsi = new JButton("Enkripsi");
        enkripsi.setSize(100,20);
        enkripsi.setLocation(350,230);
        enkripsi.addActionListener(this);
        
        openFile = new JButton("Source");
        openFile.setSize(150,20);
        openFile.setLocation(400,70);
        openFile.addActionListener(this);
        
        openKey = new JButton("Key");
        openKey.setSize(150,20);
        openKey.setLocation(400,120);
        openKey.addActionListener(this);
        
        saveFile = new JButton("Target");
        saveFile.setSize(150,20);
        saveFile.setLocation(400,170);
        saveFile.addActionListener(this);
    }

    // Method
    
    /*
     * Method actionPerformed() merupakan method override dari interface ActionListener
     * Method ini digunakan untuk mengatur event-event atau aksi-aksi
     * yang akan dilaksanakan ketika terjadi sebuah perintah dari user
     * 
     * @param ActionEvent aksi merupakan perintah dari user
     */
    @Override
    public void actionPerformed(ActionEvent aksi) {
        if ( aksi.getSource() == openFile) {
            OpenFile openFile = new OpenFile();
            namaSource = openFile.source;
            source.setText(namaSource);
        } else if (aksi.getSource() == openKey) {
            OpenKey openKeytext = new OpenKey();
            namaKey = openKeytext.key;
            keyField.setText(namaKey);
        } else if(aksi.getSource() == saveFile) {
            SaveFile save = new SaveFile();
            namaTarget = save.target;
            target.setText(namaTarget);
        } else if (aksi.getSource() == enkripsi) {
            XTSAES aes = new XTSAES(namaSource, namaKey, namaTarget);
            try {
                aes.eksekusiEnkripsi(namaKey, namaSource, namaTarget);
            } catch (Exception ex) {
                Logger.getLogger(PanelUtama.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (aksi.getSource() == dekripsi) {
            XTSAES aes = new XTSAES(namaSource, namaKey, namaTarget);
            try {
                aes.eksekusiDekripsi(namaKey, namaSource, namaTarget);
            } catch (Exception ex) {
                Logger.getLogger(PanelUtama.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   


}
