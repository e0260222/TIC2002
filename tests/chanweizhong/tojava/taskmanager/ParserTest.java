package chanweizhong.tojava.taskmanager;

import org.junit.Test;
import org.junit.Assert;

/**
 *JUnit tests for Parser class
 */
public class ParserTest {

    @Test
    public void testGetCommandWord() {
        Assert.assertEquals("todo", Parser.getCommandWord("todo Eat breakfast"));
        Assert.assertEquals("", Parser.getCommandWord(""));
    }

    @Test
    public void testGetTaskIndex_validInput_success() throws TaskManagerException {
        Assert.assertEquals(5, Parser.getTaskIndex("done 5"));
        Assert.assertEquals(5, Parser.getTaskIndex("delete 5"));
        Assert.assertEquals(5, Parser.getTaskIndex("done delete 5"));
    }

    @Test
    public void testGetTaskIndex_invalidInput_exceptionThrown() {
        try {
            Assert.assertEquals(5, Parser.getTaskIndex("done F"));
            Assert.fail();
        } catch(TaskManagerException e) {
            Assert.assertEquals("Invalid task index", e.getMessage());
        }

        try {
            Assert.assertEquals("5", Parser.getTaskIndex("done"));
            Assert.fail();
        } catch (TaskManagerException e) {
            Assert.assertEquals("Empty task index", e.getMessage());
        }

        try {
            Assert.assertEquals("", Parser.getTaskIndex(""));
            Assert.fail();
        } catch (TaskManagerException e) {
            Assert.assertEquals("Empty task index", e.getMessage());
        }
    }

    @Test
    public void testGetSortType_validInput_success() throws TaskManagerException {
        Assert.assertEquals(1, Parser.getSortType("sort 1"));
        Assert.assertEquals(2, Parser.getSortType("sort 2"));
    }

    @Test
    public void testGetSortType_invalidInput_exceptionThrown() {
        try {
            Assert.assertEquals(5, Parser.getSortType("sort F"));
            Assert.fail();
        } catch(TaskManagerException e) {
            Assert.assertEquals("Invalid sort type", e.getMessage());
        }

        try {
            Assert.assertEquals("5", Parser.getSortType("sort"));
            Assert.fail();
        } catch (TaskManagerException e) {
            Assert.assertEquals("Empty sort type", e.getMessage());
        }

        try {
            Assert.assertEquals("", Parser.getSortType(""));
            Assert.fail();
        } catch (TaskManagerException e) {
            Assert.assertEquals("Empty sort type", e.getMessage());
        }
    }

    @Test
    public void testGetListType_validInput_success() throws TaskManagerException {
        Assert.assertEquals(1, Parser.getListType("list 1"));
        Assert.assertEquals(2, Parser.getListType("list 2"));
    }

    @Test
    public void testGetListType_invalidInput_exceptionThrown() {
        try {
            Assert.assertEquals(5, Parser.getListType("list F"));
            Assert.fail();
        } catch(TaskManagerException e) {
            Assert.assertEquals("Invalid list type", e.getMessage());
        }

        try {
            Assert.assertEquals("5", Parser.getListType("list"));
            Assert.fail();
        } catch (TaskManagerException e) {
            Assert.assertEquals("Empty list type", e.getMessage());
        }

        try {
            Assert.assertEquals("", Parser.getListType(""));
            Assert.fail();
        } catch (TaskManagerException e) {
            Assert.assertEquals("Empty list type", e.getMessage());
        }
    }

    @Test
    public void testCreateTodo() {
        try {
            Assert.assertEquals("", Parser.createTodo("todo"));
            Assert.fail();
        } catch(TaskManagerException e) {
            Assert.assertEquals("Empty description for TODO", e.getMessage());
        }
    }

    @Test
    public void testCreateDeadline() {
        try {
            Assert.assertEquals("", Parser.createDeadline("deadline"));
            Assert.fail();
        } catch(TaskManagerException e) {
            Assert.assertEquals("Empty description for DEADLINE", e.getMessage());
        }

        try {
            Assert.assertEquals("", Parser.createDeadline("deadline Eat lunch"));
            Assert.fail();
        } catch(TaskManagerException e) {
            Assert.assertEquals("Empty by for DEADLINE", e.getMessage());
        }
    }

}