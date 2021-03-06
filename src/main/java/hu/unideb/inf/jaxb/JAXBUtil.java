package hu.unideb.inf.jaxb;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBUtil {

	/**
	 * Serializes an object to XML. The output document is written in UTF-8 encoding.
	 *
	 * @param o the object to serialize
	 * @param os the {@link OutputStream} to write to
	 * @throws JAXBException on any error
	 */
	public static void toXML(Object o, OutputStream os) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(o.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.marshal(o, os);
	}
	
	public static <T> String toXML(T t) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(t.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		
		Writer writer = new StringWriter();
		marshaller.marshal(t, writer);
		return writer.toString();
	}

	/**
	 * Deserializes an object from XML.
	 *
	 * @param clazz the class of the object
	 * @param is the {@link InputStream} to read from
	 * @return the resulting object
	 * @throws JAXBException on any error
	 */
	public static <T> T fromXML(Class<T> clazz, InputStream is) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return clazz.cast(unmarshaller.unmarshal(is));
	}

}