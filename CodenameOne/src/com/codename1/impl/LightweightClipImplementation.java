package com.codename1.impl;

import com.codename1.ui.geom.Rectangle;

/**
 * Created by san on 5/29/16.
 */
public class LightweightClipImplementation implements ClipImplementation {

    Rectangle rect = new Rectangle();

    @Override
    public void clipRect(Object graphics, Rectangle rect) {
        Rectangle.intersection(this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight(), rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), this.rect);
        verify();
    }

    public LightweightClipImplementation(int x, int y, int width, int height) {
        this.rect.setBounds(x, y, width, height);
        verify();
    }

    @Override
    public Rectangle getClipRect(Object graphics) {
        return rect;
    }

    @Override
    public int getClipX(Object graphics) {
        return rect.getX();
    }

    @Override
    public int getClipY(Object graphics) {
        return rect.getY();
    }

    @Override
    public int getClipWidth(Object graphics) {
        return rect.getWidth();
    }

    @Override
    public int getClipHeight(Object graphics) {
        return rect.getHeight();
    }

    @Override
    public void setClipRect(Object graphics, Rectangle rect) {
        this.setClip(graphics, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        verify();
    }

    @Override
    public void setClip(Object graphics, int x, int y, int width, int height) {
        this.rect.setBounds(x, y, width, height);
        verify();
    }

    @Override
    public void clipRect(Object graphics, int x, int y, int width, int height) {
        verify();
        int oldHeight = this.rect.getHeight();
        int oldY = this.rect.getY();
        Rectangle.intersection(this.rect.getX(), this.rect.getY(), this.rect.getWidth(), oldHeight, x, y, width, height, this.rect);
        verify();
    }

    private void verify() {
        if (this.rect.getHeight() < 0) {
            this.rect.setHeight(0);
//            this.rect.setY(this.rect.getY() + this.rect.getHeight());
//            this.rect.setHeight(-this.rect.getHeight());
        }
        if (this.rect.getWidth() < 0) {
            this.rect.setWidth(0);
//            this.rect.setX(this.rect.getX() + this.rect.getWidth());
//            this.rect.setWidth(-this.rect.getWidth());
        }
    }

    @Override
    public void pushClip(Object graphics) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void popClip(Object graphics) {
        throw new RuntimeException("Not implemented");
    }
}
