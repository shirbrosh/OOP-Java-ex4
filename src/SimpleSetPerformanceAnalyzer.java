import java.util.*;

/**
 * A class that measures the run-times requested in the "Performance Analysis" section
 */
public class SimpleSetPerformanceAnalyzer {
    /**
     * A little bit of constants to start the day
     */
    private static final int NUM_LOOP = 70000;
    private static final int NUM_LOOP_LINKED = 7000;
    private static final int MILLISECONDS = 1000000;
    private static final int INDEX_OPEN = 0;
    private static final int INDEX_CLOSED = 1;
    private static final int INDEX_TREE = 2;
    private static final int INDEX_LINKED = 3;
    private static final int INDEX_HASH = 4;
    private static final int NUMBER_OF_DATA_STRUCTURES = 5;
    private static final String NAME_OPEN = "open hash set";
    private static final String NAME_CLOSE = "close hash set";
    private static final String NAME_TREE = "tree set";
    private static final String NAME_LINKED = "linked list set";
    private static final String NAME_HASH = "hash set";
    private static final String HI = "hi";
    private static final String REALLY_SMALL_NUM = "-13170890158";
    private static final String NUM_23 = "23";

    /**
     * The parameters I will use in the tests
     */
    private OpenHashSet openHashSet;
    private ClosedHashSet closeHashSet;
    private CollectionFacadeSet collectionFacadeSetTreeSet;
    private CollectionFacadeSet collectionFacadeSetLinkedList;
    private CollectionFacadeSet collectionFacadeSetHashSet;
    private String[] dataList1;
    private String[] dataList2;
    private TreeSet<String> tree;
    private LinkedList<String> linkedList;
    private HashSet <String> hashSet;
    private SimpleSet [] dataStructureArray;

    /**
     * A contractor for the SimpleSetPerformanceAnalyzer class
     */
    public SimpleSetPerformanceAnalyzer() {
        dataList1 = Ex4Utils.file2array("data1.txt");
        dataList2 = Ex4Utils.file2array("data2.txt");
        openHashSet = new OpenHashSet();
        closeHashSet = new ClosedHashSet();
        tree = new TreeSet<>();
        linkedList = new LinkedList<>();
        hashSet = new HashSet<>();
        collectionFacadeSetTreeSet = new CollectionFacadeSet(tree);
        collectionFacadeSetLinkedList = new CollectionFacadeSet(linkedList);
        collectionFacadeSetHashSet = new CollectionFacadeSet(hashSet);
        dataStructureArray = new SimpleSet[NUMBER_OF_DATA_STRUCTURES];
    }

    /**
     * A method that builds the data structure Array
     */
    public void buildDataStructureArray(){
        dataStructureArray[INDEX_OPEN] = openHashSet;
        dataStructureArray[INDEX_CLOSED] = closeHashSet;
        dataStructureArray[INDEX_TREE] =collectionFacadeSetTreeSet;
        dataStructureArray[INDEX_LINKED] = collectionFacadeSetLinkedList;
        dataStructureArray[INDEX_HASH] = collectionFacadeSetHashSet;
    }

    /**
     * A method that connect between the data structure we want to use and its name
     * @param simpleSetIndex- the data structure index in the array
     * @return the data structure's name
     */
    public String choseName(int simpleSetIndex ){
        if(simpleSetIndex == INDEX_OPEN){
            return NAME_OPEN;
        }
        if(simpleSetIndex == INDEX_CLOSED){
            return NAME_CLOSE;
        }
        if(simpleSetIndex == INDEX_TREE){
            return NAME_TREE;
        }
        if(simpleSetIndex == INDEX_LINKED){
            return NAME_LINKED;
        }
        return NAME_HASH;
    }

    /**
     * The run time test for the add method
     * @param data- the data array the add method will receive
     * @param simpleSetIndex - the index that indicates on which structure we want the method to test
     */
    public void addRunTime(String[] data, int simpleSetIndex) {
        String simpleSetName = choseName(simpleSetIndex);
        long timeBefore = System.nanoTime();
        for (String value : data) {
            dataStructureArray[simpleSetIndex].add(value);
        }
        long difference = System.nanoTime() - timeBefore;
        System.out.println("The "+ simpleSetName +" add function run time: "
                +difference/MILLISECONDS);
    }

    /**
     * The run time test for the contains method
     * @param toCheck the String value the contains method will receive
     * @param simpleSetIndex - the index that indicates on which structure we want the method to test
     */
    public void containsHiRunTime(String toCheck, int simpleSetIndex){
        int numLoop;
        String simpleSetName = choseName(simpleSetIndex);
        if (simpleSetIndex == INDEX_LINKED){
            numLoop = NUM_LOOP_LINKED;
        }
        else {
            numLoop = NUM_LOOP;
            for (int i = 0; i < numLoop; i++) {
                dataStructureArray[simpleSetIndex].contains(toCheck);
            }
        }
        long timeBefore = System.nanoTime();
        for (int i=0; i<numLoop; i++){
            dataStructureArray[simpleSetIndex].contains(toCheck);
        }
        long difference = System.nanoTime() - timeBefore;
        System.out.println("The "+ simpleSetName+" contains function for the value " +toCheck +
                " run time: " +difference/numLoop);
    }

