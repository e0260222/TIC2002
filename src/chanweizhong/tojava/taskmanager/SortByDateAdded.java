package chanweizhong.tojava.taskmanager;

import java.util.Comparator;

/**
 * The <code>SortByDateAdded</code> class implements the Comparator interface.
 * It contains the compare method that sorts <code>Task</code> objects
 * based on added date.
 */
class SortByDateAdded implements Comparator<Task> {

    public int compare(Task a, Task b) {
        if(a.date.compareTo(b.date) > 0) {
            return 1;
        } else if (a.date.compareTo(b.date) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

}