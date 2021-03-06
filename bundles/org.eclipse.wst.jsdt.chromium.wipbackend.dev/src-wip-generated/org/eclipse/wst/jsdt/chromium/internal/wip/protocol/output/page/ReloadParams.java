// Generated source.
// Generator: org.eclipse.wst.jsdt.chromium.internal.wip.tools.protocolgenerator.Generator
// Origin: http://src.chromium.org/blink/trunk/Source/devtools/protocol.json@<unknown>

package org.eclipse.wst.jsdt.chromium.internal.wip.protocol.output.page;

/**
Reloads given page optionally ignoring the cache.
 */
public class ReloadParams extends org.eclipse.wst.jsdt.chromium.internal.wip.protocol.output.WipParams {
  /**
   @param ignoreCacheOpt If true, browser cache is ignored (as if the user pressed Shift+refresh).
   @param scriptToEvaluateOnLoadOpt If set, the script will be injected into all frames of the inspected page after reload.
   */
  public ReloadParams(Boolean ignoreCacheOpt, String scriptToEvaluateOnLoadOpt) {
    if (ignoreCacheOpt != null) {
      this.put("ignoreCache", ignoreCacheOpt);
    }
    if (scriptToEvaluateOnLoadOpt != null) {
      this.put("scriptToEvaluateOnLoad", scriptToEvaluateOnLoadOpt);
    }
  }

  public static final String METHOD_NAME = org.eclipse.wst.jsdt.chromium.internal.wip.protocol.BasicConstants.Domain.PAGE + ".reload";

  @Override protected String getRequestName() {
    return METHOD_NAME;
  }

}
