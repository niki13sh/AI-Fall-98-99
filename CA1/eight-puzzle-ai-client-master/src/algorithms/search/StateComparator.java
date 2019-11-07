package algorithms.search;

import java.util.Comparator;

public class StateComparator implements Comparator<State> {

    @Override
    public int compare(State o1, State o2) {
        if (o1.f > o2.f)
            return 1;
        else if (o1.f < o2.f)
            return -1;
        else
            return 0;
    }
}
