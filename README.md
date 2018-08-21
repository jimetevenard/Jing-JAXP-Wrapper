# Jing-JAXP-Wrapper

This artifact provides implementations of the Java XML validation API [`SchemaFactory`](https://docs.oracle.com/javase/9/docs/api/javax/xml/validation/SchemaFactory.html)
as a [Java Service](https://docs.oracle.com/javase/9/docs/api/java/util/ServiceLoader.html) for the following languages :

* RelaxNG *XML syntax* - `http://relaxng.org/ns/structure/1.0`
* RelaxNG *Compact syntax* - `http://www.iana.org/assignments/media-types/application/relax-ng-compact-syntax`
* ISO Schematron - `http://purl.oclc.org/dsdl/schematron`
* Schematron 1.5 - `http://www.ascc.net/xml/schematron`
* NVDL - `http://purl.oclc.org/dsdl/nvdl/ns/structure/1.0`

These implementations are wrappers over James Clack's [Jing](http://www.thaiopensource.com/relaxng/jing.html) validation engine.

## Usage

```
String schemaLanguage = // One of the namespaces above...
javax.xml.validationSchemaFactory.newInstance(schemaLanguage);
```


Please note that **you'll need to add your Jing distribution as a dependency**.

I put it in `provided` scope because [the only version available on Maven Central](https://mvnrepository.com/artifact/com.thaiopensource/jing/20091111) is quite outdated, so you keep the ability to build and use a [newer release](https://github.com/relaxng/jing-trang). (For ISO Schematron, this is mandatory.)
