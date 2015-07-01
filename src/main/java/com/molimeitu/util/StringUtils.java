package com.molimeitu.util;

import android.content.Context;


/**
 * 字符串处理
 * 
 * @author genie
 * 
 */
public class StringUtils {

	/**
	 * @Description: 判断String是否为空,true代表空，false代表非空
	 * @param @param string
	 * @return boolean
	 * @throws
	 */
	public static boolean isEmpty(String string) {
		if (string == null) {
			return true;
		}

		return string.isEmpty();
	}

	/**
	 * 返回两个字符串中间的内容
	 * 
	 * @param all
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getMiddleString(String all, String start, String end) {
		int beginIdx = all.indexOf(start) + start.length();
		int endIdx = all.indexOf(end);
		return all.substring(beginIdx, endIdx);
	}

	/**
	 * 判定输入汉字
	 * @param c
	 * @return
	 */
	public boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判定输入数字
	 * @param c
	 * @return
	 */
	public boolean isNumber(char c) {
		if ( c >= '0' && c <= '9' ) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判定输入字母
	 * @param c
	 * @return
	 */
	public boolean isCharacter(char c) {
		if (  ( c >= 'a' && c <= 'z') || ( c >= 'A'  &&  c <= 'Z') ) {
			return true;
		}
		return false;
	}
	
	/**
     * Capitalize the first letter
     * 
     * @param s
     *            model,manufacturer
     * @return Capitalize the first letter
     */
    public static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        }
        else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
    
	/**
	 * 密码是否有效
	 * @param password 密码
	 * @return boolean true验证通过 false验证不通过
	 */
	public static boolean isValidPwd(String password) {
		if (StringUtils.isEmpty(password)) {
			return false;
		}
		password = password.trim();
		if (password.length() < 6 || password.length() > 20) {
			return false;
		}
		String regex = "[0-9a-zA-Z]+$";
		return password.matches(regex);
	}

    /**
     * 电话码号是否有效
     * @param phone 密码
     * @return boolean true验证通过 false验证不通过
     */
    public static boolean isValidPhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }

        String regex = "^1[358]\\d{9}$";
        return phone.matches(regex);
    }

    /**
     * 邮箱是否有效
     * @param mail 密码
     * @return boolean true验证通过 false验证不通过
     */
    public static boolean isValidMail(String mail) {
        if (StringUtils.isEmpty(mail)) {
            return false;
        }

        String regex = "^[a-zA-Z][\\\\w\\\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\\\w\\\\.-]*[a-zA-Z0-9]\\\\.[a-zA-Z][a-zA-Z\\\\.]*[a-zA-Z]$";
        return mail.matches(regex);
    }

}
