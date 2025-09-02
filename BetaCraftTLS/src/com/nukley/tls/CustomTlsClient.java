package com.nukley.tls;

import java.io.IOException;

import org.bouncycastle.tls.CertificateRequest;
import org.bouncycastle.tls.CipherSuite;
import org.bouncycastle.tls.DefaultTlsClient;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.TlsAuthentication;
import org.bouncycastle.tls.TlsCredentials;
import org.bouncycastle.tls.TlsServerCertificate;
import org.bouncycastle.tls.crypto.impl.bc.BcTlsCrypto;

class CustomTlsClient extends DefaultTlsClient {
public CustomTlsClient(BcTlsCrypto crypto) {
   super(crypto);
}

public int[] getCipherSuites() {
   return new int[] {
       CipherSuite.TLS_AES_128_GCM_SHA256,
       CipherSuite.TLS_CHACHA20_POLY1305_SHA256,
       CipherSuite.TLS_AES_256_GCM_SHA384,
       CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
       CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
       CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256,
       CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256,
       CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384,
       CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
       CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
       CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
       CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
       CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
       CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256,
       CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384,
       CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA,
       CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA
   };
}

public ProtocolVersion getMinimumVersion() {
   return ProtocolVersion.TLSv10; // Allow TLS 1.1 or higher
}

public ProtocolVersion getMaxVersion() {
   return ProtocolVersion.TLSv13; // Allow TLS 1.3 if supported
}

public TlsAuthentication getAuthentication() throws IOException {
   return new TlsAuthentication() {
       public void notifyServerCertificate(TlsServerCertificate serverCertificate) throws IOException {
           System.out.println("Received server certificate.");
           // Optionally validate the certificate here
       }

       public void notifyServerCertificate(org.bouncycastle.tls.Certificate serverCertificate) throws IOException {
           // Optional implementation
       }

       public TlsCredentials getClientCredentials(CertificateRequest certificateRequest) throws IOException {
           return null; // No client credentials used in this example
       }
   };
}
}
