package com.yung.auto.framework.utility.file.txtfille;

import com.yung.auto.framework.utility.common.DefaultConfig;

import java.io.*;
import java.util.Date;

/**
 * Created by wangyujing on 2018/1/8.
 */
public class FileReaderTxt {

    /**
     * 创建文件
     *
     * @param fileName
     * @return
     */
    public static boolean createFile(File fileName) {
        boolean flag = false;
        if (!fileName.exists()) {
            try {
                fileName.createNewFile();
                flag = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public static String readTxtFile(String pathDir, String fileName) {
        if (pathDir != null) {
            pathDir = DefaultConfig.DEFAULT_FILE_PERFIX_DIR_PATH_TXT + pathDir;
        }
        if (pathDir == null) {
            pathDir = DefaultConfig.DEFAULT_FILE_DIR_PATH;
        }
        if (pathDir == null) {
            pathDir = System.getProperty("user.dir");
        }
        if (fileName == null) {
            return "";
        }
        String tempName = pathDir + "/" + fileName;
        File file = new File(tempName);
        if (file.exists()) {
            try {
                return readTxtFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 读取Txt文件内容
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String readTxtFile(File fileName) throws IOException {
        StringBuilder result = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            try {
                String read = null;
                while ((read = bufferedReader.readLine()) != null) {
                    result.append(read + "\r\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
        }
        System.out.println("读取出来的文件内容为：\r\n" + result.toString());
        return result.toString();
    }

    public static void writeOrUpdateContent(String pathDir, String fileName, String content, boolean isUpdate) {
        if (pathDir != null) {
            pathDir = DefaultConfig.DEFAULT_FILE_PERFIX_DIR_PATH_TXT + pathDir;
        }
        if (pathDir == null) {
            pathDir = DefaultConfig.DEFAULT_FILE_DIR_PATH;
        }
        if (pathDir == null) {
//            Class.class.getClass().getResource("/").getPath();
            pathDir = System.getProperty("user.dir");
        }
        if (fileName == null) {
            fileName = new Date().toString();
        }
        String destFileName = pathDir + "/" + fileName + DefaultConfig.DEFAULT_FILE_SUFFIX_TXT;
        if (!createFileOrExist(destFileName)) {
            return;
        }
        File file = new File(destFileName);
        try {
            if (isUpdate) {
                updateTxtContent(file, content);
            } else {
                writeTxtContent(file, content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件
     * @param destFileName
     * @return
     */
    public static boolean createFileOrExist(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return true;
        }
        if (destFileName.endsWith(File.separator)) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                System.out.println("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                System.out.println("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }
    }

    /**
     * 写入Txt文件
     *
     * @param fileName
     * @param content
     * @return
     * @throws Exception
     */
    public static boolean writeTxtContent(File fileName, String content) throws Exception {
        RandomAccessFile mm = null;
        boolean flag = false;
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(fileName);
            os.write(content.getBytes("GBK"));
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (mm != null) {
                mm.close();
            }
            if (os != null) {
                os.close();
            }
        }
        return flag;
    }

    /**
     * 对txt文件中新增内容
     *
     * @param file
     * @param content
     */
    public static void updateTxtContent(File file, String content) {
        String str; //原有txt内容
        StringBuilder s1 = new StringBuilder();//内容更新
        try {
            if (!file.exists()) {
//                System.out.print("文件不存在");
                createFile(file);   // 不存在则创建
            }
            BufferedReader input = new BufferedReader(new FileReader(file));

            while ((str = input.readLine()) != null) {
                s1.append(str + "\r\n");
            }
            input.close();
            s1.append(content);

            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(s1.toString());
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
