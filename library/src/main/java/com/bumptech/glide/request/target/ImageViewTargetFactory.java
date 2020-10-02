package com.bumptech.glide.request.target;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;

/**
 * A factory responsible for producing the correct type of {@link com.bumptech.glide.request.target.Target} for a given
 * {@link android.view.View} subclass.
 */
public class ImageViewTargetFactory {

    @SuppressWarnings("unchecked")
    public <Z> Target<Z> buildTarget(ImageView view, Class<Z> clazz) {
        //根据传入的class参数构建不同的Target对象，分为三种情况：
        if (GlideDrawable.class.isAssignableFrom(clazz)) {    //否则构建的是GlideDrawableImageViewTarget对象
            return (Target<Z>) new GlideDrawableImageViewTarget(view);
        } else if (Bitmap.class.equals(clazz)) {    //若加载图片时调用了asBitmap()，那么构建的是BitmapImageViewTarget对象
            return (Target<Z>) new BitmapImageViewTarget(view);
        } else if (Drawable.class.isAssignableFrom(clazz)) {    //DrawableImageViewTarget对象基本用不到，此处忽略
            return (Target<Z>) new DrawableImageViewTarget(view);
        } else {
            throw new IllegalArgumentException("Unhandled class: " + clazz
                    + ", try .as*(Class).transcode(ResourceTranscoder)");
        }
    }
}
