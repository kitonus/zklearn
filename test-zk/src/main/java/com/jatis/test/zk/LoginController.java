package com.jatis.test.zk;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;

public class LoginController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	@Wire
	private Label message;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		if ("1".equals(Executions.getCurrent().getParameter("login_error"))) {
			message.setValue("Invalid username or password");
			message.setVisible(true);
		} else {
			message.setVisible(false);
		}
	}

	
}
