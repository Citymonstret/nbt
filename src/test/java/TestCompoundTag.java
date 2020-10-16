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

import org.incendo.nbt.NBTTag;
import org.incendo.nbt.Tags;
import org.incendo.nbt.tags.CompoundTag;
import org.incendo.nbt.tags.IntTag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class TestCompoundTag {

    @Test
    public void testWrite() {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        final CompoundTag compoundTag = CompoundTag.builder()
                .integer("integer", 10).build();
        compoundTag.write(byteBuffer);
        /* Rewind the byte buffer */
        byteBuffer.rewind();
        /* Extract the int tag */
        Assertions.assertEquals(Tags.TYPE_TAG_INT, byteBuffer.get());
        final short nameLength = byteBuffer.getShort();
        final byte[] nameBytes = new byte[nameLength];
        byteBuffer.get(nameBytes);
        /* Compare the names */
        Assertions.assertEquals("integer", new String(nameBytes, StandardCharsets.UTF_8));
        /* Compare the tag value */
        Assertions.assertEquals(10, byteBuffer.getInt());
        /* Read the end tag */
        byteBuffer.get();
    }

    @Test
    public void testSize() {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        final CompoundTag compoundTag = CompoundTag.builder()
                .integer("", 10).build();
        compoundTag.write(byteBuffer);
        /*
        Type: 1
        Name Length: 2
        Name Bytes: 0
        Integer: 4
        End Tag: 1
        Total: 8
         */
        Assertions.assertEquals(8, byteBuffer.position());
    }

    @Test
    public void testRead() {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        final String tagName = "integer";
        final byte[] tagNameBytes = tagName.getBytes(StandardCharsets.UTF_8);
        byteBuffer.put(Tags.TYPE_TAG_INT);
        byteBuffer.putShort((short) tagNameBytes.length);
        byteBuffer.put(tagNameBytes);
        byteBuffer.putInt(10);
        byteBuffer.put(Tags.TYPE_TAG_END);
        /* Rewind the buffer */
        byteBuffer.rewind();
        final CompoundTag tag = CompoundTag.from(byteBuffer);
        final Map<String, NBTTag<?>> tagMap = tag.value();
        Assertions.assertFalse(tagMap.isEmpty());
        Assertions.assertTrue(tagMap.containsKey(tagName));
        final NBTTag<?> innerTag = tagMap.get(tagName);
        Assertions.assertEquals(IntTag.class, innerTag.getClass());
        Assertions.assertEquals(10, innerTag.value());
    }

}
