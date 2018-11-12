package chanweizhong.tojava.taskmanager;

import java.util.Comparator;

/**
 * The <code>SortByDateBy</code> class implements the Comparator interface.
 * It contains the compare method that sorts <code>Task</code> objects
 * based on by date.
 */
class SortByDateBy implements Comparator<Task> {

    public int compare(Task a, Task b) {

        if (a instanceof Deadline && !(b instanceof Deadline)) {
            return -1;
        } else if (!(a instanceof Deadline) && b instanceof Deadline) {
            return 1;
        } else if (!(a instanceof Deadline) && !(b instanceof Deadline)) {
            if (a.date.compareTo(b.date) > 0) {
                return 1;
            } else if (a.date.compareTo(b.date) < 0) {
                return -1;
            } else {
                return 0;
            }
        } else if (a instanceof Deadline && b instanceof Deadline) {
            if (((Deadline)a).by.compareTo(((Deadline)b).by) > 0) {
                return 1;
            } else if (((Deadline)a).by.compareTo(((Deadline)b).by) < 0) {
                return -1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

}