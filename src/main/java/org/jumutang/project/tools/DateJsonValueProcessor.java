package org.jumutang.project.tools;

import java.util.Date;

import org.jumutang.basicbox.tools.DateX;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor {

	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		// TODO Auto-generated method stub
		if (null != value) {
			
			if (value instanceof Date) {
				return DateX.formatToString((Date)value, DateX._DATE_TIME_24);
			}
			
		}
		return null;
	}

}
