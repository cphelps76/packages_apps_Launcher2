/*
 * Copyright (C) 2014 Matricom
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

package com.android.launcher2.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.launcher2.LauncherApplication;
import com.android.launcher2.Workspace;
import com.android.launcher2.AppsCustomizePagedView;

import java.util.Map;

public final class PreferencesProvider {
    public static final String PREFERENCES_KEY = "com.android.launcher_preferences";

    public static final String PREFERENCES_CHANGED = "preferences_changed";

    private static Map<String, ?> sKeyValues;

    public static void load(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_KEY, 0);
        sKeyValues = preferences.getAll();
    }

    private static int getInt(String key, int def) {
        return sKeyValues.containsKey(key) && sKeyValues.get(key) instanceof Integer ?
                (Integer) sKeyValues.get(key) : def;
    }

    private static boolean getBoolean(String key, boolean def) {
        return sKeyValues.containsKey(key) && sKeyValues.get(key) instanceof Boolean ?
                (Boolean) sKeyValues.get(key) : def;
    }

    private static String getString(String key, String def) {
        return sKeyValues.containsKey(key) && sKeyValues.get(key) instanceof String ?
                (String) sKeyValues.get(key) : def;
    }

    public static class Interface {
        public static class Homescreen {
            public static int getNumberHomescreens() {
                return getInt("ui_homescreen_screens", 5);
            }
            public static int getDefaultHomescreen(int def) {
                return getInt("ui_homescreen_default_screen", def + 1) - 1;
            }
            public static int getCellCountX(int def) {
                String[] values = getString("ui_homescreen_grid", "0|" + def).split("\\|");
                try {
                    return Integer.parseInt(values[1]);
                } catch (NumberFormatException e) {
                    return def;
                }
            }
            public static int getCellCountY(int def) {
                String[] values = getString("ui_homescreen_grid", def + "|0").split("\\|");;
                try {
                    return Integer.parseInt(values[0]);
                } catch (NumberFormatException e) {
                    return def;
                }
            }
            public static boolean getShowSearchBar() {
                return getBoolean("ui_homescreen_general_search", true);
            }
            public static boolean getResizeAnyWidget() {
                return getBoolean("ui_homescreen_general_resize_any_widget", false);
            }
            public static class Scrolling {
                public static boolean getScrollWallpaper() {
                    return getBoolean("ui_homescreen_scrolling_scroll_wallpaper", true);
                }
            }
            public static class Indicator {
                public static boolean getShowScrollingIndicator() {
                    return getBoolean("ui_homescreen_indicator_enable", true);
                }
            }
        }

        public static class Drawer {
            public static class Widgets {
                public static boolean getJoinWidgetsApps() {
                    return getBoolean("ui_drawer_widgets_join_apps", true);
                }
            }
            public static class Indicator {
                public static boolean getShowScrollingIndicator() {
                    return getBoolean("ui_drawer_indicator_enable", true);
                }
                public static boolean getFadeScrollingIndicator() {
                    return getBoolean("ui_drawer_indicator_fade", true);
                }
            }
        }

        public static class Dock {
            public static boolean getShowDivider() {
                return getBoolean("ui_dock_divider", true);
            }
        }

        public static class Icons {
            public static boolean getHideIconLabels() {
                return getBoolean("ui_icons_hide_icon_labels", false);
            }
        }
    }

    public static class Application {

    }
}
