import directivePermission from "./permission";
import directiveConfirm from "./confirm";
// 全局指令
export default function directives(app) {
  directivePermission(app);
  directiveConfirm(app);
}
