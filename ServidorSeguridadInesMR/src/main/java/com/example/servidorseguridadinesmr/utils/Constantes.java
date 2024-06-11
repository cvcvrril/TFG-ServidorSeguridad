package com.example.servidorseguridadinesmr.utils;

public class Constantes {

    public static final String[] WHITE_LIST_URL = {
            "/auth/login",
            "/auth/registro",
            "/activation",
            "/auth/forgotPassword",
            "/forgotPassword",
            "/user/byId",
            "/auth/refreshToken",
            "/auth/darBaja",
            "/darAlta"
    };

    public static final String KEYSTORE_JKS = "keystore.jks";
    public static final String RSA = "RSA";
    public static final String CN_SERVER = "CN=Server";
    public static final String PKCS_12 = "PKCS12";
    public static final String SHA_256_WITH_RSA_ENCRYPTION = "SHA256WithRSAEncryption";
    public static final String SERVER = "server";
    public static final String PERSISTENCE_UNIT_NAME = "unit3.hibernate";
    public static final String TABLE_ROLES = "roles";
    public static final String ID = "id";
    public static final String ROL = "rol";
    public static final String TABLE_USERS = "users";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String AUTH_CODE = "authCode";
    public static final String NAMED_QUERY_GET_CREDENTIALS_BY_USERNAME = "GET_CREDENTIALS_BY_USERNAME";
    public static final String NAMED_QUERY_GET_CREDENTIALS_BY_EMAIL = "GET_CREDENTIALS_BY_EMAIL";
    public static final String NAMED_QUERY_GET_CREDENTIALS_BY_AUTH_CODE = "GET_CREDENTIALS_BY_AUTH_CODE";
    public static final String NAMED_QUERY_GET_ALL_USERS = "GET_ALL_USERS";
    public static final String NAMED_QUERY_GET_ALL_USERS_BY_ID = "GET_ALL_USERS_BY_ID";
    public static final String TABLE_CREDENTIALS = "credentials";
    public static final String AUTENTICADO = "autenticado";
    public static final String BAJA = "baja";
    public static final String PASS = "pass";
    public static final String AUTH_COD = "auth_code";
    public static final String GET_CREDENTIALS_BY_USERNAME = "GET_CREDENTIALS_BY_USERNAME";
    public static final String GET_CREDENTIALS_BY_EMAIL = "GET_CREDENTIALS_BY_EMAIL";
    public static final String GET_CREDENTIALS_BY_AUTH_CODE = "GET_CREDENTIALS_BY_AUTH_CODE";
    public static final String ROL_NAME = "rol_name";
    public static final String AUTH_PATH = "/auth";
    public static final String LOGIN_PATH = "/login";
    public static final String REGISTRO_PATH = "/registro";
    public static final String FORGOT_PASSWORD_PATH = "/forgotPassword";
    public static final String REFRESH_TOKEN_PATH = "/refreshToken";
    public static final String DAR_BAJA_PATH = "/darBaja";
    public static final String NO_SE_HA_ENCONTRADO_NINGUNA_CUENTA_CON_ESTE_EMAIL = "No se ha encontrado ninguna cuenta con este email.";
    public static final String EL_REFRESH_TOKEN_ES_NULO = "El refreshToken es nulo.";
    public static final String TOKEN = "token";
    public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    public static final String SMTP = "smtp";
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String TRUE = "true";
    public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static final String MAIL_DEBUG = "mail.debug";
    public static final String DEBE_DE_ACTIVAR_LA_CUENTA = "Debe de activar la cuenta.";
    public static final String NO_HAY_CREDENCIALES_CON_ESE_USERNAME = "No hay credenciales con ese username.";
    public static final String NO_SE_PUDO_DAR_DE_BAJA_AL_USUARIO = "No se pudo dar de baja al usuario.";
    public static final String LA_CUENTA_ESTA_DADA_DE_BAJA = "La cuenta está dada de baja.";
    public static final String NO_SE_ENCONTRO_LA_ENTRADA_DE_LA_CLAVE_PRIVADA_EN_LA_KEYSTORE = "No se encontró la entrada de la clave privada en la keystore";
    public static final String NO_EXISTE_LA_CUENTA_CON_ESE_USERNAME = "No existe la cuenta con ese username";
    public static final String ERROR_AL_CARGAR_LA_CLAVE_PRIVADA_DE_LA_KEYSTORE = "Error al cargar la clave privada de la keystore";
    public static final String NO_SE_ENCONTRO_LA_ENTRADA_DE_LA_CLAVE_PUBLICA_EN_LA_KEYSTORE = "No se encontró la entrada de la clave pública en la keystore";
    public static final String ERROR_AL_CARGAR_LA_CLAVE_PUBLICA_DE_LA_KEYSTORE = "Error al cargar la clave pública de la keystore";
    public static final String ACTIVACION_DE_LA_CUENTA = "Activación de la cuenta";
    public static final String RECUPERACION_DE_LA_CUENTA = "Recuperación de la cuenta";
    public static final String BAJA_DE_LA_CUENTA = "Baja de la cuenta";
    public static final String HAY_ALGUNO_DE_LOS_CAMPOS_VACIOS = "Hay alguno de los campos vacíos";
    public static final String HUBO_UN_ERROR_EN_LA_VALIDACION_DE_LOS_DATOS = "Hubo un error en la validación de los datos";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static final String EL_EMAIL_INTRODUCIDO_NO_ES_VALIDO = "El email introducido no es válido";
    public static final String USER_PATH = "/user";
    public static final String BY_ID = "/byId";
    public static final String ACTIVATION_PATH = "/activation";
    public static final String LA_CUENTA_YA_HA_SIDO_ACTIVADA_HAGA_LOGIN = "La cuenta ya ha sido activada. Haga login.";
    public static final String LA_CREDENCIAL_NO_EXISTE = "La credencial no existe.";
    public static final String DAR_ALTA_PATH = "/darAlta";
    public static final String LA_CUENTA_YA_HA_SIDO_DADA_DE_ALTA_HAGA_LOGIN = "La cuenta ya ha sido dada de alta. Haga login.";
    public static final String NO_SE_HA_ENCONTRADO_LA_CREDENCIAL = "No se ha encontrado la credencial.";
    public static final String HUBO_UN_ERROR = "Hubo un error";
    public static final String EL_TOKEN_HA_EXPIRADO = "El token ha expirado";
    public static final String NOMBRE_COMPLETO = "nombre_completo";
    public static final String FECHA_NACIMIENTO = "fecha_nacimiento";
    public static final String CREDENTIAL = "credential";
    public static final String BASE_URL = "http://192.168.1.138";
    public static final String FORGOT_PASSWORD_LIS_PATH = "/forgotPasswordLis";
    public static final String LA_CONTRASENA_DE_ESTA_CUENTA_YA_HA_SIDO_CAMBIADA_HAGA_LOGIN = "La contraseña de esta cuenta ya ha sido cambiada. Haga login.";


    public Constantes() {
        //Comentario para que SonarLint no lo detecte
    }
}
