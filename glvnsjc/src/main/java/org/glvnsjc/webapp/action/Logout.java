package org.glvnsjc.webapp.action;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.myfaces.orchestra.conversation.ConversationManager;
import org.springframework.security.ui.rememberme.TokenBasedRememberMeServices;
import org.springframework.stereotype.Component;

@Component
public class Logout
{
    public String logout()
        throws IOException
    {
        ConversationManager.getInstance().clearCurrentConversationContext();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        
        Cookie terminate = new Cookie( TokenBasedRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, null );
        terminate.setMaxAge( 0 );
        getResponse().addCookie( terminate );
        
        return "goto-login";
    }

    protected HttpServletRequest getRequest()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }

    protected HttpServletResponse getResponse()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (HttpServletResponse) facesContext.getExternalContext().getResponse();
    }

}
