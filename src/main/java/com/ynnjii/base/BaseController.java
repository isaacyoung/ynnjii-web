package com.ynnjii.base;


import com.github.pagehelper.Page;
import com.ynnjii.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * project：ynnjii-web
 * class：
 * author：isaac
 * date：2016/9/20
 * description：
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 得到用户名
     * @return
     */
    protected String getUserName(){
        return (String) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 得到分页信息
     * @param request
     * @return
     */
    protected Page getPageByRequest(HttpServletRequest request){
        String pageNum= request.getParameter("pageNum");
        String pageSize= request.getParameter("pageSize");

        if (!StringUtils.isNullOrEmpty(pageNum)&&!StringUtils.isNullOrEmpty(pageSize)){
            Integer pNum=Integer.parseInt(pageNum);
            Integer pSize=Integer.parseInt(pageSize);
            return new Page(pNum,pSize);
        } else if (!StringUtils.isNullOrEmpty(pageNum)) {
            Integer pNum=Integer.parseInt(pageNum);
            return new Page(pNum,10);
        }

        return new Page(1,10);
    }
}
