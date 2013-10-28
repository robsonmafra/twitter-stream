package me.robsonmafra.solr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class SolrUpdate {

	private static final String SOLR_STATUS = "http://app.robsonmafra.me:8983/solr/dataimport?command=status";
//	private static final String SOLR_UPDATE = "http://app.robsonmafra.me:8983/solr/dataimport?command=delta-import&clean=false";
	private static final String SOLR_UPDATE = "http://app.robsonmafra.me:8983/solr/dataimport?command=full-import&clean=false";
	
	public static void update() {
		try {
			String status = request(SOLR_STATUS);
			if (status.indexOf("idle") > 0) {
				request(SOLR_UPDATE);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static String request(String url_address) throws Exception {
		URL url = new URL(url_address);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}
}
