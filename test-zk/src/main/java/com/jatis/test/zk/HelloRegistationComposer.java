package com.jatis.test.zk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;

import com.jatis.test.zk.dto.RegistrationDTO;
import com.jatis.test.zk.service.HelloRegistrationService;

@VariableResolver(DelegatingVariableResolver.class)
public class HelloRegistationComposer extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	
	@Wire
	private Checkbox acceptTermBox;
	
	@Wire
	private Button submitButton;
	
	@Wire
	private Textbox nameBox;
	
	@Wire
	private Datebox birthdayBox;
	
	@Wire
	private Button resetButton;
	
	@Wire
	private Rows formRows;
	
	@WireVariable(rewireOnActivate=true)
	private transient HelloRegistrationService helloRegistrationService;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		String name = (String) Executions.getCurrent().getArg().get("name");
		nameBox.setValue(name);
		
		resetButton.addEventListener(Events.ON_CLICK, new SerializableEventListener<Event>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onEvent(Event event) throws Exception {
				nameBox.setText(null);
				birthdayBox.setValue(null);
			}
			
		});
	}



	@Listen("onCheck=#acceptTermBox")
	public void changeAcceptance() {
		if (acceptTermBox.isChecked()) {
			submitButton.setDisabled(false);
		} else {
			submitButton.setDisabled(true);
		}
	}
	
	@Listen("onClick=#addRow")
	public void addRow() {
		Row row = new Row();
		Cell cell = new Cell();
		cell.setColspan(2);
		row.appendChild(cell);
		
		cell.appendChild(new Label("Field"));
		cell.appendChild(new Textbox("A value"));
		
		Button delButton = new Button("Delete");
		delButton.setAttribute("myRow", row);
		delButton.addEventListener(Events.ON_CLICK, new SerializableEventListener<Event>(){
			private static final long serialVersionUID = 1L;
			@Override
			public void onEvent(Event evt) throws Exception {
				Row myRow = (Row) evt.getTarget().getAttribute("myRow");
				formRows.removeChild(myRow);
			}
		});
		cell.appendChild(delButton);
		formRows.appendChild(row);
	}
	
	@Listen("onClick=#submitButton")
	public void submit() {
		RegistrationDTO registration = new RegistrationDTO();
		registration.setName(nameBox.getValue());
		registration.setDateOfBirth(birthdayBox.getValue());
		helloRegistrationService.save(registration );
	}
	
//	@Listen("onClick=#resetButton")
//	public void onReset() {
//		nameBox.setText(null);
//		birthdayBox.setValue(null);
//	}
}
