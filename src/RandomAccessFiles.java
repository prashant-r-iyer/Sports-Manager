import java.io.RandomAccessFile;

public class RandomAccessFiles {

    public static void add(RandomAccessFile raf, String data) {
        try {
            raf.seek(raf.length());
            raf.write(data.getBytes());
            raf.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert(RandomAccessFile raf, String data, int index) {
        try {
            byte[] bytes = new byte[(int) raf.length() - index];
            raf.seek(index);
            raf.read(bytes);
            raf.seek(index);
            raf.write(data.getBytes());
            raf.seek(index + data.length());
            raf.write(bytes);
            raf.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    // including start excluding end
    public static void delete(RandomAccessFile raf, int start, int end) {
        try {
            byte[] bytes = new byte[(int) raf.length() - end];
            raf.seek(end);
            raf.read(bytes);
            raf.seek(start);
            raf.write(bytes);
            raf.seek(raf.length() - (end - start));
            String blanks = "";
            for (int deleteCount = 0; deleteCount < end - start; deleteCount++) {
                blanks += " ";
            }
            raf.write(blanks.getBytes());
            raf.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    // including start excluding end
    public static String read(RandomAccessFile raf, int start, int end) {
        byte[] bytes = new byte[end - start];
        try {
            raf.seek(start);
            raf.read(bytes);
            raf.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }
    
}
