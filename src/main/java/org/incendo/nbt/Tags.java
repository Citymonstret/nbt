//
// MIT License
//
// Copyright (c) 2020 Alexander Söderberg & Contributors
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//

package org.incendo.nbt;

/**
 * NBT Tag type IDs
 */
public final class Tags {

    private Tags() {
    }

    /**
     * TAG_End
     */
    public static final byte TYPE_TAG_END = 0;
    /**
     * TAG_Byte
     */
    public static final byte TYPE_TAG_BYTE = 1;
    /**
     * TAG_Short
     */
    public static final byte TYPE_TAG_SHORT = 2;
    /**
     * TAG_Int
     */
    public static final byte TYPE_TAG_INT = 3;
    /**
     * TAG_Float
     */
    public static final byte TYPE_TAG_FLOAT = 4;
    /**
     * TAG_Long
     */
    public static final byte TYPE_TAG_LONG = 5;
    /**
     * TAG_Double
     */
    public static final byte TYPE_TAG_DOUBLE = 6;
    /**
     * TAG_Byte_Array
     */
    public static final byte TYPE_TAG_BYTE_ARRAY = 7;
    /**
     * TAG_String
     */
    public static final byte TYPE_TAG_STRING = 8;
    /**
     * TAG_List
     */
    public static final byte TYPE_TAG_LIST = 9;
    /**
     * TAG_Compound
     */
    public static final byte TYPE_TAG_COMPOUND = 10;
    /**
     * TAG_Int_Array
     */
    public static final byte TYPE_INT_ARRAY = 11;
    /**
     * TAG_Long_Array
     */
    public static final byte TYPE_LONG_ARRAY = 12;

}
