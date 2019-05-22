package com.jatis.test.zk.util;

import java.util.Iterator;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Include;

public class NavigationUtil {

	public static void loadPage(Page page, String uri, Map<?,?> args) {
		try {
		Iterator<Component> it = Selectors.iterable(page, "#mainInclude").iterator();
		while (it.hasNext()) {
			Component c = it.next();
			if (c instanceof Include) {
				c.getChildren().clear();
				Executions.createComponents(uri, c, args);
				break;
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
