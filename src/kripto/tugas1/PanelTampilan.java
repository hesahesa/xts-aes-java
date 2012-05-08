/*
 * Class PanelTampilan extends JPanel
 * Version:
 * -1.0 (20-04-2012) develop class PanelTampilan
 *
 * Copyright 2010 Omar Abdillah, Prahesa Kusuma Setia, Yahya Muhammad
 */
package kripto.tugas1;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;


/** Class PanelTampilan
  * Class ini berfungsi untuk membuat panel yang merupakan tampilan atas dari program ini.
  *
  * @author Omar Abdillah (0906510451)
  * @author Prahesa Kusuma  Setia (0906510470)
  * @author Yahya Muhammad (0906510565)
  */
public class PanelTampilan extends JPanel{

  /** Label judul */
  JLabel judul;
  /** Label tugas */
  JLabel tugas;
  /** Objek bertipe JLabel yang nantinya menunjuk ke author kedua */
  JLabel kripto;

  //Constructor

  /** Constructor PanelTampilan() merupakan sebuah
    * constructor dari class PanelTampilan yang melakukan
    * pembuatan objek panel berisi header pada frame utama
    */
  public PanelTampilan()
  {
    initComponents();
    setLayout(new GridLayout(3,1));
    setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    setLocation(10,10);
    setSize(690,80);
    setBackground(Color.GRAY);
    add(judul);
    add(tugas);
    add(kripto);
  }

  //Methods

  /** Method initComponents() merupakan method yang berfungsi untuk memasukkan atribut-atribut seperti
    * judul, tugas dan kripto ke dalam panel.
    * Serta melakukan pengaturan tataletak dan ukuran dari atribut-atribut tersebut
    */
  private void initComponents()
  {
    judul = new JLabel("Proses Enkripsi dan Deskripsi XTS-AES");
    judul.setHorizontalAlignment(SwingConstants.CENTER);
    judul.setSize(680,40);
    judul.setFont(new Font("Times New Roman",0,25));
    judul.setForeground(Color.white);
    judul.setHorizontalTextPosition(SwingConstants.CENTER);
    judul.setVerticalTextPosition(SwingConstants.CENTER);

    tugas = new JLabel("Tugas Mata Kuliah Kriptografi dan Keamanan Informasi");
    tugas.setHorizontalAlignment(SwingConstants.CENTER);
    tugas.setSize(680,20);
    tugas.setFont(new Font("Arial",0,13));
    tugas.setForeground(Color.white);
    tugas.setHorizontalTextPosition(SwingConstants.CENTER);
    tugas.setVerticalTextPosition(SwingConstants.CENTER);

    kripto = new JLabel("Fakultas Ilmu Komputer Universitas Indonesia");
    kripto.setHorizontalAlignment(SwingConstants.CENTER);
    kripto.setSize(680,20);
    kripto.setFont(new Font("Arial",0,13));
    kripto.setForeground(Color.white);
    kripto.setHorizontalTextPosition(SwingConstants.CENTER);
    kripto.setVerticalTextPosition(SwingConstants.CENTER);
  }
}

