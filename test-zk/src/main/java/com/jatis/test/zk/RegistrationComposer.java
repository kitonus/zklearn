package com.jatis.test.zk;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

import com.jatis.test.zk.dto.RegistrationDTO;
import com.jatis.test.zk.service.RegistrationService;
import com.jatis.test.zk.util.NavigationUtil;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class RegistrationComposer extends SelectorComposer<Component>{
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationComposer.class); 

	private static final long serialVersionUID = 1L;
	@Wire
    private Button submitButton;
	
	@Wire
	private Checkbox acceptTermBox;
	
	@Wire
	private Textbox nameBox;
	
	@Wire
	private Radiogroup genderRadio;
	
	@Wire
	private Datebox birthdayBox;
	    
	@WireVariable(rewireOnActivate=true)
    private transient RegistrationService regService;
    
    @Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		@SuppressWarnings("unchecked")
		Map<String, String> args = (Map<String, String>) Executions.getCurrent().getArg();
		if (args != null) {
			String name = args.get("name");
			if (name != null) {
				RegistrationDTO dto = this.regService.findOne(name);
				
				if (dto != null) {
					this.nameBox.setValue(dto.getName());
					String gender = dto.isMale() ? "male" : "female";
					for (Radio r : this.genderRadio.getItems()) {
						if (gender.equals(r.getValue())) {
							this.genderRadio.setSelectedItem(r);
						}
					}
					this.birthdayBox.setValue(dto.getDateOfBirth());
				}
			}
		}
	}

	@Listen("onCheck=#acceptTermBox")
    public void changeSubmitStatus(){
    	logger.info("Change submit status");
        if (acceptTermBox.isChecked()){
            submitButton.setDisabled(false);
        }else{
            submitButton.setDisabled(true);
        }
    }
    
    @Listen("onClick=#submitButton")
    public void submit() {
    	RegistrationDTO dto = new RegistrationDTO();
    	dto.setName(nameBox.getValue());
    	dto.setDateOfBirth(birthdayBox.getValue());
    	dto.setMale("male".equalsIgnoreCase(genderRadio.getSelectedItem().getValue()));
    	regService.register(dto);
    	NavigationUtil.loadPage(this.getSelf().getPage(), "/registration_list_main.zul", null);
    }
}
