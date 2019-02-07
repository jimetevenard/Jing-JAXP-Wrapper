package com.jimetevenard.xml.jingWrapper;

import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jimetevenard.utils.OperationResult;
import com.thaiopensource.resolver.Resolver;
import com.thaiopensource.resolver.xml.ls.LS;
import com.thaiopensource.util.PropertyMapBuilder;
import com.thaiopensource.validate.SchemaReader;
import com.thaiopensource.validate.ValidateProperty;
import com.thaiopensource.validate.ValidationDriver;
import com.thaiopensource.validate.auto.AutoSchemaReader;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.Locator2Impl;

public class AutoSchemaValidator extends Validator {

    private ErrorHandler errorHandler;
    private LSResourceResolver resourceResolver;
    private final SchemaFactory parentSchemaFactory;
    private final Source schema;

    private ValidationDriver validationDriver;
    private OperationResult schemaLoading = new OperationResult();

    protected AutoSchemaValidator(Source schema, SchemaFactory parentSchemaFactory) {
        super();
        this.schema = schema;
        this.parentSchemaFactory = parentSchemaFactory;
        reset();
    }

    @Override
    public void reset() {
        this.schemaLoading.reset();
        this.errorHandler = this.parentSchemaFactory.getErrorHandler();
        this.resourceResolver = this.parentSchemaFactory.getResourceResolver();
        loadSchema();
    }

    private void loadSchema() {
        PropertyMapBuilder properties = new PropertyMapBuilder();
        properties.put(ValidateProperty.ERROR_HANDLER, this.errorHandler);
        SchemaReader sr = new AutoSchemaReader();
        Resolver resolver = LS.createResolver(this.resourceResolver);
        properties.put(ValidateProperty.RESOLVER, resolver);
        this.validationDriver = new ValidationDriver(properties.toPropertyMap(), sr);

        try {
            if (this.validationDriver.loadSchema(new InputSource(this.schema.getSystemId()))) {
                this.schemaLoading.succes();
            } else {
                this.schemaLoading.fail();
            }
        } catch (SAXException | IOException e) {
            this.schemaLoading.fail(e);
        }
    }

    @Override
    public void validate(Source source, Result result) throws SAXException, IOException {
        checkState();
        boolean ret = this.validationDriver.validate(new InputSource(source.getSystemId()));
        if(!ret) {
            getErrorHandler().error(new SAXParseException("Error when validating. See logs for detail", new Locator2Impl()));
        }
    }

    public void checkState() {
        if (!this.schemaLoading.isSucces()) {
            Exception failCause = this.schemaLoading.getError().orElse(new Exception("Unknown cause"));
            throw new IllegalStateException("NO SCHEMA LOADED", failCause);
        }

    }

    @Override
    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    @Override
    public void setResourceResolver(LSResourceResolver resourceResolver) {
        this.resourceResolver = resourceResolver;
    }

    @Override
    public LSResourceResolver getResourceResolver() {
        return this.resourceResolver;
    }

}
