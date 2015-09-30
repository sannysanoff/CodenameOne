/*
 * Copyright (c) 2012, Codename One and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Codename One designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *  
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * 
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Please contact Codename One through http://www.codenameone.com/ if you 
 * need additional information or have any questions.
 */

package java.util;

/**
 *
 * @author Shai Almog
 */
public class Locale {
    private static Locale defaultLocale;
    private String language;
    private String country;
    public Locale(String language, String locale) {
        this.language = language;
        this.country = locale;
    }
    
    public Locale() {
        language = getOSLanguage();
        int pos;
        if (language != null && (pos = language.indexOf('-')) != -1) {
            country = language.substring(pos+1);
            language = language.substring(0, pos);
        }
        if (language != null && (pos = language.indexOf('_')) != -1) {
            country = language.substring(pos+1);
            language = language.substring(0, pos);
        }
        if (country == null) {
            country = "US";//getOSCountry();
        }
    }
    
    public static Locale getDefault() {
        if(defaultLocale == null) {
            defaultLocale = new Locale();
        }
        return defaultLocale;
    }
    
    public static void setDefault(Locale l) {
        defaultLocale = l;
    }
    
    public String getLanguage() {
        return language;
    }
        
    public String getCountry() {
        return country;
    }

    private static native String getOSLanguage();
    //private static native String getOSCountry();
}
