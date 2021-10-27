/*
 * @(#) View.java
 *
 */
package view;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

import model.graph.GNode;
import model.graph.builder.GModelBuilder;
import model.graph.provider.GLabelProvider;
import model.graph.provider.GNodeContentProvider;

public class SimpleZestViewer {
   private GraphViewer        gViewer;
   private int                layout        = 1;
   public static final String SIMPLEVIEW_ID = "project-ex-1028-zest-type-pathirana.partdescriptor.simplezestview-1028pathirana";
   public final static String POPUPMENU = "project-ex-1028-zest-type-pathirana.popupmenu.0";
   private StyledText         styledText;
   @Inject
   EPartService               service;

   @PostConstruct
   public void createControls(Composite parent, EMenuService menuService) {
      gViewer = new GraphViewer(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
      styledText = new StyledText(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
      menuService.registerContextMenu(styledText, POPUPMENU);

   }
   
   public void update() throws IOException
   {
      gViewer.setContentProvider(new GNodeContentProvider());
      gViewer.setLabelProvider(new GLabelProvider());
      gViewer.setInput(GModelBuilder.instance().build().getNodes());
      gViewer.setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
      gViewer.applyLayout();
   }

   public void setLayoutManager() {
      switch (layout) {
      case 1:
         gViewer.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
         layout++;
         break;
      case 2:
         gViewer.setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
         layout = 1;
         break;
      }
   }

   @PreDestroy
   public void dispose() {
   }

   @Focus
   public void setFocus() {
      this.gViewer.getGraphControl().setFocus();
   }

   public GraphViewer getGraphViewer() {
      return this.gViewer;
   }
}