    public static void main(String []args){
        //test for data 1:
        SimpleSetPerformanceAnalyzer test1 = new SimpleSetPerformanceAnalyzer();
        //test for data 2:
        SimpleSetPerformanceAnalyzer test2 = new SimpleSetPerformanceAnalyzer();

        //Builds the array of Data Structures:
        test1.buildDataStructureArray();
        test2.buildDataStructureArray();

        //*****************************//
        // For each function the code will run the test on each of the data structures, if you only want
        // to test some of them just type "//" before the ones you don't want to test:
        //*****************************//

        // for openHashSet chose index = INDEX_OPEN
        // for closeHashSet chose index = INDEX_CLOSED
        // for collectionFacadeSetTreeSet chose index = INDEX_TREE
        // for collectionFacadeSetLinkedList chose index = INDEX_LINKED
        // for collectionFacadeSetHashSet chose index = INDEX_HASH
        // The addRunTime function, if you only want to test some of them just type "//" before the ones
        // you don't want to test:

        //**************Add data1 tests***************//
        //add data1 to OpenHashSet
        test1.addRunTime(test1.dataList1 , INDEX_OPEN);
        //add data1 to CloseHashSet
        test1.addRunTime(test1.dataList1 , INDEX_CLOSED);
        //add data1 to TreeSet
        test1.addRunTime(test1.dataList1 , INDEX_TREE);
        //add data1 to LinkedList
        test1.addRunTime(test1.dataList1 , INDEX_LINKED);
        //add data1 to SetHashSet
        test1.addRunTime(test1.dataList1 , INDEX_HASH);


        //**************Add data2 tests***************//
        //add data2 to OpenHashSet
        test2.addRunTime(test2.dataList2 , INDEX_OPEN);
        //add data2 to CloseHashSet
        test2.addRunTime(test2.dataList2 , INDEX_CLOSED);
        //add data2 to TreeSet
        test2.addRunTime(test2.dataList2 , INDEX_TREE);
        //add data2 to LinkedList
        test2.addRunTime(test2.dataList2 , INDEX_LINKED);
        //add data2 to SetHashSet
        test2.addRunTime(test2.dataList2 , INDEX_HASH);

        //**************contains "hi" for data1***************//
        //contains "hi" in OpenHashSet initialized with data1
        test1.containsHiRunTime(HI,INDEX_OPEN);
        //contains "hi" in CloseHashSet initialized with data1
        test1.containsHiRunTime(HI,INDEX_CLOSED);
        //contains "hi" in TreeSet initialized with data1
        test1.containsHiRunTime(HI,INDEX_TREE);
        //contains "hi" in LinkedList initialized with data1
        test1.containsHiRunTime(HI,INDEX_LINKED);
        //contains "hi" in SetHashSet initialized with data1
        test1.containsHiRunTime(HI,INDEX_HASH);

        //**************contains data1 "-13170890158" ***************//
        //contains "-13170890158" in OpenHashSet initialized with data1
        test1.containsHiRunTime(REALLY_SMALL_NUM,INDEX_OPEN);
        //contains "-13170890158" in CloseHashSet initialized with data1
        test1.containsHiRunTime(REALLY_SMALL_NUM,INDEX_CLOSED);
        //contains "-13170890158" in TreeSet initialized with data1
        test1.containsHiRunTime(REALLY_SMALL_NUM,INDEX_TREE);
        //contains "-13170890158" in LinkedList initialized with data1
        test1.containsHiRunTime(REALLY_SMALL_NUM,INDEX_LINKED);
        //contains "-13170890158" in SetHashSet initialized with data1
        test1.containsHiRunTime(REALLY_SMALL_NUM,INDEX_HASH);

        //**************contains data2 "23" ***************//
        //contains "23" in OpenHashSet initialized with data2
        test2.containsHiRunTime(NUM_23,INDEX_OPEN);
        //contains "23" in CloseHashSet initialized with data2
        test2.containsHiRunTime(NUM_23,INDEX_CLOSED);
        //contains "23" in TreeSet initialized with data2
        test2.containsHiRunTime(NUM_23,INDEX_TREE);
        //contains "23" in LinkedList initialized with data2
        test2.containsHiRunTime(NUM_23,INDEX_LINKED);
        //contains "23" in SetHashSet initialized with data2
        test2.containsHiRunTime(NUM_23,INDEX_HASH);

        //**************contains "hi" for data2***************//
        //contains "hi" in OpenHashSet initialized with data2
        test2.containsHiRunTime(HI,INDEX_OPEN);
        //contains "hi" in CloseHashSet initialized with data2
        test2.containsHiRunTime(HI,INDEX_CLOSED);
        //contains "hi" in TreeSet initialized with data2
        test2.containsHiRunTime(HI,INDEX_TREE);
        //contains "hi" in LinkedList initialized with data2
        test2.containsHiRunTime(HI,INDEX_LINKED);
        //contains "hi" in SetHashSet initialized with data2
        test2.containsHiRunTime(HI,INDEX_HASH);
    }
}
