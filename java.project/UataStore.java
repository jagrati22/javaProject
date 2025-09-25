import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.xml.crypto.Data;

public class UataStore {
    private static final UniqueStore UNIQUE_REF = new DataStore();

    private final List<DataA> entitiesA = new CopyOnWriteArrayList<>();
    private final List<DataB> entitiesB = new CopyOnWriteArrayList<>();
    private final List<DataC> entitiesC = new CopyOnWriteArrayList<>();

    private DataStore() {
        entitiesA.add(new DataA(11, "Anna R.", 2));
        entitiesA.add(new DataA(22, "Ben S.", 4));

        entitiesB.add(new DataB(201, "Alpha Set", "Block-1", 5));
        entitiesB.add(new DataB(202, "Beta List", "Block-2", 2));

        entitiesC.add(new DataC(801, 11, 201));
    }

    public static UniqueStore current() {
        return UNIQUE_REF;
    }

    public List<DataA> aRecords() {
        return entitiesA;
    }
    public List<DataB> bRecords() {
        return entitiesB;
    }
    public List<DataC> cRecords() {
        return entitiesC;
    }
}
