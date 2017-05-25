package com.ynnjii.sys.security;

import com.ynnjii.sys.domain.SysUser;
import com.ynnjii.sys.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yzh
 * Created on 2016/9/28.
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(sysUserService.findRoles(username));
        authorizationInfo.setStringPermissions(sysUserService.findPermissions(username));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        SysUser userParams = new SysUser();
        userParams.setLoginName(username);
        SysUser sysUser = sysUserService.selectOne(userParams);
        if (sysUser == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if (sysUser.getLoginFlag()==1){
            throw new DisabledAccountException();
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                sysUser.getLoginName(), //用户名
                sysUser.getPassword(), //密码
                ByteSource.Util.bytes(sysUser.getSalt()),
                getName()  //realm name
        );
        return authenticationInfo;
    }

}
