package com.swiftylions.SLStore.constants;

public class  ApplicationConstants {
    private ApplicationConstants() {
        throw new AssertionError("Utility class cannot be instantiated.");
    }
    public static final String JWT_SECRET_KEY = "JWT_SECRET";
    public static final String JWT_SECRET_DEFAULT_VALUE = "$2a$12$vgnGgTd0gTYp0dV0WjBN8eEZFLS8BVbC2lehsuwZ7RpeumCnAzyqG";
    public static final String JWT_HEADER = "Authorization";

    public static final String ORDER_STATUS_CREATED = "CREATED";
    public static final String ORDER_STATUS_CONFIRMED = "CONFIRMED";
    public static final String ORDER_STATUS_CANCELLED = "CANCELLED";

    public static final String OPEN_MESSAGE = "OPEN";
    public static final String CLOSED_MESSAGE = "CLOSED";

}

