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
    public void testGetTaskIndex() {
        try {
            Assert.assertEquals(5, Parser.getTaskIndex("done 5"));
            Assert.assertEquals(5, Parser.getTaskIndex("delete 5"));
            Assert.assertEquals(5, Parser.getTaskIndex("done delete 5"));
            Assert.assertEquals(5, Parser.getTaskIndex("done F"));
            Assert.assertEquals(5, Parser.getTaskIndex("done"));
            Assert.fail();
        } catch(TaskManagerException e) {
            Assert.assertEquals("Invalid index", e.getMessage());
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