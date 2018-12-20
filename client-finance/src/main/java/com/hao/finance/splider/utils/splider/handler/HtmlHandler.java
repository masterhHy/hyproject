package com.hao.finance.splider.utils.splider.handler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class HtmlHandler extends ErrorHandler implements ObjectHandler {

	public void webData(String data) {
		Document parse = Jsoup.parse(data);
		this.webData(parse);
	}
	public abstract void webData(Document doc);
}
