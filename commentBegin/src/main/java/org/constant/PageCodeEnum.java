package org.constant;

public enum PageCodeEnum {
	AD_SUCCESS(1001,"广告添加成功"),
	AD_FAIL(1002,"广告添加失败"),
	AD_DELETE_FAIL(1003,"广告删除失败"),
	AD_DELETE_SUCCESS(1004,"广告删除成功"),
	LOGIN_FAIL(1301,"用户名或密码错误"),
	AD_MODIFY_SUCCESS(1005,"修改成功"),
	AD_MODIFY_FAIL(1006,"修改失败"),
	BUSINESS_ADD_SUCCESS(1100,"商户添加成功"),
	BUSINESS_ADD_FAIL(1101,"商户添加失败"),
	BUSINESS_DELETE_SUCCESS(1103,"商戶刪除成功"),
	BUSINESS_DELETE_FAIL(1104,"商户删除失败"),
	BUSINESS_MODIFY_SUCCESS(1105,"商戶修改成功"),
	BUSINESS_MODIFY_FAIL(1106,"商户修改失败"),
	USER_SUCCESS(1401,"用户添加成功"),
	USER_FAIL(1402,"用户添加失败"),
	USER_DELETE_SUCCESS(1403,"用户删除成功"),
	USER_DELETE_FAIL(1404,"用户删除失败"),
	USER_MODIFY_SUCCESS(1405,"用户修改成功"),
	USER_MOFIGY_FAIL(1406,"用户修改失败"),
	USER_EXISTS(1407,"用户名已存在"),
	ADD_SUCCESS(1000,"添加成功"),
	ADD_FAIL(1300,"添加失败"),
	MODIFY_SUCCESS(1100,"修改成功"),
	MODIFY_FAIL(1400,"修改失败"),
	REMOVE_FAIL(1500,"删除失败"),
	REMOVE_SUCCESS(1200,"删除成功"),
	SESSION_TIMEOUT(2001,"session超时"),
	NO_AUTH(2002,"没有权限访问");
	
	
	private Integer code;
	private String message;
	
	public static final String KEY="pageCode";
	
    private PageCodeEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	
}
