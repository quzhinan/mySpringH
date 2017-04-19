package com.qzn.models;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonData<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Integer perPageNum = 10;

	private String resultMark;

	private List<T> resultList;

	private Integer page;

	private Integer sizeOfThisPage;

	private long sumPage;

	private Long sizeOfAll;

	public JsonData() {
	}

	public JsonData(String resultMark) {
		this.resultMark = resultMark;
	}

	public JsonData(Long sizeOfAll) {
		if (sizeOfAll > 0) {
			this.sizeOfAll = sizeOfAll;
			this.sumPage = (long) Math.ceil(sizeOfAll / perPageNum);
			this.resultMark = "SUCCESS";
		} else {
			this.sizeOfAll = 0l;
			this.sumPage = 1l;
			this.sizeOfThisPage = 0;
			this.resultMark = "EMPTY";
		}

	}

	public static Long sumPage(Long sizeOfAll) {
		return (long) Math.ceil(sizeOfAll / perPageNum);
	}

	public static Integer getOffset(Integer page) {
		return (page - 1) * perPageNum;
	}

	public String toJsonString() throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, Object> tmpMapper = new HashMap<>();
		tmpMapper.put("sizeOfThisPage", sizeOfThisPage);
		tmpMapper.put("sizeOfAll", sizeOfAll);
		tmpMapper.put("sumPage", sumPage);
		tmpMapper.put("resultList", resultList);
		tmpMapper.put("resultMark", resultMark);
		tmpMapper.put("page", page);
		ObjectMapper objm = new ObjectMapper();
		String supplierJson = objm.writeValueAsString(tmpMapper);
		return supplierJson;
	}

	public static String toJsonString(Object data) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objm = new ObjectMapper();
		String supplierJson = objm.writeValueAsString(data);
		return supplierJson;
	}

	public String getResultMark() {
		return resultMark;
	}

	public void setResultMark(String resultMark) {
		this.resultMark = resultMark;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSizeOfThisPage() {
		return sizeOfThisPage;
	}

	public void setSizeOfThisPage(Integer sizeOfThisPage) {
		this.sizeOfThisPage = sizeOfThisPage;
	}

	public long getSumPage() {
		return sumPage;
	}

	public void setSumPage(long sumPage) {
		this.sumPage = sumPage;
	}

	public Long getSizeOfAll() {
		return sizeOfAll;
	}

	public void setSizeOfAll(Long sizeOfAll) {
		this.sizeOfAll = sizeOfAll;
	}

}
