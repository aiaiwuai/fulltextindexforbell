package cn.com.alcatel_sbell.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Encoder;

public class FileUtils {
	public static String getRootPath(HttpServletRequest request,String path) {
		String rootPath = request.getSession().getServletContext()
				.getRealPath(path);
		return rootPath;
	}
	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * 
	 * @param filename
	 *            下载文件名
	 * @param agent
	 *            客户端浏览器
	 * @return 编码后的下载附件名
	 * @throws IOException
	 */
	public static String encodeDownloadFilename(String filename, String agent)
			throws IOException {
		if (agent.contains("Firefox")) { // 火狐浏览器
			filename = "=?UTF-8?B?"
					+ new BASE64Encoder().encode(filename.getBytes("utf-8"))
					+ "?=";
			filename = filename.replaceAll("\r\n", "");
		} else { // IE及其他浏览器
			filename = URLEncoder.encode(filename, "utf-8");
			filename = filename.replace("+"," ");
		}
		return filename;
	}
	public static String generateRandonFileName(String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf("."));
		return UUID.randomUUID().toString() + ext;
	}
	public static String generateRandomDir(String uuidFileName) {
		int hashCode = uuidFileName.hashCode();
		int d1 = hashCode & 0xf;
		int d2 = (hashCode >> 4) & 0xf;
		return "/" + d1 + "/" + d2+"/";
	}
}
