package serverside;

import serverside.model.datamodel.StudentPageModel;
import serverside.controller.MainServerController;
import serverside.view.ServerSideView;

public class ServerMain {
    public static void main(String[] args) {
        ServerSideView serverView = new ServerSideView();
        StudentPageModel serverModel = new StudentPageModel(10);

        MainServerController serverController = new MainServerController(serverModel, serverView);
    }
}
