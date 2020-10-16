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

import org.incendo.nbt.tags.IntTag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

public class TestIntTag {

    @Test
    public void testIntWrite() {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        final IntTag intTag = IntTag.of(10);
        intTag.write(byteBuffer);
        /* Rewind to start */
        byteBuffer.rewind();
        Assertions.assertEquals(10, byteBuffer.getInt());
    }

    @Test
    public void testIntRead() {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(4);
        /* Rewind to start */
        byteBuffer.rewind();
        final IntTag intTag = IntTag.from(byteBuffer);
        Assertions.assertEquals(4, intTag.value());
    }

}
