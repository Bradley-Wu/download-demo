package cn.tedu.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import cn.tedu.utils.MatrixToImageWriter;

public class Tests {

	public static void main(String[] args) {
		InputStream inputStream = null;
		try {
			//����ŵ�����Ҫ�����ά�����Ϣ
			inputStream = MatrixToImageWriter.creatImage("www.baidu.com");
			byte[] bytes = new byte[inputStream.available()];
			inputStream.read(bytes);
			File file = new File("E:\\demo\\��ά��.jpg");
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
