package com.jatis.test.zk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

public class NavigationComposer extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	@Wire
	Rows menuRows;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		Row r = new Row();
		Image i = new Image("images/png/013-broom.png");
		i.setWidth("36px");
		r.appendChild(i);
		A a = new A("Google");
		a.setHref("https://www.google.com");
		r.appendChild(a);
		menuRows.appendChild(r);
	}
	
	@Listen("onClick=a#regDtls")
	public void toRegistrationDtls() {
		loadPage("/registration.zul", null);
	}

	@Listen("onClick=a#regList")
	public void toRegistrationList() {
		loadPage("/registration_list_main.zul", null);
	}
	
	@Listen("onClick=a#hello")
	public void toHello() {
		Map<String, String> helloArgs = new HashMap<String, String>();
		helloArgs.put("name", "Orang Baru");
		loadPage("/hello.zul", helloArgs);
	}
	
	private void loadPage(String uri, Map<?,?> args) {
		try {
			Iterator<Component> it = Selectors.iterable(menuRows.getPage(), "#mainInclude").iterator();
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
