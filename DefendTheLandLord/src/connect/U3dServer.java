package connect;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Dao.poker.Card;

/**
 * unity3d �����
 * @author lm
 *
 */
public class U3dServer implements Runnable {
	public void run() {
		ServerSocket u3dServerSocket = null;
		while(true){
			try {
				u3dServerSocket=new ServerSocket(8088);			
				System.out.println("u3d�����Ѿ�����,����:"+u3dServerSocket.getLocalPort()+"�˿�");		
				while (true) {
					Socket socket = u3dServerSocket.accept();
					System.out.println("�ͻ��˽���");
					new RequestReceiver(socket).start();
				}
			} catch (IOException e) {
				System.err.println("����������ʧ��");
				if (u3dServerSocket != null) {
					try {
						u3dServerSocket.close();
					} catch (IOException ioe) {
					}
					u3dServerSocket = null;
				}
			}
			
			// ������ʱ����
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {

			}
		}
	}
	/**
	 * �ͻ�����������߳�
	 * @author lm
	 *
	 */
	class RequestReceiver extends Thread {

		/** ���ĳ����ֽ��� */
		private int messageLengthBytes = 1024;

		private Socket socket;

		/** socket���봦���� */
		private BufferedInputStream bis = null;
		
		public RequestReceiver(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				//��ȡsocket�е�����
				bis = new BufferedInputStream(socket.getInputStream());
				byte[] buf = new byte[messageLengthBytes];
				
				/**
				 * ��Socket���Ĵ��������,Ӧ����ȷ���ĵ���
				 */
				while (true) {
					/*
					 * ����ҵ����ʽ�Ǹ��ݲ�ͬ�ı�����,�����߳�,���ò�ͬ��ҵ���߼����д���
					 * ����ҵ��������� 
					 */
					//��ȡ�ֽ������е�����
					bis.read(buf);
					//���
					System.out.println(new String(buf,"utf-8"));
					OutputStream out = socket.getOutputStream();
					//��ͻ��˴������ݵ��ֽ�����
					out.write(new String("i am server").getBytes());
					out.write(new String("random poker send").getBytes());
					out.write(Card.sendPoker().getBytes());
				}
			} catch (IOException e) {
				System.err.println("��ȡ���ĳ���");
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
					}
					socket = null;
				}
			}
			
		}
	}
}