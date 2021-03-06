package pl.net.bluesoft.rnd.processtool.ui.activity;

import org.aperteworkflow.util.liferay.LiferayBridge;
import pl.net.bluesoft.rnd.processtool.ProcessToolContext;
import pl.net.bluesoft.rnd.processtool.bpm.ProcessToolBpmSession;
import pl.net.bluesoft.rnd.processtool.model.ProcessInstance;
import pl.net.bluesoft.rnd.processtool.model.UserData;

/**
 * User: POlszewski
 * Date: 2011-09-06
 * Time: 10:44:16
 */
public class OtherUserProcessesListPane extends MyProcessesListPane {
    private final UserData userData;
    private ProcessToolBpmSession bmpSession;

    public OtherUserProcessesListPane(ActivityMainPane activityMainPane, String title, UserData userData) {
		super(activityMainPane, title, false);
		this.userData = userData;
        init(title);
	}

    @Override
    protected void displayProcessData(ProcessInstance processInstance) {
        activityMainPane.displayProcessData(processInstance, getBpmSession());
    }

    @Override
    protected ProcessToolBpmSession getBpmSession() {
        if (bmpSession == null) {
            ProcessToolContext ctx = ProcessToolContext.Util.getThreadProcessToolContext();
            bmpSession = activityMainPane.getBpmSession().createSession(userData, LiferayBridge.getUserRoles(userData), ctx);
        }
        return bmpSession;
    }
}
