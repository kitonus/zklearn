package com.jatis.test.zk;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Label;

import com.jatis.test.zk.service.SecurityHelper;

public class BannerComposer extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	@Wire
	private Checkbox forceEnUS;
	
	@Wire
	private Label lblLang;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		changeLang();
	}



	@Listen("onCheck=#forceEnUS")
	public void changeLang() {
		if (forceEnUS.isChecked()) {
			Executions.getCurrent().getSession().setAttribute("org.zkoss.web.preferred.locale", "en_US");
			lblLang.setValue("Enlish - US");
			this.getPage().invalidate();
		} else {
			Executions.getCurrent().getSession().setAttribute(
					"org.zkoss.web.preferred.locale", SecurityHelper.getCurrentLocale());
			lblLang.setValue(SecurityHelper.getCurrentLocale().getDisplayName());
			this.getPage().invalidate();
			
		}
	}
}
