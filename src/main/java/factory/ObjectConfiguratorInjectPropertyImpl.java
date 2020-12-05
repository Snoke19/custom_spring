package factory;

import lombok.SneakyThrows;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@ToString
public class ObjectConfiguratorInjectPropertyImpl implements ObjectConfigurator {
  Map<String, String> propertiesMap;

  @SneakyThrows
  public ObjectConfiguratorInjectPropertyImpl() {
    String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
    Stream<String> stringStream = new BufferedReader(new FileReader(path)).lines();
    propertiesMap = stringStream.map(line -> line.split("=")).collect(toMap(ar -> ar[0], arr -> arr[1]));
  }

  @Override
  @SneakyThrows
  public void configure(Object t) {

    Class<?> implClass = t.getClass();
    for (Field field : implClass.getDeclaredFields()) {
      InjectProperty annotation = field.getAnnotation(InjectProperty.class);

      if (annotation != null) {
        String value = annotation.value().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.value());
        field.setAccessible(true);
        field.set(t, value);
      }
    }
  }
}
