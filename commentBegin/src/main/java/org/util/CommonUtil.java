package org.util;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.constant.SessionKeyConst;
import org.dto.ActionDto;


/**
 * 共通工具类.
 */
public class CommonUtil {
	/**
	 * 方法描述：判断一个字符串是否为null或空字符串（被trim后为空字符串的也算）。
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @return false：不是空字符串，true：是空字符串
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static boolean isNull(Object object) {
		if(object!=null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 生成指定位数的随机整数
	 * 
	 * @param number
	 *            位数
	 * @return 随机整数
	 */
	public static int random(int number) {
		return (int) ((Math.random() * 9 + 1) * Math.pow(10, number - 1));
	}

	/**
	 * 获取UUID
	 * 
	 * @return UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	//我真的是操蛋了这代码了
	public static boolean contains(HttpSession session,String url,String method) {
		Object object=session.getAttribute(SessionKeyConst.ACTION_INFO);
		if(object!=null) {
			List<ActionDto> list=(List<ActionDto>)object;
			for(ActionDto actionDto:list) {
				if(!isEmpty(actionDto.getMethod())&&!actionDto.getMethod().equals(method)) {
					continue;
				}
				if(!url.matches(actionDto.getUrl())) {
					continue;
				}
				return true;
			}
		}
		return false;
	}
	
}
