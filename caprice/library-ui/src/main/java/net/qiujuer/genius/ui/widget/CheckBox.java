/*
 * Copyright (C) 2015 Qiujuer <qiujuer@live.cn>
 * WebSite http://www.qiujuer.net
 * Created 08/10/2015
 * Changed 08/12/2015
 * Version 3.0.0
 * Author Qiujuer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.qiujuer.genius.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import net.qiujuer.genius.ui.R;
import net.qiujuer.genius.ui.drawable.CircleCheckDrawable;

/**
 * This is CheckBox widget
 * The widget extend view widget
 */
public class CheckBox extends android.widget.CheckBox {
    public CheckBox(Context context) {
        super(context);
        init(null, 0, 0);
    }

    public CheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, R.attr.gCheckBoxStyle, 0);
    }

    public CheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CheckBox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(CheckBox.class.getName());
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(CheckBox.class.getName());
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        if (attrs == null)
            return;

        final Context context = getContext();
        final Resources resource = getResources();
        final float density = resource.getDisplayMetrics().density;

        // Load attributes
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.CheckBox, defStyleAttr, defStyleRes);

        int ringSize = a.getDimensionPixelOffset(R.styleable.CheckBox_gRingSize, (int) (density * 2));
        int circleRadius = a.getDimensionPixelOffset(R.styleable.CheckBox_gCircleRadius, -1);
        ColorStateList color = a.getColorStateList(R.styleable.CheckBox_gColor);

        a.recycle();

        if (color == null)
            color = resource.getColorStateList(R.color.g_default_check_box);

        boolean isCustom = true;

        if (circleRadius == -1) {
            circleRadius = (int) (density * 8);
            isCustom = false;
        }

        CircleCheckDrawable drawable = new CircleCheckDrawable(color);
        drawable.setRingSize(ringSize);
        drawable.setCircleRadius(circleRadius, isCustom);
        setButtonDrawable(drawable);
    }
}
