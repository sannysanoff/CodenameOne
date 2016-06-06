package com.codename1.impl.android;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;

/**
 *
 * @author San
 */
public class AndroidSimpleView extends View implements CodenameOneSurface {

    AndroidImplementation androidImplementation;
    CodenameOneView cn1View;

    public AndroidSimpleView(Activity activity, AndroidImplementation androidImplementation) {
        super(activity);
        this.androidImplementation = androidImplementation;
        cn1View = new CodenameOneView(activity, this, androidImplementation, false);

    }



    public boolean isOpaque() {
        return true;
    }


    private void visibilityChangedTo(boolean visible) {
        cn1View.visibilityChangedTo(visible);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        // method used for View implementation. is it still
        // required with a SurfaceView?
        super.onWindowVisibilityChanged(visibility);
        this.visibilityChangedTo(visibility == View.VISIBLE);
    }

    @Override
    protected void onSizeChanged(final int w, final int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (!Display.isInitialized()) {
            return;
        }
        Display.getInstance().callSerially(new Runnable() {

            public void run() {
                cn1View.handleSizeChange(w, h);
            }
        });
    }


    public void flushGraphics(Rect rect) {
//        Canvas c = null;
//        try {
//            c = this.surfaceHolder.lockCanvas(rect);
//            if (c != null) {
//                this.onDraw(c);
//            }
//        } catch (Throwable e) {
//            Log.e("Codename One", "paint problem.", e);
//        } finally {
//            try {
//                if (c != null) {
//                    this.surfaceHolder.unlockCanvasAndPost(c);
//                }
//            } catch (Throwable t) {
//                // workaround for potential exception here
//                t.printStackTrace();
//            }
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        cn1View.buffy = new AndroidGraphics(androidImplementation, canvas, true);

    }


    public void flushGraphics() {
//        if (!created) {
//            return;
//        }
//        Canvas c = null;
//        try {
//            c = this.surfaceHolder.lockCanvas();
//            if (c != null) {
//                this.onDraw(c);
//            }
//        } catch (Throwable e) {
//            Log.e("Codename One", "paint problem.", e);
//        } finally {
//            if (c != null) {
//                this.surfaceHolder.unlockCanvasAndPost(c);
//            }
//        }
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (InPlaceEditView.isEditing()) {
            return true;
        }
        return cn1View.onKeyUpDown(true, keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (InPlaceEditView.isEditing()) {
            return true;
        }
        return cn1View.onKeyUpDown(false, keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return cn1View.onTouchEvent(event);
    }

    public AndroidGraphics getGraphics() {
        return cn1View.buffy;
    }

    public int getViewHeight() {
        return cn1View.height;
    }

    public int getViewWidth() {
        return cn1View.width;
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {

        if (!Display.isInitialized() || Display.getInstance().getCurrent() == null) {
            return super.onCreateInputConnection(editorInfo);
        }
        cn1View.setInputType(editorInfo);
        return super.onCreateInputConnection(editorInfo);
    }

    @Override
    public boolean onCheckIsTextEditor() {
        if (!Display.isInitialized() || Display.getInstance().getCurrent() == null) {
            return false;
        }
        Component txtCmp = Display.getInstance().getCurrent().getFocused();
        if (txtCmp != null && txtCmp instanceof TextField) {
            return true;
        }
        return false;
    }

    @Override
    public View getAndroidView() {
        return this;
    }


    public boolean alwaysRepaintAll() {
        return false;
    }
}