package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawResult;

/**
 * This class implements a view that can write on any PrintStream.
 */
public final class DrawNumberStandardOutputView implements DrawNumberView {

    /**
     * Builds a new {@link DrawNumberStandardOutputView}.
     */
    public DrawNumberStandardOutputView() {
        /*
         * Constructor present to comply with Java's requirement of documenting the default constructor.
         */
    }

    @Override
    public void setController(final DrawNumberController observer) {
        /*
         * This view does not handle input, so we do not store the controller.
         */
    }

    @Override
    public void start() {
        /*
         * Nothing to initialize for console output; ready to print results.
         */
    }

    @Override
    public void result(final DrawResult res) {
        /*
         * Print the result description to the standard output.
         */
        System.out.println(res.getDescription()); //NOPMD
    }
}
