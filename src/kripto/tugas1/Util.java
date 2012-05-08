/*
 * Class Util
 * Version:
 * -1.0 (20-04-2012) develop class Util
 *
 * Copyright 2010 Omar Abdillah, Prahesa Kusuma Setia, Yahya Muhammad
 */
package kripto.tugas1;

/** Class Util
  * Class ini merupakan kelas untuk melakukan pengubahan-pengubahan terhadap data
  *
  * @author Omar Abdillah (0906510451)
  * @author Prahesa Kusuma  Setia (0906510470)
  * @author Yahya Muhammad (0906510565)
  */
class Util {

    /**
     * merupakan kelas yang digunakan untuk untuk mengkonversi short array ke byte array
     * @param sa merupakan short array yang akan dikonversi
     * @return byte array hasil representasi dari short array
     */
    public static byte[] short2byte (short[] sa) {
        int length = sa.length;
        byte[] ba = new byte[length * 2];
        for (int i = 0, j = 0, k; i < length; ) {
            k = sa[i++];
            ba[j++] = (byte)((k >>> 8) & 0xFF);
            ba[j++] = (byte)( k        & 0xFF);
        }
        return (ba);
    }

    /**
     * merupakan kelas yang digunakan untuk mengkonversi byte array ke short array
     * 
     * @param ba merupakan byte array yang akan dikonversi
     * @return hasil dari konversi yang berupa short array
     */
    public static short[] byte2short (byte[] ba) {
        int length = ba.length;
        short[] sa = new short[length / 2];
        for (int i = 0, j = 0; j < length / 2; ) {
            sa[j++] = (short)(((ba[i++] & 0xFF) <<  8) |
	               ((ba[i++] & 0xFF)      ));
        }
        return (sa);
    }

