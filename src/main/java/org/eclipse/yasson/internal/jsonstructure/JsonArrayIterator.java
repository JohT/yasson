/*******************************************************************************
 * Copyright (c) 2019 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 ******************************************************************************/
package org.eclipse.yasson.internal.jsonstructure;

import org.eclipse.yasson.internal.properties.MessageKeys;
import org.eclipse.yasson.internal.properties.Messages;

import javax.json.JsonArray;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.bind.JsonbException;
import javax.json.stream.JsonParser;
import java.util.Iterator;

/**
 * Iterates over {@link JsonArray}.
 */
public class JsonArrayIterator extends JsonStructureIterator {

    private final Iterator<JsonValue> valueIterator;

    private JsonValue currentValue;

    public JsonArrayIterator(JsonArray jsonArray) {
        this.valueIterator = jsonArray.iterator();
    }

    /**
     * After {@link JsonParser.Event} END_ARRAY is returned from next() iterator is removed from the stack
     * @return always true
     */
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public JsonParser.Event next() {
        if (valueIterator.hasNext()) {
            currentValue = valueIterator.next();
            return getValueEvent(currentValue);
        }
        return JsonParser.Event.END_ARRAY;
    }

    @Override
    JsonValue getValue() {
        return currentValue;
    }

    @Override
    JsonbException createIncompatibleValueError() {
        return new JsonbException(Messages.getMessage(MessageKeys.NUMBER_INCOMPATIBLE_VALUE_TYPE_ARRAY, getValue().getValueType()));
    }

    @Override
    String getString() {
        if (currentValue instanceof JsonString) {
            return ((JsonString) currentValue).getString();
        }
        return currentValue.toString();
    }
}
