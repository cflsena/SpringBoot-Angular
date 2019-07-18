package com.example.dev.backend.api.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public final class GenericUtils {
	
	/**
	 * @param obj - Objeto a ser verificado
	 * @return Retorna true se o objeto estiver nulo
	 */
	public static boolean isNull (Object obj) {
		return obj == null;
	}
	
	/**
	 * @param obj - Objeto a ser verificado
	 * @return Retorna true se o objeto n�o estiver nulo
	 */
	public static boolean isNotNull (Object obj) {
		return !isNull(obj);
	}
	
	/**
	 * @param obj - Objeto a ser verificado
	 * @return Retorna true se o objeto estiver vazio
	 */
	public static boolean isEmpyt (Object obj) {
		if (obj instanceof List) {
			return !isNull(obj) && ((List<?>) obj).size() == 0;
		} else if (obj instanceof Object []) {
			return !isNull(obj) && ((Object []) obj).length == 0;
		}
		return !isNull(obj) && obj.toString().equals(StringUtils.EMPTY);
	}
	
	/**
	 * @param obj - Objeto a ser verificado
	 * @return Retorna true se o objeto n�o estiver vazio
	 */
	public static boolean isNotEmpyt (Object obj) {
		return !isEmpyt(obj);
	}
	
	/**
	 * @param obj - Objeto a ser verificado
	 * @return Retorna true se o objeto estiver nulo ou vazio 
	 */
	public static boolean isEmpytOrNull (Object obj) {
		if (obj instanceof List) {
			return isNull(obj) || ((List<?>) obj).size() == 0;
		} else if (obj instanceof Object []) {
			return isNull(obj) || ((Object []) obj).length == 0;
		}
		return isNull(obj) || obj.toString().equals(StringUtils.EMPTY);
	}
	
	/**
	 * @param obj - Objeto a ser verificado
	 * @return Retorna true se o objeto n�o estiver nulo ou vazio
	 */
	public static boolean isNotEmpytOrNotNull (Object obj) {
		return !isEmpytOrNull(obj);
	}

}
