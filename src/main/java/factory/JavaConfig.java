package factory;

import factory.Config;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

public class JavaConfig implements Config {

  @Getter
  private final Reflections scanner;
  private final Map<Class, Class> ifcMoreImplClass;

  public JavaConfig(String packageToScan, Map<Class, Class> ifcMoreImplClass) {
    this.scanner = new Reflections(packageToScan);
    this.ifcMoreImplClass = ifcMoreImplClass;
  }

  @Override
  public <T> Class<? extends T> getImplClass(Class<T> ifc) {

    return this.ifcMoreImplClass.computeIfAbsent(ifc, aClass -> {
      Set<Class<? extends T>> classSet = scanner.getSubTypesOf(ifc);

      if (classSet.size() != 1) {
        throw new RuntimeException(ifc + " has 0 more thab one impl, please update your config");
      }

      return classSet.iterator().next();
    });
  }
}
