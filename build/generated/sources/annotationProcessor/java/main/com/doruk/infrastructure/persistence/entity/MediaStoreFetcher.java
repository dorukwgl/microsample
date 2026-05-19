package com.doruk.infrastructure.persistence.entity;

import java.lang.Override;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.lang.NewChain;
import org.babyfish.jimmer.meta.ImmutableProp;
import org.babyfish.jimmer.sql.ast.table.Table;
import org.babyfish.jimmer.sql.fetcher.Fetcher;
import org.babyfish.jimmer.sql.fetcher.FieldConfig;
import org.babyfish.jimmer.sql.fetcher.IdOnlyFetchType;
import org.babyfish.jimmer.sql.fetcher.impl.FetcherImpl;
import org.babyfish.jimmer.sql.fetcher.spi.AbstractTypedFetcher;

@GeneratedBy(
        type = MediaStore.class
)
public class MediaStoreFetcher extends AbstractTypedFetcher<MediaStore, MediaStoreFetcher> {
    public static final MediaStoreFetcher $ = new MediaStoreFetcher(null);

    private MediaStoreFetcher(FetcherImpl<MediaStore> base) {
        super(MediaStore.class, base);
    }

    private MediaStoreFetcher(MediaStoreFetcher prev, ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        super(prev, prop, negative, idOnlyFetchType);
    }

    private MediaStoreFetcher(MediaStoreFetcher prev, ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        super(prev, prop, fieldConfig);
    }

    public static MediaStoreFetcher $from(Fetcher<MediaStore> base) {
        return base instanceof MediaStoreFetcher ? 
        	(MediaStoreFetcher)base : 
        	new MediaStoreFetcher((FetcherImpl<MediaStore>)base);
    }

    @NewChain
    public MediaStoreFetcher objectKey() {
        return add("objectKey");
    }

    @NewChain
    public MediaStoreFetcher objectKey(boolean enabled) {
        return enabled ? add("objectKey") : remove("objectKey");
    }

    @NewChain
    public MediaStoreFetcher visibility() {
        return add("visibility");
    }

    @NewChain
    public MediaStoreFetcher visibility(boolean enabled) {
        return enabled ? add("visibility") : remove("visibility");
    }

    @NewChain
    public MediaStoreFetcher mimeType() {
        return add("mimeType");
    }

    @NewChain
    public MediaStoreFetcher mimeType(boolean enabled) {
        return enabled ? add("mimeType") : remove("mimeType");
    }

    @NewChain
    public MediaStoreFetcher size() {
        return add("size");
    }

    @NewChain
    public MediaStoreFetcher size(boolean enabled) {
        return enabled ? add("size") : remove("size");
    }

    @NewChain
    public MediaStoreFetcher createdAt() {
        return add("createdAt");
    }

    @NewChain
    public MediaStoreFetcher createdAt(boolean enabled) {
        return enabled ? add("createdAt") : remove("createdAt");
    }

    @NewChain
    public MediaStoreFetcher deletedAt() {
        return add("deletedAt");
    }

    @NewChain
    public MediaStoreFetcher deletedAt(boolean enabled) {
        return enabled ? add("deletedAt") : remove("deletedAt");
    }

    @Override
    protected MediaStoreFetcher createFetcher(ImmutableProp prop, boolean negative,
            IdOnlyFetchType idOnlyFetchType) {
        return new MediaStoreFetcher(this, prop, negative, idOnlyFetchType);
    }

    @Override
    protected MediaStoreFetcher createFetcher(ImmutableProp prop,
            FieldConfig<?, ? extends Table<?>> fieldConfig) {
        return new MediaStoreFetcher(this, prop, fieldConfig);
    }
}
