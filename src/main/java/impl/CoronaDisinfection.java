package impl;

import factory.ObjectFactory;

public class CoronaDisinfection {

  private Announcer announcer = ObjectFactory.getInstance().createObject(Announcer.class);
  private Policeman policeman = ObjectFactory.getInstance().createObject(Policeman.class);

  public void start(Room room) {

    announcer.announce("Start disinfection, get out all! now!");
    policeman.makePeopleLeaveRoom();

    this.disinfection(room);

    announcer.announce("You can go to this room");
  }

  private void disinfection(Room room) {
    System.out.println("Disinfection room!");
  }
}
