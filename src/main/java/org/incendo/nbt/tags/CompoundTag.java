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
import org.incendo.nbt.CompoundBuilder;
import org.incendo.nbt.NBTTag;
import org.incendo.nbt.Tags;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * TAG_Compound
 */
public final class CompoundTag extends NBTTag<Map<String, NBTTag<?>>> {

    private final Map<String, NBTTag<?>> tags;
    private final int size;

    private CompoundTag(final @NonNull Map<@NonNull String, @NonNull NBTTag<?>> tags) {
        this.tags = tags;
        /* Calculate the size */
        int size = 1; /* TAG_END */
        for (final Map.Entry<String, NBTTag<?>> tagEntry : this.tags.entrySet()) {
            final byte[] name = tagEntry.getKey().getBytes(StandardCharsets.UTF_8);
            size += 5 /* tag type + short */ + name.length;
        }
        this.size = size;
    }

    /**
     * Create a new compound tag from a map of name-tag pairs
     *
     * @param tags Map containing the name-tag pairs
     * @return Created compound tag
     */
    public static @NonNull CompoundTag of(final @NonNull Map<@NonNull String, @NonNull NBTTag<?>> tags) {
        return new CompoundTag(
                Collections.unmodifiableMap(tags)
        );
    }

    /**
     * Create a new compound builder
     *
     * @return Created builder instance
     */
    public static @NonNull CompoundBuilder builder() {
        return new CompoundBuilder();
    }

    /**
     * Read the compound tag from a byte buffer
     *
     * @param byteBuffer Buffer to read from
     * @return Read compound tag
     */
    public static @NonNull CompoundTag from(final @NonNull ByteBuffer byteBuffer) {
        final CompoundBuilder builder = builder();
        byte type;
        while ((type = byteBuffer.get()) != Tags.TYPE_TAG_END) {
            /* Read the name */
            final int nameLength = byteBuffer.getShort();
            final byte[] nameBytes = new byte[nameLength];
            byteBuffer.get(nameBytes);
            final String name = new String(nameBytes, StandardCharsets.UTF_8);
            /* Read the tag */
            switch (type) {
                case Tags.TYPE_TAG_INT:
                    builder.tag(name, IntTag.from(byteBuffer));
                    break;
                default:
                    break;
            }
        }
        return builder.build();
    }

    @Override
    public void write(
            final @NonNull ByteBuffer byteBuffer
    ) {
        for (final Map.Entry<String, NBTTag<?>> tagEntry : this.tags.entrySet()) {
            final String name = tagEntry.getKey();
            final NBTTag<?> tag = tagEntry.getValue();
            /* Write tag type */
            byteBuffer.put(tag.id());
            /* Write tag name */
            final byte[] nameBytes = name.getBytes(StandardCharsets.UTF_8);
            byteBuffer.putShort((short) nameBytes.length);
            byteBuffer.put(nameBytes);
            /* Write tag payload */
            tag.write(byteBuffer);
        }
        /* Write end */
        byteBuffer.put(Tags.TYPE_TAG_END);
    }

    @Override
    public @NonNull Map<@NonNull String, @NonNull NBTTag<?>> value() {
        return new HashMap<>(this.tags);
    }

    @Override
    public byte id() {
        return Tags.TYPE_TAG_COMPOUND;
    }

    @Override
    public int size() {
        return this.size;
    }

}
