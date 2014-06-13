package com.hundsun.ares.studio.ui.console;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.ui.ARESUI;

public class ARESConsoleFactory implements IConsoleFactory {

	public void openConsole() {
		   IConsole myConsole = getConsole();
		   IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		   String id = IConsoleConstants.ID_CONSOLE_VIEW;
		   IConsoleView view;
		try {
			view = (IConsoleView) page.showView(id);
			view.display(myConsole);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	public static MessageConsole getConsole() {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (ConsoleHelper.CONSOLE_ID.equals(existing[i].getName()))
				return (MessageConsole) existing[i];
		// no console found, so create a new one
		MessageConsole myConsole = ARESUI.getDefault().getConsole();

		conMan.addConsoles(new IConsole[] { myConsole });
		return myConsole;
	}
	
	public static void openARESConsole() {
		IConsole myConsole = getConsole();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		String id = IConsoleConstants.ID_CONSOLE_VIEW;
		IConsoleView view;
		try {
			view = (IConsoleView) page.showView(id, null, IWorkbenchPage.VIEW_CREATE);
			view.display(myConsole);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

}
