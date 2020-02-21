package com.dandh.test;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class DandHSSLContext {

	private static DandHSSLContext DandHSSLContextSingleton = null;

	private File truststore = null;
	private String truststorePassword = null;
	private String truststoreType = null;

	private DandHSSLContext() {
		this.truststore = new File("C:/Program Files/Java/jdk1.8.0_171/jre/lib/security/cacerts");
		this.truststorePassword = "changeit";
		this.truststoreType = "JKS";
	}

	/*sets defaults truststore*/
	public SSLContext getSSLContext()
			throws SSLLoadException {
		return getSSLContext(this.truststore, this.truststorePassword, this.truststoreType);
	}
	
	public SSLContext getSSLContext(File truststore, String truststorePassword, String truststoreType)
			throws SSLLoadException {
		SSLContext sslContext = null;
		try {

			sslContext = SSLContext.getInstance("TLS");
			TrustManagerFactory tmf;
			tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			KeyStore ts = null;
			if (truststore != null && truststoreType != null) {
				ts = KeyStore.getInstance(truststoreType);
				ts.load(new FileInputStream(truststore), truststorePassword.toCharArray());
				tmf.init(ts);
			}

			tmf.init(ts);
			sslContext.init(null, tmf.getTrustManagers(), null);
		} catch (Exception e) {
			throw new SSLLoadException(e.getMessage());

		}

		return sslContext;
	}

	public static DandHSSLContext getInstance(File truststore, String truststorePassword, String truststoreType)
			throws SSLLoadException {

		if (DandHSSLContextSingleton == null) {

			DandHSSLContextSingleton = new DandHSSLContext();
		}
		return DandHSSLContextSingleton;

	}

	public static DandHSSLContext getInstance() throws SSLLoadException {

		if (DandHSSLContextSingleton == null) {

			DandHSSLContextSingleton = new DandHSSLContext();
		}
		return DandHSSLContextSingleton;

	}

}
