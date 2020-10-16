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

package org.incendo.nbt.tags;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.nbt.NBTTag;
import org.incendo.nbt.Tags;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * Tag_Int
 */
public final class IntTag extends NBTTag<Integer> {

    private final int value;

    private IntTag(final int value) {
        this.value = value;
    }

    /**
     * Create a new int tag with the specified value
     *
     * @param value Value
     * @return Created int tag
     */
    public static @NonNull IntTag of(final int value) {
        return new IntTag(value);
    }

    /**
     * Create an int tag from a byte buffer. This will read 4 bytes
     * and return the resulting number.
     *
     * @param byteBuffer Byte buffer
     * @return Read int tag
     */
    public static @NonNull IntTag from(
            final @NonNull ByteBuffer byteBuffer
    ) {
        return new IntTag(byteBuffer.getInt());
    }

    @Override
    public void write(final @NonNull ByteBuffer byteBuffer)
    {
        byteBuffer.putInt(this.value);
    }

    @Override
    public @NonNull Integer value() {
        return this.value;
    }

    @Override
    public byte id() {
        return Tags.TYPE_TAG_INT;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final IntTag intTag = (IntTag) o;
        return value == intTag.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

}
