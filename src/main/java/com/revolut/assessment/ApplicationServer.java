package com.revolut.assessment;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Arrays;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.assessment.web.AccountResource;

public class ApplicationServer {

  private static Logger LOG = LoggerFactory.getLogger(ApplicationServer.class);

  public static void main(String[] args) throws Exception {
    LOG.info("JettyServer started with main args:" + Arrays.toString(args));
    Server server = null;
    try {
      InetAddress inetAddress = InetAddress.getByName("localhost");
      InetSocketAddress address = new InetSocketAddress(inetAddress, 8080);
      server = createServer(address);
      server.start();
      server.join();
    } catch (Exception ex) {
      LoggerFactory.getLogger(ApplicationServer.class.getName());
    }
  }

  private static Server createServer(final InetSocketAddress address) {
    Server server = new Server(8080);
    ServletContextHandler context = new ServletContextHandler(server, "/revolut-bank", ServletContextHandler.SESSIONS);
    ServletHolder holder = new ServletHolder(ServletContainer.class);
    holder.setInitParameter(ServerProperties.PROVIDER_PACKAGES, AccountResource.class.getPackage().getName());
    context.addServlet(holder, "/*");
    return server;
  }
}
