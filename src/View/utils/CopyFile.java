package View.utils;

import java.io.*;

public class CopyFile {
    public static void main(String[] args) {
        File file = new File("src/View/static/resImages/add.png");
        copyFile(file,"test2222");
    }

    public static String copyFile(File file, String fileName){
        String path = "src\\View\\static\\resImages";
        path += "\\" + fileName + ".";
        path += getFileNameExtension(file.getPath());
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        int len = 0;
        byte [] buf  = new byte[1024];
        try {
            fileInputStream = new FileInputStream(file);
            fileOutputStream = new FileOutputStream(path);
            char c;
            while((len = fileInputStream.read(buf)) != -1){
                fileOutputStream.write(buf, 0, len);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return path;
    }

    //获取文件名后缀
    public static String getFileNameExtension(String filePath){
        File file = new File(filePath);
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return suffix;
    }
}
