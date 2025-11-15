package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {

    private static final int DEATH_CAUSE_TIME = 40;
    private static final int DEATH_DETAILS_TIME = 6000 + DEATH_CAUSE_TIME;

    private final Map<String, Death> deathHumans = new HashMap<>();
    private String lastNameWritten;

    /**
     * Returns the rule associated with the given number.
     *
     * @param ruleNumber the number of the rule to retrieve (starting from 1)
     * @return the selected rule
     * @throws IllegalArgumentException if the index is out of range
     */
    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("Invalid Rule Number : " + ruleNumber);
        }
        return RULES.get(ruleNumber - 1);
    }

    /**
     * Writes a name inside the notebook.
     * If the name was never written before, a new entry is created.
     *
     * @param name the name to write
     * @throws IllegalArgumentException if the name is null
     */
    @Override
    public void writeName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name is null");
        }
        if (!deathHumans.containsKey(name)) {
            deathHumans.put(name, new Death(System.currentTimeMillis()));
        }
        lastNameWritten = name;
    }

    /**
     * Writes the cause of death for the most recently written name.
     * The cause can only be written within 40 milliseconds.
     *
     * @param cause the cause of death to write
     * @return true if the cause was written successfully, false if time is expired
     * @throws IllegalArgumentException if cause is null
     * @throws IllegalStateException if no name was written yet
     */
    @Override
    public boolean writeDeathCause(final String cause) {
        if (cause == null) {
            throw new IllegalArgumentException("Death cause is null");
        }
        if (lastNameWritten == null) {
            throw new IllegalStateException("No name written yet");
        }

        final Death d = deathHumans.get(lastNameWritten);
        final long currentTime = System.currentTimeMillis();

        if (currentTime - d.timeWritten <= DEATH_CAUSE_TIME) {
            d.cause = cause;
            d.causeTime = currentTime;
            return true;
        }
        return false;
    }

    /**
     * Writes additional details for the most recently updated death entry.
     * Details can only be written within 6040 milliseconds after the cause.
     *
     * @param details the details of the death
     * @return true if the details were stored, false otherwise
     * @throws IllegalArgumentException if details is null
     * @throws IllegalStateException if no name was written yet
     */
    @Override
    public boolean writeDetails(final String details) {
        if (details == null) {
            throw new IllegalArgumentException("Details is null");
        }
        if (lastNameWritten == null) {
            throw new IllegalStateException("There's no name in DeathNote");
        }

        final Death d = deathHumans.get(lastNameWritten);
        final long currentTime = System.currentTimeMillis();

        if (currentTime - d.causeTime <= DEATH_DETAILS_TIME) {
            d.details = details;
            return true;
        }
        return false;
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }
    
    private static final class Death {
        
        private final long timeWritten;
        private long causeTime;
        private String cause;
        private String details;

        /**
         * Creates a new entry with default values.
         *
         * @param timeWritten the timestamp when the name was written
         */
        Death(final long timeWritten) {
            this.timeWritten = timeWritten;
            this.causeTime = timeWritten;
            this.cause = "heart attack";
            this.details = "";
        }
    }
}
