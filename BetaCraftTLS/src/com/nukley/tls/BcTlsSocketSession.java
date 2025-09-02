package com.nukley.tls;

import java.security.Principal;
import java.security.cert.Certificate;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;

class BcTlsSSLSession implements SSLSession {

    private final String cipherSuite;
    private final String protocol;

    public BcTlsSSLSession(String cipherSuite, String protocol) {
        this.cipherSuite = cipherSuite;
        this.protocol = protocol;
    }

    
    public byte[] getId() {
        return new byte[0];
    }

    
    public SSLSessionContext getSessionContext() {
        return null;
    }

    
    public long getCreationTime() {
        return System.currentTimeMillis();
    }

    
    public long getLastAccessedTime() {
        return System.currentTimeMillis();
    }

    
    public void invalidate() {
        // No-op for now
    }

    
    public boolean isValid() {
        return true;
    }

    
    public void putValue(String name, Object value) {
        // No-op
    }

    
    public Object getValue(String name) {
        return null;
    }

    
    public void removeValue(String name) {
        // No-op
    }

    
    public String[] getValueNames() {
        return new String[0];
    }

    
    public Principal getPeerPrincipal() {
        return null;
    }

    
    public Principal getLocalPrincipal() {
        return null;
    }

    
    public String getCipherSuite() {
        return cipherSuite;
    }

    
    public String getProtocol() {
        return protocol;
    }

    
    public String getPeerHost() {
        return null;
    }

    
    public int getPeerPort() {
        return 0;
    }

    
    public int getPacketBufferSize() {
        return 0;
    }

    
    public int getApplicationBufferSize() {
        return 0;
    }


	public Certificate[] getLocalCertificates() {
		// TODO Auto-generated method stub
		return null;
	}


	public javax.security.cert.X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
		// TODO Auto-generated method stub
		return null;
	}


	public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
		// TODO Auto-generated method stub
		return null;
	}
}