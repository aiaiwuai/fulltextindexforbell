package cn.com.alcatel_sbell.fulltextindex.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.com.alcatel_sbell.fulltextindex.bean.DocumentBean;
import cn.com.alcatel_sbell.lucene.utils.LuceneUtils;
import cn.com.alcatel_sbell.utils.FileUtils;
import cn.com.alcatel_sbell.utils.I18N;

@Controller
@RequestMapping("")
@Component
public class IndexController {
	Logger index = Logger.getLogger("index");

	@RequestMapping("/index")
	public ModelAndView show(HttpServletRequest request) throws Exception {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("index");
		mView.addObject("uploadfiletoindex",
				new I18N().getLocalMessage(request, "uploadfiletoindex"));
		return mView;
	}

	@RequestMapping("/deletepage")
	public ModelAndView delelte() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("delete");
		return mv;
	}

	@RequestMapping("/delete")
	public @ResponseBody Object delete(
			@RequestParam(value = "id", defaultValue="all") String id)
			throws Exception {
		if(StringUtils.equals("all", id)){
			try {
				LuceneUtils.deleteAllDocument();
				return "'sucess':'ok','status':'1'";
			} catch (Exception e) {
				return "'error':'unkown error','status':'3'";
			}
			
		}
		try {
			Term term = new Term("id", id);
			System.out.println(id);
			Document documentByAssignId = LuceneUtils.getDocumentByAssignId(
					"id", id);
			new File(documentByAssignId.get("saveFilePath")).delete();
			IndexWriter indexWriter = cn.com.alcatel_sbell.lucene.utils.LuceneUtils
					.getIndexWriter();
			indexWriter.deleteDocuments(term);
			indexWriter.commit();
			return "'sucess':'ok','status':'1'";
		} catch (Exception e) {
			return "'error':'unkown error','status':'2'";
		}

	}
    public void deleteIndexById(String id){
    	Term term = new Term("id", id);
		try {
			IndexWriter indexWriter = cn.com.alcatel_sbell.lucene.utils.LuceneUtils
					.getIndexWriter();
			indexWriter.deleteDocuments(term);
			indexWriter.commit();
		} catch (Exception e) {
			index.info("delete exists indexed document faliure!");
		}
    }
	@RequestMapping("/start_index")
	public @ResponseBody Object start_index(
			@RequestParam(value = "path", defaultValue = "\\\\sbardwf7\\Wireless_Training") String path,
			@RequestParam(value = "ext", defaultValue = "xls,doc,pdf") String types,HttpServletRequest request) {
		try {
			String parameter = request.getParameter("path");
			System.out.println(new String(parameter.getBytes("iso-8859-1"),"utf-8"));
			String encodingConvert = cn.com.alcatel_sbell.utils.StringUtils.encodingConvert("iso-8859-1", "utf-8", path);
			index.info("manual index start ........");
			indexFiles(encodingConvert,types);

			return "index success";
		} catch (Exception e) {
			return "index faliure";
		}

	}
	public void indexFiles(String path,String typesString){
		String[] split = typesString.split(",");
		List<String> types = Arrays.asList(split);
		File pfile = new File(path);
		index.info("get files........");
		List<File> allFiles = FileUtils.getAllFiles(pfile, types);
		Integer i=allFiles.size();
		index.info("file count "+i);
		for (File file : allFiles) {
			index.info("index "+file.getAbsolutePath()+"........");
			try {
				indexfile(file);		
			} catch (Exception e) {
				index.error(e.getMessage());
			}
		
			index.info("file remain "+--i);
		}
		index.info("all done ........");
		
	}

	@Scheduled(cron = "0 50 11 * * ? ")
	public void autoIndex() throws IOException, TikaException {
		index.info("auto start........");
		String[] split = new String("xls,doc,xlsx,docx,pdf,ppt,pptm").split(",");
		List<String> types = Arrays.asList(split);
//		File pfile = new File("\\\\sbardwf7\\Wireless_Training\\");
//		File pfile = new File("\\\\sbardwf7\\swe\\wzy\\wqs\\test");
		File pfile = new File("E:\\javadocument\\Activity赵庆轩\\activity\\day1\\资料");
		index.info("get files........");
		List<File> allFiles = FileUtils.getAllFiles(pfile, types);
		Integer i=allFiles.size();
		index.info("file count "+i);
		for (File file : allFiles) {
			index.info("index "+file.getAbsolutePath()+"........");
			indexfile(file);
			index.info("file remain "+--i);
		}
		index.info("all done ........");
	}

	public Map<String, String> searchByAbsolutePathAndName(File file)
			throws IOException {

		System.out.println("filename" + file.getName());
		String filename = file.getName();
		Map<String, String> map = new HashMap<String, String>();
		IndexSearcher indexSearcher = null;
		try {
			indexSearcher = LuceneUtils.getIndexSearcher();
		} catch (Exception e) {
			return map;
		}
		Query query1 = new TermQuery(new Term("filename", filename));
		Query query2 = new TermQuery(new Term("saveFilePath",
				file.getAbsolutePath()));
		BooleanQuery query = new BooleanQuery();
		query.add(query1, Occur.MUST);
		query.add(query2, Occur.MUST);
		TopDocs topDocs = indexSearcher.search(query, Integer.MAX_VALUE);
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			System.out.println("score" + scoreDoc.score);
			map.put("score", scoreDoc.score + "");
			Document document = indexSearcher.doc(scoreDoc.doc);
			String string2 = document.get("filename");
			System.out.println("filename:" + string2);
			map.put("filename", string2);
			map.put("lastmodified", document.get("lastmodified"));
			map.put("lastmodifiedstr", document.get("lastmodifiedstr"));
			map.put("id", document.get("id"));
			map.put("saveFilePath", document.get("saveFilePath"));
		}
		return map;
	}

	public void indexfile(File file) throws IOException {
		Tika tkTika = new Tika();
		String string1="";
		try {
			 string1 = tkTika.parseToString(file);
		} catch (Exception e) {
			index.error("can't read file:"+file.getAbsolutePath());
			return;
		}
		String filename = file.getName();
		long lastmodifieddate = file.lastModified();
		if ("".equals(string1.trim())) {
			index.info("we get none  text from your docuemnt '" + filename
					+ "',check it.");
			return;
		}

		Map<String, String> ob = searchByAbsolutePathAndName(file);
		if (StringUtils.equals(filename, ob.get("filename"))) {
			float v = Long.parseLong(ob.get("lastmodified")) / 1000
					- lastmodifieddate / 1000;
			if (v < 1 && v > -1) {
				index.info("you upload the identical docuemnt of '" + filename
						+ "'");
				return;
			}
			else{
				deleteIndexById(ob.get("id"));
			}
		}
		Document document = new Document();
		document.add(new StringField("id", FileUtils
				.generateRandonFileName(filename), Store.YES));
		document.add(new StringField("filename", filename, Store.YES));
		document.add(new StringField("saveFilePath", file.getAbsolutePath(),
				Store.YES));
		document.add(new StringField("holder", "public", Store.YES));

		document.add(new StringField("lastmodified", Long
				.toString(lastmodifieddate), Store.YES));
		document.add(new StringField("lastmodifiedstr", new Date(
				lastmodifieddate).toString(), Store.YES));
		document.add(new TextField("content", string1.trim(), Store.YES));
		IndexWriter indexWriter = LuceneUtils.getIndexWriter();
		indexWriter.addDocument(document);
		indexWriter.commit();
		index.info("index sucess" + filename);
	}

	@RequestMapping("/upload")
	public @ResponseBody Object upload(
			@RequestParam(value = "upload", required = true) MultipartFile file,
			@RequestParam(value = "lastmodifieddate", required = true) Long lastmodifieddate,
			@RequestParam(value = "lastmodifieddatestr", required = true) String lastmodifieddatestr,
			@RequestParam(value = "holder", required = true) String holder,
			HttpServletRequest request, ModelMap model,
			CommonsMultipartResolver cmr, DocumentBean db) throws Exception {
		Tika tkTika = new Tika();
		if (!StringUtils.equals("public", holder)) {
			holder = (String) request.getSession().getAttribute("REMOTE_USER");
		}
		db.setHolder(holder);
		String string1 = tkTika.parseToString(file.getInputStream());
		Metadata mt = new Metadata();
		tkTika.parse(file.getInputStream(), mt);
		String contontType = mt.get(Metadata.CONTENT_TYPE);
		String filename = file.getOriginalFilename();
		db.setFilename(filename);
		if ("".equals(string1.trim())) {
			return "we get none  text from your docuemnt '" + filename
					+ "',check it.";
		}
		AjaxQuery aj = new AjaxQuery();
		@SuppressWarnings("unchecked")
		Map<String, String> ob = (HashMap<String, String>) aj
				.queryByFilenameAndHolder(db.getFilename(), db.getHolder(),
						request);
		if (StringUtils.equals(filename, ob.get("filename"))
				&& StringUtils.equals(holder, ob.get("holder"))) {
			float v = Long.parseLong(ob.get("lastmodified")) / 1000
					- lastmodifieddate / 1000;
			if (v < 1 && v > -1) {
				return "you upload the identical docuemnt of '" + filename
						+ "'";
			}
		}
		saveFile(file, request, lastmodifieddate, db);
		String userName = (String) request.getSession().getAttribute(
				"REMOTE_USER");
		Document document = new Document();
		document.add(new StringField("id", db.getFileID(), Store.YES));
		document.add(new StringField("filename", filename, Store.YES));
		document.add(new StringField("uploader", userName, Store.YES));
		document.add(new StringField("contentType", contontType, Store.YES));
		document.add(new StringField("holder", db.getHolder(), Store.YES));
		document.add(new StringField("saveFilePath", db.getFilePath(),
				Store.YES));
		document.add(new StringField("lastmodified", lastmodifieddate
				.toString(), Store.YES));
		document.add(new StringField("lastmodifiedstr", lastmodifieddatestr,
				Store.YES));
		document.add(new TextField("content", string1.trim(), Store.YES));
		IndexWriter indexWriter = LuceneUtils.getIndexWriter();
		indexWriter.addDocument(document);
		System.out.println(string1.substring(0, 100));
		indexWriter.commit();
		index.info("index sucess" + filename);
		return 1;
	}

	public void saveFile(MultipartFile file, HttpServletRequest request,
			Long lastmodifieddate, DocumentBean db)
			throws IllegalStateException, IOException {
		String path = FileUtils.getRootPath(request, "\\WEB-INF\\upload")
				+ FileUtils.generateRandomDir(FileUtils
						.generateRandonFileName(file.getOriginalFilename()));
		File srcFile = new File(path);
		srcFile.mkdirs();
		db.setFileID(FileUtils.generateRandonFileName(db.getFilename()));
		File tarFile = new File(srcFile, FileUtils.generateRandonFileName(file
				.getOriginalFilename()));
		file.transferTo(tarFile);
		tarFile.setLastModified(lastmodifieddate);
		db.setFilePath(tarFile.getAbsolutePath());
	}

	@RequestMapping("/fulltext")
	public ModelAndView fullText(ModelAndView mv,
			@RequestParam(value = "id", required = true) String id) {
//		try {
//			Document documentByAssignId = LuceneUtils.getDocumentByAssignId(
//					"id", id);
//			mv.addObject("document", documentByAssignId);
//		} catch (Exception e) {
//			mv.addObject("content", "get error!");
//		}
		mv.addObject("content", "closed!");
		mv.setViewName("fullText");
		return mv;
	}
	@RequestMapping("/exporttoexcel")
	public @ResponseBody Object exporttoexcel(@RequestParam(value = "key", required = true) String string,
			@RequestParam(value = "length", defaultValue = "50") Integer digstlength,
			HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "holder", required = true) String holder) throws Exception{
		if(StringUtils.equals(holder, "on")){
			holder="public";
		}else{
			holder=(String) request.getSession().getAttribute("REMOTE_USER");
		}
			@SuppressWarnings("unchecked")
			Map<String, Object> search = (Map<String, Object>)search(string, digstlength, request, holder);
			 XSSFWorkbook wb =new  XSSFWorkbook (); 
			XSSFSheet sheet = wb.createSheet("new sheet"); 
			Integer i=0,j=0,count=0;
			XSSFRow row = sheet.createRow(i++);
			row.createCell(j++).setCellValue("order");
			row.createCell(j++).setCellValue("filename");
			row.createCell(j++).setCellValue("matchscore");
			row.createCell(j++).setCellValue("filePath");
			row.createCell(j++).setCellValue("uploader");
			row.createCell(j++).setCellValue("lastmodifieddatestr");
//			row.createCell(j++).setCellValue("digist");
			Collection<Object> values = search.values();
			for (Object object : values) {
				 row = sheet.createRow(i++);
				 Integer k=0;
				try {
					DocumentBean  documentBean=(DocumentBean)object;
					row.createCell(k++).setCellValue(i-1);
					row.createCell(k++).setCellValue(documentBean.getFilename());
					row.createCell(k++).setCellValue(documentBean.getMatchscore());
					row.createCell(k++).setCellValue(documentBean.getFilePath());
					row.createCell(k++).setCellValue(documentBean.getUploader());
					row.createCell(k++).setCellValue(documentBean.getLastmodifieddatestr());
//					row.createCell(k++).setCellValue(documentBean.getDigist());
				} catch (Exception e) {
					count=(Integer)object;
					System.out.println(object);
				}
				
			}
			String filename = string+"_"+digstlength+"_"+count+".xlsx";
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			String agent = request.getHeader("user-agent");
			filename = FileUtils.encodeDownloadFilename(filename, agent);
			response.setHeader("Content-Disposition", "attachment;filename="
					+ filename);
//			response.setHeader("Content-Disposition", "attachment;filename="
//					+ "x.xlsx");
			ServletOutputStream outputStream = response.getOutputStream();
			FileOutputStream fOut = new FileOutputStream(filename);	
//			wb.write(outputStream);
			wb.write(fOut);
			wb.close();
//			outputStream.flush();
//			outputStream.close();
			InputStream in = new FileInputStream(new File(filename));
			int b;
			while ((b = in.read()) != -1) {
				response.getOutputStream().write(b);
			}
			in.close();
			return null;
			
//           FileInputStream fis=new file
//			HSSFCell cell = row.createCell((short)0); 
//			cell.setCellValue(1); 
//			row.createCell((short)1).setCellValue(1.2); 
//			row.createCell((short)2).setCellValue("This is a string"); 
//			row.createCell((short)3).setCellValue(true); 
//			FileOutputStream fileOut = new FileOutputStream("workbook.xls"); 
//			wb.write(fileOut); 
//			fileOut.close();
//		Document document;
//		try {
//			document = LuceneUtils.getDocumentByAssignId("id", id);
//		} catch (Exception e1) {
//
//		}
//		 File file = new File(document.get("saveFilePath"));
//		
//		String filename = document.get("filename");
//		String mimeType = document.get("contentType");
//		response.setContentType("application/vnd.ms-excel;charset=utf-8");
//		String agent = request.getHeader("user-agent");
//		filename = FileUtils.encodeDownloadFilename(filename, agent);
//		response.setHeader("Content-Disposition", "attachment;filename="
//				+ filename);
//		InputStream in = new FileInputStream(file);
//		int b;
//		while ((b = in.read()) != -1) {
//			response.getOutputStream().write(b);
//		}
//		in.close();
//		
		
		
		
		
	}
	@RequestMapping("/search")
	public @ResponseBody Object search(
			@RequestParam(value = "key", required = true) String string,
			@RequestParam(value = "length", defaultValue = "20") Integer digstlength,
			HttpServletRequest request,
			@RequestParam(value = "holder", required = true) String holder)
			throws Exception {
		if ("" == string) {
			return null;
		}
		System.out.println(string);
		Analyzer analyzer = new IKAnalyzer();
		QueryParser queryParser = new QueryParser("content", analyzer);
		Query query1 = queryParser.parse(string);
		if (!StringUtils.equals("public", holder)) {
			holder = (String) request.getSession().getAttribute("REMOTE_USER");
		}
		Query query2 = new TermQuery(new Term("holder", holder));
		BooleanQuery query = new BooleanQuery();
		query.add(query1, Occur.MUST);
		query.add(query2, Occur.MUST);
		IndexSearcher indexSearcher = LuceneUtils.getIndexSearcher();
		System.out.println(query);
		TopDocs topDocs = indexSearcher.search(query, Integer.MAX_VALUE);
		System.out.println("result：" + topDocs.totalHits);
		Formatter formatter = new SimpleHTMLFormatter("<font color='red'>",
				"</font>");
		Scorer frgementScorer = new QueryScorer(query);
		Highlighter highlighter = new Highlighter(formatter, frgementScorer);
		highlighter.setTextFragmenter(new SimpleFragmenter(digstlength));
		// Map<String, Map<String, String>> map = new LinkedHashMap<String,
		// Map<String, String>>();
		// for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
		// Map<String, String> cMap = new HashMap<String, String>();
		// System.out.println("得分" + scoreDoc.score);
		// cMap.put("score", Float.toString(scoreDoc.score));
		// Document document = indexSearcher.doc(scoreDoc.doc);
		// String string2 = document.get("filename");
		// System.out.println(string2);
		// String bestFragment = highlighter.getBestFragment(new IKAnalyzer(),
		// "contont", document.get("content"));
		// // System.out.println("pdf:" + document.get("content"));
		// System.out.println(bestFragment);
		// cMap.put("digest", bestFragment);
		// map.put(string2, cMap);
		// }
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			DocumentBean db = new DocumentBean();
			db.setMatchscore(scoreDoc.score);
			Document document = indexSearcher.doc(scoreDoc.doc);
			String bestFragment = highlighter.getBestFragment(new IKAnalyzer(),
					"contont", document.get("content"));
			db.setFilename(document.get("filename"));
			db.setDigist(bestFragment);
			db.setLastmodifieddatestr(document.get("lastmodifiedstr"));
			db.setLastmodified(document.get("lastmodified"));
			db.setHolder(document.get("holder"));
			if (new File(document.get("saveFilePath")).exists()) {
				db.setFilePath(document.get("saveFilePath"));
			} else {
				db.setFilePath("");
			}
			db.setUploader(document.get("uploader"));
			db.setFileID(document.get("id"));
			map.put(db.getFileID(), db);
		}
		
		map.put("length", map.size());
		//todo: excel文件生成和保存，存在性和时效性检查，每天第一次查询生成，每天自动删除。
		return map;

	}
}
