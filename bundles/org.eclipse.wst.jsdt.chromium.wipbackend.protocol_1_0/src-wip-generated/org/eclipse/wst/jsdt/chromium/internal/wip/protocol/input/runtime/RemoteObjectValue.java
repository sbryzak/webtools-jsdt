// Generated source.
// Generator: org.eclipse.wst.jsdt.chromium.internal.wip.tools.protocolgenerator.Generator
// Origin: http://svn.webkit.org/repository/webkit/trunk/Source/WebCore/inspector/Inspector.json@102140

package org.eclipse.wst.jsdt.chromium.internal.wip.protocol.input.runtime;

/**
 Mirror object referencing original JavaScript object.
 */
@org.eclipse.wst.jsdt.chromium.internal.protocolparser.JsonType
public interface RemoteObjectValue {
  /**
   Object type.
   */
  Type type();

  /**
   Object subtype hint. Specified for <code>object</code> type values only.
   */
  @org.eclipse.wst.jsdt.chromium.internal.protocolparser.JsonOptionalField
  Subtype subtype();

  /**
   Object class (constructor) name. Specified for <code>object</code> type values only.
   */
  @org.eclipse.wst.jsdt.chromium.internal.protocolparser.JsonOptionalField
  String className();

  /**
   Remote object value (in case of primitive values or JSON values if it was requested).
   */
  @org.eclipse.wst.jsdt.chromium.internal.protocolparser.JsonOptionalField
  @org.eclipse.wst.jsdt.chromium.internal.protocolparser.JsonNullable
  Object value();

  /**
   String representation of the object.
   */
  @org.eclipse.wst.jsdt.chromium.internal.protocolparser.JsonOptionalField
  String description();

  /**
   Unique object identifier (for non-primitive values).
   */
  @org.eclipse.wst.jsdt.chromium.internal.protocolparser.JsonOptionalField
  String/*See org.eclipse.wst.jsdt.chromium.internal.wip.protocol.common.runtime.RemoteObjectIdTypedef*/ objectId();

  /**
   Object type.
   */
  public enum Type {
    OBJECT,
    FUNCTION,
    UNDEFINED,
    STRING,
    NUMBER,
    BOOLEAN,
  }
  /**
   Object subtype hint. Specified for <code>object</code> type values only.
   */
  public enum Subtype {
    ARRAY,
    NULL,
    NODE,
    REGEXP,
    DATE,
  }
}
