/*
 * Class XTSAES
 * Version:
 * -1.0 (20-04-2012) develop class XTSAES
 *
 * Copyright 2010 Omar Abdillah, Prahesa Kusuma Setia, Yahya Muhammad
 */
package kripto.tugas1;

import java.io.*;

/**
 * Class XTSAES Class ini berisi barisan perintah yang digunakan untuk melakukan
 * enkripsi maupun dekripsi data dari plaintext dengan key tertentu
 *
 * @author Omar Abdillah (0906510451)
 * @author Prahesa Kusuma Setia (0906510470)
 * @author Yahya Muhammad (0906510565)
 */
class XTSAES {

    /**
     * variabel-variabel yang digunakan
     */
    private String FILE_PLAIN;
    private String FILE_CIPHER;
    private String FILE_KEY;
    private static int BLOCK_SIZE = 16;                     //128-bits (16-bytes)
    private static int KEY_LENGTH_HEX = 64;                 //256-bits (32-bytes)
    private static byte[] nonce = Util.hex2byte("12345678901234567890123456789012");
    private static int NUMBER_OF_THREAD = 100;
    private byte[] nonceDP = null;
    private byte[][] multiplyDP = null;

    // Constructor dari kelas XTSAES
    public XTSAES(String plain, String key, String cipher) {
        this.FILE_PLAIN = plain;
        this.FILE_KEY = key;
        this.FILE_CIPHER = cipher;
    }

