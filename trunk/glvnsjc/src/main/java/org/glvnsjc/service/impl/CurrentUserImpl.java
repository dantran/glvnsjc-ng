package org.glvnsjc.service.impl;

import org.glvnsjc.model.domain.User;
import org.glvnsjc.service.CurrentUser;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component( "currentUser")
public class CurrentUserImpl
    implements CurrentUser
{
    public User getUserDetails()
    {
        //goto to thread local storage to get principal

        Authentication au = SecurityContextHolder.getContext().getAuthentication();

        User user = null;

        if ( au != null )
        {
            user = (User) au.getPrincipal();
        }

        return user;
    }


}
