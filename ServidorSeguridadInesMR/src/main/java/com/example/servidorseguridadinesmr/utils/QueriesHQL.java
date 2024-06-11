package com.example.servidorseguridadinesmr.utils;

public class QueriesHQL {
    public static final String GET_CREDENTIALS_BY_USERNAME = "from CredentialEntity where username = :username";
    public static final String GET_CREDENTIALS_BY_EMAIL = "from CredentialEntity where email = :email";
    public static final String GET_CREDENTIALS_BY_AUTH_CODE = "from CredentialEntity where authCode = :authCode";
    public static final String FROM_USER_ENTITY = "from UserEntity";
    public static final String FROM_USER_ENTITY_WHERE_ID_ID = "from UserEntity where id = :id";
}