    /**
     * merupakan kelas yang digunakan untuk mengkonversi int array ke dalam byte array
     * 
     * @param ia merupakan int array yang akan dikonversi
     * @return merupakan byte array hasil dari konversi
     */
    public static byte[] int2byte (int[] ia) {
        int length = ia.length;
        byte[] ba = new byte[length * 4];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            ba[j++] = (byte)((k >>>24) & 0xFF);
            ba[j++] = (byte)((k >>>16) & 0xFF);
            ba[j++] = (byte)((k >>> 8) & 0xFF);
            ba[j++] = (byte)( k        & 0xFF);
        }
        return (ba);
    }

    /**
     * merupakan kelas yang digunakan untuk mengkonversi byte array ke int array
     * 
     * @param ba merupakan byte array yang akan dikonversi
     * @return merupakan int array hasil dari konversi
     */
    public static int[] byte2int (byte[] ba) {
        int length = ba.length;
        int[] ia = new int[length / 4];
        for (int i = 0, j = 0; j < length / 4; ) {
            ia[j++] = (((ba[i++] & 0xFF) << 24) |
	               ((ba[i++] & 0xFF) << 16) |
	               ((ba[i++] & 0xFF) <<  8) |
	               ((ba[i++] & 0xFF)      ));
        }
        return (ia);
    }


    /** mapping array dari  hex value (0-15) ke corresponding hex digit (0-9a-f). */
    public static final char[] HEX_DIGITS = {
        '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'
    };

    /**
     * utility method untuk mengonversi byte array ke hexadecimal string
     *
     * @param ba array of bytes yang akan dikonversi
     * @return representasi hex dari byte array tersebut   
     */
    public static String toHEX (byte[] ba) {
        int length = ba.length;
        char[] buf = new char[length * 3];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ba[i++];
            buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_DIGITS[ k        & 0x0F];
	    buf[j++] = ' ';
        }
        return new String(buf);
    }

    /**
     * utility method yang digunakan untuk mengonversi short array ke hexadecimal string
     * @param ia merupakan short array yang akan dikonversi
     * @return  merupakan hasil dari konversi berupa hexadecimal string
     */
    public static String toHEX (short[] ia) {
        int length = ia.length;
        char[] buf = new char[length * 5];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            buf[j++] = HEX_DIGITS[(k >>>12) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 8) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_DIGITS[ k        & 0x0F];
	    buf[j++] = ' ';
        }
        return new String(buf);
    }

    /**
     * utility method yang digunakan untuk mengonversi int array ke hexadecimal string
     * @param ia merupakan int array yang akan dikonversi
     * @return  merupakan hasil dari konversi berupa hexadecimal string
     */
    public static String toHEX (int[] ia) {
        int length = ia.length;
        char[] buf = new char[length * 10];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            buf[j++] = HEX_DIGITS[(k >>>28) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>>24) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>>20) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>>16) & 0x0F];
	    buf[j++] = ' ';
            buf[j++] = HEX_DIGITS[(k >>>12) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 8) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_DIGITS[ k        & 0x0F];
	    buf[j++] = ' ';
        }
        return new String(buf);
    }


    /**
     * merupakan method yang digunakan untuk mengonversi byte ke dalam hexadecimal string
     * @param b merupakan byte yang akan dikonversi
     * @return  merupakan hasil dari konversi
     */
    public static String toHEX1 (byte b) {
        char[] buf = new char[2];
	int j = 0;
        buf[j++] = HEX_DIGITS[(b >>> 4) & 0x0F];
        buf[j++] = HEX_DIGITS[ b        & 0x0F];
        return new String(buf);
    }
    
    /**
     * merupakan utility method yang diguankan untuk mengkonversi byte array ke hexadecimal string
     * @param ba merupakan byte array yang akan dikonversi
     * @return merupakan hasil dari konversi yang berupa hexadecimal string
     */
    public static String toHEX1 (byte[] ba) {
        int length = ba.length;
        char[] buf = new char[length * 2];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ba[i++];
            buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_DIGITS[ k        & 0x0F];
        }
        return new String(buf);
    }
    
    /**
     * merupakan utility method yang digunakan untuk mengonversi short array ke dalam hexadecimal string
     * @param ia merupakan short array yang akan dikonversi
     * @return merupakan hasil dari konversi berupa kexadesimal string
     */
    public static String toHEX1 (short[] ia) {
        int length = ia.length;
        char[] buf = new char[length * 4];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            buf[j++] = HEX_DIGITS[(k >>>12) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 8) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_DIGITS[ k        & 0x0F];
        }
        return new String(buf);
    }
    
    /**
     * merupakan utility method yang digunakan untuk mengonversi integer ke hexadecimal string
     * @param i merupakan integer yang akan dikonversi
     * @return merupakan hasil dari konversi berupa hexadecimal string
     */
    public static String toHEX1 (int i) {
        char[] buf = new char[8];
	int j = 0;
        buf[j++] = HEX_DIGITS[(i >>>28) & 0x0F];
        buf[j++] = HEX_DIGITS[(i >>>24) & 0x0F];
        buf[j++] = HEX_DIGITS[(i >>>20) & 0x0F];
        buf[j++] = HEX_DIGITS[(i >>>16) & 0x0F];
        buf[j++] = HEX_DIGITS[(i >>>12) & 0x0F];
        buf[j++] = HEX_DIGITS[(i >>> 8) & 0x0F];
        buf[j++] = HEX_DIGITS[(i >>> 4) & 0x0F];
        buf[j++] = HEX_DIGITS[ i        & 0x0F];
        return new String(buf);
    }

    
    /**
     * merupakan utility method yang digunakan untuk mengonversi int array ke dalam hexadecimal string
     * @param ia merupakan int array yang akan dikonversi
     * @return  merupakan hasil dari konversi berupa hexadecimal string
     */
    public static String toHEX1 (int[] ia) {
        int length = ia.length;
        char[] buf = new char[length * 8];
        for (int i = 0, j = 0, k; i < length; ) {
            k = ia[i++];
            buf[j++] = HEX_DIGITS[(k >>>28) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>>24) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>>20) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>>16) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>>12) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 8) & 0x0F];
            buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
            buf[j++] = HEX_DIGITS[ k        & 0x0F];
        }
        return new String(buf);
    }
    
    /**
     * merupakan utility method yang digunakan untuk mengonversi dari hexadecimal string ke dalam byte array
     * @param hex merupakan hexadecimal string yang akan dikonversi
     * @return merupakan hasil dari konversi berupa byte array
     */
    public static byte[] hex2byte(String hex) {
        int len = hex.length();
        byte[] buf = new byte[((len + 1) / 2)];

        int i = 0, j = 0;
        if ((len % 2) == 1)
            buf[j++] = (byte) hexDigit(hex.charAt(i++));

        while (i < len) {
            buf[j++] = (byte) ((hexDigit(hex.charAt(i++)) << 4) |
                                hexDigit(hex.charAt(i++)));
        }
        return buf;
    }

    /**
     * merupakan method yang digunakan untuk melakukan validasi terhadao input berupa hexadecimal string
     * @param hex input yang akan divalidasi
     * @return berupa boolean yang menyatakan kevalidan dari data
     */
    public static boolean isHex(String hex) {
        int len = hex.length();
	int i = 0;
	char ch;

	while (i < len) {
	     ch = hex.charAt(i++);
	     if (! ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'F') ||
	           (ch >= 'a' && ch <= 'f'))) return false;
	}
	return true;
    }
    
    /**
     * merupakan method yang digunakan untuk mengonversi karakter hexadecimal ke string
     * @param ch input yang berupa hexadecimal karakter
     * @return integer yang sesuai dengan karakter hexadecimalnya
     */
    public static int hexDigit(char ch) {
        if (ch >= '0' && ch <= '9')
            return ch - '0';
        if (ch >= 'A' && ch <= 'F')
            return ch - 'A' + 10;
        if (ch >= 'a' && ch <= 'f')
            return ch - 'a' + 10;

        return(0);
    }

}
