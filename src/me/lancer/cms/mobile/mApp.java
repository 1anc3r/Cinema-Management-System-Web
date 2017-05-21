package me.lancer.cms.mobile;

import java.util.ArrayList;
import java.util.List;

public final class mApp {
	private static List<String> sessionList = new ArrayList<>();

	public static boolean getSessionid(String id) {
		return sessionList.contains(id);
	}

	public static void setSessionid(String id) {
		sessionList.add(id);
	}
}
