/*******************************************************************************
 * Copyright (c) 2016 IBM Corporation. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * 	Contributors:
 * 	    IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.jsdt.js.node.runtime;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.wst.jsdt.core.runtime.IJSRuntimeInstall;
import org.eclipse.wst.jsdt.core.runtime.IJSRuntimeInstallProvider;
import org.eclipse.wst.jsdt.core.runtime.IJSRuntimeType;

/**
 * @author "Adalberto Lopez Venegas (adalbert)"
 */
public class NodeJSRuntimeProvider implements IJSRuntimeInstallProvider {
	@Override
	public Collection<IJSRuntimeInstall> getJSRuntimeInstallContributions(IJSRuntimeType runtimeType) {
		ArrayList<IJSRuntimeInstall> array = new ArrayList<IJSRuntimeInstall>();
		IJSRuntimeInstall contributedRuntimeInstall = runtimeType.createRuntimeInstall("GlobalNodeJsRuntime");
		contributedRuntimeInstall.setName ("Global Node.js");
		array.add(contributedRuntimeInstall);
		return array;
	}

}
