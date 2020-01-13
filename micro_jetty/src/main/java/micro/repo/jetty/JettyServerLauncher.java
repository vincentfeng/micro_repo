package micro.repo.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyServerLauncher {

    private static final int DEFAULT_PORT = 8080;
    private static final String DEFAULT_WEBAPP = "WebRoot";
    private static final String DEFAULT_WEB = "WebRoot/WEB-INF/web.xml";
    private static final String DEFAULT_PROJECT = "ABMP";
    private static Server server;

    public static void main(String[] args) throws Exception {
        JettyServerLauncher.startJetty(getPortFromArgs(args));
    }

    private static int getPortFromArgs(String[] args) {
        if (args.length > 0) {
            try {
                return Integer.valueOf(args[0]);
            } catch (NumberFormatException ignore) {
                System.out.println(ignore);
            }
        }
        return DEFAULT_PORT;
    }

    private static void startJetty(int port) throws Exception {
        server = new Server(port);
        //server.setHandler(getWebAppContext());
        server.start();
        server.join();
    }

    public static void stopJetty() throws Exception{
        server.stop();
    }

    private static WebAppContext getWebAppContext(){
        WebAppContext context = new WebAppContext();
        context.setDescriptor(DEFAULT_WEB);
        context.setResourceBase(DEFAULT_WEBAPP);
        context.setContextPath(DEFAULT_PROJECT);
        context.setParentLoaderPriority(true);
        return context;
    }
}
