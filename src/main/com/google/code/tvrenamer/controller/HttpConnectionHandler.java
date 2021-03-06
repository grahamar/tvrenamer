package com.google.code.tvrenamer.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import com.google.code.tvrenamer.controller.util.StringUtils;
import com.google.code.tvrenamer.model.TVRenamerIOException;

public class HttpConnectionHandler {
	private static Logger logger = Logger.getLogger(HttpConnectionHandler.class.getName());
	private static final int CONNECT_TIMEOUT_MS = 2000;
	private static final int READ_TIMEOUT_MS = 5000;
	
	/**
	 * Download the URL and return as a String
	 * @param urlString the URL as a String
	 * @return String of the contents
	 */
	public String downloadUrl(String urlString) throws TVRenamerIOException {
		try {
			return downloadUrl(new URL(urlString));
		} catch (MalformedURLException e) {
			logger.log(Level.SEVERE, urlString + " is not a valid URL ", e);
			return "";
		}
	}

	/**
	 * Download the URL and return as a String.
	 * Gzip handling from http://goo.gl/J88WG
	 * 
	 * @param url
	 *            the URL to download
	 * @return String of the URL contents
	 * @throws IOException
	 *             when there is an error connecting or reading the URL
	 */
	public String downloadUrl(URL url) throws TVRenamerIOException {
		InputStream inputStream = null;
		StringBuilder contents = new StringBuilder();

		try {
			if (url != null) {
				logger.info("Downloading URL " + url.toString());
				
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				HttpURLConnection.setFollowRedirects(true);
				// allow both GZip and Deflate (ZLib) encodings
				conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
				conn.setConnectTimeout(CONNECT_TIMEOUT_MS);
				conn.setReadTimeout(READ_TIMEOUT_MS);

				// create the appropriate stream wrapper based on the encoding type
				String encoding = conn.getContentEncoding();
				if (encoding != null && encoding.equalsIgnoreCase("gzip")) {
					inputStream = new GZIPInputStream(conn.getInputStream());
				} else if (encoding != null && encoding.equalsIgnoreCase("deflate")) {
					inputStream = new InflaterInputStream(conn.getInputStream(), new Inflater(true));
				} else {
					inputStream = conn.getInputStream();
				}

				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

				logger.finer("Before reading url stream");

				String s;
				while ((s = reader.readLine()) != null) {
					contents.append(s);
				}

				if (logger.isLoggable(Level.FINEST)) {
					logger.finest("Url stream:\n" + StringUtils.encodeSpecialCharacters(contents.toString()));
				}
			}
		} catch (Exception e) {
			String message = "Exception when attempting to download and parse URL " + url;
			logger.log(Level.SEVERE, message, e);
			throw new TVRenamerIOException(message, e);
		} finally {
			try {
				if(inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Exception when attempting to close input stream", e);
			}
		}

		return StringUtils.encodeSpecialCharacters(contents.toString());
	}
}
