/*
 * @(#) SimpleZestHandler.java
 *
 */
package handler;

import java.io.IOException;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import view.SimpleZestViewer;

public class SimpleZestViewHandler {
	   @Inject
	   EPartService service;

	   @Execute
	   public void execute() throws IOException {
	      System.out.println("SimpleZestHandler!!");
	      // Find a view.
	      MPart findPart = service.findPart(SimpleZestViewer.SIMPLEVIEW_ID);
	      SimpleZestViewer simpleView = (SimpleZestViewer) findPart.getObject();
	      simpleView.update();
	     
	   }
}