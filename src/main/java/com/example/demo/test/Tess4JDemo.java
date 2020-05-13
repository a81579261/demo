package com.example.demo.test;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class Tess4JDemo {

    public static void main(String[] args) throws IOException {
        // 识别图片的文件（修改为自己的图片路径）
        String imagePath = ClassLoader.getSystemResource("image/engNum.jpg").getPath();
        if (imagePath.length() > 0) {
            imagePath = imagePath.substring(1);
        }
        System.out.println("imagePath:" + imagePath);
        File file = new File(imagePath);

        //图片转图片流
        BufferedImage img = ImageIO.read(file);
        // 这里对图片黑白处理,增强识别率.这里先通过截图,截取图片中需要识别的部分
        img = ImageHelper.convertImageToGrayscale(img);
        // 图片锐化,自己使用中影响识别率的主要因素是针式打印机字迹不连贯,所以锐化反而降低识别率
//        img = ImageHelper.convertImageToBinary(img);
        // 图片放大5倍,增强识别率(很多图片本身无法识别,放大7倍时就可以轻易识,但是考滤到客户电脑配置低,针式打印机打印不连贯的问题,这里就放大7倍)
        img = ImageHelper.getScaledInstance(img, img.getWidth() * 7, img.getHeight() * 7);

        ITesseract instance = new Tesseract();
        //设置训练库的位置
        String path = ClassLoader.getSystemResource("tessdata").getPath();
        if (path.length() > 0) {
            path = path.substring(1);
        }
        //打印一下路径，看有没有问题
        System.out.println("tessdata:" + path);
        instance.setDatapath(path);
        //chi_sim ：简体中文， eng    根据需求选择语言库
        instance.setLanguage("eng");
        String result = null;
        try {
            long startTime = System.currentTimeMillis();
            result = instance.doOCR(img);
            long endTime = System.currentTimeMillis();
            System.out.println("Time is：" + (endTime - startTime) + " 毫秒");
        } catch (TesseractException e) {
            e.printStackTrace();
        }

        System.out.println("result: " + result);
    }


    public static void removeBackground(String imgUrl, String resUrl) {
        //定义一个临界阈值
        int threshold = 300;
        try {
            BufferedImage img = ImageIO.read(new File(imgUrl));
            int width = img.getWidth();
            int height = img.getHeight();
            for (int i = 1; i < width; i++) {
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        Color color = new Color(img.getRGB(x, y));
                        System.out.println("red:" + color.getRed() + " | green:" + color.getGreen() + " | blue:" + color.getBlue());
                        int num = color.getRed() + color.getGreen() + color.getBlue();
                        if (num >= threshold) {
                            img.setRGB(x, y, Color.WHITE.getRGB());
                        }
                    }
                }
            }
            for (int i = 1; i < width; i++) {
                Color color1 = new Color(img.getRGB(i, 1));
                int num1 = color1.getRed() + color1.getGreen() + color1.getBlue();
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        Color color = new Color(img.getRGB(x, y));

                        int num = color.getRed() + color.getGreen() + color.getBlue();
                        if (num == num1) {
                            img.setRGB(x, y, Color.BLACK.getRGB());
                        } else {
                            img.setRGB(x, y, Color.WHITE.getRGB());
                        }
                    }
                }
            }
            File file = new File(resUrl);
            if (!file.exists()) {
                File dir = file.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ImageIO.write(img, "jpg", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
