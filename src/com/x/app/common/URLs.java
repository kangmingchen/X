package com.x.app.common;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

import com.x.modle.XObject;

/**
 * <p>
 * Description:接口URL实体类
 * </p>
 * 
 * @author Chenkm
 * @version 1.0
 * @date 2014年5月16日
 */
public class URLs extends XObject {

	private static final long serialVersionUID = 8177670331431462319L;

	public final static String HOST = "192.168.0.1";
	public final static String HTTP = "http://";
	public final static String HTTPS = "https://";

	private final static String URL_SPILTER = "/";
	private final static String URL_UNDERLINE = "_";

	private final static String URL_API_HOST = HTTP + HOST + URL_SPILTER;
	/**
	 * 获取最新信息列表接口
	 */
	public final static String NEWS_LIST = URL_API_HOST + "test";
	/**
	 * 获取消息详细信息接口
	 */
	public final static String NEWS_DETAIL = URL_API_HOST + "detail";
	/**
	 * 获取服务器版本信息
	 */
	public final static String UPDATE_VERSION = URL_API_HOST + "MobileAppVersion.xml";

	private final static String URL_HOST = "x.com";
	private final static String URL_WWW_HOST = "www." + URL_HOST;

	private final static String URL_TYPE_NEWS = URL_WWW_HOST + URL_SPILTER + URL_SPILTER;

	public final static int URL_OBJ_TYPE_NEWS = 0x001;

	private int objId;
	private String objKey = "";
	private int objType;

	public int getObjId() {
		return objId;
	}

	public void setObjId(int objId) {
		this.objId = objId;
	}

	public String getObjKey() {
		return objKey;
	}

	public void setObjKey(String objKey) {
		this.objKey = objKey;
	}

	public int getObjType() {
		return objType;
	}

	public void setObjType(int objType) {
		this.objType = objType;
	}

	/**
	 * 转化URL为URLs实体
	 * 
	 * @param path
	 * @return 不能转化的链接返回null
	 * @throws UnsupportedEncodingException
	 */
	public final static URLs parseURL(String path) {
		if (StringUtils.isEmpty(path))
			return null;
		URLs urls = null;
		String objId = "";
		try {
			path = formatUrl(path);
			URL url = new URL(path);
			// 站内链接
			if (url.getHost().contains(URL_HOST)) {
				urls = new URLs();
				// www
				if (path.contains(URL_WWW_HOST)) {
					// 信息 www.x.com/news/xxx
					if (path.contains(URL_TYPE_NEWS)) {
						objId = parseObjId(path, URL_TYPE_NEWS);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return urls;
	}

	/**
	 * 解析URL获得objId
	 * 
	 * @param path
	 * @param url_type
	 * @return
	 */
	private final static String parseObjId(String path, String url_type) {
		String objId = "";
		int p = 0;
		String str = "";
		String[] tmp = null;
		p = path.indexOf(url_type) + url_type.length();
		str = path.substring(p);
		if (str.contains(URL_SPILTER)) {
			tmp = str.split(URL_SPILTER);
			objId = tmp[0];
		} else {
			objId = str;
		}
		return objId;
	}

	/**
	 * 解析URL获得objKey
	 * 
	 * @param path
	 * @param url_type
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private final static String parseObjKey(String path, String url_type) throws UnsupportedEncodingException {
		path = URLDecoder.decode(path, UTF8);
		String objKey = "";
		int p = 0;
		String str = "";
		String[] tmp = null;
		p = path.indexOf(url_type) + url_type.length();
		str = path.substring(p);
		if (str.contains("?")) {
			tmp = str.split("?");
			objKey = tmp[0];
		} else {
			objKey = str;
		}
		return objKey;
	}

	/**
	 * 对URL进行格式处理
	 * 
	 * @param path
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private final static String formatUrl(String path) throws UnsupportedEncodingException {
		if (path.startsWith("http://") || path.startsWith("https://")) {
			return path;
		}
		return HTTP + URLEncoder.encode(path, UTF8);
	}

}
