package com.codename1.impl;

import com.codename1.ui.geom.Rectangle;

/**
 * Created by san on 5/29/16.
 */
public interface ClipImplementation {

    Rectangle getClipRect(Object graphics);

    /**
     * Returns the clipping coordinate
     *
     * @param graphics the graphics context
     * @return the clipping coordinate
     */
    int getClipX(Object graphics);

    /**
     * Returns the clipping coordinate
     *
     * @param graphics the graphics context
     * @return the clipping coordinate
     */
    int getClipY(Object graphics);

    /**
     * Returns the clipping coordinate
     *
     * @param graphics the graphics context
     * @return the clipping coordinate
     */
    int getClipWidth(Object graphics);

    /**
     * Returns the clipping coordinate
     *
     * @param graphics the graphics context
     * @return the clipping coordinate
     */
    int getClipHeight(Object graphics);

    void setClipRect(Object graphics, Rectangle rect);

    /**
     * Installs a new clipping rectangle
     *
     * @param graphics the graphics context
     * @param x coordinate
     * @param y coordinate
     * @param width size
     * @param height size
     * @param rect rectangle representing the new clipping area
     */
    void setClip(Object graphics, int x, int y, int width, int height);

    void clipRect(Object graphics, Rectangle rect);

    /**
     * Changes the current clipping rectangle to subset the current clipping with
     * the given clipping.
     *
     * @param graphics the graphics context
     * @param x coordinate
     * @param y coordinate
     * @param width size
     * @param height size
     * @param rect rectangle representing the new clipping area
     */
    void clipRect(Object graphics, int x, int y, int width, int height);

    void pushClip(Object graphics);

    void popClip(Object graphics);
}
