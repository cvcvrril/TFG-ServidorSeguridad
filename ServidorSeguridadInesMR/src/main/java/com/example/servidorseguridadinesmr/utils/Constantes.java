package com.example.servidorseguridadinesmr.utils;

public class Constantes {
    public static final String KEYSTORE_PFX = "keystore.pfx";
    public static final String RSA = "RSA";
    public static final String CN_SERVER = "CN=Server";
    public static final String PKCS_12 = "PKCS12";
    public static final String SHA_256_WITH_RSA_ENCRYPTION = "SHA256WithRSAEncryption";
    public static final String SERVER = "server";
    public static final String PERSISTENCE_UNIT_NAME = "unit3.hibernate";
    public static final String PBKDF_2_WITH_HMAC_SHA_256 = "PBKDF2WithHmacSHA256";
    public static final String AES = "AES";
    public static final String AES_GCM_NO_PADDING = "AES/GCM/noPadding";
    public static final String KEY_STORE_PASSWORD = "${KeyStorePassword}";
    public static final String[] WHITE_LIST_URL = {
            "/auth/login",
            "/auth/registro",
            "/activation",
            "/auth/forgotPassword",
            "/forgotPassword",
            "/user/byId",
            "/auth/refreshToken"
    };

    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String TABLE_ROLES = "roles";
    public static final String ID = "id";
    public static final String ROL = "rol";
    public static final String TABLE_USERS = "users";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ROLES = "roles";
    public static final String QUERY_GET_ALL_WITH_PERMISOS = "select u from UserEntity u";
    public static final String ERROR_USER_NOT_FOUND = "User not found";
    public static final String CLAIM_USER = "user";
    public static final String CLAIM_ROL = "rol";
    public static final String ALIAS_SERVER = "server";
    public static final String PASSWORD_STRING = "Prueba";
    public static final String ROL_USER = "USER";
    public static final String ERROR_USERNAME_PASSWORD_VACIOS = "Ni el usuario ni la contraseña pueden estar vacíos.";
    public static final String REQUEST_MAPPING = "/auth";
    public static final String MAPPING_LOGIN = "/login";
    public static final String MAPPING_REGISTRO = "/registro";
    public static final String REQUEST_PARAM_USERNAME = "username";
    public static final String REQUEST_PARAM_PASSWORD = "password";
    public static final String DESCRIPCION_POR_DEFECTO_PERSONAJE = "Personaje añadido por el usuario";
    public static final String USER_EMPTY = "User vacío";
    public static final String EMAIL = "email";
    public static final String AUTH_CODE = "authCode";
    public static final String NAMED_QUERY_GET_CREDENTIALS_BY_USERNAME = "GET_CREDENTIALS_BY_USERNAME";
    public static final String NAMED_QUERY_GET_CREDENTIALS_BY_EMAIL = "GET_CREDENTIALS_BY_EMAIL";
    public static final String NAMED_QUERY_GET_CREDENTIALS_BY_AUTH_CODE = "GET_CREDENTIALS_BY_AUTH_CODE";
    public static final String NAMED_QUERY_GET_ALL_USERS = "GET_ALL_USERS";


    public Constantes() {
    }
}
