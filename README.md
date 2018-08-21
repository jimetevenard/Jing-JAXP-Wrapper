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
javax.xml.validationSchemaFactory.newInstance("Needed Schema namespace");
```
