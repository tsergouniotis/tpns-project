package com.tpns.domain.article;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class MediaResource implements Serializable {

	private static final long serialVersionUID = -6820814412104405821L;

	private Long id;

	private MediaResourceType type;

	private String url;

	public MediaResource() {
	}

	public MediaResource(MediaResourceType type, String url) {
		this.type = type;
		this.url = url;
	}

	@XmlElement(name = "id")
	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	@XmlElement(name = "type")
	public MediaResourceType getType() {
		return type;
	}

	public void setType(MediaResourceType type) {
		this.type = type;
	}

	@XmlElement(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
