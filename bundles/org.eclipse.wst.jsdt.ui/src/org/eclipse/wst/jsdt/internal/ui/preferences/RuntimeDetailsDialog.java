/*******************************************************************************
 * Copyright (c) 2006, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.jsdt.internal.ui.preferences;


import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.jsdt.core.runtime.IJSRuntimeInstall;
import org.eclipse.wst.jsdt.internal.ui.JavaScriptPlugin;

/**
 * Displays details of a runtime install (read only, for contributed runtimes).
 * 
 */
@SuppressWarnings("restriction")
public class RuntimeDetailsDialog extends Dialog {
	
	private IJSRuntimeInstall fRuntime;
		
	public RuntimeDetailsDialog(Shell shell, IJSRuntimeInstall vm) {
		super(shell);
		setShellStyle(getShellStyle() | SWT.RESIZE);		
		fRuntime= vm;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(PreferencesMessages.RuntimeDetailsDialog_RuntimeDetails_Title);
	}		
			
	@Override
	protected Control createDialogArea(Composite ancestor) {
		Composite parent = (Composite)super.createDialogArea(ancestor);
		GridLayout layout = new GridLayout(2, false);
		layout.makeColumnsEqualWidth = false;
		parent.setLayout(layout);
		
		// type
		createLabel(parent, PreferencesMessages.RuntimeDetailsDialog_TypeField_Label);
		createLabel(parent, fRuntime.getRuntimeType().getName());

		// name
		createLabel(parent, PreferencesMessages.RuntimeDetailsDialog_NameField_Label);
		createLabel(parent, fRuntime.getName());
		
		// home
		createLabel(parent, PreferencesMessages.RuntimeDetailsDialog_LocationField_Label);
		String home = fRuntime.getInstallLocation().exists() ? 
					fRuntime.getInstallLocation().getAbsolutePath() :
					PreferencesMessages.GlobalCommand_SystemPath_Label;
		createLabel(parent, home);
		
		// Args
		SWTFactory.createLabel(parent, PreferencesMessages.RuntimeDetailsDialog_ArgumentsField_Label, 1);
		String text = fRuntime.getJSRuntimeArgumentsAsString();
		if (text == null) {
			text = ""; //$NON-NLS-1$
		}
		Text argText = SWTFactory.createSingleText(parent, 1);
		argText.setText(text);
		GridData gd = (GridData) argText.getLayoutData(); 
		gd.widthHint = 300;
		
		applyDialogFont(parent);
		return parent;
	}
	
	private Label createLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(text);
		return label;
	}
	
	/**
	 * Returns the name of the section that this dialog stores its settings in
	 * 
	 * @return String
	 */
	protected String getDialogSettingsSectionName() {
		return "RUNTIME_DETAILS_DIALOG_SECTION"; //$NON-NLS-1$
	}
	
    @Override
	protected IDialogSettings getDialogBoundsSettings() {
    	 IDialogSettings settings = JavaScriptPlugin.getDefault().getDialogSettings();
         IDialogSettings section = settings.getSection(getDialogSettingsSectionName());
         if (section == null) {
             section = settings.addNewSection(getDialogSettingsSectionName());
         } 
         return section;
    }
    
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
	}    
}
