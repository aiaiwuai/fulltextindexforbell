package cn.com.alcatel_sbell.fulltextindex.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;





import cn.com.alcatel_sbell.fulltextindex.bean.DocumentBean;
import cn.com.alcatel_sbell.lucene.utils.LuceneUtils;
import cn.com.alcatel_sbell.utils.FileUtils;

@Controller
@RequestMapping("/ajax")
public class AjaxQuery {
	@RequestMapping("/querybyfilename")
	public @ResponseBody
	Object queryByFilenameAndHolder(
			@RequestParam(value = "filename", required = true) String filename,
			@RequestParam(value = "holder", required = true) String holder,HttpServletRequest request)
			throws Exception {
		System.out.println("filename" + filename);
	
		Map<String, String> map = new HashMap<String, String>();
		IndexSearcher indexSearcher =null;
		try {
			indexSearcher= LuceneUtils.getIndexSearcher();
		} catch (Exception e) {
			return map;
		}
		Query query1 = new TermQuery(new Term("filename", filename));
		if(!StringUtils.equalsIgnoreCase("public", holder)){
			holder=(String)request.getSession().getAttribute("REMOTE_USER");
		}
		Query query2 = new TermQuery(new Term("holder", holder));
		BooleanQuery query=new  BooleanQuery();
		query.add(query1, Occur.MUST);
		query.add(query2, Occur.MUST);
		System.out.println("query" + query);
		TopDocs topDocs = indexSearcher.search(query, Integer.MAX_VALUE);
		System.out.println("结果数：" + topDocs.totalHits);
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			System.out.println("score" + scoreDoc.score);
			map.put("score", scoreDoc.score + "");
			Document document = indexSearcher.doc(scoreDoc.doc);
			String string2 = document.get("filename");
			System.out.println("filename:" + string2);
			map.put("filename", string2);
			map.put("lastmodified", document.get("lastmodified"));
			map.put("lastmodifiedstr", document.get("lastmodifiedstr"));
			map.put("holder", document.get("holder"));
			map.put("saveFilePath", document.get("saveFilePath"));
			map.put("uploader", document.get("uploader"));
		}
		return map;
	}
	@RequestMapping("/downloadfile")
	public @ResponseBody Object downlaodfile(
			@RequestParam(value = "id", required = true) String id,HttpServletRequest request,HttpServletResponse response)
			throws IOException {
				Document document;
				try {
					document = LuceneUtils.getDocumentByAssignId("id", id);
				} catch (Exception e1) {
				return "'error':'unkown error','status':'3'";

				}
				 File file = new File(document.get("saveFilePath"));
				
				String filename = document.get("filename");
				String mimeType = document.get("contentType");
				response.setContentType(mimeType);
				String agent = request.getHeader("user-agent");
				filename = FileUtils.encodeDownloadFilename(filename, agent);
				response.setHeader("Content-Disposition", "attachment;filename="
						+ filename);
				InputStream in = new FileInputStream(file);
				int b;
				while ((b = in.read()) != -1) {
					response.getOutputStream().write(b);
				}
				in.close();
				return null;
	}
	
	@RequestMapping("/deletefile")
	public @ResponseBody Object deletefile(
			@RequestParam(value = "id", defaultValue="all") String id,HttpSession session)
			throws IOException {
		List<HashMap<String, String>> list=new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map=new HashMap<String, String>();
		Document document;
		try {
			document = LuceneUtils.getDocumentByAssignId("id", id);
		} catch (Exception e1) {
			map.put("error", "unkown error");
			map.put("status", "3");
			list.add(map);
			return list;
			
		}
		if(!StringUtils.equals(document.get("uploader"),(String) session.getAttribute("REMOTE_USER"))){
			map.put("error", "you have no authorization!");
			map.put("status", "0");
			list.add(map);
			return list;
		}
		try {
			new File(document.get("saveFilePath")).delete();
			map.put("sucess", "ok");
			map.put("status", "1");
			list.add(map);
			return list;
		} catch (Exception e) {
			map.put("error", "error on delete file");
			map.put("status", "2");
			list.add(map);
			return list;
		}
	}
	@RequestMapping("/list")
	public @ResponseBody Object list(HttpServletRequest request) throws Exception {
		IndexSearcher indexSearcher=null;
		try {
			indexSearcher = LuceneUtils.getIndexSearcher();
		} catch (Exception e) {
			return "";
		}
		Query query1 = new TermQuery(new Term("holder", "public"));
		Query query2 = new TermQuery(new Term("uploader", (String)request.getSession().getAttribute("REMOTE_USER")));
		Query query3 = new TermQuery(new Term("holder", (String)request.getSession().getAttribute("REMOTE_USER")));

		BooleanQuery query=new  BooleanQuery();
		query.add(query1, Occur.SHOULD);
		query.add(query2, Occur.SHOULD);
		query.add(query3, Occur.SHOULD);
		TopDocs search = indexSearcher.search(query, 30);
		Map<String,DocumentBean> map = new LinkedHashMap<String, DocumentBean>();
		for (ScoreDoc scoreDoc : search.scoreDocs) {
			DocumentBean db=new DocumentBean();
			Document document = indexSearcher.doc(scoreDoc.doc);
			db.setFilename(document.get("filename"));
			db.setLastmodifieddatestr( document.get("lastmodifiedstr"));
			db.setLastmodified(document.get("lastmodified"));
			db.setHolder(document.get("holder"));
			if(new File(document.get("saveFilePath")).exists()){
				db.setFilePath(document.get("saveFilePath"));
			}else{
				db.setFilePath("");
			}
			db.setUploader(document.get("uploader"));
			db.setFileID(document.get("id"));
			map.put(db.getFileID(),db);
		}
		return map;
	}
}
