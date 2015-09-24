package com.xross.tools.xeda.editor.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.xross.tools.xeda.editor.commands.DeleteTransitionCommand;
import com.xross.tools.xeda.editor.model.MessageRoute;

public class MessageRouteEditPolicy extends ComponentEditPolicy{

    protected Command createDeleteCommand(GroupRequest deleteRequest) {
        return new DeleteTransitionCommand((MessageRoute)getHost().getModel());
    }
}
