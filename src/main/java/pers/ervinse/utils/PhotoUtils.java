package pers.ervinse.utils;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class PhotoUtils {
    /**
     * 将照片转换为字节
     *
     * @param path 路径
     * @return {@link Byte[]}
     */
    public static byte[] convertPhotoToByte(String path) {
        File file = new File(path);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int available = fileInputStream.available();
            byte[] bytes = new byte[available];
            fileInputStream.read(bytes);
            return Base64.getEncoder().encode(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static int savePhoto(HttpServletRequest request, String path) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(request.getInputStream());
            int available = bufferedInputStream.available();
            byte[] bytes = new byte[available];
            bufferedInputStream.read(bytes);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(path)));
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
