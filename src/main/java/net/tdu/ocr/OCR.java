package net.tdu.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * 0 = Orientation and script detection (OSD) only. 1 = Automatic page
 * segmentation with OSD. 2 = Automatic page segmentation, but no OSD, or OCR 3
 * = Fully automatic page segmentation, but no OSD. (Default) 4 = Assume a
 * single column of text of variable sizes. 5 = Assume a single uniform block of
 * vertically aligned text. 6 = Assume a single uniform block of text. 7 = Treat
 * the image as a single text line. 8 = Treat the image as a single word. 9 =
 * Treat the image as a single word in a circle. 10 = Treat the image as a
 * single character.
 * 
 * @author tangdu
 * 
 * @time 2013-4-3 上午10:14:11
 */
public class OCR {
	private static OCR OCR = null;
	private final static String LANG_OPTION = "-l";
	private final static String ENG_LANG = "eng";
	private final static String CHI_LANG = "chi_sim";
	private final static String PSD_OPTION = "-psm";
	private final static String PSD_MODE = "8";
	private final static String RESULT_FILE = "result";
	private String tessPath = "E:\\soft\\Tesseract-OCR\\tesseract";

	public static OCR getInstance() {
		if (OCR == null) {
			return OCR = new OCR();
		}
		return OCR;
	}

	public String recognizeText(File imageFile, String imageFormat)
			throws Exception {
		File tempImage = ImageIOHelper.createImage(imageFile, imageFormat);
		File outputFile = new File(imageFile.getParentFile(), RESULT_FILE);
		StringBuffer sb = new StringBuffer();
		List<String> cmd = new ArrayList<String>();
		ProcessBuilder pb = new ProcessBuilder();

		cmd.add(tessPath);
		cmd.add(tempImage.getName());
		cmd.add(RESULT_FILE);
		cmd.add(LANG_OPTION);
		// cmd.add(ENG_LANG);
		cmd.add(CHI_LANG);
		cmd.add(PSD_OPTION);
		cmd.add(PSD_MODE);

		pb.command(cmd);
		pb.redirectErrorStream(true);
		pb.directory(imageFile.getParentFile());
		Process process = pb.start();
		// tesseract 1.jpg result -l eng
		int w = process.waitFor();
		if (w == 0) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(outputFile.getAbsolutePath() + ".txt"),
					"UTF-8"));
			String str;
			while ((str = in.readLine()) != null) {
				sb.append(str).append("\n");
			}
			in.close();
		} else {
			String msg;
			switch (w) {
			case 1:
				msg = "Errors accessing files. There may be spaces in your image's filename.";
				break;
			case 29:
				msg = "Cannot recognize the image or its selected region.";
				break;
			case 31:
				msg = "Unsupported image format.";
				break;
			default:
				msg = "Errors occurred.";
			}
			tempImage.delete();
			throw new RuntimeException(msg);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		try {
			for (int i = 0; i <= 4; i++) {

				URL url = new URL(
						"https://feixin.10086.cn/account/login/ValidateNumberPicture.aspx?d="
								+ Math.random());
				InputStream in = url.openStream();
				FileOutputStream out = new FileOutputStream("E:\\soft\\Tesseract-OCR\\" + i + ".jpg");
				IOUtils.copy(in, out);
				out.close();
				in.close();

				/*
				 * String result = new OCR().recognizeText(new File("F://" + i +
				 * ".jpg"), "JPG"); System.out.println(result.replace("\\s*",
				 * ""));
				 */
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
