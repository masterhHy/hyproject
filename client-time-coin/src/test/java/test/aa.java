package test;

public class aa {

	public static void main(String[] args) {
		String url ="/client-time-coin/api/coin/getCoinData";
		String tarUrl = url.substring(url.indexOf("/api")+4);
		String project = url.substring(1, url.indexOf("/api"));
		System.out.println(tarUrl);
		System.out.println(project);
	}
}
