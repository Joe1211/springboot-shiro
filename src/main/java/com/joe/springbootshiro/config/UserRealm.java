package com.joe.springbootshiro.config;

import com.joe.springbootshiro.dao.User;
import com.joe.springbootshiro.mapper.UserMapper;
import com.joe.springbootshiro.service.UserService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加资源授权字符串
        //info.addStringPermission("user:add");

        //到数据库查询当前登录用户的授权字符串
        //获取当前登录用户的id
//        Subject subject = SecurityUtils.getSubject();
//        User user = (User)subject.getPrincipal();

        //获取数据库用户
        String username = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("username---------------------->"+username);
        User user = userService.getUser(username);
        User dbUser = userService.getUserId(user.getId());

        info.addStringPermission(dbUser.getPerms());
        userService.getUserId(user.getId());
        return info;
    }

    @Autowired
    UserService userService;

    /**
     *提供账户信息，返回认证信息
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)arg0;

        //获取数据库用户
        User user = userService.getUser(token.getUsername());
        System.out.println("user------>"+user);

        if (user == null){
            //用户名不存在
            return null;//shiro底层会抛出UnknowAccountException
        }
        //判断密码
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getId()+user.getName());

        SimpleAuthenticationInfo info = null;
       info = new SimpleAuthenticationInfo(user.getName(),user.getPassword(),credentialsSalt,getName());
       // info = new SimpleAuthenticationInfo(user.getName(),user.getPassword(),"");
        return info;
    }
}
