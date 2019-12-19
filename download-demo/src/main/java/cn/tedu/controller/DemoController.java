package cn.tedu.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/demo")
@Controller
public class DemoController {
	
	@RequestMapping("/download.do")
	@ResponseBody
	public byte[] download(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Type", "image/png");
		String file = "ÑÝÊ¾Í¼Æ¬.png";
		file = URLEncoder.encode(file, "UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + file + "\"");
		byte[] bytes = creatPNG();
		return bytes;
	}

	private byte[] creatPNG() throws IOException {
		BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
		img.setRGB(50, 25, 0xffffff);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(img, "png", out);
		out.close();
		byte[] png = out.toByteArray();
		return png;
	}
}
