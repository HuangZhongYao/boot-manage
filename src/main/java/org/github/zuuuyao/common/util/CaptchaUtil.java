//package org.github.zuuuyao.common.util;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.image.BufferedImage;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Random;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//import org.apache.batik.dom.GenericDOMImplementation;
//import org.apache.batik.svggen.SVGGraphics2D;
//import org.w3c.dom.DOMImplementation;
//import org.w3c.dom.Document;
//
///**
// * @Desc: Created by IntelliJ IDEA.
// * @Author: ZhongYao.Huang
// * @Copyright: ZuuuuYao By Github
// * @Time: 2024-07-15 18:57
// */
//@Setter
//@Getter
//@Builder
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(callSuper = false)
//public class CaptchaUtil {
//
//
//    public static void main(String[] args) {
//        generateVerificationCodeSVG("verificationCode.svg");
//    }
//
//    public static void generateVerificationCodeSVG(String fileName) {
//        String verificationCode = generateVerificationCode();
//        int width = 200;
//        int height = 80;
//
//        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
//        Document document = domImpl.createDocument(null, "svg", null);
//
//        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
//        svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//            RenderingHints.VALUE_ANTIALIAS_ON);
//
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        Graphics2D graphics = image.createGraphics();
//
//        graphics.setColor(Color.WHITE);
//        graphics.fillRect(0, 0, width, height);
//
//        graphics.setColor(Color.BLACK);
//        graphics.setFont(new Font("Arial", Font.BOLD, 30));
//
//        Random random = new Random();
//
//        for (int i = 0; i < verificationCode.length(); i++) {
//            int x = 20 + i * 30 + random.nextInt(10);
//            int y = 40 + random.nextInt(20);
//            graphics.drawString(String.valueOf(verificationCode.charAt(i)), x, y);
//        }
//
//        svgGenerator.drawImage(image, 0, 0, null);
//
//        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
//            svgGenerator.stream(outputStream, true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String generateVerificationCode() {
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        Random random = new Random();
//        StringBuilder verificationCode = new StringBuilder();
//        for (int i = 0; i < 6; i++) {
//            verificationCode.append(characters.charAt(random.nextInt(characters.length())));
//        }
//        return verificationCode.toString();
//    }
//}
