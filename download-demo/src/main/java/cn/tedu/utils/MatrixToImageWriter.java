package cn.tedu.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;


public class MatrixToImageWriter {
	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private MatrixToImageWriter() {
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
	}

	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format " + format);
		}
	}

	public static InputStream creatImage(String text) throws Exception
	{
	// text = "http://www.baidu.com"; // ��ά������
	int width = 300; // ��ά��ͼƬ���
	int height = 300; // ��ά��ͼƬ�߶�
	String format = "jpg";// ��ά���ͼƬ��ʽ
	Hashtable<EncodeHintType, String> hints = new Hashtable<>();
	hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // ������ʹ���ַ�������
	BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
	BarcodeFormat.QR_CODE, width, height, hints);
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	MatrixToImageWriter.writeToStream(bitMatrix, format, out);
	return new ByteArrayInputStream(out.toByteArray());
	}

	����������������������������������Ȩ����������ΪCSDN������_�¡���ԭ�����£�

	��ѭ CC 4.0 BY-SA ��ȨЭ�飬ת���븽��ԭ�ĳ������Ӽ���������ԭ�����ӣ�https:// blog.csdn.net/qq_39699665/article/details/83152650
}
