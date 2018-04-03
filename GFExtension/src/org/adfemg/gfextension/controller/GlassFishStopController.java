package org.adfemg.gfextension.controller;

import java.io.IOException;

import oracle.ide.Context;
import oracle.ide.config.Preferences;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;
import oracle.ide.log.LogManager;
import oracle.ide.log.LogPage;

import org.adfemg.gfextension.data.GlassFishDataStructure;


/**
 * Controller for action org.adfemg.gfextension.glassfishstop.
 */
public final class GlassFishStopController implements Controller {

    /**
     * Determines when the action is enabled. In this case, it is always enabled.
     * @param action whose command is to be executed.
     * @param context the current context
     * @return true so no other controllers should check the satus for this action
     */
    @Override
    public boolean update(IdeAction action, Context context) {
        return true;
    }

    /**
     * Handles the user interaction with this action. In this case it calls the Glassfish stop command
     * @param action whose command is to be executed.
     * @param context the current context
     * @return true because this controller does handle the action.
     */
    @Override
    public boolean handleEvent(IdeAction action, Context context) {
        Preferences pr = Preferences.getPreferences();
        GlassFishDataStructure tc = GlassFishDataStructure.getInstance(pr);

        Process p;
        try {
            p = Runtime.getRuntime().exec(tc.getOSCommand() + " " + tc.getGlassfishStop());
            LogPage msgPage = LogManager.getLogManager().getMsgPage();
            msgPage.log("Glassfish Stopped");
            msgPage.log("\n");
        } catch (IOException e) {
            LogPage msgPage = LogManager.getLogManager().getMsgPage();
            msgPage.log(e);
            msgPage.log("\n");
            System.out.println(e);
        }

        return true;
    }
}
