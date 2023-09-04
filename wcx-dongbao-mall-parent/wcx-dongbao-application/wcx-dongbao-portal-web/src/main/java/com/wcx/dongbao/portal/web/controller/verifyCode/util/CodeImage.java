package com.wcx.dongbao.portal.web.controller.verifyCode.util;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

@Data
public class CodeImage {//验证码
    private String code;
    private ByteArrayInputStream image;
    final private static int width = 400;
    final private static int height = 100;

    private CodeImage() {

    }

    public static CodeImage createDigits(){
        CodeImage codeImage=new CodeImage();
        //图形缓冲区 相当于黑板
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //使用画笔在黑板上作画
        Graphics graphics = img.getGraphics();
        graphics.setColor(new Color(ran(0,255),ran(0,255),ran(0,255)));//设置颜色
        graphics.fillRect(0,0,width,height);
        graphics.setFont(new Font("宋体",Font.PLAIN,ran(20,40)));
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < 6; i++) {
            String digit=String.valueOf(ran(0,9));
            sb.append(digit);
            graphics.setColor(new Color(ran(0,255),ran(0,255),ran(0,255)));//换颜色
            graphics.setFont(new Font("宋体",Font.PLAIN,ran(20,40)));//换字体大小
            graphics.drawString(digit,ran(0,(width/12))+(width/6)*i,ran(height>>2,3*height>>2));
        }
        for (int i=0;i<25;i++){//划线，增加识别难度
            graphics.setColor(new Color(ran(0,255),ran(0,255),ran(0,255)));//换颜色
            int x1=ran(0,width),y1=ran(0,height),x2=ran(x1,width),y2=ran(y1,height);
            graphics.drawLine(x1,y1,x2,y2);
        }
        graphics.dispose();//收笔
        codeImage.code=sb.toString();
        ByteOutputStream outputStream=new ByteOutputStream();
        try {
            ImageOutputStream imageOutputStream=ImageIO.createImageOutputStream(outputStream);
            ImageIO.write(img,"jpeg",imageOutputStream);
            codeImage.image=new ByteArrayInputStream(outputStream.getBytes());
        }catch (Exception e){
            System.out.println("生成code失败");
        }
        return codeImage;
    }

    public static CodeImage createCompute(){
        CodeImage codeImage=new CodeImage();
        //图形缓冲区 相当于黑板
        BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //使用画笔在黑板上作画
        Graphics graphics = img.getGraphics();
        graphics.setColor(new Color(ran(0,255),ran(0,255),ran(0,255)));//设置颜色
        graphics.fillRect(0,0,width,height);
        graphics.setFont(new Font("宋体",Font.PLAIN,ran(20,40)));

        String[] strings=new String[5];// 1 + 1 = ?
        int num1=ran(0,20),num2=ran(0,20);
        int op=ran(0,1)==0?'+':'-';
        codeImage.code=""+(op=='+'?num1+num2:num1-num2);
        strings[0]=""+num1;
        strings[1]=""+(char)op;
        strings[2]=""+num2;
        strings[3]="=";
        strings[4]="?";

        for (int i = 0; i < 5; i++) {
            String s=strings[i];
            graphics.setColor(new Color(ran(0,255),ran(0,255),ran(0,255)));//换颜色
            graphics.setFont(new Font("宋体",Font.PLAIN,ran(30,40)));//换字体大小
            graphics.drawString(s,ran(0,(width/12))+(width/6)*i,ran(height>>2,3*height>>2));
        }
        for (int i=0;i<25;i++){//划线，增加识别难度
            graphics.setColor(new Color(ran(0,255),ran(0,255),ran(0,255)));//换颜色
            int x1=ran(0,width),y1=ran(0,height),x2=ran(x1,width),y2=ran(y1,height);
            graphics.drawLine(x1,y1,x2,y2);
        }
        graphics.dispose();//收笔
        ByteOutputStream outputStream=new ByteOutputStream();
        try {
            ImageOutputStream imageOutputStream=ImageIO.createImageOutputStream(outputStream);
            ImageIO.write(img,"jpeg",imageOutputStream);
            codeImage.image=new ByteArrayInputStream(outputStream.getBytes());
        }catch (Exception e){
            System.out.println("生成code失败");
        }
        return codeImage;
    }

    public static int ran(int l,int r){
        return l+(int)(Math.random()*(r-l+1));
    }

}
