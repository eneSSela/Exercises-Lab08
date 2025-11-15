package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

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
}