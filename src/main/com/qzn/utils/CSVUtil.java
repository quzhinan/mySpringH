package com.qzn.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

/**
 * CSV共通方法
 */
public class CSVUtil {

	/**
	 * アップロードされたファイルは、通常ファイルに変換され
	 * 
	 * @param mf
	 *            アップロードファイル
	 * @return file
	 */
	public static File multipartFileToFile(MultipartFile mf) {
		CommonsMultipartFile cf = (CommonsMultipartFile) mf;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File f = fi.getStoreLocation();
		return f;
	}

	/**
	 * ファイルの内容を読みます
	 * 
	 * @param file
	 *            ローカルファイル
	 * @return ファイルの内容
	 * @throws IOException
	 */
	public static List<String[]> getContentFromFile(File file) throws IOException {
		List<String[]> content = new ArrayList<String[]>();
		CsvListReader reader = new CsvListReader(new FileReader(file), CsvPreference.STANDARD_PREFERENCE);
		reader.getHeader(true);
		List<String> line = new ArrayList<String>();
		while ((line = reader.read()) != null) {
			content.add(line.toArray(new String[] {}));
		}
		reader.close();
		return content;
	}

	/**
	 * ファイルの内容を読みます
	 * 
	 * @param file
	 *            Reader
	 * @return ファイルの内容
	 * @throws IOException
	 */
	public static List<String[]> getContentFromReader(Reader file) throws IOException {
		List<String[]> content = new ArrayList<String[]>();
		CsvListReader reader = new CsvListReader(file, CsvPreference.STANDARD_PREFERENCE);
		List<String> line = new ArrayList<String>();
		while ((line = reader.read()) != null) {
			content.add(line.toArray(new String[] {}));
		}
		reader.close();
		return content;
	}

	/**
	 * タイトルをスキップのファイルの内容を読みます
	 * 
	 * @param file
	 *            Reader
	 * @return ファイルの内容
	 * @throws IOException
	 */
	public static List<String[]> getContentWithoutHeaderFromReader(Reader file) throws IOException {
		List<String[]> content = new ArrayList<String[]>();
		CsvListReader reader = new CsvListReader(file, CsvPreference.STANDARD_PREFERENCE);
		reader.getHeader(true);
		List<String> line = new ArrayList<String>();
		while ((line = reader.read()) != null) {
			content.add(line.toArray(new String[] {}));
		}
		reader.close();
		return content;
	}

	/**
	 * ファイルのタイトルを読みます
	 * 
	 * @param file
	 *            ローカルファイル
	 * @return
	 * @throws IOException
	 */
	public String[] getHeaderFromFile(File file) throws IOException {
		CsvListReader reader = new CsvListReader(new FileReader

		(file), CsvPreference.STANDARD_PREFERENCE);
		reader.close();
		return reader.getHeader(true);
	}

	/**
	 * ファイルにタイトルやコンテンツを書きます
	 * 
	 * @param file
	 *            ローカルファイル
	 * @param header
	 *            タイトル
	 * @param content
	 *            内容
	 * @throws IOException
	 */
	public static void writeToCsv(File file, String[] header, List<String[]> content) throws

	IOException {
		CsvListWriter writer = new CsvListWriter(new FileWriter(file),

				CsvPreference.STANDARD_PREFERENCE);
		writer.writeHeader(header);
		for (String[] str : content) {
			writer.write(str);
		}
		writer.close();
	}

	/**
	 * ファイルにタイトルやコンテンツを書きます
	 * 
	 * @param file
	 *            ローカルファイル
	 * @param header
	 *            タイトル
	 * @param content
	 *            内容
	 * @param code
	 *            コーディング
	 * @throws IOException
	 */
	public static void writeToCsvWithCode(File file, String[] header, List<String[]> content, String code) throws

	IOException {
		CsvListWriter writer = new CsvListWriter(new FileWriterWithEncoding(file, code),

				CsvPreference.STANDARD_PREFERENCE);
		writer.writeHeader(header);
		for (String[] str : content) {
			writer.write(str);
		}
		writer.close();
	}

	/**
	 * ファイルにコンテンツを書きます
	 * 
	 * @param file
	 *            ローカルファイル
	 * @param content
	 *            内容
	 * @throws IOException
	 */
	public static void writeContentToCsv(File file, List<String[]> content) throws IOException {
		CsvListWriter writer = new CsvListWriter(new FileWriter(file),

				CsvPreference.STANDARD_PREFERENCE);
		for (String[] str : content) {
			if (str != null) {
				writer.write(str);
			}
		}
		writer.close();
	}

	/**
	 * ファイルにタイトルを書きます
	 * 
	 * @param file
	 *            ローカルファイル
	 * @param header
	 *            タイトル
	 * @throws IOException
	 */
	public static void writeHeaderToCsv(File file, String[] header) throws IOException {
		CsvListWriter writer = new CsvListWriter(new FileWriter(file),

				CsvPreference.STANDARD_PREFERENCE);
		writer.writeHeader(header);
		writer.close();
	}

	/**
	 * ファイルにタイトルを書きます
	 * 
	 * @param file
	 *            ローカルファイル
	 * @param header
	 *            タイトル
	 * @param code
	 *            コーディング
	 * @throws IOException
	 */
	public static void writeHeaderToCsvWithCode(File file, String[] header, String code) throws IOException {
		CsvListWriter writer = new CsvListWriter(new FileWriterWithEncoding(file, code),

				CsvPreference.STANDARD_PREFERENCE);
		writer.writeHeader(header);
		writer.close();
	}

	/**
	 * ファイルにコンテンツを追加します
	 * 
	 * @param file
	 *            ローカルファイル
	 * @param content
	 *            内容
	 * @param code
	 *            コーディング
	 * @throws IOException
	 */
	public static void AddContentToCsvWithCode(File file, List<String[]> content, String code) throws IOException {
		CsvListWriter writer = new CsvListWriter(new FileWriterWithEncoding(file, code, true),

				CsvPreference.STANDARD_PREFERENCE);

		for (String[] str : content) {
			writer.write(str);
		}
		writer.close();
	}
}