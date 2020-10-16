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

import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.nbt.tags.CompoundTag;
import org.incendo.nbt.tags.IntTag;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility that allows you to build {@link CompoundTag compound tags}
 */
public final class CompoundBuilder {

    private final Map<String, NBTTag<?>> tags = new HashMap<>();

    /**
     * Create a new compound builder
     */
    public CompoundBuilder() {
    }

    /**
     * Store an {@link IntTag integer tag} in the compound
     *
     * @param name  Name of the tag
     * @param value Integer value
     * @return Builder instance
     */
    public @NonNull CompoundBuilder integer(final @NonNull String name, final int value) {
        this.tags.put(name, IntTag.of(value));
        return this;
    }

    /**
     * Store a {@link CompoundTag compound tag} in the compound
     *
     * @param name        Tag name
     * @param compoundTag Compound tag
     * @return Builder instance
     */
    public @NonNull CompoundBuilder compound(final @NonNull String name, final CompoundTag compoundTag) {
        this.tags.put(name, compoundTag);
        return this;
    }

    /**
     * Store a {@link NBTTag tag} in the compound
     *
     * @param name Tag name
     * @param tag  Tag
     * @return Builder instance
     */
    public @NonNull CompoundBuilder tag(final @NonNull String name, final NBTTag<?> tag) {
        this.tags.put(name, tag);
        return this;
    }

    /**
     * Build a new compound tag containing the tags provided to this builder
     *
     * @return Created compound tag
     */
    public @NonNull CompoundTag build() {
        return CompoundTag.of(tags);
    }

}
