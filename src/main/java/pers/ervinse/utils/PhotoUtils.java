package pers.ervinse.utils;

import java.io.*;
import java.util.Base64;

public class PhotoUtils {
    /**
     * 将照片转换为字节
     *
     * @param path 路径
     * @return {@link Byte[]}
     */
    public static byte[] convertPhotoToByte(String path){
        File file = new File(path);
        try (FileInputStream fileInputStream=new FileInputStream(file)){
            int available = fileInputStream.available();
            byte [] bytes=new byte[available];
            fileInputStream.read(bytes);
            return Base64.getEncoder().encode(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
