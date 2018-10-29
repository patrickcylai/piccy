package com.piccy.demo.responses;

public class FileResponse {
	
	private String filename;
	
	private String downloadUri;
	
	private long size;
	
	private String filetype;
	
	
	public FileResponse(String filename, String downloadUri, String filetype, long size) {
		this.filename = filename;
		this.downloadUri = downloadUri;
		this.size = size;
		this.filetype = filetype;
	}
	
	
	public String getFilename() { return this.filename; }
	
	public String getDownloadUri() { return this.downloadUri; }
	
	public String getFiletype() { return this.filetype; }
	
	public long getSize() { return this.size; }
	
	
	

}
