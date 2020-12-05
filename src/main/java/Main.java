import impl.CoronaDisinfection;
import impl.Room;

public class Main {
  public static void main(String[] args) {

    CoronaDisinfection coronaDisinfection = new CoronaDisinfection();
    coronaDisinfection.start(new Room());
  }
}
