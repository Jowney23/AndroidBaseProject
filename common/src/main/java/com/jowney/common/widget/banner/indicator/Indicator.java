package com.jowney.common.widget.banner.indicator;

import android.view.View;

import androidx.annotation.NonNull;

import com.jowney.common.widget.banner.config.IndicatorConfig;
import com.jowney.common.widget.banner.listener.OnPageChangeListener;


public interface Indicator extends OnPageChangeListener {
    @NonNull
    View getIndicatorView();

    IndicatorConfig getIndicatorConfig();

    void onPageChanged(int count, int currentPosition);

}
