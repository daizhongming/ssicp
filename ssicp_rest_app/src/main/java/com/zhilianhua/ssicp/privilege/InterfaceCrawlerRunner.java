//package com.zhilianhua.ssicp.privilege;
//
//import com.zhilianhua.ssicp.utils.ReflectUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.aop.framework.AopProxyUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
//@Component
//@Slf4j
//public class InterfaceCrawlerRunner implements ApplicationRunner {
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Controller.class);
//        InterfaceVo interfaceVo = new InterfaceVo();
//        interfaceVo.setClasses(new ArrayList());
//        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
//            Class<?> valueClass = AopProxyUtils.ultimateTargetClass(entry.getValue());
//            String id = valueClass.getName();
//            Privilege annotation = valueClass.getAnnotation(Privilege.class);
//            InterfaceVo.Class aClass = new InterfaceVo.Class();
//            aClass.setId(id);
//            aClass.setTitle(Objects.nonNull(annotation) ? annotation.value() : id);
//            List<InterfaceVo.Group> groups = new ArrayList<>();
//            for (Method method : valueClass.getDeclaredMethods()) {
//                String childId = ReflectUtils.getInterfaceId(method);
//                InterfaceVo.Method aMethod = new InterfaceVo.Method();
//                aMethod.setId(childId);
//                Privilege childAnnotation = method.getAnnotation(Privilege.class);
//                if (Objects.nonNull(childAnnotation) && childAnnotation.group() != GroupEnum.PUBLIC) {
//                    aMethod.setTitle(Objects.nonNull(childAnnotation) ? childAnnotation.value() : childId);
//                    String groupName = Objects.nonNull(childAnnotation) ? childAnnotation.group().getGroupName() : GroupEnum.PUBLIC.getGroupName();
//                    groups.stream().filter(group -> group.getTitle().equals(groupName)).findFirst().orElseGet(() -> {
//                        InterfaceVo.Group group = new InterfaceVo.Group();
//                        group.setId(id + groupName);
//                        group.setTitle(groupName);
//                        group.setChildren(new ArrayList());
//                        groups.add(group);
//                        return group;
//                    }).getChildren().add(aMethod);
//                }
//            }
//            if (CollectionUtils.isNotEmpty(groups)) {
//                aClass.setChildren(groups);
//                interfaceVo.getClasses().add(aClass);
//            }
//        }
//        InterfacePrivilegesContainer.INTERFACEVO = interfaceVo;
//    }
//}
