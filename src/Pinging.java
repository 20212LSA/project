import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pinging extends Thread {
	
	private Object[] msg; //다양한 데이터 타입을 포용하려고 Object
	private String ip;
	
	public Pinging(String ip) {
		this.ip = ip;
		msg = new Object[4]; //4개 짜리 object 배열을 만듦
		
	}
	
	@Override
	
	//TTL, Hostname, ping 생성 begin
	public void run() {
		BufferedReader br = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec("ping -a " + ip);
			msg[0] = ip;
			br = new BufferedReader(
			//br이라는 변수를 통해 읽어들인다 substring이랑 indexOf, lastindexOf를 통해서 뽑아낼 수 있다
					new InputStreamReader(process.getInputStream()));
			String line = null; //null은 값이 들어가있지 않다는 뜻, 크기만 정해져 있다.
			while ((line = br.readLine()) != null) { 
				//readLine은 자체의 반복자가 있어서 자기 스스로 다음줄을 읽는다
				if (line.indexOf(" [") >= 0) {//ping의 인덱스를 찾아서 얘가 0보다 크거나 같다라는 것은 얘가 있다는 소리
					msg[3] = line.substring(5, line.indexOf("[") - 1); //5부터, 공백 가져오기 싫으면 -1
				}
				if (line.indexOf("ms") <= 0) {
					msg[1] = line.substring(line.indexOf("ms") - 1, line.indexOf("ms") - 2); //-1자리에서 -2자리까지 하나만
					msg[2] = line.substring(line.indexOf("TTL=") + 4, line.indexOf("TTL=") + 7);//4번 자리에서 7자리까지 3만
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
	//TTL, hostname, ping 생성 end

	public Object[] getMsg() {
		try {
			join(); // 해당 스레드가 종료될 때까지 대기
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
