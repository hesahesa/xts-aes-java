/*
 * Class SaveFile
 * Version:
 * -1.0 (20-04-2012) develop class SaveFile
 *
 * Copyright 2010 Omar Abdillah, Prahesa Kusuma Setia, Yahya Muhammad
 */
package kripto.tugas1;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
* Kelas SaveFile ini merupakan kelas yang akan menampilkan 
* windows pilihan terkait file yang akan digunakan sebagai file hasil (ciphertext)
* @author Omar Abdillah (0906510451)
* @author Prahesa Kusuma  Setia (0906510470)
* @author Yahya Muhammad (0906510565)
*/
public final class SaveFile {

    // atribut yang bertipe JFileChooser yang digunakan untuk memberikan pilihan kepada user
    private JFileChooser pilihan;
    // atribut yang bertipe File yang digunakan sebagai file yang dipilih user
    private File file;
    // atribut yang bertipe String yang digunakan untuk menangkap dari path suatu file dalam direktori
    public String target;
    
    // Constructor
    
    /*
     * Constructor ini berisi kode-kode program untuk menampilkan JFileChooser
     * serta eksekusi yang dilakukan.
     */
    public SaveFile() {
        pilihan =  new JFileChooser(); // inisiasi atribut pilihan
        int result = pilihan.showSaveDialog(null); // result sebagai return value dari showSaveDialog
        if( result == JFileChooser.APPROVE_OPTION) {
            saveFile(); // pemanggilan method saveFile()
        } else if ( result ==  JFileChooser.CANCEL_OPTION) {
            return;
        }
    }

    /*
     * Method saveFile() merupakan method yang berfungsi 
     * untuk menangkap file yang dipilih oleh user
     * dan melakukan eksekusi selanjutnya.
     */
    public void saveFile() {
        // set file sebagai file yang dipilih oleh user
        file = pilihan.getSelectedFile(); // inisiasi dari atribut file
        target = file.getPath();
    }
}
