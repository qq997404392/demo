package com.oyf.test.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.pdfbox.pdmodel.PDDocument;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/9/29
 * @description：Base64流&PDF文件互转
 */
public class Base64PDFUtil {

    /**
     * 将Base64流转成PDF文件
     * @param base64Str Base64流
     * @param filePath 存放文件名
     */
    public static void base64StrToPDF(String base64Str, String filePath) {
        ByteArrayInputStream bais = null;
        BufferedInputStream bin = null;
        FileOutputStream fout = null;
        BufferedOutputStream bout = null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] bytes = decoder.decodeBuffer(base64Str);
            // 创建将bytes作为其缓冲区的输入流对象
            bais = new ByteArrayInputStream(bytes);
            // 创建从底层输入流中读取数据的缓冲输入流对象
            bin = new BufferedInputStream(bais);
            // 指定输出文件
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            // 创建到指定文件的输出流
            fout = new FileOutputStream(file);
            // 为文件输出流对接缓冲输出流对象
            bout = new BufferedOutputStream(fout);
            byte[] buffers = new byte[1024];
            int len = bin.read(buffers);
            while (len != -1) {
                bout.write(buffers, 0, len);
                len = bin.read(buffers);
            }
            bout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(bout, fout, bin, bais);
        }
    }

    /**
     * PDF文件转Base64流
     * @param filePath 文件目录
     * @return Base64流
     */
    public static String pdfToBase64Str(String filePath) {
        FileInputStream fin = null;
        BufferedInputStream bin = null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bout = null;
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            File encryptPath = new File(filePath);
            // 建立读取文件的文件输出流
            fin = new FileInputStream(encryptPath);
            // 在文件输出流上安装节点流
            bin = new BufferedInputStream(fin);
            // 创建具有缓冲区的byte数组输出流
            baos = new ByteArrayOutputStream();
            // 创建缓冲输出流，以便将数据写入指定的底层输出流
            bout = new BufferedOutputStream(baos);
            byte[] buffer = new byte[1024];
            int len = bin.read(buffer);
            while (len != -1) {
                bout.write(buffer, 0, len);
                len = bin.read(buffer);
            }
            bout.flush();
            byte[] bytes = baos.toByteArray();
            return Base64.encode(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(bout, baos, bin, fin);
        }
        return null;
    }

    /**
     * 解除PDF口令
     * @param filePath PDF文件路径
     * @param password 文件密码
     * @return
     */
    public static String decipher(String filePath, String password) {
        try {
            File encryptFile = new File(filePath);
            String parentPath = encryptFile.getParent();
            PDDocument load = PDDocument.load(encryptFile, password);
            load.setAllSecurityToBeRemoved(true);
            String decodeFilePath = parentPath + File.separator + "_decode.pdf";
            load.save(decodeFilePath);
            load.close();
            return decodeFilePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭多个流
     * @param closeables
     */
    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                if (null != closeable) {
                    closeable.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        String base64Str = "xxx";
        String password = "123";
        String filePath = "D:/tmp/pdfPath" + File.separator + "source.pdf";
        base64StrToPDF(base64Str, filePath);
        String decryptPath = decipher(filePath, password);
        System.out.println("pdf文件: " + decryptPath);
    }

}
