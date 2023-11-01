package cn.topviewclub.hth.common.core.web.controller;

import cn.topviewclub.hth.common.core.constant.HttpStatusConstants;
//import cn.topviewclub.hth.common.core.utils.PageUtils;
import cn.topviewclub.hth.common.core.web.domain.AjaxResult;
import cn.topviewclub.hth.common.core.web.page.TableDataInfo;
//import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date类型转换
        binder.registerCustomEditor(Date.class,new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                super.setAsText(text);
            }
        });
    }

    /**
     * 设置请求分页数据
     */
//    protected void startPage()
//    {
//        PageUtils.startPage();
//    }
//
//    /**
//     * 清理分页的线程变量
//     */
//    protected void clearPage()
//    {
//        PageUtils.clearPage();
//    }

    /**
     * 响应请求分页数据
     */
//    @SuppressWarnings({ "rawtypes", "unchecked" })
//    protected TableDataInfo getDataTable(List<?> list)
//    {
//        TableDataInfo rspData = new TableDataInfo();
//        rspData.setCode(HttpStatusConstants.SUCCESS);
//        rspData.setRows(list);
//        rspData.setMsg("查询成功");
//        rspData.setTotal(new PageInfo(list).getTotal());
//        return rspData;
//    }

    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(Object data)
    {
        return AjaxResult.success(data);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    /**
     * 返回警告消息
     */
    public AjaxResult warn(String message)
    {
        return AjaxResult.warn(message);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }


}
