package com.nukley.tls;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpHost;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.protocol.HttpContext;
import org.bouncycastle.tls.TlsClient;
import org.bouncycastle.tls.TlsClientProtocol;
import org.bouncycastle.tls.crypto.impl.bc.BcTlsCrypto;

public class CustomTlsSocketFactory extends SSLSocketFactory implements LayeredConnectionSocketFactory {
    private final BcTlsCrypto randomCrypt;

    public CustomTlsSocketFactory(BcTlsCrypto randomCrypt) {
        this.randomCrypt = randomCrypt;
    }

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return createCustomTlsSocket(host, port);
    }

    @Override
    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
        if (autoClose) {
            socket.close();
        }
        return createCustomTlsSocket(host, port);
    }

    private Socket createCustomTlsSocket(String host, int port) throws IOException {
        Socket socket = new Socket(host, port);
        TlsClientProtocol tlsClientProtocol = new TlsClientProtocol(socket.getInputStream(), socket.getOutputStream());
        TlsClient client = new CustomTlsClient(randomCrypt);
        tlsClientProtocol.connect(client);
        return new BcTlsSSLSocket(socket, tlsClientProtocol);
    }

	public Socket connectSocket(int arg0, Socket arg1, HttpHost arg2, InetSocketAddress arg3, InetSocketAddress arg4,
			HttpContext arg5) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Socket createSocket(HttpContext arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Socket createLayeredSocket(Socket arg0, String arg1, int arg2, HttpContext arg3)
			throws IOException, UnknownHostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getDefaultCipherSuites() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getSupportedCipherSuites() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Socket createSocket(InetAddress host, int port) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
			throws IOException, UnknownHostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

    // Unused methods are removed to streamline the class
}
