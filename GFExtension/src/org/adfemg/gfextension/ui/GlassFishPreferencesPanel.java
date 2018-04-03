package org.adfemg.gfextension.ui;

import org.adfemg.gfextension.data.GlassFishDataStructure;

import javax.swing.JTextField;

import oracle.ide.panels.DefaultTraversablePanel;
import oracle.ide.panels.TraversableContext;

import oracle.javatools.ui.layout.FieldLayoutBuilder;


/**
 * GlassFish Preferences preference page implementation.
 */
final class GlassFishPreferencesPanel extends DefaultTraversablePanel {

    @SuppressWarnings("compatibility:2232167150101298771")
    private static final long serialVersionUID = 1L;

    /** Textfield for the Glassfish home directory*/
    private final JTextField home = new JTextField();

    /** Textfield for the Glassfish start command*/
    private final JTextField start = new JTextField();

    /** Textfield for the Glassfish stop command*/
    private final JTextField stop = new JTextField();

    /** Textfield for the Glassfish debug command*/
    private final JTextField dbg = new JTextField();

    /** Textfield for the Glassfish admin console URL*/
    private final JTextField admin = new JTextField();

    /**
     * Creates the preferences panel, sets the layout and adds labels to the textfields
     */
    public GlassFishPreferencesPanel() {
        FieldLayoutBuilder b = new FieldLayoutBuilder(this);

        b.addHintText("Fill the complete path to the files that will start/stop/debug your GlassFish instance.");
        b.add(b.field().label().withText("&Glassfish Home Directory:").component(home));
        b.add(b.field().label().withText("&Start Glassfish Command:").component(start));
        b.add(b.field().label().withText("&Stop Glassfish Command:").component(stop));
        b.add(b.field().label().withText("&Start Glassfish in Dubug Mode Command:").component(dbg));
        b.add(b.field().label().withText("&Glassfish Admin URL:").component(admin));
        b.addVerticalSpring();
    }

    /**
     * Populates the controls in Glassfish Preferences from the data model
     * @param context
     */
    @Override
    public void onEntry(TraversableContext context) {
        final GlassFishDataStructure data = getGlassFishDataStructure(context);
        home.setText(data.getGlassfishHome());
        start.setText(data.getGlassfishStart());
        stop.setText(data.getGlassfishStop());
        dbg.setText(data.getGlassfishDb());
        admin.setText(data.getGlassfishAdmin());
    }

    /**
     * Populates the data model from the controls in Glassfish Preferences
     * @param context
     */
    @Override
    public void onExit(TraversableContext context) {
        final GlassFishDataStructure data = getGlassFishDataStructure(context);
        data.setGlassfishHome(home.getText());
        data.setGlassfishStart(start.getText());
        data.setGlassfishStop(stop.getText());
        data.setGlassfishDb(dbg.getText());
        data.setGlassfishAdmin(admin.getText());
    }

    private static GlassFishDataStructure getGlassFishDataStructure(TraversableContext tc) {
        return GlassFishDataStructure.getInstance(tc.getPropertyStorage());
    }
}
