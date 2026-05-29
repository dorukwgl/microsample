package com.doruk.infrastructure.security;

import com.doruk.infrastructure.config.AppConfig;
import jakarta.inject.Singleton;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Singleton
public class SignatureHelper {
    private final AppConfig config;
    private final PrivateKey privateKey;

    public SignatureHelper(AppConfig config) {
        try {
            this.config = config;
            byte[] keyBytes = Base64.getDecoder().decode(config.currentServerPrivateKey());
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory factory = KeyFactory.getInstance("Ed25519");
            this.privateKey = factory.generatePrivate(spec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String sign(String payload, long timestamp) throws Exception {
        // payload signing must include timestamp to bind them together
        String signingInput = timestamp + "." + payload;

        Signature signer = Signature.getInstance("Ed25519");
        signer.initSign(this.privateKey);
        signer.update(signingInput.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(signer.sign());
    }

    public boolean verify(String base64PublicKey, String signingInput, String base64Signature)
            throws Exception {

        byte[] keyBytes = Base64.getDecoder().decode(base64PublicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance("Ed25519");
        PublicKey publicKey = factory.generatePublic(spec);

        Signature verifier = Signature.getInstance("Ed25519");
        verifier.initVerify(publicKey);
        verifier.update(signingInput.getBytes(StandardCharsets.UTF_8));

        return verifier.verify(Base64.getDecoder().decode(base64Signature));
    }
}
