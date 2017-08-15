package org.jumutang.project.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 适用于多个表的关联检索,手动运行main.
public class SelectJoinbean {

	public static void main(String[] args) {
		//单表
		//String danbiao="SELECT ID, RANK_NAME, RETURN_DIAMOND, RETURN_GOLD, CREAT_TIME, DELETE_FLAG FROM t_rank";
		//表前加个temp前缀
		//diang(danbiao,"TU","有别名");
		//表前加个temp前缀
        //diang(danbiao,"tu","");
		
		// 多表
		selectLeftJoin();
	}




	private static void selectLeftJoin() {
		String t1="SELECT ID, OPEN_ID, NICK_NAME, HEAD_IMG, MOBILE, NAME, CREAT_TIME, USED_DIAMOND, ALL_DIAMOND, USED_GOLD, ALL_GOLD, USER_RANK_ID, USER_MAX_RANK_ID, THIRD_PART_ID, JS_USER_ID, DELETE_FLAG FROM t_mall_user";
		String t2="SELECT ID, RANK_NAME, RETURN_DIAMOND, RETURN_GOLD, CREAT_TIME, DELETE_FLAG FROM t_rank";
		String t3="SELECT ID, OPEN_ID, NICK_NAME, HEAD_IMG, MOBILE, NAME, CREAT_TIME, USED_DIAMOND, ALL_DIAMOND, USED_GOLD, ALL_GOLD, USER_RANK_ID, USER_MAX_RANK_ID, THIRD_PART_ID, JS_USER_ID, DELETE_FLAG FROM t_mall_user";
		String t4="SELECT ID, PRIZESET, PRIZENAME, PRIZENUM, TYPE1_PRIZEPROBABILITY, TYPE2_PRIZEPROBABILITY, TYPE3_PRIZEPROBABILITY, TYPE4_PRIZEPROBABILITY, TYPE5_PRIZEPROBABILITY, FETCH_NUM, STATUS, SHOW_ORDER, DELETE_FLAG, FETCH_WAY FROM t_prize";
		
		List<String> tabnameList= new ArrayList<>();
		HashMap<String, List<String>> connamelist= new HashMap<>();
        String[]  tablearray={t1,t2};
		String[]  resulttablebiename={"TA","TB","TC","TD"};
		for (String st : tablearray) {
			String[] split = st.split("FROM");
			String tbname=split[1].trim();
			tabnameList.add(tbname);
			String replacecom = split[0].replace("SELECT", "");
			String[] split2 = replacecom.split(",");
			List<String> aconlist = new ArrayList<>();
			for (String str : split2) {
				aconlist.add(str.trim());
			}
			connamelist.put(tbname, aconlist);
		}
		
		String resultselcet="SELECT %s \nFROM %s";
		StringBuffer selecnote= new StringBuffer();
		StringBuffer fromnote= new StringBuffer();
		// select 内容
		for (int i= 0;i<connamelist.size();i++){
			String biename = resulttablebiename[i];
			List<String> list = connamelist.get(tabnameList.get(i));
			for (int j= 0;j<list.size();j++) {
				String str= list.get(j);
				if(j==list.size()-1){
					selecnote.append(biename+"."+str+" "+biename+str+"\n");
				}else{
					selecnote.append(biename+"."+str+" "+biename+str+",");
				}
			}
			if(i<connamelist.size()-1){
				selecnote.append(",");
			}

		}
		
		for (int i= 0;i<tabnameList.size();i++){
			String tablename = tabnameList.get(i);
			String biename = resulttablebiename[i];
			if(i==0){
				fromnote.append(tablename+" "+biename+"\n");
			}else{
				fromnote.append("LEFT JOIN"+" "+tablename+" "+biename+" ON ***"+"\n");
			}
			
		}
		// 检索的内容
		
		System.out.println("----------// 检索的内容----------");
		System.out.println(String.format(resultselcet, selecnote,fromnote));
		


		{
			StringBuilder fields = new StringBuilder();
			StringBuilder methods = new StringBuilder();
			StringBuilder beanResult1 = new StringBuilder();
			StringBuilder beanResult2 = new StringBuilder();
			for (int i = 0; i < connamelist.size(); i++) {
				String biename = resulttablebiename[i];
				List<String> list = connamelist.get(tabnameList.get(i));
				for (int j = 0; j < list.size(); j++) {
					String str = list.get(j);
	
					fields.append(getFieldStr(biename + str, "String", null));
					methods.append(getMethodStr(biename + str, "String"));
					beanResult1.append(getSelectResutl("bean"+(i+1),str,biename + str));
					beanResult2.append(getSelectResutl("bean",biename + str,biename + str));
	
				}
	
			}
			System.out.println("----------//bean的内容----------");
			System.out.println(fields.append(methods));
			System.out.println("----------//bean检索的内容((适合多个bean分开)----------");
			System.out.println(beanResult1);
			System.out.println("----------//bean检索的内容(适合多个bean已经合成一个了)----------");
			System.out.println(beanResult2);
		}
	}




