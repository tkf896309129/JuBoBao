package com.example.huoshangkou.jubowan.photo;

/**
 * 作者：唐先生
 * 包名：com.example.demo.demotang.compress
 * 类名：FileUtils
 * 描述：
 * 创建时间：2016-12-22  14:35
 */

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

public class FileUtils {

    public static String SDPATH = Environment.getExternalStorageDirectory()
            + "/pos/";

    public static File saveBitmap(Bitmap bm, String picName) {
        File f = null;
        try {
            if (!isFileExist("")) {
                System.out.println("创建文件");
                File tempf = createSDDir("");
            }
            f = new File(SDPATH, picName + ".JPEG");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    public static File createSDDir(String dirName) throws IOException {
        File dir = new File(SDPATH + dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            System.out.println("createSDDir:" + dir.getAbsolutePath());
            System.out.println("createSDDir:" + dir.mkdir());
        }
        return dir;
    }

    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e+"");
        }
    }

    public static boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        file.isFile();
        System.out.println(file.exists());
        return file.exists();
    }

    public static void delFile(String fileName) {
        File file = new File(SDPATH + fileName);
        if (file.isFile()) {
            file.delete();
        }
        file.exists();
    }

    public static void deleteDir() {
        File dir = new File(SDPATH);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete();
            else if (file.isDirectory())
                deleteDir();
        }
        dir.delete();
    }

    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void writeInputFile(File file, String content) {
        try {
            FileOutputStream fileInputStream = new FileOutputStream(file);
            fileInputStream.write(content.getBytes());
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String readInputFile(File file) {
        String content = "";
        try {
            FileInputStream inputStreamFile = new FileInputStream(file);
            InputStreamReader inputStream = new InputStreamReader(inputStreamFile);
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
