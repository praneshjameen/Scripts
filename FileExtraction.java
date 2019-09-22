import java.io.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FileExtraction {
    public static void main(String[] args) {

        try {
            // Open the zip file
            String zip=args[0];
            ZipFile zipFile = new ZipFile(zip);
            Enumeration<?> enu = zipFile.entries();
            Date startedTime=new Date(System.currentTimeMillis());
            String dirName=zip.substring(0,zip.lastIndexOf("\\")+1);
            if(zip.contains("developmode.zip")){
                dirName+="developmode\\";
            }
            while (enu.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) enu.nextElement();
                String name = dirName+zipEntry.getName();
                long size = zipEntry.getSize();
                long compressedSize = zipEntry.getCompressedSize();
                System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n",
                        name, size, compressedSize);

                // Do we need to create a directory ?
                File file = new File(name);
                if (name.endsWith("/")) {
                    file.mkdirs();
                    continue;
                }

                File parent = file.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }

                // Extract the file
                InputStream is = zipFile.getInputStream(zipEntry);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = is.read(bytes)) >= 0) {
                    fos.write(bytes, 0, length);
                }
                is.close();
                fos.close();

            }
            Date endedTime=new Date(System.currentTimeMillis());
            zipFile.close();
            System.out.println("Started Time : "+startedTime);
            System.out.println("Ended Time : "+endedTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

