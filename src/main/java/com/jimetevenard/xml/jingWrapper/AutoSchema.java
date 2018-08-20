package com.jimetevenard.xml.jingWrapper;

import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.validation.ValidatorHandler;

public class AutoSchema extends Schema{
	
	private SchemaFactory parent;
	private Source schema;
	
	AutoSchema(Source schema,SchemaFactory parent) {
		super();
		this.parent = parent;
		this.schema = schema;
	}

	@Override
	public Validator newValidator() {
		return new AutoSchemaValidator(schema, parent);
	}

	@Override
	public ValidatorHandler newValidatorHandler() {
		throw new UnsupportedOperationException("NOPE."); // TODO
	}

}