    /**
     * method eksekusiEnkripsi merupakan method yang berfungsi untuk menyiapkan
     * data-data yang akan dienkripsi
     *
     * @param key merupakan key yang digunakan untuk proses enkripsi
     * @param plain merupakan plaintext dari proses enkripsi
     * @param cipher merupakan hasil dari proses enkripsi
     * @throws Exception
     */
    public void eksekusiEnkripsi(String key, String plain, String cipher) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(key));
        String baca = br.readLine();
        br.close();
        String key1 = baca.substring(0, KEY_LENGTH_HEX / 2);
        String key2 = baca.substring(KEY_LENGTH_HEX / 2, baca.length());
        System.out.println("key1\t= " + key1);
        System.out.println("key2\t= " + key2);
        System.out.println("tweak\t= " + Util.toHEX1(nonce));
        RandomAccessFile raf1 = new RandomAccessFile(plain, "r");
        RandomAccessFile raf2 = new RandomAccessFile(cipher, "rw");
        // pemanggilan method XTSAESEncrypt untuk melakukan enkripsi
        XTSAESEncrypt(raf1, raf2, Util.hex2byte(key1), Util.hex2byte(key2), nonce);
        raf1.close();
        raf2.close();
        System.out.println("Encryption Done!");
    }

    /**
     * merupakan method yang digunakan untuk menyiapkan data-data yang akan
     * didekripsi
     *
     * @param key merupakan key yang digunakan dalam proses dekripsi
     * @param plain merupakan plaintext dari proses dekripsi
     * @param cipher merupakan hasil dari proses dekripsi
     * @throws Exception
     */
    public void eksekusiDekripsi(String key, String plain, String cipher) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(key));
        String baca = br.readLine();
        br.close();
        String key1 = baca.substring(0, KEY_LENGTH_HEX / 2);
        String key2 = baca.substring(KEY_LENGTH_HEX / 2, baca.length());
        System.out.println("key1\t= " + key1);
        System.out.println("key2\t= " + key2);
        System.out.println("tweak\t= " + Util.toHEX1(nonce));
        RandomAccessFile raf1 = new RandomAccessFile(plain, "r");
        RandomAccessFile raf2 = new RandomAccessFile(cipher, "rw");
        //pemanggilan method XTSAESDecrypt untuk melakukan dekripsi data
        XTSAESDecrypt(raf1, raf2, Util.hex2byte(key1), Util.hex2byte(key2), nonce);
        raf1.close();
        raf2.close();
        System.out.println("Decryption Done!");
    }

    /**
     * merupakan method yang melakukan enkripsi data
     *
     * @param in merupakan input dari proses enkripsi
     * @param out merupakan output dari proses enkripsi
     * @param key1 merupakan key bagian pertama
     * @param key2 merupakan key bagian kedua
     * @param i
     * @throws Exception
     */
    public void XTSAESEncrypt(RandomAccessFile in, RandomAccessFile out,
            byte[] key1, byte[] key2, byte[] i) throws Exception {
        long fileSize = in.length();
        int m = (int) (fileSize / BLOCK_SIZE);
        int b = (int) (fileSize % BLOCK_SIZE);
        byte[][] bufferIn = new byte[m + 1][16];
        bufferIn[m] = new byte[b];
        for (int a = 0; a < bufferIn.length; a++) {
            in.read(bufferIn[a]);
        }
        byte[][] bufferOut = new byte[m + 1][16];
        bufferOut[m] = new byte[b];
        AES aes = new AES();
        aes.setKey(key2);
        if(nonceDP==null) nonceDP = aes.encrypt(i);
        buildTable(nonceDP, m + 1);

        Thread[] worker = new Thread[NUMBER_OF_THREAD];
        for (int a = 0; a <= m - 2; a++) {
            worker[a % NUMBER_OF_THREAD] = new Thread(new WorkerThread(WorkerThread.MODE_ENCRYPT,
                    bufferOut[a], bufferIn[a], key1, key2, a, i));
            worker[a % NUMBER_OF_THREAD].start();
            if (a % NUMBER_OF_THREAD == NUMBER_OF_THREAD - 1) {
                for (int aa = 0; aa < NUMBER_OF_THREAD; aa++) {
                    if (worker[aa] != null) {
                        worker[aa].join(0);
                    }
                }
            }
        }
        for (int a = 0; a < NUMBER_OF_THREAD; a++) {
            if (worker[a] != null) {
                worker[a].join(0);
            }
        }

        System.out.println("---finish all thread");
        if (b == 0) {
            System.out.println("---file size is divisible by block size");
            perBlockEncrypt(bufferOut[m - 1], bufferIn[m - 1], key1, key2, m - 1, i);
            bufferOut[m] = new byte[0];
        } else {
            System.out.println("---file size is not divisible by block size");
            byte[] cc = new byte[BLOCK_SIZE];
            perBlockEncrypt(cc, bufferIn[m - 1], key1, key2, m - 1, i);
            System.arraycopy(cc, 0, bufferOut[m], 0, b);
            byte[] cp = new byte[16 - b];
            int ctr = 16 - b;
            int xx = cc.length - 1;
            int yy = cp.length - 1;
            while (ctr-- != 0) {
                cp[yy--] = cc[xx--];
            }
            byte[] pp = new byte[16];
            for (int a = 0; a < b; a++) {
                pp[a] = bufferIn[m][a];
            }
            for (int a = b; a < pp.length; a++) {
                pp[a] = cp[a - b];
            }
            perBlockEncrypt(bufferOut[m - 1], pp, key1, key2, m, i);
        }
        for (int a = 0; a < bufferOut.length; a++) {
            out.write(bufferOut[a]);
        }
    }

    public void XTSAESDecrypt(RandomAccessFile in, RandomAccessFile out,
            byte[] key1, byte[] key2, byte[] i) throws Exception {
        long fileSize = in.length();
        int m = (int) (fileSize / BLOCK_SIZE);
        int b = (int) (fileSize % BLOCK_SIZE);
        byte[][] bufferIn = new byte[m + 1][16];
        bufferIn[m] = new byte[b];
        for (int a = 0; a < bufferIn.length; a++) {
            in.read(bufferIn[a]);
        }
        byte[][] bufferOut = new byte[m + 1][16];
        bufferOut[m] = new byte[b];AES aes = new AES();
        aes.setKey(key2);
        if(nonceDP==null) nonceDP = aes.encrypt(i);
        buildTable(nonceDP, m + 1);        


        Thread[] worker = new Thread[NUMBER_OF_THREAD];
        for (int a = 0; a <= m - 2; a++) {
            worker[a % NUMBER_OF_THREAD] = new Thread(new WorkerThread(WorkerThread.MODE_DECRYPT,
                    bufferOut[a], bufferIn[a], key1, key2, a, i));
            worker[a % NUMBER_OF_THREAD].start();
            if (a % NUMBER_OF_THREAD == NUMBER_OF_THREAD - 1) {
                for (int aa = 0; aa < NUMBER_OF_THREAD; aa++) {
                    if (worker[aa] != null) {
                        worker[aa].join(0);
                    }
                }
            }
        }
        for (int a = 0; a < NUMBER_OF_THREAD; a++) {
            if (worker[a] != null) {
                worker[a].join(0);
            }
        }

        System.out.println("---finish all thread");
        if (b == 0) {
            System.out.println("---file size is divisible by block size");
            perBlockDecrypt(bufferOut[m - 1], bufferIn[m - 1], key1, key2, m - 1, i);
            bufferOut[m] = new byte[0];
        } else {
            System.out.println("---file size is not divisible by block size");
            byte[] cc = new byte[BLOCK_SIZE];
            perBlockDecrypt(cc, bufferIn[m - 1], key1, key2, m, i);
            System.arraycopy(cc, 0, bufferOut[m], 0, b);
            byte[] cp = new byte[16 - b];
            int ctr = 16 - b;
            int xx = cc.length - 1;
            int yy = cp.length - 1;
            while (ctr-- != 0) {
                cp[yy--] = cc[xx--];
            }
            byte[] pp = new byte[16];
            for (int a = 0; a < b; a++) {
                pp[a] = bufferIn[m][a];
            }
            for (int a = b; a < pp.length; a++) {
                pp[a] = cp[a - b];
            }
            perBlockDecrypt(bufferOut[m - 1], pp, key1, key2, m - 1, i);
        }
        for (int a = 0; a < bufferOut.length; a++) {
            out.write(bufferOut[a]);
        }
    }

    /**
     * merupakan method yang digunakan untuk melakukan enkripsi per block data
     *
     * @param ret
     * @param plain
     * @param key1
     * @param key2
     * @param j
     * @param i
     */
    public void perBlockEncrypt(byte[] ret, byte[] plain, byte[] key1,
            byte[] key2, int j, byte[] i) {
        AES aes = new AES();
//        aes.setKey(key2);
//        if(nonceDP==null) nonceDP = aes.encrypt(i);
        byte[] t = multiplyByPowJ(nonceDP, j);
        byte[] pp = new byte[BLOCK_SIZE];
        for (int a = 0; a < pp.length; a++) {
            pp[a] = (byte) (plain[a] ^ t[a]);
        }
        aes = new AES();
        aes.setKey(key1);
        byte[] cc = aes.encrypt(pp);
        for (int a = 0; a < ret.length; a++) {
            ret[a] = (byte) (cc[a] ^ t[a]);
        }
    }

    /**
     * merupakan method yang digunakan untuk melakukan dekripsi per block data
     *
     * @param ret
     * @param cipher
     * @param key1
     * @param key2
     * @param j
     * @param i
     */
    public void perBlockDecrypt(byte[] ret, byte[] cipher, byte[] key1,
            byte[] key2, int j, byte[] i) {
        AES aes = new AES();
//        aes.setKey(key2);
//        if(nonceDP==null) nonceDP = aes.encrypt(i);
        byte[] t = multiplyByPowJ(nonceDP, j);
        byte[] cc = new byte[BLOCK_SIZE];
        for (int a = 0; a < cc.length; a++) {
            cc[a] = (byte) (cipher[a] ^ t[a]);
        }
        aes = new AES();
        aes.setKey(key1);
        byte[] pp = aes.decrypt(cc);
        for (int a = 0; a < ret.length; a++) {
            ret[a] = (byte) (pp[a] ^ t[a]);
        }
    }

    /**
     * merupakan method yang digunakan untuk melakukan multiplication
     *
     * @param a sebagai masukan nilai pertama
     * @param j sebagai masukan nilai kedua
     * @return array of bytes
     */
