package st.sergey.minsky.shop2doordelivers.security;

public class SecurityConstants {
    /*public static final String SIGN_UP_URLS = "/api/auth/*";*/
    public static final String SIGN_UP_URLS = "/*";
    public static final String SECRET_KEY_GEN_JWT = "SecretKeyGenJWT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    public static final long EXPIRATION_TIME = 600_000; //10min./
    private static final String CREATE_CATEGORY_ENDPOINT = "/category";
    private static final String CREATE_PRODUCT_ENDPOINT = "/product";
    private static final String CREATE_STORE_ENDPOINT = "/store";

}
