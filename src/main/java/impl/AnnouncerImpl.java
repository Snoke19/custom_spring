package impl;

import factory.ObjectFactory;

public class AnnouncerImpl implements Announcer {

  private Recommender recommender = ObjectFactory.getInstance().createObject(Recommender.class);

  @Override
  public void announce(String message) {
    this.recommender.recommend();
    System.out.println(message);
  }
}
