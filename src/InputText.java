import java.io.*;

public class InputText {
	final static int LENGTH = 100;

	public static String getData(String fileName, int segment) {
		int i = 0;
		if ((fileName == null) || (fileName.length() == 0))
			return "";
		File f;
		FileReader in = null;
		String s = "";
		try {
			f = new File(fileName);
			in = new FileReader(f);
			char[] buffer = new char[LENGTH];
			int len;
			if (GlobalFlag.choice == 1) {
				if (segment >= (int) (f.length() / LENGTH))
					GlobalFlag.textTag = 0;
			} else if (GlobalFlag.choice == 2) {
				if (segment >= (int) (f.length() / LENGTH - 1) + 1)
					GlobalFlag.textTag = 0;
			}
			while ((len = in.read(buffer)) != -1) {
				s = new String(buffer, 0, len);
				s = s.replaceAll("\r\n", "").replaceAll(" +", " ").trim();
				i++;
				if (i == segment) {
					GlobalFlag.textTag++;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}
}
