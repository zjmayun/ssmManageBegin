package org.dto;

import org.constant.ApiCodeEnum;

public class ApiCodeDto {
	private Integer errno;
	private String msg;
	private String code;
	private String token;

	public ApiCodeDto(ApiCodeEnum apiCodeEnum) {
		this.errno=apiCodeEnum.getErrno();
		this.msg=apiCodeEnum.getMsg();
	}
	
	public Integer getErrno() {
		return errno;
	}

	public void setErrno(Integer errno) {
		this.errno = errno;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
     
}
