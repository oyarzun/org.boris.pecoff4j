/*******************************************************************************
 * This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     Peter Smith
 *******************************************************************************/
package org.boris.pecoff4j.resources;

import java.io.IOException;

import org.boris.pecoff4j.io.IDataReader;
import org.boris.pecoff4j.util.Reflection;

public class StringTable
{
    private int length;
    private int valueLength;
    private int type;
    private String key;
    private int padding;

    public int getLength() {
        return length;
    }

    public int getValueLength() {
        return valueLength;
    }

    public int getType() {
        return type;
    }

    public int getPadding() {
        return padding;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String toString() {
        return Reflection.toString(this);
    }

    public static StringTable read(IDataReader dr) throws IOException {
        StringTable vfi = new StringTable();
        vfi.length = dr.readWord();
        if (vfi.length == 0) {
            return null;
        }
        vfi.valueLength = dr.readWord();
        vfi.type = dr.readWord();
        vfi.key = dr.readUnicode();
        if (vfi.key.length() % 2 == 1) {
            dr.readWord(); // padding
            vfi.padding = 2;
        }
        return vfi;
    }
}
