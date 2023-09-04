package com.wcx.dongbao.portal.web.controller.verifyCode;

import com.wcx.dongbao.portal.web.controller.verifyCode.util.CodeImage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

@RestController
@RequestMapping("/code")
public class VerifyCodeController {
    final String attrName="verifyCode";

    @GetMapping("/generator")
    public void generatorCode(HttpServletRequest request,HttpServletResponse response){//生成验证码的接口
        CodeImage codeImage = CodeImage.createDigits();
        String code= codeImage.getCode();
        request.getSession().setAttribute(attrName,code);//将code写在session里
        ByteArrayInputStream image= codeImage.getImage();
        response.setContentType("image/jpeg");
        byte[] bytes=new byte[1024];
        try(ServletOutputStream out=response.getOutputStream()){//这样写会自动释放资源
            while(image.read(bytes)!=-1){
                out.write(bytes);//直接把二进制流传到浏览器（前端）
            }
        }catch (Exception e){
            System.out.println("生成验证码失败");
        }
    }

    //https://tool.jisuapi.com/base642pic.html
    @GetMapping("/generator-digits-base64")
    public String generatorCode_base64(HttpServletRequest request){//将图片以base64的形式编码，然后传到前端
        CodeImage codeImage = CodeImage.createDigits();
        String code= codeImage.getCode();
        request.getSession().setAttribute(attrName,code);//将code写在session里
        ByteArrayInputStream image= codeImage.getImage();
        ByteArrayOutputStream swapStream=new ByteArrayOutputStream();
        byte[] buff=new byte[1024];
        int r=0;
        while((r=image.read(buff,0,1024))>0){
            swapStream.write(buff,0,r);
        }
        byte[] data = swapStream.toByteArray();
        return Base64.getEncoder().encodeToString(data);
    }

    @GetMapping("/generator-compute")
    public void generatorCode_compute(HttpServletRequest request,HttpServletResponse response){//生成计算的验证码
        CodeImage codeImage = CodeImage.createCompute();
        String code= codeImage.getCode();
        request.getSession().setAttribute(attrName,code);//将code写在session里
        ByteArrayInputStream image= codeImage.getImage();
        response.setContentType("image/jpeg");
        byte[] bytes=new byte[1024];
        try(ServletOutputStream out=response.getOutputStream()){//这样写会自动释放资源
            while(image.read(bytes)!=-1){
                out.write(bytes);//直接把二进制流传到浏览器（前端）
            }
        }catch (Exception e){
            System.out.println("生成验证码失败");
        }
    }

    @GetMapping("/generator-compute-base64")
    public String generatorCode_compute_base64(HttpServletRequest request){//将图片以base64的形式编码，然后传到前端
        CodeImage codeImage = CodeImage.createCompute();
        String code= codeImage.getCode();
        request.getSession().setAttribute(attrName,code);//将code写在session里
        ByteArrayInputStream image= codeImage.getImage();
        ByteArrayOutputStream swapStream=new ByteArrayOutputStream();
        byte[] buff=new byte[1024];
        int r=0;
        while((r=image.read(buff,0,1024))>0){
            swapStream.write(buff,0,r);
        }
        byte[] data = swapStream.toByteArray();
        return Base64.getEncoder().encodeToString(data);
    }

    @GetMapping("/verify")
    public String verify(String verifyCode, HttpServletRequest request){//校验验证码的接口
        String right=request.getSession().getAttribute(attrName).toString();
        if (verifyCode.equals(right)){
            return "通过";
        }else {
            return "不通过";
        }
    }


}
