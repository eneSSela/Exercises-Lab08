package it.unibo.mvc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private static final int NUM_VIEWS = 3;

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args)
            throws ClassNotFoundException,
                   NoSuchMethodException,
                   InstantiationException,
                   IllegalAccessException,
                   InvocationTargetException {

        final DrawNumberImpl model = new DrawNumberImpl();
        final DrawNumberController controller = new DrawNumberControllerImpl(model);

        // Array di classi view da caricare
        final Class<?>[] viewClasses = {
            it.unibo.mvc.view.DrawNumberStandardOutputView.class,
            it.unibo.mvc.view.DrawNumberSwingView.class,
        };

        for (final Class<?> viewClass : viewClasses) {
            // Prendiamo il costruttore senza parametri
            final Constructor<?> ctor = viewClass.getConstructor();

            // Creiamo NUM_VIEWS istanze di ciascuna view
            for (int i = 0; i < NUM_VIEWS; i++) {
                final Object viewInstance = ctor.newInstance();
                if (viewInstance instanceof DrawNumberView view) {
                    controller.addView(view);
                } else {
                    throw new IllegalStateException(viewInstance.getClass()
                            + " non implementa DrawNumberView");
                }
            }
        }
    }
}