	private static void diang(String danbiao2,String temp,String biem) {
		String danbiao=danbiao2;
		String[] split = danbiao.split("FROM");
		String tbname=split[1].trim();                  //表名字
		String replacecom = split[0].replace("SELECT", "");
		String[] split2 = replacecom.split(",");
		List<String> aconlist = new ArrayList<>();     // select 项目
		for (String str : split2) {
			aconlist.add(str.trim());
		}
		StringBuilder fields = new StringBuilder();
		StringBuilder methods = new StringBuilder();
		StringBuilder beanResult1 = new StringBuilder();
		StringBuilder beanResult2 = new StringBuilder();
		StringBuilder beaninsert = new StringBuilder();
		
		StringBuilder beaninselecttemp = new StringBuilder();
		
		for (String item : aconlist) {
			
			String biename = tbname;

				fields.append(getFieldStr(item, "String", null));
				methods.append(getMethodStr(item, "String"));
				beanResult2.append(getSelectResutl("bean",item,item));
				
				beaninsert.append(getInsertps(item,null,null));
				
				if(StringUtil.isEmpty(biem)){
					beaninselecttemp.append(temp+"."+item +", ");
				}else{
					beaninselecttemp.append(temp+"."+item +" "+temp+item+", ");
				}


		}
		System.out.println("----------//bean的内容----------");
		System.out.println(fields.append(methods));
		System.out.println("----------//bean检索的内容(适合多个bean已经合成一个了)----------");
		System.out.println(beanResult2);
		System.out.println("----------//bean的插入----------");
		System.out.println(beaninsert);
		System.out.println("----------//selecttemp的----------");
		System.out.println(beaninselecttemp);
		System.out.println("----------current_timestamp----------");
	}

	
	
	
	private static String getSelectResutl(String bean, String NAME, String NAME2) {
		String str = "%s.set%s(rs.getString(\"%s\"));\t\n";

		return String.format(str, bean,NAME,NAME2);
	}
	
	private static String getInsertps(String bean, String NAME, String NAME2) {
//		String str = "%s.set%s(rs.getString(\"%s\"));\t\n";
		
		String str2 = "ps.setString(i++, bean.get%s());\t\n";

		return String.format(str2, bean,NAME,NAME2);
	}
	
	private static String getFieldStr(String field, String type, String cmt) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t").append("private ").append(type).append(" ")
				.append(field).append(";");
		if (cmt != null) {
			sb.append("//").append(cmt);
		}
		sb.append("\r\n");
		return sb.toString();
	}
	
	private static String getMethodStr(String field, String type) {
		StringBuilder get = new StringBuilder("\tpublic ");
		get.append(type).append(" ");
		if (type.equals("boolean")) {
			get.append(field);
		} else {
			get.append("get");
			get.append(upperFirestChar(field));
		}
		get.append("(){").append("\r\n\t\treturn this.").append(field)
				.append(";\r\n\t}\r\n");
		StringBuilder set = new StringBuilder("\tpublic void ");

		if (type.equals("boolean")) {
			set.append(field);
		} else {
			set.append("set");
			set.append(upperFirestChar(field));
		}
		set.append("(").append(type).append(" ").append(field)
				.append("){\r\n\t\tthis.").append(field).append("=")
				.append(field).append(";\r\n\t}\r\n");
		get.append(set);
		return get.toString();
	}
	
	public static String upperFirestChar(String src) {
		return src.substring(0, 1).toUpperCase().concat(src.substring(1));
	}
}
