package com.chaos.model;

import org.springframework.context.ApplicationEvent;
import sun.applet.AppletEvent;

/**
 * Created by marwen on 20/12/15.
 */
public class EmailEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;
    private String subject;
    private String content;

    public EmailEvent(Object objectSource, String subject, String content) {

        super(objectSource);
        this.subject = subject;
        this.content = content;

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
