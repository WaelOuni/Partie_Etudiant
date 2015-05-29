package rnu.iit.waelgroup.student.dummy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rnu.iit.waelgroup.student.Models.Course;

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
    public static ArrayList<Course> courses = new ArrayList<Course>() ;
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        /**
         * A map of sample (students) items, by ID.
         */

        //    Etudiant e1 = new Etudiant(14207208,"Ouni", "Wael",1, "homme", "waelounie@gmail.com", "+21699663587","bac", "1234567859");


/*
        *//**
         * A map of sample (Courses) items, by ID.
         *//*
        Course c1 = new Course(0, "Theoremes de pytaghore pour le triangle ", "theoreme tres connus pour le class de bacc", "http://math.bac.pythagore.json", "mathematique pour le bac ", "abdlbasst eben hamdouaaaaa", "2015,05,07");

        Course c2 = new Course(1, "calcul geometrique dans le plan (O,I,J)", "differentes régles pour le calcul de surfaces et le perimetre du formes geometrique ", "http://math.6emeSecond/calcGeo.json", "math 6éme primaire", "imen benn farhat", "2015,05,07");

        Course c3 = new Course(2, "manipuler entiers complexes", "definition de plan complexe (O, I, J, Z) et les regles de calcul complexes ", "http://math.bac/complexe.json", "mathematique pour le bac", "wael Ouni","2015,05,07");

        Course c4 = new Course(3, "formules trigonométriques", "les differents formules trigoometriques pour les calculs angulaires ", "http://math.2emeSecondaire/trigonoetrie.json", "math pour le 2eme secondaire", "hachem rmida ", "2015,05,07");

        Course c5 = new Course(0, "formes canoniques", "etudes pour les formes canoniques( elipse,spherique ...) dans l'espace ", "http://math.bac/formesCanoniques.json", "math pour le bacc", "Hamdi zinedin", "2015,05,07");

        Course c6 = new Course(1, "variables inconnues d'une equation", "la maniere de calcul les valeurs de variables inconuue dans une equation", "http://math.1ereSec/calculCarInconue.json", "math niveau 1ere Secondaire", "Omar jmal", "2015,05,07");

        courses.add(c1);
        courses.add(c2);
        courses.add(c3);
        courses.add(c4);

        courses.add(c5);
        courses.add(c6);*/
    }

    static {
        // Add 3 sample items.
        addItem(new DummyItem("1", "Item 1"));
        addItem(new DummyItem("2", "Item 2"));
        addItem(new DummyItem("3", "Item 3"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
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
