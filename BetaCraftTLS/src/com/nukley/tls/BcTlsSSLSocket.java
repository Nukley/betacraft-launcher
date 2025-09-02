package com.nukley.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.bouncycastle.tls.TlsClientProtocol;

class BcTlsSSLSocket extends SSLSocket {
    private final Socket socket;
    private final TlsClientProtocol tlsClientProtocol;
    private final SSLSession sslSession;

    public BcTlsSSLSocket(Socket socket, TlsClientProtocol tlsClientProtocol) {
        this.socket = socket;
        this.tlsClientProtocol = tlsClientProtocol;
        this.sslSession = new BcTlsSSLSession("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "TLSv1.2"); // Example cipher suite & protocol
    }

    
    public InputStream getInputStream() throws IOException {
        return tlsClientProtocol.getInputStream();
    }

    
    public OutputStream getOutputStream() throws IOException {
        return tlsClientProtocol.getOutputStream();
    }

    
    public void close() throws IOException {
        tlsClientProtocol.close();
        socket.close();
    }

    
    public SSLSession getSession() {
        return sslSession; // Provide the SSL session
    }

    // Implement the necessary SSLSocket abstract methods (many can be no-ops)
     public void startHandshake() throws IOException { /* Already handled by TlsClientProtocol */ }
     public void addHandshakeCompletedListener(javax.net.ssl.HandshakeCompletedListener listener) { /* No-op */ }
     public void removeHandshakeCompletedListener(javax.net.ssl.HandshakeCompletedListener listener) { /* No-op */ }
     public String[] getSupportedCipherSuites() { return new String[0]; }
     public String[] getEnabledCipherSuites() { return new String[0]; }
     public void setEnabledCipherSuites(String[] suites) { /* No-op */ }
     public String[] getSupportedProtocols() { return new String[0]; }
     public String[] getEnabledProtocols() { return new String[0]; }
     public void setEnabledProtocols(String[] protocols) { /* No-op */ }
     public void setUseClientMode(boolean mode) { /* No-op */ }
     public boolean getUseClientMode() { return true; }
     public void setNeedClientAuth(boolean need) { /* No-op */ }
     public boolean getNeedClientAuth() { return false; }
     public void setWantClientAuth(boolean want) { /* No-op */ }
     public boolean getWantClientAuth() { return false; }
     public void setEnableSessionCreation(boolean flag) { /* No-op */ }
     public boolean getEnableSessionCreation() { return true; }
}
