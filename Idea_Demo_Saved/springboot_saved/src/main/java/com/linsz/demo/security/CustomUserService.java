package com.linsz.demo.security;

import com.linsz.demo.error.BusinessException;
import com.linsz.demo.model.AyUser;
import com.linsz.demo.model.AyUserRoleRel;
import com.linsz.demo.service.AyRoleService;
import com.linsz.demo.service.AyUserRoleRelService;
import com.linsz.demo.service.AyUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {

    @Resource
    private AyUserService ayUserService;

    @Resource
    private AyUserRoleRelService ayUserRoleRelService;

    @Resource
    private AyRoleService ayRoleService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        AyUser ayUser = ayUserService.findByUserName(name);
        if(ayUser == null){
            throw new BusinessException("用户不存在");
        }
        //获取用户所有的关联角色
        List<AyUserRoleRel> ayRoleList = ayUserRoleRelService.findByUserId(ayUser.getId());
        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
        if(ayRoleList != null && ayRoleList.size() > 0){
            for(AyUserRoleRel rel:ayRoleList){
                //获取用户关联角色名称
                String roleName = ayRoleService.find(rel.getRoleId()).getName();
                authorityList.add(new SimpleGrantedAuthority(roleName));
            }
        }
        return new User(ayUser.getName(), ayUser.getPassword(), authorityList);
    }
}

