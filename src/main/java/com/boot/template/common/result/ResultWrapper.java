package com.boot.template.common.result;

import com.boot.template.common.constant.error.ErrorSystemCode;
import com.boot.template.common.result.impl.DefaultResult;
import com.boot.template.common.utils.JsonHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回数据工具
 */
public class ResultWrapper {

    /**
     * 包装带分页列表的数据成为Map结构
     * @param list
     * @param status
     * @param message
     * @param location
     * @param code
     * @return
     */
    public static IResultable wrapListPage(List<?> list, String status, String message, String location, String code) {
        DefaultResult webResult = new DefaultResult();

        Map<String, Object> dataMap = new HashMap<String, Object>();
        Map<String, Object> pageConfig = new HashMap<String, Object>();

        if (list == null) {
            pageConfig.put("page", 0);
            pageConfig.put("pageSize", 0);
            pageConfig.put("totalPage", 0);
            pageConfig.put("totalCount", 0);
            dataMap.put("dataList", "");
        } else {
            @SuppressWarnings("unchecked")
            PageInfo<Object> pi = new PageInfo<Object>((List<Object>) list);
            pageConfig.put("page", pi.getPageNum());
            pageConfig.put("pageSize", pi.getPageSize());
            pageConfig.put("totalPage", pi.getPages());
            pageConfig.put("totalCount", pi.getTotal());
            dataMap.put("dataList", list);
        }
        dataMap.put("pageConfig", pageConfig);

        webResult.setData(dataMap);
        webResult.setStatus(status);
        webResult.setMessage(message);
        webResult.setCode(code);
        webResult.setLocation(location);

        return webResult;
    }

    /**
     * 包装带分页列表的数据成为Map结构
     *      code默认1000(成功)，location=""
     * @param list
     * @param status
     * @param message
     * @return
     */
    public static IResultable wrapListPage(List<?> list, String status, String message) {

        return wrapListPage(list, status, message, "", ErrorSystemCode.SYSTEM_SUCC.getCode());
    }

    /**
     * 封装没有分页的list
     *
     * @param list
     * @param status
     * @param message
     * @return Map<String, Object>
     */
    public static IResultable wrapListNoPage(List<?> list, String status, String message, String location, String code) {
        DefaultResult webResult = new DefaultResult();

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("dataList", list);

        webResult.setData(dataMap);
        webResult.setStatus(status);
        webResult.setMessage(message);
        webResult.setCode(code);
        webResult.setLocation(location);

        return webResult;
    }

    /**
     * 封装没有分页的list
     *      code默认1000(成功)，location=""
     * @param list
     * @param status
     * @param message
     * @return
     */
    public static IResultable wrapListNoPage(List<?> list, String status, String message) {

        return wrapListNoPage(list, status, message, "", ErrorSystemCode.SYSTEM_SUCC.getCode());
    }


    /**
     * 包装普通Map响应：code可自定义
     * @param data 参数可null
     * @param status 查询状态码
     * @param message 信息
     * @param code 业务码
     * @return
     */
    public static IResultable wrapObject(Object data, String status, String message, String code) {
        DefaultResult webResult = new DefaultResult();
        if (data == null) {
            webResult.setData("");
        } else {
            webResult.setData(data);
        }
        webResult.setStatus(status);
        webResult.setMessage(message);
        webResult.setCode(code);
        webResult.setLocation("");
        return webResult;
    }

    /**
     * 包装普通Map响应，code默认=1000（操作成功）
     * @param data
     * @param status
     * @param message
     * @return Map<String, Object>
     */
    public static IResultable wrapObject(Object data, String status, String message) {
        return wrapObject(data,status,message,"",ErrorSystemCode.SYSTEM_SUCC.getCode());
    }

    /**
     * 包装普通Map响应，data参数可null
     *
     * @param data
     * @param status
     * @param message
     * @param location
     * @return Map<String, Object>
     */
    public static IResultable wrapObject(Object data, String status, String message, String location, String code) {
        DefaultResult webResult = new DefaultResult();
        if (data == null) {
            webResult.setData("");
        } else {
            webResult.setData(data);
        }
        webResult.setStatus(status);
        webResult.setMessage(message);
        webResult.setLocation(location);
        webResult.setCode(code);
        return webResult;
    }

