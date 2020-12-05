package factory;

import impl.Policeman;
import impl.PolicemanImpl;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectFactory {

  private static final ObjectFactory ourInstance = new ObjectFactory();
  private final List<ObjectConfigurator> configurators = new ArrayList<>();
  private final Config config;

  @SneakyThrows
  public ObjectFactory() {
    this.config = new JavaConfig("", new HashMap<>(Map.of(Policeman.class, PolicemanImpl.class)));
    for (Class<? extends ObjectConfigurator> aClass : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
      configurators.add(aClass.getDeclaredConstructor().newInstance());
    }
  }

  public static ObjectFactory getInstance() {return ourInstance;}

  @SneakyThrows
  public <T> T createObject(Class<T> type) {
    Class <? extends T> implClass = type;
    if (type.isInterface()) {
      implClass = config.getImplClass(type);
    }

    T t = implClass.getDeclaredConstructor().newInstance();

    configurators.forEach(data -> {
      data.configure(t);
    });

    return t;
  }
}
