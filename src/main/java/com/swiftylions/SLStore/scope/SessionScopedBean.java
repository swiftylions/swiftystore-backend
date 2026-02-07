package com.swiftylions.SLStore.scope;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter @Setter
@Slf4j
public class SessionScopedBean {
    public String userName;

    public SessionScopedBean() {
        log.info("SessionScopedBean Initialized ✅");
    }
}
