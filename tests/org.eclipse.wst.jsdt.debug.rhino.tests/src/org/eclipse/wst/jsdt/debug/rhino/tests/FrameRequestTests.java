/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.jsdt.debug.rhino.tests;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.jsdt.debug.internal.rhino.transport.DebugSession;
import org.eclipse.wst.jsdt.debug.internal.rhino.transport.DisconnectedException;
import org.eclipse.wst.jsdt.debug.internal.rhino.transport.EventPacket;
import org.eclipse.wst.jsdt.debug.internal.rhino.transport.JSONConstants;
import org.eclipse.wst.jsdt.debug.internal.rhino.transport.Request;
import org.eclipse.wst.jsdt.debug.internal.rhino.transport.Response;
import org.eclipse.wst.jsdt.debug.internal.rhino.transport.TimeoutException;
import org.eclipse.wst.jsdt.debug.rhino.tests.TestEventHandler.Subhandler;

/**
 * Variety of tests for requesting frame(s) / frame information
 * 
 * @since 1.1
 */
public class FrameRequestTests extends RequestTest {

	/**
	 * Tests getting a frame once suspended
	 * @throws Exception
	 */
	public void testFramesAndFrameLookup() throws Exception {
		eventHandler.addSubhandler(new SetBreakpointHandler(new int[] {6}));
		eventHandler.addSubhandler(new FrameCheckHandler());
		
		String script = Util.getTestSource(Util.SRC_SCRIPTS_CONTAINER, "script1.js"); //$NON-NLS-1$
		assertNotNull("The test source for [script1.js] must exist", script); //$NON-NLS-1$
		//script + breakpoint on line 6
		evalScript(script, 2);
	}
	
	/**
	 * Tests getting frames and running an evaluate
	 * @throws Exception
	 */
	public void testFramesAndFrameLookupAndEvaluate() throws Exception {
		eventHandler.addSubhandler(new SetBreakpointHandler(new int[] {6}));
		Subhandler frameCheckHandler = new Subhandler() {
			/* (non-Javadoc)
			 * @see org.eclipse.wst.jsdt.debug.rhino.tests.TestEventHandler.Subhandler#testName()
			 */
			public String testName() {
				return getName();
			}
			public boolean handleEvent(DebugSession debugSession, EventPacket event) {
				if (event.getEvent().equals(JSONConstants.BREAK)) {
					Number threadId = (Number) event.getBody().get(JSONConstants.THREAD_ID);
					Number contextId = (Number) event.getBody().get(JSONConstants.CONTEXT_ID);
					Request request = new Request(JSONConstants.FRAMES);
					request.getArguments().put(JSONConstants.THREAD_ID, threadId);
					try {
						debugSession.sendRequest(request);
						Response response = debugSession.receiveResponse(request.getSequence(), 10000);
						assertTrue(response.isSuccess());
						Collection frames = (Collection) response.getBody().get(JSONConstants.FRAMES);
						for (Iterator iterator = frames.iterator(); iterator.hasNext();) {
							Number frameId = (Number) iterator.next();
							request = new Request(JSONConstants.EVALUATE);
							request.getArguments().put(JSONConstants.THREAD_ID, threadId);
							request.getArguments().put(JSONConstants.CONTEXT_ID, contextId);
							request.getArguments().put(JSONConstants.FRAME_ID, frameId);
							request.getArguments().put(JSONConstants.EXPRESSION, "this"); //$NON-NLS-1$
							debugSession.sendRequest(request);
							response = debugSession.receiveResponse(request.getSequence(), 10000);
							assertTrue(response.isSuccess());
							assertTrue(response.getBody().containsKey(JSONConstants.EVALUATE));
						}
					} catch (DisconnectedException e) {
						e.printStackTrace();
					} catch (TimeoutException e) {
						e.printStackTrace();
					}
					return true;
				}
				return false;
			}
		};
		eventHandler.addSubhandler(frameCheckHandler);

		String script = Util.getTestSource(Util.SRC_SCRIPTS_CONTAINER, "script1.js"); //$NON-NLS-1$
		assertNotNull("The test source for [script1.js] must exist", script); //$NON-NLS-1$
		//script + breakpoint
		evalScript(script, 2);
	}
}
