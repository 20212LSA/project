import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class PortScanner {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		final ExecutorService es = Executors.newFixedThreadPool(20);
		final String ip = "127.0.0.1";
		final int timeout = 200;
		final List<Future<ScanResult>> futures = new ArrayList<>();
		//65535, 1024
		for (int port = 1; port <= 1024; port++) {
		//for(int port = 1; port<=80; port++){
			futures.add(portlsOpen(es, ip, port, timeout));	
		}
		es.awaitTermination(200L, TimeUnit.MILLISECONDS);
		int openPorts = 0;
		for(final Future<ScanResult>f : futures) {
			if(f.get().isOpen()) {
				openPorts++;
				System.out.println(f.get().getPort());
			}
		}
		System.out.println("Thread are"+openPorts+"open ports on host" + ip+"(probed with a timeout of"
				+ timeout + "ms)");
	}

	public static Future<ScanResult>portlsOpen(final ExecutorService es, final String ip, final int port, final int timeout){
		return es.submit(new Callable<ScanResult>() {
			@Override
			public ScanResult call() {
				try {
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(ip, port), timeout);
					socket.close();
					return new ScanResult(port, true);
				}catch(Exception ex) {
					return new ScanResult(port, false);
				}
			}
		});
	}
	
	public static class ScanResult{
		private int port;
		private boolean isOpen;
		
		public ScanResult(int port, boolean isOpen) {
			super();
			this.port = port;
			this.isOpen = isOpen;
		}
		public int getPort() {
			return port;
		}
		public void setPort(int port) {
			this.port = port;
		}
		public boolean isOpen() {
			return isOpen;
		}
		public void setOpen(boolean isOpen) {
			this.isOpen = isOpen;
		}
	}
	
}
