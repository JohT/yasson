/*******************************************************************************
 * Copyright (c) 2016, 2017 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 * Roman Grigoriadi
 ******************************************************************************/

package org.eclipse.yasson.internal.serializer;

import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import java.util.Map;

/**
 * Serializer for {@link JsonObject} type.
 *
 * @author Roman Grigoriadi
 */
public class JsonObjectSerializer extends AbstractJsonpSerializer<JsonObject> {

    protected JsonObjectSerializer(SerializerBuilder builder) {
        super(builder);
    }

    @Override
    protected void serializeInternal(JsonObject obj, JsonGenerator generator, SerializationContext ctx) {
        for (Map.Entry<String, JsonValue> entry : obj.entrySet()) {
            generator.write(entry.getKey(), entry.getValue());
        }
    }

    @Override
    protected void writeStart(JsonGenerator generator) {
        generator.writeStartObject();
    }

    @Override
    protected void writeStart(String key, JsonGenerator generator) {
        generator.writeStartObject(key);
    }
}
