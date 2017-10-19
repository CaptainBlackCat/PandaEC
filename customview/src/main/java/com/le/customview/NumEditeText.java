package com.le.customview;
import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 解决多个EditText添加addTextchangeListener的重复监听
 */

public class NumEditeText extends android.support.v7.widget.AppCompatEditText {
    private List<TextWatcher> mListeners = null;

    public NumEditeText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        if (mListeners == null) {
            mListeners = new ArrayList<>();
        }
        mListeners.add(watcher);
        super.addTextChangedListener(watcher);
    }

    @Override
    public void removeTextChangedListener(TextWatcher watcher) {
        if (mListeners != null) {
            int i = mListeners.indexOf(watcher);
            if (i > 0) {
                mListeners.remove(i);
            }
        }
        super.removeTextChangedListener(watcher);
    }

    public void clearTextChangeListener() {
        if (mListeners != null) {
            for (TextWatcher watcher : mListeners) {
                super.removeTextChangedListener(watcher);
            }
            mListeners.clear();
            mListeners = null;
        }
    }
}
