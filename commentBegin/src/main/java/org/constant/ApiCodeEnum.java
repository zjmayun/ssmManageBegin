package org.constant;

public enum ApiCodeEnum {
	SMS_SEND_FAIL(1001,"验证码发送失败"),
	FAIL(1,"用户验证码已存在"),
	SMS_ERROR(2,"验证码错误"),
	SUCCESS(0,"OK"),
    MEMBER_NOT_SAME(4,"会员不一致"),	
	TOKEN_FAIL(3,"Token不存在"),
	USER_NOT_EXISTS(1002,"用户不存在"),
	BUSINESS_MODIFY_FAIL(1106,"商户修改失败");
	
	private Integer errno;
	private String msg;
	
	
    ApiCodeEnum(Integer errno, String msg) {
		this.errno = errno;
		this.msg = msg;
	}

	public Integer getErrno() {
		return errno;
	}

	public String getMsg() {
		return msg;
	}
	
}
