// Copyright (c) 2009 The Chromium Authors. All rights reserved.
// This program and the accompanying materials are made available
// under the terms of the Eclipse Public License v1.0 which accompanies
// this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html

package org.eclipse.wst.jsdt.chromium.internal.v8native.protocol.input.data;

import org.eclipse.wst.jsdt.chromium.internal.protocolparser.JsonType;

@JsonType
public interface ContextData {
  long value();
  String type();
}
