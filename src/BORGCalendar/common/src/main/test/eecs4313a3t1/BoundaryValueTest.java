package eecs4313a3t1;

import static org.junit.Assert.*;

import org.junit.Test;
import net.sf.borg.common.*;
import java.io.IOException;

public class BoundaryValueTest implements SocketHandler {

	/**
	 * process a socket message
	 */
	@Override
	public synchronized String processMessage(String msg) {
		return msg;
	}

	@Test
	public void test_sendMsg_WeakNormal() {
		/** Method used: Boundary Value Testing **/
		String validHost = "localhost";

		int port_norm = 2929; // x_norm
		int port_min = 0; // x_min
		int port_min_plus = 1; // x_min+
		int port_max = 65535; // x_max
		int port_max_minus = 65534; // x_max-

		String response = "";
		// port_norm
		String msg = "Port 2929";
		SocketServer ss = new SocketServer(port_norm, this);
		try {
			response = SocketClient.sendMsg(validHost, port_norm, msg);
			assertEquals("Testing if a localhost on port_norm sends a message", response, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// port_min
		/*
		 * Throws connection problem. port 0 isn't available on my computer Connect
		 * Exception extends Socket Exception which extends IOException
		 */
		msg = "Port 0";
		try {
			ss = new SocketServer(port_min, this);
			response = SocketClient.sendMsg(validHost, port_min, msg);
			assertEquals("Testing if a localhost on port_min sends a message", response, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// port_min+
		msg = "Port 1";
		try {
			ss = new SocketServer(port_min_plus, this);
			response = SocketClient.sendMsg(validHost, port_min_plus, msg);
			assertEquals("Testing if a localhost on port port_min+ sends a message", response, msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// port_max
		msg = "Port 65535";
		try {
			ss = new SocketServer(port_max, this);
			response = SocketClient.sendMsg(validHost, port_max, msg);
			assertEquals("Testing if a localhost on port port_max sends a message", response, msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// port_max-
		msg = "Port 65534";
		try {
			ss = new SocketServer(port_max_minus, this);
			response = SocketClient.sendMsg(validHost, port_max_minus, msg);
			assertEquals("Testing if a localhost on port_max- sends a message", response, msg);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test_sendMsg_WeakRobust() {
		/** Method used: Boundary Value Testing **/
		String validHost = "localhost";

		int port_norm = 2929; // x_norm
		int port_min = 0; // x_min
		int port_min_plus = 1; // x_min+
		int port_max = 65535; // x_max
		int port_max_minus = 65534; // x_max-

		// robustness
		String invalidHost = "asdfasdf";
		int port_min_minus = -1; // x_min-
		int port_max_plus = 65536; // x_max_+

		String response = "";
		// port_norm
		String msg = "Port 2929";
		SocketServer ss = new SocketServer(port_norm, this);
		try {
			response = SocketClient.sendMsg(validHost, port_norm, msg);
			assertEquals("Testing if a localhost on port_norm sends a message", response, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* Unknown host exception extends IOException */
		try {
			response = SocketClient.sendMsg(invalidHost, port_norm, msg);
			assertEquals("Testing if an invalid host on port_norm sends a message", response, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// port_min
		/*
		 * Throws connection problem. port 0 isn't available on my computer Connect
		 * Exception extends Socket Exception which extends IOException
		 */
		msg = "Port 0";
		try {
			ss = new SocketServer(port_min, this);
			response = SocketClient.sendMsg(validHost, port_min, msg);
			assertEquals("Testing if a localhost on port_min sends a message", response, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// port_min+
		msg = "Port 1";
		try {
			ss = new SocketServer(port_min_plus, this);
			response = SocketClient.sendMsg(validHost, port_min_plus, msg);
			assertEquals("Testing if a localhost on port port_min+ sends a message", response, msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// port_max
		msg = "Port 65535";
		try {
			ss = new SocketServer(port_max, this);
			response = SocketClient.sendMsg(validHost, port_max, msg);
			assertEquals("Testing if a localhost on port port_max sends a message", response, msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// port_max-
		msg = "Port 65534";
		try {
			ss = new SocketServer(port_max_minus, this);
			response = SocketClient.sendMsg(validHost, port_max_minus, msg);
			assertEquals("Testing if a localhost on port_max- sends a message", response, msg);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// port_min-
		/*
		 * Illegal argument Exception
		 */

		msg = "Port -1";
		try {
			ss = new SocketServer(port_min_minus, this);
			response = SocketClient.sendMsg(validHost, port_min_minus, msg);
			assertEquals("Testing if a localhost on port_min- sends a message", response, msg);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException iae) {
			fail("Ports < 0 should be handled by the method");
		}

		// port_max+
		/*
		 * Illegal argument Exception
		 */
		msg = "Port 65536";
		try {
			ss = new SocketServer(port_max_plus, this);
			response = SocketClient.sendMsg(validHost, port_max_plus, msg);
			assertEquals("Testing if a localhost on port_max+ sends a message", response, msg);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException iae) {
			fail("Ports > 65535 should be handled by the method");
		}
	}
    
	/**
	 * ADDITIONAL TESTCASES TO HIT MORE COVERAGE
	 */
    //hits line 44/45 increases coverage to 88.5%
    @Test
    public void sendNullMessageTest() {
    String msg = null;
    SocketServer ss;
    String response;
        try {
            ss = new SocketServer(2920, null);
            response = SocketClient.sendMsg("localhost", 2920, msg);
            assertEquals("Testing if a localhost on port port_max sends a message", response,msg);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
	/**
	 * ADDITIONAL TESTCASES FOR MUTATION COVERAGE
	 */
    @Test
    public void checkServerAlive() {
    String msg = null;
    SocketServer ss;
    String response;
        try {
            ss = new SocketServer(2922, this);
            response = SocketClient.sendMsg("localhost", 2922, msg);
            assertTrue(ss.isAlive());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
