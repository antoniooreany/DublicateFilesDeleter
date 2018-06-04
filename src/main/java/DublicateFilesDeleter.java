import java.io.File;
import java.io.FileInputStream;

/**
 * Created by gorshkov on 30.05.2018.
 */
public class DublicateFilesDeleter {
    public static void main(String[] args) throws Exception {
        /*

        docs 130456
        txts

        */

        long timeMillis0 = System.currentTimeMillis();
        deleteDublicateFiles(new File("D:\\Old_D — копия\\docs\\"));
        long timeMillis1 = System.currentTimeMillis();
        System.out.println(timeMillis1 - timeMillis0);
    }

    private static void deleteDublicateFiles(File folder) throws Exception {
        long filesChecked = 0;
        long filesDeleted = 0;
        File[] folderEntries = folder.listFiles();
        for (File entry0 : folderEntries) {
            if (entry0.isDirectory()) {
                deleteDublicateFiles(entry0);
                continue;
            }
            int equalFilesCount = 0;
            String absolutePath0 = entry0.getAbsolutePath();
            for (File entry1 : folderEntries) {
                if (entry0.length() == entry1.length()) {
                    String hash0 = getHash(entry0); //check for identity by hash: 1187
                    String hash1 = getHash(entry1); // 208 657 / 1 187 = 175,78
                    boolean hashIsEquals = hash0.compareTo(hash1) == 0;
                    if (hashIsEquals) equalFilesCount++;

//                    final BinaryCompare binaryCompare = new BinaryCompare(); //check for identity byte-by-byte: 208657
//                    final boolean compareExactly = binaryCompare.compareExactly(entry0, entry1); // 208 657 / 1 187 = 175,78
//                    if (compareExactly) equalFilesCount++;

                }
                if (equalFilesCount > 1) {
//                    final String entry1Name = entry1.getName();
//                    final String entry1Parent = entry1.getParent();
//                    final File entry1Dest = new File(entry1Parent + "\\____" + entry1Name);
//                    final boolean renameTo = entry1.renameTo(entry1Dest);
                    final boolean deleted = entry0.delete();
                    filesDeleted++;
                    equalFilesCount = 0;
                }
                filesChecked++;
//                Runtime.getRuntime().exec("cls");
                System.out.println("Checking:  " + absolutePath0);
                System.out.println("Checked:   " + filesChecked);
                System.out.println("Deleted:   " + filesDeleted);
            }
        }
    }

    private static String getHash(File entry) throws Exception {
        byte[] fileInArray = new byte[(int) entry.length()];
        FileInputStream fis = new FileInputStream(entry);
        int read = fis.read(fileInArray);
        String hash = MD5CheckSum.getHash(fileInArray);
        fis.close();
        return hash;
    }
}

