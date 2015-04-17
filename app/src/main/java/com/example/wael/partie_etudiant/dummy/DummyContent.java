package com.example.wael.partie_etudiant.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        addItem(new DummyItem("1", "Test menu 1"));
        addItem(new DummyItem("2", "Test menu 2"));
        addItem(new DummyItem("3", "Test menu 3"));
        addItem(new DummyItem("4", "Test menu 4"));
        addItem(new DummyItem("5", "Test menu 5"));
        addItem(new DummyItem("6", "Test menu 6"));
        addItem(new DummyItem("7", "Test menu 7"));
        addItem(new DummyItem("8", "Test menu 8"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /*
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;
        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
