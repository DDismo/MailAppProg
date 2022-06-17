package mailapp.prog.mailappprog.Controller;

import mailapp.prog.mailappprog.Model.Persistence;

import java.net.Socket;

public class ServerOperation implements Runnable{
    private final Socket conn;
    private final Persistence p;

    public ServerOperation(Socket connection, Persistence persistence){
        conn = connection;
        p = persistence;
    }

    @Override
    public void run(){

    }
}
