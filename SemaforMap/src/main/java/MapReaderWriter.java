/**
 * Created by yanggao on 7/3/17.
 */
import edu.cmu.cs.lti.ark.util.SerializedObjects;
import gnu.trove.THashMap;
import gnu.trove.THashSet;
import java.util.*;

public class MapReaderWriter {

    public static THashMap<String, THashSet<String>> readMap(String filePath){
        @SuppressWarnings("unchecked")
        THashMap<String, THashSet<String>> T = (THashMap<String, THashSet<String>>) SerializedObjects.readSerializedObject(filePath);
        return T;
    }

    public static void printKeyValue(THashMap<String, THashSet<String>> map){
        Set<String> keys = map.keySet();
        for (String s: keys){
            System.out.print("Key: "+s);
            THashSet<String> values = map.get(s);
            for (String v: values) {
                System.out.print("\n\t" + v);
            }
            System.out.println();
        }
    }

    public static Boolean add_elements(THashMap<String, THashSet<String>> map, String newKey, String[] elements){
        THashSet<String> eSet = new THashSet<String>();
        try {
            eSet.addAll(Arrays.asList(elements));
            map.put(newKey, eSet);
        } catch (Exception ex){
            ex.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public static void writeToMap(THashMap<String, THashSet<String>> map, String path){
        SerializedObjects.writeSerializedObject(map, path);
        System.out.print("\nDone\n");
    }
    public static void main(String[] args) {
        THashMap<String, THashSet<String>> frame = MapReaderWriter.readMap("/Users/yanggao/Documents/GitHub/SemaforMap/framenet.frame.element.map");
        THashMap<String, THashSet<String>> original = MapReaderWriter.readMap("/Users/yanggao/Documents/GitHub/SemaforMap/framenet.original.map");

        MapReaderWriter.printKeyValue(frame);
        System.out.println("############################################################");
        MapReaderWriter.printKeyValue(original);

        //creating a frame elements
        String elements[] = {
                "Action",
                "Issue",
                "Protester",
                "Side",
                "Degree",
                "Descriptor",
                "Duration",
                "Explanation",
                "Frequency",
                "Concessive",
                "Manner",
                "Means",
                "Particular_iteration",
                "Place",
                "Purpose",
                "Time"
        };
        Boolean status = MapReaderWriter.add_elements(frame, "Protest", elements);
        if (status == Boolean.TRUE){
            System.out.println("Successfully add elements");
        }
        else{
            System.out.println("Failed to add elements");
        }

        MapReaderWriter.writeToMap(frame, "/Users/yanggao/Documents/GitHub/SemaforMap/output/framenet.frame.element.map");

        // create lexical units
        elements = new String[]{
                "protests_NNS",
                "protest_NN",
                "protest_VB",
                "protested_VBD",
                "protesting_VBG",
                "protested_VBN",
                "protester_NN",
                "protesters_NNS",
                "protestor_NN",
                "protestors_NNS",
                "demostration_NN",
                "demonstrations_NNS",
                "demonstrator_NN",
                "demonstrators_NNS"
        };
        status = MapReaderWriter.add_elements(original, "Protest", elements);
        if (status == Boolean.TRUE){
            System.out.println("Successfully add lexical units");
        }
        else{
            System.out.println("Failed to add lexical units");
        }
        MapReaderWriter.writeToMap(original, "/Users/yanggao/Documents/GitHub/SemaforMap/output/framenet.original.map");

        // check map
        frame = MapReaderWriter.readMap("/Users/yanggao/Documents/GitHub/SemaforMap/output/framenet.frame.element.map");
        original = MapReaderWriter.readMap("/Users/yanggao/Documents/GitHub/SemaforMap/output/framenet.original.map");

        MapReaderWriter.printKeyValue(frame);
        System.out.println("############################################################");
        MapReaderWriter.printKeyValue(original);


    }

}
