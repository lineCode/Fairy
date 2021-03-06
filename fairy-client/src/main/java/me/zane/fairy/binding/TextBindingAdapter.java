/*
 * Copyright (C) 2017 Zane.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.zane.fairy.binding;

import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.concurrent.Executor;

import me.zane.fairy.Config;
import me.zane.fairy.R;
import me.zane.fairy.TextRender;
import me.zane.fairy.resource.AppExecutors;
import me.zane.fairy.vo.LogcatContent;
import me.zane.fairy.vo.LogcatItem;

/**
 * Created by Zane on 2017/11/20.
 * Email: zanebot96@gmail.com
 */

public class TextBindingAdapter {
    @BindingAdapter("render")
    public static void append(TextView view, LogcatContent content) {
        if (content != null) {
            CharSequence s = content.getContent();
            if (s.equals(Config.CLEAR_SIGNAL)) {
                view.setText("clear data");
            } else if (!s.equals("")) {
                TextRender.renderText(view, view.getRootView().findViewById(R.id.scrollview_logcat), s, content.isFirst());
            }
        }
    }

    @BindingAdapter("item_grep")
    public static void itemGrep(TextView view, LogcatItem item) {
        String itemGrep;
        Resources resource = view.getContext().getResources();
        if (item.getGrep().equals("")) {
            itemGrep = String.format(resource.getString(R.string.logcat), item.getOptions(), item.getFilter());
        } else {
            itemGrep = String.format(resource.getString(R.string.logcat_grep), item.getOptions(), item.getFilter(), item.getGrep());
        }
        view.setText(itemGrep);
    }
}
