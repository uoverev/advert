package com.advert.tool.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CloseHelper {

	private static Log log = LogFactory.getLog(CloseHelper.class);

	public static void close(SocketChannel channel){
	    try {
            if (channel != null) {
                channel.close();
                channel = null;
            }
        }
        catch (Exception ex) {
            if (log.isErrorEnabled())
                log.error("关闭SocketChannel异常", ex);
        }
	}
	
	public static void close(InputStream in) {
		try {
			if (in != null) {
				in.close();
				in = null;
			}
		}
		catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error("关闭输入流异常", ex);
		}
	}

	public static void close(OutputStream out) {
		try {
			if (out != null) {
				out.close();
				out = null;
			}
		}
		catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error("关闭输入流异常", ex);
		}
	}

	public static void close(BufferedReader in) {
		try {
			if (in != null) {
				in.close();
				in = null;
			}
		}
		catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error("关闭输入流异常", ex);
		}
	}

	public static void close(ServerSocket server) {
		try {
			if (server != null) {
				server.close();
				server = null;
			}
		}
		catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error("关闭Socket异常", ex);
		}
	}

	public static void close(Socket client) {
		try {
			if (client != null) {
				client.close();
				client = null;
			}
		}
		catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error("关闭Socket异常", ex);
		}
	}

	public static void close(Writer out) {
		try {
			if (out != null) {
				out.close();
				out = null;
			}
		}
		catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error("关闭Writer异常", ex);
		}
	}

	public static void close(Reader reader) {
		try {
			if (reader != null) {
				reader.close();
				reader = null;
			}
		}
		catch (Exception ex) {
			if (log.isErrorEnabled())
				log.error("关闭Writer异常", ex);
		}
	}
}
