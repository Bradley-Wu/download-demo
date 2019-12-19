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
	// text = "http://www.baidu.com"; // 二维码内容
	int width = 300; // 二维码图片宽度
	int height = 300; // 二维码图片高度
	String format = "jpg";// 二维码的图片格式
	Hashtable<EncodeHintType, String> hints = new Hashtable<>();
	hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
	BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
	BarcodeFormat.QR_CODE, width, height, hints);
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	MatrixToImageWriter.writeToStream(bitMatrix, format, out);
	return new ByteArrayInputStream(out.toByteArray());
	}

	————————————————版权声明：本文为CSDN博主「_温」的原创文章，

	遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。原文链接：https:// blog.csdn.net/qq_39699665/article/details/83152650
}
