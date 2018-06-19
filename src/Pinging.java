import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pinging extends Thread {
	
	private Object[] msg; //�پ��� ������ Ÿ���� �����Ϸ��� Object
	private String ip;
	
	public Pinging(String ip) {
		this.ip = ip;
		msg = new Object[4]; //4�� ¥�� object �迭�� ����
		
	}
	
	@Override
	
	//TTL, Hostname, ping ���� begin
	public void run() {
		BufferedReader br = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec("ping -a " + ip);
			msg[0] = ip;
			br = new BufferedReader(
			//br�̶�� ������ ���� �о���δ� substring�̶� indexOf, lastindexOf�� ���ؼ� �̾Ƴ� �� �ִ�
					new InputStreamReader(process.getInputStream()));
			String line = null; //null�� ���� ������ �ʴٴ� ��, ũ�⸸ ������ �ִ�.
			while ((line = br.readLine()) != null) { 
				//readLine�� ��ü�� �ݺ��ڰ� �־ �ڱ� ������ �������� �д´�
				if (line.indexOf(" [") >= 0) {//ping�� �ε����� ã�Ƽ� �갡 0���� ũ�ų� ���ٶ�� ���� �갡 �ִٴ� �Ҹ�
					msg[3] = line.substring(5, line.indexOf("[") - 1); //5����, ���� �������� ������ -1
				}
				if (line.indexOf("ms") <= 0) {
					msg[1] = line.substring(line.indexOf("ms") - 1, line.indexOf("ms") - 2); //-1�ڸ����� -2�ڸ����� �ϳ���
					msg[2] = line.substring(line.indexOf("TTL=") + 4, line.indexOf("TTL=") + 7);//4�� �ڸ����� 7�ڸ����� 3��
					break;
				}
			} 
		} catch (Exception e) {
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//TTL, hostname, ping ���� end

	public Object[] getMsg() {
		try {
			join(); // �ش� �����尡 ����� ������ ���
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return msg;
	}
	
	public static void main(String[] args) {
		Pinging[] pg = new Pinging[254];
		String fixedIP = "192.168.2.";
		for(int i=0; i<=253; i++) {
			pg[i] = new Pinging(fixedIP + (i+1));
			pg[i].start();
		}
		for(int i =0; i<=253; i++) {
			Object[] msg = pg[i].getMsg();
			if(msg == null) {
				System.out.println("die");
			}else {
				System.out.println(msg[0] + "," + msg[1] + "," + msg[2] +"," + msg[3]);
			}
		}
	}
}
