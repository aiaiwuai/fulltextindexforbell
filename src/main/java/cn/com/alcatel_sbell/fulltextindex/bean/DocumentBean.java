package cn.com.alcatel_sbell.fulltextindex.bean;

public class DocumentBean {
	private String filename;
	private String fileID;
	private String filePath;
	private String uploader;
	private String holder;
	private String lastmodified;
	private String lastmodifieddatestr;
	private String fileIndexStatus;
	private String digist;
	private float matchscore;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileID() {
		return fileID;
	}
	public void setFileID(String fileID) {
		this.fileID = fileID;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getHolder() {
		return holder;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	public String getLastmodifieddatestr() {
		return lastmodifieddatestr;
	}
	public void setLastmodifieddatestr(String lastmodifieddatestr) {
		this.lastmodifieddatestr = lastmodifieddatestr;
	}
	public String getFileIndexStatus() {
		return fileIndexStatus;
	}
	public void setFileIndexStatus(String fileIndexStatus) {
		this.fileIndexStatus = fileIndexStatus;
	}
	public String getLastmodified() {
		return lastmodified;
	}
	public void setLastmodified(String lastmodified) {
		this.lastmodified = lastmodified;
	}
	public String getDigist() {
		return digist;
	}
	public void setDigist(String digist) {
		this.digist = digist;
	}
	public float getMatchscore() {
		return matchscore;
	}
	public void setMatchscore(float matchscore) {
		this.matchscore = matchscore;
	}
	
	
}
