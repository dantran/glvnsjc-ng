package org.glvnsjc.webapp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class TestView
{
    
    private String testString = "TestString";
    
    public String getTestString()
    {
        return this.testString;
    }

}
