package com.jatis.test.zk;

import java.io.IOException;
import java.util.Locale;

import org.zkoss.util.Locales;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Label;

import com.jatis.test.zk.service.SecurityHelper;

public class BannerComposer extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	@Wire
	private Checkbox forceEnUS;
	
	@Wire
	private Checkbox forceInID;
	
	@Wire
	private Label lblLang;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		Locale l = 	(Locale) Executions.getCurrent().getSession().getAttribute(Attributes.PREFERRED_LOCALE);
		if (l == null ) {
			l = Locales.getCurrent();
		}
		if (l != null) {
			String lStr = l.toString();
			lblLang.setValue(lStr);
			if ("in_ID".equals(lStr) || "ID".equals(lStr)) {
				this.forceEnUS.setChecked(false);
				this.forceInID.setChecked(true);
			} else if ("en_US".equals(lStr) || "US".equals(lStr)) {
				this.forceEnUS.setChecked(true);
				this.forceInID.setChecked(false);
			} else {
				this.forceEnUS.setChecked(false);
				this.forceInID.setChecked(false);
			}
		}
	}



	@Listen("onCheck=#forceEnUS")
	public void chaneEnUS() throws IOException {
		if (forceEnUS.isChecked()) {
			this.forceInID.setChecked(false);
			Locale locale = new Locale("en", "US");
			Executions.getCurrent().getSession().setAttribute(Attributes.PREFERRED_LOCALE, locale);
			lblLang.setValue(locale.toString());
			Executions.sendRedirect(null);
		} else {
			this.forceInID.setChecked(true);
			Locale locale = new Locale("id", "ID");
			Executions.getCurrent().getSession().setAttribute(Attributes.PREFERRED_LOCALE, locale);
			lblLang.setValue(locale.toString());
			Executions.sendRedirect(null);
		}
	}

	@Listen("onCheck=#forceInID")
	public void changeInID() throws IOException {
		if (forceInID.isChecked()) {
			this.forceEnUS.setChecked(false);
			Locale locale = new Locale("id", "ID");
			Executions.getCurrent().getSession().setAttribute(Attributes.PREFERRED_LOCALE, locale);
			lblLang.setValue(locale.toString());
			Executions.sendRedirect(null);
		} else {
			this.forceEnUS.setChecked(true);
			Locale locale = new Locale("en", "US");
			Executions.getCurrent().getSession().setAttribute(Attributes.PREFERRED_LOCALE, locale);
			lblLang.setValue(locale.toString());
			Executions.sendRedirect(null);
		}
	}

}
