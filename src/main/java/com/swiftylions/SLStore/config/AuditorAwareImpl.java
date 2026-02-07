package com.swiftylions.SLStore.config;

import com.swiftylions.SLStore.entity.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.of("Anonymous User");
        }

        Object principal = authentication.getPrincipal();
        String username;

        if (principal instanceof Customer customer) {
            username = customer.getEmail();
        } else {
            username = principal.toString(); //fallback
        }
        return Optional.of(username);

    }
}
