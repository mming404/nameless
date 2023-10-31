package cn.topviewclub.hth.common.core.utils;

import cn.topviewclub.hth.common.core.utils.sql.SqlUtils;
import cn.topviewclub.hth.common.core.web.page.PageDomain;
import cn.topviewclub.hth.common.core.web.page.TableSupport;
import com.github.pagehelper.PageHelper;

/**
 * 分页工具类
 *
 * @author phix
 */
public class PageUtils extends PageHelper
{
    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtils.escapeOrderBySql(pageDomain.getOrderBy());
        Boolean reasonable = pageDomain.getReasonable();
        PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage()
    {
        PageHelper.clearPage();
    }
}