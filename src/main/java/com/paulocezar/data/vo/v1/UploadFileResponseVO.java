package com.paulocezar.data.vo.v1;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

public class UploadFileResponseVO extends ResourceSupport implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;
	
	public UploadFileResponseVO() {
	}
	
	public UploadFileResponseVO(String fileName, String fileDownloadUri, String fileType, long size) {
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
	}

	protected String getFileName() {
		return fileName;
	}

	protected void setFileName(String fileName) {
		this.fileName = fileName;
	}

	protected String getFileDownloadUri() {
		return fileDownloadUri;
	}

	protected void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	protected String getFileType() {
		return fileType;
	}

	protected void setFileType(String fileType) {
		this.fileType = fileType;
	}

	protected long getSize() {
		return size;
	}

	protected void setSize(long size) {
		this.size = size;
	}

	
}