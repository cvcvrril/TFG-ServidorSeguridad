package com.example.servidorseguridadinesmr.seguridad;

import com.example.servidorseguridadinesmr.utils.Constantes;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Log4j2
public class MainKeyStore {

    @Value("${application.security.keystore.password}")
    private static final String keyStorePassword = "password";


    public static void main(String[] args) {

        try {
            Security.addProvider(new BouncyCastleProvider());
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(Constantes.RSA);
            keyGen.initialize(2048,new SecureRandom());
            KeyPair clavesRSA = keyGen.generateKeyPair();
            PrivateKey clavePrivada = clavesRSA.getPrivate();
            PublicKey clavePublica = clavesRSA.getPublic();

            X509V3CertificateGenerator cert1 = new X509V3CertificateGenerator();

            cert1.setSerialNumber(BigInteger.valueOf(1));
            cert1.setSubjectDN(new X509Principal(Constantes.CN_SERVER));
            cert1.setIssuerDN(new X509Principal(Constantes.CN_SERVER));
            cert1.setPublicKey(clavePublica);
            cert1.setNotBefore(Date.from(LocalDate.now().plus(365, ChronoUnit.DAYS).atStartOfDay().toInstant(ZoneOffset.UTC)));
            cert1.setNotAfter(new Date());
            cert1.setSignatureAlgorithm(Constantes.SHA_256_WITH_RSA_ENCRYPTION);

            X509Certificate cert =  cert1.generate(clavePrivada);

            KeyStore ks = KeyStore.getInstance(Constantes.PKCS_12);
            char[] password = keyStorePassword.toCharArray();
            ks.load(null, null);
            ks.setCertificateEntry(Constantes.SERVER, cert);
            ks.setKeyEntry(Constantes.SERVER, clavePrivada, password, new Certificate[]{cert});
            FileOutputStream fos = new FileOutputStream(Constantes.KEYSTORE_JKS);
            ks.store(fos, password);
            fos.close();


        }catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }
}
