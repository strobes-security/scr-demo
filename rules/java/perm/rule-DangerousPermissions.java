// License: LGPL-3.0 License (c) find-sec-bugs
package perm;
import java.lang.reflect.ReflectPermission;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Policy;
import java.util.ArrayList;
import java.util.List;

public class DangerousPermissions extends Policy {
    public void danger(CodeSource cs) {
        PermissionCollection pc = super.getPermissions(cs);
        // ruleid: java_perm_rule-DangerousPermissions
        pc.add(new ReflectPermission("suppressAccessChecks"));
    }

    public void danger2(PermissionCollection pc) {
        // ruleid: java_perm_rule-DangerousPermissions
        pc.add(new RuntimePermission("createClassLoader"));
    }

    public void danger3(PermissionCollection pc) {
        // ruleid: java_perm_rule-DangerousPermissions
        RuntimePermission perm = new RuntimePermission("createClassLoader");
        pc.add(perm);
    }

    public void danger4(PermissionCollection pc) {
        // ruleid: java_perm_rule-DangerousPermissions
        ReflectPermission perm = new ReflectPermission("suppressAccessChecks");
        pc.add(perm);
    }

    public void ok(CodeSource cs) {
        ReflectPermission perm = new ReflectPermission("suppressAccessChecks");
        List<ReflectPermission> list = new ArrayList<>();
        list.add(perm);
    }
}
