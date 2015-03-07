package net.code.bean;

public interface XType {

	//form xtype
	public static enum FormType{
		FORM("form"),
		CHECKBOX("checkbox"),
		COMBO("combo"),	
		DATEFIELD("datefield"),	
		TIMEFIELD("timefield"),	
		FIELD("field"),	
		FIELDSET("fieldset"),	
		HIDDEN("hidden"),	
		HTMLEDITOR("htmleditor"),	
		LABEL("label"),	
		NUMBERFIELD("numberfield"),	
		RADIO("radio"),	
		TEXTAREA("textarea"),	
		TEXTFIELD("textfield"),	
		TRIGGER("trigger"),
		CHECKBOXGROUP("checkboxgroup"),	
		DISPLAYFIELD("displayfield"),	
		RADIOGROUP("radiogroup");
		
		public String value; 
		 
		FormType(String value) { 
            this.value = value; 
        } 
	}
	
	
}