//    public byte[] multiplyByPowJ(byte[] a, int j) {
//        if (j == 0) {
//            return a;
//        }
//        byte[] hasil = new byte[BLOCK_SIZE];
//        for (int i = 0; i < j; i++) {
//            hasil[0] = (byte) ((2 * (a[0] % 128)) ^ (135 * (a[15] / 128)));
//            for (int k = 1; k < 16; k++) {
//                hasil[k] = (byte) ((2 * (a[k] % 128)) ^ (a[k - 1] / 128));
//            }
//            a = hasil;
//        }
//        return hasil;
//    }
    
    public byte[] multiplyByPowJ(byte[] a, int j) {
        return multiplyDP[j];
    }
    
    
    private void buildTable(byte[] a, int numBlock) {
        multiplyDP = new byte[numBlock][BLOCK_SIZE];
        multiplyDP[0] = a;
        for (int i = 1; i < numBlock; i++) {
            multiplyDP[i][0] = (byte) ((2 * (multiplyDP[i-1][0] % 128)) ^ (135 * (multiplyDP[i-1][15] / 128)));
            for (int k = 1; k < 16; k++) {
                multiplyDP[i][k] = (byte) ((2 * (multiplyDP[i-1][k] % 128)) ^ (multiplyDP[i-1][k - 1] / 128));
            }
        }
    }

    /**
     * Class WorjerThread merupakan kelas yang berfungsi untuk melakukan proses
     * enkripsi dan dekripsi sesuai dengan modenya
     *
     * @author Omar Abdillah (0906510451)
     * @author Prahesa Kusuma Setia (0906510470)
     * @author Yahya Muhammad (0906510565)
     */
    class WorkerThread implements Runnable {

        /**
         * variabel-variabel yang digunakan
         */
        public static final int MODE_ENCRYPT = 0;
        public static final int MODE_DECRYPT = 1;
        private int mode;
        private byte[] dest;
        private byte[] source;
        private byte[] key1;
        private byte[] key2;
        private int j;
        private byte[] i;

        // Constructor
        public WorkerThread(int mode, byte[] dest, byte[] source, byte[] key1,
                byte[] key2, int j, byte[] i) {
            this.mode = mode;
            this.dest = dest;
            this.source = source;
            this.key1 = key1;
            this.key2 = key2;
            this.j = j;
            this.i = i;
        }

        @Override
        public void run() {
            switch (this.mode) {
                case MODE_ENCRYPT:
                    perBlockEncrypt(dest, source, key1, key2, j, i);
                    break;
                case MODE_DECRYPT:
                    perBlockDecrypt(dest, source, key1, key2, j, i);
                    break;
            }
        }
    }
}