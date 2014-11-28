package net.m163;

public class SendMsg2 extends SendMsg{

	public static void main(String[] args) {
		try {
			SendMsg2 msg2 = new SendMsg2();
			msg2.login();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
