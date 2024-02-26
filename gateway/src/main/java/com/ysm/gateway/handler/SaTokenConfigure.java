package com.ysm.gateway.handler;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * [Sa-Token 权限认证] 配置类 
 * @author click33
 */
//@Configuration
//public class SaTokenConfigure {
//    // 注册 Sa-Token全局过滤器
//    @Bean
//    public SaReactorFilter getSaReactorFilter() {
//        return new SaReactorFilter()
//            // 拦截地址
//            .addInclude("/test")    /* 拦截全部path */
//            // 开放地址
//            .addExclude("/auth/login")
//            // 鉴权方法：每次访问进入
//            .setAuth(obj -> {
//                // 登录校验 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
//                SaRouter.match("/**", "/auth/login", r -> StpUtil.checkLogin());
//
//                // 权限认证 -- 不同模块, 校验不同权限
//                SaRouter.match("/user/**", r -> StpUtil.checkPermission("test"));
//                SaRouter.match("/interaction/**", r -> StpUtil.checkPermission("test"));
//                SaRouter.match("/goods/**", r -> StpUtil.checkPermission("test"));
//                SaRouter.match("/order/**", r -> StpUtil.checkPermission("test"));
//
//                // 更多匹配 ...  */
//            })
//            // 异常处理方法：每次setAuth函数出现异常时进入
//            .setError(e -> {
//                return SaResult.error(e.getMessage());
//            })
//            ;
//    }
//}
