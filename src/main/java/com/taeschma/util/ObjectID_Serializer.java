package com.taeschma.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.bson.types.ObjectId;

/**
 *
 * @author marco
 */
public class ObjectID_Serializer extends JsonSerializer<ObjectId> {

	@Override
	public void serialize(ObjectId objid, JsonGenerator jsongen, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		if (objid == null) {
			jsongen.writeNull();
		} else {
			jsongen.writeString(objid.toString());
		}

	}

}