/*
 * Class OpenPlainText 
 * Version:
 * -1.0 (20-04-2012) develop class OpenPlainText
 *
 * Copyright 2010 Omar Abdillah, Prahesa Kusuma Setia, Yahya Muhammad
 */
package kripto.tugas1;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
* Kelas OpenPlainText ini merupakan kelas yang akan menampilkan 
* windows pilihan terkait file yang akan digunakan sebagai plaintext
* @author Omar Abdillah (0906510451)
* @author Prahesa Kusuma  Setia (0906510470)
* @author Yahya Muhammad (0906510565)
*/
final class OpenFile {

    // atribut yang bertipe JFileChooser yang digunakan untuk memberikan pilihan kepada user
    private JFileChooser pilihan;
    // atribut yang bertipe File yang digunakan sebagai file yang dipilih user
    private File file;
    // atribut yang bertipe String yang digunakan untuk menangkap dari path suatu file dalam direktori
    public String source;

    // Constructor
    
    /*
     * Constructor ini berisi kode-kode program untuk menampilkan JFileChooser
     * serta eksekusi yang dilakukan.
     */
    public OpenFile() {
        pilihan =  new JFileChooser(); // inisiasi atribut pilihan
        int result = pilihan.showOpenDialog(null); // result sebagai return value dari showSaveDialog
        if( result == JFileChooser.APPROVE_OPTION) {
            loadFile(); // pemanggilan method loadFile()
        } else if ( result ==  JFileChooser.CANCEL_OPTION) {
            return;
        }
    }


    /*
     * Method loadFile() merupakan method yang berfungsi 
     * untuk menangkap file yang dipilih oleh user
     * dan melakukan eksekusi selanjutnya.
     */
    public void loadFile() {
        // menangkap file yang dipilih dengan atribut file
        file = pilihan.getSelectedFile(); // inisiasi dari atribut file
        source = file.getPath();
    }
}