package mailapp.prog.mailappprog.Model;

import mailapp.prog.mailappprog.Controller.ServerOperation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server implements Runnable{

    private final Persistence p;
    private final ServerSocket socket;
    private final ExecutorService es;
    private final Thread thisThread;

    public Server(Persistence p) throws IOException{
        socket = new ServerSocket(9898);
        es = Executors.newFixedThreadPool(5);
        this.p = p;
        thisThread = new Thread(this);
        thisThread.start();
    }

    public String getStatus(){
        return thisThread.getState().toString();
    }

    public void closeConnection() throws InterruptedException, IOException {
        //cerca di chiudere tutti i thread aperti in es
        es.shutdown(); // Disable new tasks from being submitted
        // Wait a while for existing tasks to terminate
        if (!es.awaitTermination(60, TimeUnit.SECONDS)) {
            es.shutdownNow(); // Cancel currently executing tasks
            // Wait a while for tasks to respond to being cancelled
            if (!es.awaitTermination(60, TimeUnit.SECONDS))
                p.printErrorLog("il pool non è terminato!");
        }
        //chiudi la connessione
        socket.close();
    }

    public void interrupt(){
        thisThread.interrupt();
    }

    @Override
    public void run(){
        p.addLog("il server ha iniziato la sua attività correttamente");
        while(!socket.isClosed()){
            try {
                es.execute(new ServerOperation(socket.accept(), p));
            }catch(SocketException e){
               if(e.getMessage().compareToIgnoreCase("socket closed") == 0){
                   p.addLog("Il server ha concluso la sua attività");
               }
            } catch (IOException e) {
                p.printErrorLog(e);
            }finally{
                try {
                    closeConnection();
                } catch (InterruptedException | IOException e) {
                    p.printErrorLog(e);
                    thisThread.interrupt(); // preservo lo stato di interrupt
                }
            }
        }
    }
}
