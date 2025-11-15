package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static final String ALICE = "Alice";
    private static final String BOB = "Bob";
    private static final int ZERO = 0;
    private static final int NEGATIVE_NUMBER = -1;

    @Test
    void testInvalidNegativeRule() {
        final DeathNote note = new DeathNoteImpl();
        try {
            note.getRule(ZERO);
            fail("Invalid Rule...\n");
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
            assertFalse(e.getMessage().isBlank());
        }

        try {
            note.getRule(NEGATIVE_NUMBER);
            fail("Invaild Rule...\n");
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
            assertFalse(e.getMessage().isBlank());
        }
    }

    @Test
    void testRuleNullOrEmpty() {
        final DeathNote notes = new DeathNoteImpl();
        for(int i = 1; i <= DeathNote.RULES.size(); i++) {
            final String result = notes.getRule(i);
            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertFalse(result.isBlank());
        }
    }

    @Test
    void testNameWritten() {
        final DeathNote notes = new DeathNoteImpl();
        assertFalse(notes.isNameWritten(ALICE));
        notes.writeName(ALICE);
        assertTrue(notes.isNameWritten(ALICE));
        assertFalse(notes.isNameWritten(BOB));
        assertFalse(notes.isNameWritten(""));
    }
}