package org.adfemg.gfextension.controller;

import java.io.IOException;

import java.net.URL;

import oracle.ide.Context;
import oracle.ide.config.Preferences;
import oracle.ide.controller.Controller;
import oracle.ide.controller.IdeAction;
import oracle.ide.log.LogManager;
import oracle.ide.log.LogPage;
import oracle.ide.webbrowser.BrowserRunner;

import org.adfemg.gfextension.data.GlassFishDataStructure;


/**
 * Controller for action org.adfemg.gfextension.glassfishadminconsole.
 */
public final class GlassFishAdminConsoleController implements Controller {

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
     * Handles the user interaction with this action. In this case it calls the Glassfish admin console
     * @param action whose command is to be executed.
     * @param context the current context
     * @return true because this controller does handle the action.
     */
    @Override
    public boolean handleEvent(IdeAction action, Context context) {
        Preferences pr = Preferences.getPreferences();
        GlassFishDataStructure tc = GlassFishDataStructure.getInstance(pr);

        try {
            URL url = new URL(tc.getGlassfishAdmin());
            BrowserRunner.getBrowserRunner().runBrowserOnURL(url, null, null);
            LogPage msgPage = LogManager.getLogManager().getMsgPage();
            msgPage.log("Glassfish Administrator is Starting ");
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
