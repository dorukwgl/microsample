package com.doruk.infrastructure.util;

import com.doruk.application.enums.ImageVariant;
import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ImageScalingUtil {

    private ImageScalingUtil() {}

    public static Pair<Integer, InputStream> scaleAndCompress(InputStream stream, ImageVariant variant) {
        var t = Thumbnails.of(stream)
                .outputQuality(variant == ImageVariant.ORIGINAL ? 0.95f : 0.7f);

        if (variant != ImageVariant.ORIGINAL)
            t.size(variant.maxSize(), variant.maxSize());

        var out = new ByteArrayOutputStream(64 * 1024);
        try {
            t.toOutputStream(out);
            var bytes = out.toByteArray();
            return new Pair<>(bytes.length, new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
