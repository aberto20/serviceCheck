import akka.actor.Cancellable;

import play.Application;
import play.GlobalSettings;

import play.mvc.*;
import play.mvc.Http.*;
import play.libs.F.*;

import static controllers.Application.checkServices;

import static play.mvc.Results.*;

/**
 * Created by fyjfry on 1/24/16.
 */
public class Global extends GlobalSettings {

    private Cancellable scheduler;

    @Override
    public void onStart(Application application) {
        super.onStart(application);
        schedule();
    }

    @Override
    public void onStop(Application application) {
        //Stop the scheduler
        if (scheduler != null) {
            scheduler.cancel();
        }
    }

    private void schedule() {
        checkServices();
    }




}