    /**
     * 包装普通Map响应并转化为JSON，全自定义
     *
     * @param data
     * @param status
     * @param message
     * @param location
     * @return String
     */
    public static String wrapMap2json(Map<?, ?> data, String status, String message, String location, String code) {
        DefaultResult webResult = new DefaultResult();
        if (data == null || data.isEmpty()) {
            webResult.setData("");
        } else {
            webResult.setData(data);
        }
        webResult.setStatus(status);
        webResult.setMessage(message);
        webResult.setLocation(location);
        webResult.setCode(code);

        String json = "{\"status\": \"0\", \"data\": \"\", \"message\": \"系统错误，请联系管理员！\", \"location\": \"\"}";
        try {
            json = JsonHelper.toJson(webResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 包装普通Map响应并转化为JSON，code默认1000(成功)
     * @param data
     * @param status
     * @param message
     * @param location
     * @return
     */
    public static String wrapMap2json(Map<?, ?> data, String status, String message, String location) {

        return wrapMap2json(data, status, message, location, ErrorSystemCode.SYSTEM_SUCC.getCode());
    }

    /**
     * 包装普通Map响应并转化为JSON，code默认1000(成功)，location=""
     * @param data
     * @param status
     * @param message
     * @return
     */
    public static String wrapMap2json(Map<?, ?> data, String status, String message) {

        return wrapMap2json(data, status, message, "", ErrorSystemCode.SYSTEM_SUCC.getCode());
    }

    /**
	 * 封装含表头配置的分页信息
	 * @param list
	 * @param headList
	 * @param status
	 * @param message
	 * @return
	 */
	@SuppressWarnings("all")
	public static IResultable wrapListPageWithHead(Map<String, Object> dataMap, String status, String message, Integer page, Integer pageSize) {
		DefaultResult webResult = (DefaultResult) wrapListPageNew((List) dataMap.get("dataList"), status, message, page, pageSize);
		((Map) webResult.getData()).put("headList", dataMap.get("headList") == null ? new ArrayList<>() : dataMap.get("headList"));
		return webResult;
	}
	/**
	 * 封装含表头配置的不含分页的信息
	 * @param list
	 * @param headList
	 * @param status
	 * @param message
	 * @return
	 */
	@SuppressWarnings("all")
	public static IResultable wrapListNoPageWithHead(Map<String, Object> dataMap, String status, String message) {
		DefaultResult webResult = (DefaultResult) wrapListNoPage((List) dataMap.get("dataList"), status, message);
		((Map) webResult.getData()).put("headList", dataMap.get("headList") == null ? new ArrayList<>() : dataMap.get("headList"));
		return webResult;
	}
	
	/**
     * 包装带分页列表的数据成为Map结构
     *
     * @param list
     * @param status
     * @param message
     * @return Map<String, Object>
     */
    public static IResultable wrapListPageNew(List<?> list, String status, String message, Integer page, Integer pageSize) {
        DefaultResult webResult = new DefaultResult();

        Map<String, Object> dataMap = new HashMap<String, Object>();
        Map<String, Object> pageConfig = new HashMap<String, Object>();

        if (CollectionUtils.isEmpty(list)) {
            pageConfig.put("page", page);
            pageConfig.put("pageSize", pageSize);
            pageConfig.put("totalPage", 0);
            pageConfig.put("totalCount", 0);
            dataMap.put("dataList", new ArrayList<>());
        } else {
            @SuppressWarnings("unchecked")
            PageInfo<Object> pi = new PageInfo<Object>((List<Object>) list);
            pageConfig.put("page", pi.getPageNum());
            pageConfig.put("pageSize", pi.getPageSize());
            pageConfig.put("totalPage", pi.getPages());
            pageConfig.put("totalCount", pi.getTotal());
            dataMap.put("dataList", list);
        }
        dataMap.put("pageConfig", pageConfig);

        webResult.setData(dataMap);
        webResult.setStatus(status);
        webResult.setMessage(message);
        webResult.setLocation("");
        webResult.setCode(ErrorSystemCode.SYSTEM_SUCC.getCode());

        return webResult;
    }

}
