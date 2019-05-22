package com.jatis.test.zk;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import com.jatis.test.zk.entity.MemberEntity;
import com.jatis.test.zk.event.SerializableEventListener;
import com.jatis.test.zk.service.RegistrationService;
import com.jatis.test.zk.util.NavigationUtil;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class RegistrationListComposer extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	@WireVariable(rewireOnActivate=true)
    private transient RegistrationService regService;

	@Wire
	private Rows memberRows;
	
	@Wire
	private Row noDataRow;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat sdfLastUpdate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		
		List<MemberEntity> members = regService.findAll();
		memberRows.getChildren().clear();
		if (members.isEmpty()) {
			noDataRow.setVisible(true);
			memberRows.appendChild(noDataRow);
		} else {
			noDataRow.setVisible(false);
			memberRows.appendChild(noDataRow);
			for (MemberEntity m : members) {
				Row row = new Row();
				Label lname = new Label(m.getName());
				row.appendChild(lname);
				Label lgender = new Label();
				lgender.setValue(Boolean.TRUE.equals(m.getGenderMale()) ? "male" : "female");
				row.appendChild(lgender);
				Label lbirthDate = new Label(sdf.format(m.getDateOfBirth()));
				row.appendChild(lbirthDate);
				Label llastUpdate = new Label(sdfLastUpdate.format(m.getLastUpdate()));
				row.appendChild(llastUpdate);
				Hbox hbox = new Hbox();
				Button bEdit = new Button("edit");
				hbox.appendChild(bEdit);
				bEdit.setAttribute("id", m.getName());
				bEdit.setAttribute("relatedRow", row);
				bEdit.addEventListener(Events.ON_CLICK, 
						new SerializableEventListener<Event>() {

							@Override
							public void onEvent(Event event) throws Exception {
								Component target = event.getTarget();
								String id = (String)target.getAttribute("id");
								HashMap<String, String> args = new HashMap<>();
								args.put("name", id);
								NavigationUtil.loadPage(target.getPage(), "/registration.zul", args);
							}
						});
				Button bDelete = new Button("delete");
				bDelete.setAttribute("id", m.getName());
				bDelete.setAttribute("relatedRow", row);
				hbox.appendChild(bDelete);
				bDelete.addEventListener(Events.ON_CLICK, 
						new SerializableEventListener<Event>() {

							@Override
							public void onEvent(Event event) throws Exception {
								Component target = event.getTarget();
								String id = (String)target.getAttribute("id");
								regService.delete(id);
								memberRows.removeChild((Component)target.getAttribute("relatedRow"));
								if (memberRows.getChildren().size() == 1) {
									noDataRow.setVisible(true);
								}
							}
						});
				row.appendChild(hbox);
				memberRows.appendChild(row);
			}
		}
	}
		
}
