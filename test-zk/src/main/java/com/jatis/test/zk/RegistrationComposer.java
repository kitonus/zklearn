package com.jatis.test.zk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

import com.jatis.test.zk.dto.RegistrationDTO;
import com.jatis.test.zk.service.RegistrationService;

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
    }
}
