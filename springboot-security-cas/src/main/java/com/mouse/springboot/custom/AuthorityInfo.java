package com.mouse.springboot.custom;

import org.springframework.security.core.GrantedAuthority;

/**
 * 权限信息 
 * 
 * @author mouse
 * 
 */
public class AuthorityInfo implements GrantedAuthority
{
    private static final long serialVersionUID = -175781100474818800L;

    /**
     * 权限CODE 
     */
    private String authority;

    public AuthorityInfo(String authority)
    {
        this.authority = authority;
    }

    @Override
    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }

}