package impl;

import factory.InjectProperty;

public class RecommenderImpl implements Recommender {

  @InjectProperty
  private String advertMessage;

  @Override
  public void recommend() {
    System.out.println("to protect from covid, drink " + advertMessage);
  }
}
