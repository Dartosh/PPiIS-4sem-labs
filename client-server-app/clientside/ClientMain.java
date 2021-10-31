package clientside;
import clientside.controller.MainClientController;
import clientside.view.ClientSideView;


public class ClientMain {
    public static void main(String[] args) {
        ClientSideView clientView = new ClientSideView();
        MainClientController clientController = new MainClientController(clientView);
    }
}
