package by.epam.rentacar.controller.util;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UploadHelper {

    private static final String PRE_FILE_NAME = "photo";

    private static final String CONTENT_DISPOSITION = "content-disposition";
    private static final String FILENAME = "filename";
    private static final String EQ = "=";
    private static final String DOT = ".";
    private static final String QUOTES = "\"";
    private static final String SEMICOLON = ";";
    private static final String BLANK = "";

    private static final String JPEG = "jpeg";
    private static final String JPG = "jpg";
    private static final String PNG = "png";
    private static final String GIF = "gif";

    public static String writeFile(Part filePart, String directory) {

        File fileDir = new File(directory);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        String format = getFileFormat(filePart);
        File file = null;

        try (InputStream inputStream = filePart.getInputStream()) {

            file = File.createTempFile(PRE_FILE_NAME, format, fileDir);
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return file.getName();

    }

    private static String getFileFormat(Part filePart) {

        String filename = getFileName(filePart);

        if (filename == null) {
            //exception
        }

        return filename.substring(filename.lastIndexOf(DOT));

    }

    public static boolean checkFileFormat(String filename) {
        String format = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
        return format.equalsIgnoreCase(JPEG)
                || format.equalsIgnoreCase(JPG)
                || format.equalsIgnoreCase(PNG)
                || format.equalsIgnoreCase(GIF);
    }

    private static String getFileName(Part filePart) {

        for (String content : filePart.getHeader(CONTENT_DISPOSITION).split(SEMICOLON)) {
            if (content.trim().startsWith(FILENAME)) {
                return content.substring(content.indexOf(EQ) +1).trim().replace(QUOTES, BLANK);
            }
        }

        return null;

    }

    private UploadHelper() {

    }

}
