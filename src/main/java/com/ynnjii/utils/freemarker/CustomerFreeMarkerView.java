package com.ynnjii.utils.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 项目名称：车到山前后台
 * 类描述：
 * 创建人：yzh
 * 创建时间：2017/6/19
 * 备注：
 */
public class CustomerFreeMarkerView extends FreeMarkerView {
    private static final String CONTEXT_PATH = "baseRoot";
    @Override
    protected void exposeHelpers(Map<String, Object> model,
                                 HttpServletRequest request) throws Exception {
        model.put(CONTEXT_PATH, request.getContextPath());
        super.exposeHelpers(model, request);
    }

}
