package com.swiftylions.SLStore.controller;

import com.swiftylions.SLStore.scope.ApplicationScopedBean;
import com.swiftylions.SLStore.scope.RequestScopedBean;
import com.swiftylions.SLStore.scope.SessionScopedBean;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/scope")
@RequiredArgsConstructor
public class ScopeController {
    private final RequestScopedBean requestScopedBean;
    private final SessionScopedBean sessionScopedBean;
    private final ApplicationScopedBean applicationScopedBean;

    @GetMapping("/request")
    public ResponseEntity<String> testRequestScopedBean() {
        requestScopedBean.setUserName("John Snow");
        return ResponseEntity.ok().body(requestScopedBean.getUserName());
    };

    @GetMapping("/session")
    public ResponseEntity<String> testSessionScopedBean() {
        sessionScopedBean.setUserName("John Snow");
        return ResponseEntity.ok().body(sessionScopedBean.getUserName());
    };

    @GetMapping("/application")
    public ResponseEntity<Integer> testApplicationScopedBean() {
        applicationScopedBean.incrementVisitorCount();
        return ResponseEntity.ok().body(applicationScopedBean.getVisitorCount());
    };

    @GetMapping("/test")
    public ResponseEntity<Integer> testScope() {
        return ResponseEntity.ok().body(applicationScopedBean.getVisitorCount());
    };

}
