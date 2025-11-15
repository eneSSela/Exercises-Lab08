package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {

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
    public String getRule(int ruleNumber) {
        if(ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("Invalid Rule Number : " + ruleNumber);
        }
        return RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Name is null");
        }
        if(!deathHumans.containsKey(name)) {
            deathHumans.put(name, new Death(System.currentTimeMillis()));
        }
        lastNameWritten = name;
    }

    @Override
    public boolean writeDeathCause(String cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    public boolean writeDetails(String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
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
