/*
 * Class FrameUtama extends JFrame
 * Version:
 * -1.0 (20-04-2012) develop class FrameUtama
 *
 * Copyright 2010 Omar Abdillah, Prahesa Kusuma Setia, Yahya Muhammad
 */
package kripto.tugas1;

import java.awt.Color;
import javax.swing.JFrame;

/** Class PanelTampilan
  * Class ini berfungsi untuk membuat frame utama dari tampilan program ini
  *
  * @author Omar Abdillah (0906510451)
  * @author Prahesa Kusuma  Setia (0906510470)
  * @author Yahya Muhammad (0906510565)
  */
public class FrameUtama extends JFrame {
    // atribut panelHeader sebagai objek dari kelas PanelTampilan
    private PanelTampilan panelHeader = new PanelTampilan();
    // atribut panelUtama sebagai objek dari kelas PanelUtama
    private PanelUtama panelUtama = new PanelUtama();

    // Constructor
    
    /*
     * Constructor dari kelas FrameUtama() yang berisi pengaturan dari 
     * frame yang akan digunakan sebagai tampilan dari program ini
     */
    public FrameUtama() {
        super( "XTS AES" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLayout(null);
        setSize( 700, 420 );
        setBackground(Color.blue);
        setVisible( true );
        setBounds(0,0,720,450);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().add(panelHeader);
        getContentPane().add(panelUtama);
        setVisible(true);
    }
}
