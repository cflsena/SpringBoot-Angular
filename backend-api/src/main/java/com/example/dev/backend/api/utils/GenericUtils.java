package com.example.dev.backend.api.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public final class GenericUtils {
	
	public static boolean isNull (Object obj) {
		return obj == null;
	}
	
	public static boolean isNotNull (Object obj) {
		return !isNull(obj);
	}
	
	public static boolean isEmpyt (Object obj) {
		if (obj instanceof List) {
			return !isNull(obj) && ((List<?>) obj).size() == 0;
		} else if (obj instanceof Object []) {
			return !isNull(obj) && ((Object []) obj).length == 0;
		}
		return !isNull(obj) && obj.toString().equals(StringUtils.EMPTY);
	}
	
	public static boolean isNotEmpyt (Object obj) {
		return !isEmpyt(obj);
	}
	
	public static boolean isEmpytOrNull (Object obj) {
		if (obj instanceof List) {
			return isNull(obj) || ((List<?>) obj).size() == 0;
		} else if (obj instanceof Object []) {
			return isNull(obj) || ((Object []) obj).length == 0;
		}
		return isNull(obj) || obj.toString().equals(StringUtils.EMPTY);
	}
	
	public static boolean isNotEmpytAndNotNull (Object obj) {
		return !isEmpytOrNull(obj);
	}

}
